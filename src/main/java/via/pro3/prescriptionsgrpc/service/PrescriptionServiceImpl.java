package via.pro3.prescriptionsgrpc.service;

import com.google.protobuf.Empty;
import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import via.pro3.prescriptionsgrpc.entities.hospital.Drug;
import via.pro3.prescriptionsgrpc.entities.hospital.Prescription;
import via.pro3.prescriptionsgrpc.entities.hospital.PrescriptionDrug;
import via.pro3.prescriptionsgrpc.entities.hospital.User;
import via.pro3.prescriptionsgrpc.entities.pharmacy.PharmacyDrug;
import via.pro3.prescriptionsgrpc.generated.*;
import via.pro3.prescriptionsgrpc.repository.*;

import java.sql.SQLException;
import java.time.*;
import java.util.*;
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

    private final PasswordEncoder passwordEncoder;

    @Autowired public PrescriptionServiceImpl(IDatabasePrescriptionRepository prescriptionRepository,
                                              IDatabaseUserRepository userRepository, IDatabasePrescriptionDrugRepository prescriptionDrugRepository, IPharmacyDrugRepository drugStorageRepository, IDatabaseDrugRepository drugRepository, PasswordEncoder passwordEncoder)
    {
        this.prescriptionRepository = prescriptionRepository;
        this.userRepository = userRepository;
        this.prescriptionDrugRepository = prescriptionDrugRepository;
        this.drugStorageRepository = drugStorageRepository;
        this.drugRepository = drugRepository;
        this.passwordEncoder = passwordEncoder;
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

    private User.Roles convertToDbRole(UserRoles role)
    {
        User.Roles dbRole = switch (role){
            case UserRoles.Patient -> User.Roles.PATIENT;
            case UserRoles.Doctor -> User.Roles.DOCTOR;
            case UserRoles.Pharmacist -> User.Roles.PHARMACIST;
            case null, default -> null;
        };

        return dbRole;
    }

    private List<via.pro3.prescriptionsgrpc.generated.Drug> convertPrescriptionDrugsToGRPCDrugs(Iterable<PrescriptionDrug> prescriptionDrugs){
        List<via.pro3.prescriptionsgrpc.generated.Drug> drugs = new ArrayList<>();

        for(PrescriptionDrug prescriptionDrug : prescriptionDrugs){
            drugs.add(via.pro3.prescriptionsgrpc.generated.Drug.newBuilder()
                .setName(prescriptionDrug.getDrug().getName())
                .setAvailabilityCount(prescriptionDrug.getAvailabilityCount())
                .setDescription(prescriptionDrug.getDrug().getDescription())
                .setNote(prescriptionDrug.getNote())
                .setStartingAmount(prescriptionDrug.getStartingAmount())
                .build());
        }

        return drugs;
    }


    @Override
    public void createPrescription(CreatePrescriptionRequest request,
                                   StreamObserver<PrescriptionReply> responseObserver) {
        Prescription p = new Prescription();

        User doctor = userRepository.findById(request.getDoctorId()).orElse(null);
        User patient = userRepository.findById(request.getPatientId()).orElse(null);

            if(doctor==null || patient==null){
                System.out.println("doctor or patient not found");
                responseObserver.onError(new NullPointerException("doctor or patient is null"));
                return;
            }

            if(!doctor.getRole().equals(User.Roles.DOCTOR.role)){
                System.out.println("doctor role not found");
                responseObserver.onError(new IllegalAccessException("Doctor is not doctoooor"));
                return;
            }

          Set<Drug> drugs = new HashSet<>();

          Set<PrescriptionDrug> prescriptionDrugs = new HashSet<>();

          for(CreatePrescriptionDrug drug : request.getDrugsList()){
              PharmacyDrug pharmacyDrug = drugStorageRepository.findById(drug.getName()).orElse(null);

          Drug drugToSave;

          //prepare to save drug into hospital db if exists on pharmacy db
          try {
              drugToSave = new Drug(
                  pharmacyDrug.getName(),
                  pharmacyDrug.getDescription()
              );
              drugs.add(drugToSave);
          }catch (NullPointerException e){
              System.out.println();
              responseObserver.onError(new NoSuchElementException("Drug not in database"));
              return;
          }

          PrescriptionDrug prescriptionDrug = new PrescriptionDrug(
              drugToSave,
              p,
              drug.getNote(),
              drug.getAvailabilityCount(),
              drug.getStartingAmount()
          );

          prescriptionDrugs.add(prescriptionDrug);
      }

      //below error shows up when attempting to create prescription with new drug id,
      //it works properly if id already exists, no clue whats going on rly
      //Row was updated or deleted by another transaction (or unsaved-value mapping was incorrect)
      //small update: havent seen the error in a long while
      drugRepository.saveAll(drugs);

      p.setDoctor(doctor);
      p.setPatient(patient);
      p.setIssueDate(LocalDate.now());
      LocalDate expiration = convertToLocalDate(request.getExpirationDate());
      if(expiration.isBefore(LocalDate.now())){
          expiration = LocalDate.now().plusMonths(1);
      }
      p.setExpirationDate(expiration);
      p.setPrescriptionDrugs(prescriptionDrugs);
      Prescription created = prescriptionRepository.save(p);

      List<PrescriptionDrug> savedPDs = prescriptionDrugRepository.saveAll(prescriptionDrugs);

      List<via.pro3.prescriptionsgrpc.generated.Drug> replyDrugs = convertPrescriptionDrugsToGRPCDrugs(savedPDs);

      Timestamp issueDate = convertToTimestamp(p.getIssueDate());
      Timestamp expirationDate = convertToTimestamp(p.getExpirationDate());

      PrescriptionReply reply = PrescriptionReply.newBuilder()
          .setId(created.getId())
          .setDoctorId(created.getDoctor().getId().intValue())
          .setPatientId(created.getPatient().getId().intValue())
          .setExpirationDate(expirationDate)
          .setIssueDate(issueDate)
          .addAllDrugs(replyDrugs)
          .build();

      responseObserver.onNext(reply);
      responseObserver.onCompleted();
  }

    @Override
    public void getPrescriptions(PrescriptionsRequest request,
                                 StreamObserver<GetPrescriptionsReply> responseObserver) {

        long patientCpr = request.getPatientId();
        User patient = userRepository.findById(patientCpr).orElse(null);

        if(patient==null){
            GetPrescriptionsReply emptyReply = GetPrescriptionsReply.newBuilder().build();
            responseObserver.onNext(emptyReply);
            responseObserver.onError(new NullPointerException("patient is null"));
            return;
        }

    List<Prescription> prescriptions =
        prescriptionRepository.findByPatient_Id(patientCpr);

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

            .setDoctorId(p.getDoctor().getId())
            .setPatientId(p.getPatient().getId())
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
            User doctor = userRepository.findById(request.getDoctorId()).orElse(null);
            User patient = userRepository.findById(request.getPatientId()).orElse(null);

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

            for(CreatePrescriptionDrug drug : request.getDrugsList()){
                PharmacyDrug pharmacyDrug = drugStorageRepository.findById(drug.getName()).orElse(null);

                Drug drugToSave;

                //prepare to save drug into hospital db if exists on pharmacy db
                try {
                    drugToSave = new Drug(
                        pharmacyDrug.getName(),
                        pharmacyDrug.getDescription()
                    );

                    drugRepository.save(drugToSave);
                }catch (NullPointerException e){
                    System.out.println();
                    responseObserver.onError(new NoSuchElementException("Drug not in database"));
                    return;
                }

                PrescriptionDrug prescriptionDrug = new PrescriptionDrug(
                    drugToSave,
                    updated,
                    drug.getNote(),
                    drug.getAvailabilityCount(),
                    drug.getStartingAmount()
                );

                newPrescriptionDrugs.add(prescriptionDrug);
            }

            //delete all prescriptiondrugs, so we can create them again with new given drugs (ids in the future)
            prescriptionDrugRepository.deleteAll(oldPrescriptionDrugs);

            //if no problems with drugs, replace old with new
            prescriptionDrugRepository.saveAll(newPrescriptionDrugs);

            List<via.pro3.prescriptionsgrpc.generated.Drug> replyDrugs = convertPrescriptionDrugsToGRPCDrugs(newPrescriptionDrugs);

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

        User user = userRepository.findById(cpr).orElse(null);

        if (user != null && passwordEncoder.matches(request.getPassword(), user.getPassword())) {
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
        if(userRepository.existsById(request.getCpr())){
            responseObserver.onError(new SQLException("User already exists"));
            return;
        }
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = new User(
                request.getName(),
                request.getSurname(),
                encodedPassword,
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

    @Override public void createRoleUser(CreateRoleUserRequest request,
        StreamObserver<CreateUserReply> responseObserver)
    {
        if(userRepository.existsById(request.getCpr())){
            responseObserver.onError(new SQLException("User already exists"));
            return;
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = new User(
            request.getName(),
            request.getSurname(),
            encodedPassword,
            request.getPhone(),
            request.getCpr(),
            convertToDbRole(request.getRole()),
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

      User user = userRepository.findById(cpr).orElse(null);

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
        //get all drugs from pharmacy, map to drug response
        List<PharmacyDrug> allDrugs = drugStorageRepository.findAll();
        List<DrugList> items = allDrugs.stream().map(pd ->
            DrugList.newBuilder()
              .setName(pd.getName())
              .setStock(pd.getStock())
              .setPrice(pd.getPrice())
              .setReorderLevel(pd.getReorderLevel())
              .setDescription(pd.getDescription())
              .build()
        ).toList();

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
            request.getName(), request.getDescription(), request.getStock(), request.getPrice(), request.getReorderLevel()));

        CreateDrugReply reply = CreateDrugReply.newBuilder()
            .setName(created.getName())
            .setDescription(created.getDescription())
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
        PharmacyDrug found = drugStorageRepository.findById(request.getName()).orElse(null);

        try {
            found.setStock(request.getStock());
            found.setPrice(request.getPrice());
            found.setReorderLevel(request.getReorderLevel());
            found.setDescription(request.getDescription());

            drugStorageRepository.save(found);

            UpdateDrugReply reply = UpdateDrugReply.newBuilder()
                .setName(found.getName())
                .setDescription(found.getDescription())
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