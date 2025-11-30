package via.pro3.prescriptionsgrpc.service;

import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import via.pro3.prescriptionsgrpc.entities.*;
import via.pro3.prescriptionsgrpc.entities.Drug;
import via.pro3.prescriptionsgrpc.generated.*;
import via.pro3.prescriptionsgrpc.repository.*;

import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@GRpcService
public class PrescriptionServiceImpl extends HospitalGrpc.HospitalImplBase {

  private IDatabasePrescriptionRepository prescriptionRepository;

  private IDatabaseUserRepository userRepository;

  private IDatabasePrescriptionDrugRepository prescriptionDrugRepository;

  @Autowired
  private IDatabaseDrugRepository drugRepository;

    @Autowired public PrescriptionServiceImpl(IDatabasePrescriptionRepository prescriptionRepository,
        IDatabaseUserRepository userRepository, IDatabasePrescriptionDrugRepository prescriptionDrugRepository)
    {
        this.prescriptionRepository = prescriptionRepository;
        this.userRepository = userRepository;
        this.prescriptionDrugRepository = prescriptionDrugRepository;
    }
    private LocalDate convertToLocalDate(Timestamp timestamp) {
        Instant instant = Instant.ofEpochSecond(timestamp.getSeconds(), timestamp.getNanos());
        LocalDate time = instant.atZone(ZoneId.systemDefault()).toLocalDate();
        return time;
    }

    private Timestamp convertToTimestamp(LocalDate date)
    {
        Instant instant = date.atStartOfDay(ZoneId.systemDefault()).toInstant();

        return Timestamp.newBuilder().setSeconds(instant.getEpochSecond()).setNanos(instant.getNano()).build();
    }
  @Override
  public void createPrescription(CreatePrescriptionRequest request,
                                 StreamObserver<PrescriptionReply> responseObserver) {
    Prescription p = new Prescription();

    User doctor = userRepository.findById(request.getDoctorId()).orElse(null);
    User patient = userRepository.findById(request.getPatientId()).orElse(null);

    if(doctor==null || patient==null){
            responseObserver.onError(new NullPointerException("doctor or patient is null"));
        return;
    }
    if(doctor.getRole().equals("doctor")){
          responseObserver.onError(new IllegalAccessException("Doctor is not doctoooor"));
          return;
      }
    p.setDoctor(doctor);
    p.setPatient(patient);
    p.setIssueDate(LocalDate.now());
    p.setExpirationDate(LocalDate.now().plusMonths(1));

    p = prescriptionRepository.save(p);
//here drug impl
//    Drug drug = drugRepository.findById(drug.getId());
      List<Drug> drugs = new ArrayList<>();
      List<PrescriptionDrug> prescriptionDrugs = new ArrayList<>();
      //replyDrugs for later, when we generate ids instead of sending ready-made ones
      List<via.pro3.prescriptionsgrpc.generated.Drug> replyDrugs = new ArrayList<>();
    for(via.pro3.prescriptionsgrpc.generated.Drug drug : request.getDrugsList()){
        Drug drugToSave = new Drug();
        drugToSave.setName(drug.getName());
        drugToSave.setDescription(drug.getDescription());
        drugToSave.setAmount(drug.getAmount());
        drugs.add(drugToSave);

        //prescriptionDrug??
        PrescriptionDrug prescriptionDrug = new PrescriptionDrug();
        prescriptionDrug.setPrescription(p);
        prescriptionDrug.setDrug(drugToSave);
        prescriptionDrug.setNote(drug.getNote());
        prescriptionDrug.setAvailabilityCount(drug.getAvailabilityCount());
        prescriptionDrugs.add(prescriptionDrug);
    }

    List<Drug> savedDrugs = drugRepository.saveAll(drugs);
    List<PrescriptionDrug> savedPrescriptionDrugs = prescriptionDrugRepository.saveAll(prescriptionDrugs);

    //set reply date
    Timestamp issueTs = Timestamp.newBuilder()
        .setSeconds(p.getIssueDate().atStartOfDay(ZoneOffset.UTC).toEpochSecond())
        .setNanos(0)
        .build();
    Timestamp expTs = Timestamp.newBuilder()
        .setSeconds(p.getExpirationDate().atStartOfDay(ZoneOffset.UTC).toEpochSecond())
        .setNanos(0)
        .build();

    PrescriptionReply reply = PrescriptionReply.newBuilder()
        .setId(p.getId())
        .setDoctorId(Math.toIntExact(p.getDoctor().getId()))
        .setPatientId(Math.toIntExact(p.getPatient().getId()))
        .setIssueDate(issueTs)
        .setExpirationDate(expTs)
        .addAllDrugs(request.getDrugsList())
        .build();

    responseObserver.onNext(reply);
    responseObserver.onCompleted();
  }

