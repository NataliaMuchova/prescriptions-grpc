package via.pro3.prescriptionsgrpc.service;

import com.google.protobuf.Empty;
import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import via.pro3.prescriptionsgrpc.entities.hospital.Drug;
import via.pro3.prescriptionsgrpc.entities.hospital.Prescription;
import via.pro3.prescriptionsgrpc.entities.hospital.PrescriptionDrug;
import via.pro3.prescriptionsgrpc.entities.hospital.User;
import via.pro3.prescriptionsgrpc.entities.pharmacy.PharmacyDrug;
import via.pro3.prescriptionsgrpc.generated.*;
import via.pro3.prescriptionsgrpc.repository.*;

import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@GRpcService
public class PrescriptionServiceImpl extends HospitalGrpc.HospitalImplBase {

  private final IDatabasePrescriptionRepository prescriptionRepository;

  private final IDatabaseUserRepository userRepository;

  private final IDatabasePrescriptionDrugRepository prescriptionDrugRepository;

  private final IPharmacyDrugRepository drugStorageRepository;

  private final IDatabaseDrugRepository drugRepository;

    @Autowired public PrescriptionServiceImpl(IDatabasePrescriptionRepository prescriptionRepository,
        IDatabaseUserRepository userRepository, IDatabasePrescriptionDrugRepository prescriptionDrugRepository, IPharmacyDrugRepository drugStorageRepository, IDatabaseDrugRepository drugRepository)
    {
        this.prescriptionRepository = prescriptionRepository;
        this.userRepository = userRepository;
        this.prescriptionDrugRepository = prescriptionDrugRepository;
        this.drugStorageRepository = drugStorageRepository;
        this.drugRepository = drugRepository;
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
              drug.getName(),
              drug.getDescription()
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
              drug.getAvailabilityCount(),
              drug.getStartingAmount()
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
                        .setName(prescriptionDrug.getDrug().getName())
                        .setAvailabilityCount(prescriptionDrug.getAvailabilityCount())
                        .setNote(prescriptionDrug.getNote())
                        .setStartingAmount(prescriptionDrug.getStartingAmount())
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

    @Override public void updatePrescription(UpdatePrescriptionRequest request,
        StreamObserver<UpdatePrescriptionReply> responseObserver)
    {
        Prescription found = prescriptionRepository.findById(request.getId()).orElse(null);

        try{
            User doctor = userRepository.findById((int) request.getDoctorId()).orElse(null);
            User patient = userRepository.findById((int) request.getPatientId()).orElse(null);

            List<PrescriptionDrug> oldPrescriptionDrugs = prescriptionDrugRepository.findByPrescription(found);

            //make sure new doctor is still a doctor
            if(getUserRole(doctor) != UserRoles.Doctor){
                responseObserver.onError(new IllegalAccessException("Doctor is not doctoooor"));
                return;
            }

            //update prescription
            found.setDoctor(doctor);
            found.setPatient(patient);
            found.setExpirationDate(convertToLocalDate(request.getExpirationDate()));
            found.setIssueDate(convertToLocalDate(request.getIssueDate()));

            //now we save updated prescription, and operate on updated properties in case of
            //generated values being different
            Prescription updated = prescriptionRepository.save(found);

            //we build a partial reply, without drugs
            UpdatePrescriptionReply noDrugReply = UpdatePrescriptionReply.newBuilder()
                .setId(updated.getId())
                .setIssueDate(convertToTimestamp(updated.getIssueDate()))
                .setExpirationDate(convertToTimestamp(updated.getExpirationDate()))
                .setDoctorId(updated.getDoctor().getId())
                .setPatientId(updated.getPatient().getId())
                .buildPartial();

            //since drugs in the request are a combination of prescriptiondrugs and drugs
            //we seperate them here to create new prescriptiondrug connections
            List<PrescriptionDrug> newPrescriptionDrugs = new ArrayList<>();

            List<via.pro3.prescriptionsgrpc.generated.Drug> replyDrugs = new ArrayList<>();

            for(via.pro3.prescriptionsgrpc.generated.Drug drug : request.getDrugsList()){
                Drug foundDrug = drugRepository.findById(drug.getName()).orElseThrow();

                newPrescriptionDrugs.add(new PrescriptionDrug(
                    foundDrug,
                    updated,
                    drug.getNote(),
                    drug.getAvailabilityCount(),
                    drug.getStartingAmount()
                ));
                //founddrug refers to drug table, we use drug variable when its a value related to
                //prescriptiondrug
                replyDrugs.add(via.pro3.prescriptionsgrpc.generated.Drug.newBuilder()
                    .setNote(drug.getNote())
                    .setDescription(foundDrug.getDescription())
                    .setAvailabilityCount(drug.getAvailabilityCount())
                    .setStartingAmount(drug.getStartingAmount())
                    .setName(foundDrug.getName())
                    .build());
            }

            //delete all prescriptiondrugs, so we can create them again with new given drugs (ids in the future)
            prescriptionDrugRepository.deleteAll(oldPrescriptionDrugs);

            //if no problems with drugs, replace old with new
            prescriptionDrugRepository.saveAll(newPrescriptionDrugs);

            //build reply with previous reply that has no drugs (you cannot set them after)
            //add drugs at the end
            UpdatePrescriptionReply reply = UpdatePrescriptionReply.newBuilder()
                .setId(noDrugReply.getId())
                .setIssueDate(noDrugReply.getIssueDate())
                .setExpirationDate(noDrugReply.getExpirationDate())
                .setDoctorId(noDrugReply.getDoctorId())
                .setPatientId(noDrugReply.getPatientId())
                .addAllDrugs(replyDrugs)
                .build();

            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }catch(NullPointerException | NoSuchElementException e){
            responseObserver.onError(e);
        }
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

  @Override public void getUser(GetUserRequest request, StreamObserver<GetUserReply> responseObserver)
  {
    try {
      long cpr = request.getCpr();

      User user = userRepository.findById((int) cpr).orElse(null);

      if (user == null) {
        responseObserver.onError(new NoSuchElementException("User not found with CPR: " + cpr));
        return;
      }

      GetUserReply reply = GetUserReply.newBuilder()
          .setName(user.getName())
          .setSurname(user.getSurname())
          .setPhone(user.getPhone())
          .setCpr(user.getId())
          .setGender(user.getGender())
          .setBirthday(convertToTimestamp(user.getBirthday()))
          .setRole(String.valueOf(getUserRole(user)))
          .build();

      responseObserver.onNext(reply);
      responseObserver.onCompleted();
    }
    catch (Exception e) {
      responseObserver.onError(e);
    }
  }

    @Override public void getDrugStorage(Empty request,
                                         StreamObserver<GetDrugStorageReply> responseObserver)
    {
        List<PharmacyDrug> all = drugStorageRepository.findAll();
        List<DrugList> items =all.stream().map(pd ->{
          Drug hospitalDrug = drugRepository.findById(pd.getName()).orElse(null);
          String description = hospitalDrug != null ? hospitalDrug.getDescription():"";
          return DrugList.newBuilder()
              .setName(pd.getName())
              .setId(pd.getId())
              .setStock(pd.getStock())
              .setPrice(pd.getPrice())
              .setReorderLevel(pd.getReorderLevel())
              .setDescription(description)
              .build();
        })
            .toList();

        GetDrugStorageReply reply = GetDrugStorageReply.newBuilder()
              .addAllDrugs(items)
              .build();

        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override public void createDrugStorage(CreateDrugRequest request,
        StreamObserver<CreateDrugReply> responseObserver)
    {
        PharmacyDrug created = drugStorageRepository.save(new PharmacyDrug(
            request.getName(), request.getStock(), request.getPrice(), request.getReorderLevel()));

        CreateDrugReply reply = CreateDrugReply.newBuilder()
            .setName(created.getName())
            .setId(created.getId())
            .setStock(created.getStock())
            .setPrice(created.getPrice())
            .setReorderLevel(created.getReorderLevel())
            .build();

        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override public void updateDrugStorage(UpdateDrugRequest request,
        StreamObserver<UpdateDrugReply> responseObserver)
    {
        PharmacyDrug found = drugStorageRepository.findById(request.getId()).orElse(null);

        try {
            found.setStock(request.getStock());
            found.setPrice(request.getPrice());
            found.setReorderLevel(request.getReorderLevel());
            found.setName(request.getName());

            drugStorageRepository.save(found);

            UpdateDrugReply reply = UpdateDrugReply.newBuilder()
                .setName(found.getName())
                .setId(found.getId())
                .setStock(found.getStock())
                .setPrice(found.getPrice())
                .setReorderLevel(found.getReorderLevel())
                .build();

            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }catch(NullPointerException e){
            responseObserver.onError(e);
        }
    }
}