package via.pro3.prescriptionsgrpc.service;

import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import jakarta.persistence.EntityManager;
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
import java.util.*;
import java.util.stream.Collectors;

@GRpcService
public class PrescriptionServiceImpl extends HospitalGrpc.HospitalImplBase {

  private final IDatabasePrescriptionRepository prescriptionRepository;

  private final IDatabaseUserRepository userRepository;

  private final IDatabasePrescriptionDrugRepository prescriptionDrugRepository;

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
    
    private UserRoles getUserRole(User user){
        UserRoles role = switch (user.getRole())
        {
            case "patient" -> UserRoles.Patient;
            case "doctor" -> UserRoles.Doctor;
            case "pharmacist" -> UserRoles.Pharmacist;
            case null, default -> UserRoles.Invalid;
        };

        return role;
    }

  @Override
  public void createPrescription(CreatePrescriptionRequest request,
                                 StreamObserver<PrescriptionReply> responseObserver) {
      User doctor = userRepository.findById(request.getDoctorId()).orElse(null);
      User patient = userRepository.findById(request.getPatientId()).orElse(null);

      if(doctor==null || patient==null){
          System.out.println("doctor or patient not found");
          responseObserver.onError(new NullPointerException("doctor or patient is null"));
          return;
      }

      if(!doctor.getRole().equals("doctor")){
          System.out.println("doctor role not found");
          responseObserver.onError(new IllegalAccessException("Doctor is not doctoooor"));
          return;
      }

      Prescription p = new Prescription();

      Set<Drug> drugs = new HashSet<>();

      Set<PrescriptionDrug> prescriptionDrugs = new HashSet<>();

      for(via.pro3.prescriptionsgrpc.generated.Drug drug : request.getDrugsList()){
          Drug drugToSave = new Drug(
              drug.getId(),
              drug.getName(),
              drug.getDescription(),
              drug.getAmount()
          );

          //TODO: make drug creation possible
          //below error shows up when attempting to create prescription with new drug id,
          //it works properly if id already exists, no clue whats going on rly
          //Row was updated or deleted by another transaction (or unsaved-value mapping was incorrect)
          drugRepository.saveAndFlush(drugToSave);

          drugs.add(drugToSave);

          PrescriptionDrug prescriptionDrug = new PrescriptionDrug(
              drugToSave,
              p,
              drug.getNote(),
              drug.getAvailabilityCount()
          );

          prescriptionDrugs.add(prescriptionDrug);
      }

      //drugRepository.saveAll(drugs);

      p.setDoctor(doctor);
      p.setPatient(patient);
      p.setIssueDate(LocalDate.now());
      p.setExpirationDate(LocalDate.now().plusMonths(1));
      p.setPrescriptionDrugs(prescriptionDrugs);
      Prescription created = prescriptionRepository.save(p);

      prescriptionDrugRepository.saveAll(prescriptionDrugs);

      Timestamp issueDate = convertToTimestamp(p.getIssueDate());
      Timestamp expirationDate = convertToTimestamp(p.getExpirationDate());

      PrescriptionReply reply = PrescriptionReply.newBuilder()
          .setId(created.getId())
          .setDoctorId(created.getDoctor().getId().intValue())
          .setPatientId(created.getPatient().getId().intValue())
          .setExpirationDate(expirationDate)
          .setIssueDate(issueDate)
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
                .addAllDrugs(prescriptionDrugRepository.findByPrescription(p).stream().map(prescriptionDrug -> via.pro3.prescriptionsgrpc.generated.Drug.newBuilder()
                        .setDescription(prescriptionDrug.getDrug().getDescription())
                        .setAmount(prescriptionDrug.getDrug().getAmount())
                        .setName(prescriptionDrug.getDrug().getName())
                        .setId(prescriptionDrug.getDrug().getId())
                        .setAvailabilityCount(prescriptionDrug.getAvailabilityCount())
                        .setNote(prescriptionDrug.getNote())
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

        User user = userRepository.findById((int) cpr).orElse(null);

        if (user != null && user.getPassword().equals(request.getPassword()))
        {
            UserRoles role = getUserRole(user);

            CheckCredentialsReply reply = CheckCredentialsReply.newBuilder().setRole(role).build();
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
        if(userRepository.existsById((int) request.getCpr())){
            responseObserver.onError(new SQLException("User already exists"));
            return;
        }

        User user = new User(
            request.getName(),
            request.getSurname(),
            request.getPassword(),
            request.getPhone(),
            request.getCpr(),
            User.Roles.PATIENT,
            convertToLocalDate(request.getBirthday()),
            request.getGender()
        );

        //useful for any generated fields (in this case role being set to patient)
        User created = userRepository.save(user);

        CreateUserReply response = CreateUserReply.newBuilder()
            .setName(created.getName())
            .setSurname(created.getSurname())
            .setPassword(created.getPassword())
            .setPhone(created.getPhone())
            .setCpr(created.getId())
            .setRole(getUserRole(created))
            .setGender(created.getGender())
            .setBirthday(convertToTimestamp(created.getBirthday()))
            .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}