  @Override
  public void getPrescriptions(PrescriptionsRequest request,
                               StreamObserver<GetPrescriptionsReply> responseObserver) {

        long patientCpr = request.getPatientId();
        User patient = userRepository.findById((int) patientCpr).orElse(null);

        if(patient==null){
            GetPrescriptionsReply emptyReply = GetPrescriptionsReply.newBuilder().build();
            responseObserver.onNext(emptyReply);
            responseObserver.onError(new NullPointerException("patient is null"));
            return;
        }

    List<Prescription> prescriptions =
        prescriptionRepository.findByPatient_Id(Math.toIntExact(patientCpr));

    //where drugs?
      //TODO!!!!: IN PROTO CHANGES INT32 INTO INT64. DELETE MATH.TOINTEXACT
    List<PrescriptionReply> items = prescriptions.stream()
        .map(p -> PrescriptionReply.newBuilder()
            .setId(p.getId())
            .setIssueDate(convertToTimestamp(p.getIssueDate()))
                .setExpirationDate(convertToTimestamp(p.getExpirationDate()))
                .addAllDrugs(drugRepository.findByPrescriptionId(p.getId()).stream().map(drug -> via.pro3.prescriptionsgrpc.generated.Drug.newBuilder()
                        .setDescription(drug.getDescription())
                        .setAmount(drug.getAmount())
                        .setName(drug.getName())
                        .setId(drug.getId())
                        .setAvailabilityCount(1)
                        .setNote("")
                        .build())
                        .toList())

            .setDoctorId(Math.toIntExact(p.getDoctor().getId()))
            .setPatientId(Math.toIntExact(p.getPatient().getId()))
            .build())
        .collect(Collectors.toList());

    GetPrescriptionsReply reply = GetPrescriptionsReply.newBuilder()
        .addAllPrescriptions(items)
        .build();

    responseObserver.onNext(reply);
    responseObserver.onCompleted();
  }

    @Override public void checkCredentials(
        CheckCredentialsRequest request, io.grpc.stub.StreamObserver<CheckCredentialsReply> responseObserver)
    {
        long cpr = request.getUserId();

        User doctor = userRepository.findById((int) cpr).orElse(null);

        boolean doctorValid = doctor != null && doctor.getPassword().equals(request.getPassword());

        if (doctorValid)
        {
            CheckCredentialsReply reply = CheckCredentialsReply.newBuilder().setRole(UserRoles.Doctor).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
            return;
        }

        User patient = userRepository.findById((int) cpr).orElse(null);

        boolean patientValid = patient != null && patient.getPassword().equals(request.getPassword());

        if (patientValid)
        {
            CheckCredentialsReply reply = CheckCredentialsReply.newBuilder().setRole(UserRoles.Patient).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
            return;
        }

        CheckCredentialsReply reply = CheckCredentialsReply.newBuilder().setRole(UserRoles.Invalid).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override public void createUser(CreateUserRequest request,
        StreamObserver<CreateUserReply> responseObserver)
    {
        //checks if user exists, without this you could continuously
        //create new users on 1 cpr to overwrite others
        //error that is sent does not properly send to invoker
        //should create a task for error responses in grpc
        if(userRepository.existsById(new UserId(request.getCpr(), User.Roles.PATIENT.role))){
            responseObserver.onError(new SQLException("User already exists"));
            return;
        }

        User user = new User(
            request.getName(),
            request.getSurname(),
            request.getPassword(),
            request.getPhone(),
            request.getCpr()
        );

        //useful for any generated fields (in this case role being set to patient)
        User created = userRepository.save(user);

        //to be changed, when patient and doctor table is removed
        Patient patient = patientRepository.save(new Patient(created, request.getGender(), convertToLocalDate(request.getBirthday())));

        CreateUserReply response = CreateUserReply.newBuilder()
            .setName(created.getName())
            .setSurname(created.getSurname())
            .setPassword(created.getPassword())
            .setPhone(created.getPhone())
            .setCpr(created.getId().getCpr())
            .setRole(UserRoles.Patient)
            .setGender(patient.getGender())
            .setBirthday(convertToTimestamp(patient.getBirthday()))
            .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}