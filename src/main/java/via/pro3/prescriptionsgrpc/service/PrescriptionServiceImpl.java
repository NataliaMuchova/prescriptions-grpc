package via.pro3.prescriptionsgrpc.service;

import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import via.pro3.prescriptionsgrpc.entities.*;
import via.pro3.prescriptionsgrpc.entities.Drug;
import via.pro3.prescriptionsgrpc.generated.*;
import via.pro3.prescriptionsgrpc.repository.*;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@GRpcService
public class PrescriptionServiceImpl extends HospitalGrpc.HospitalImplBase {

  @Autowired
  private IDatabasePrescriptionRepository prescriptionRepository;

  @Autowired
  private IDatabaseDoctorRepository doctorRepository;

  @Autowired
  private IDatabasePatientRepository patientRepository;

  @Autowired
  private IDatabaseUserRepository userRepository;

  @Autowired
  private IDatabaseDrugRepository drugRepository;

  @Override
  public void createPrescription(CreatePrescriptionRequest request,
                                 StreamObserver<PrescriptionReply> responseObserver) {
    Prescription p = new Prescription();

    Doctor doctor = doctorRepository.findById(request.getDoctorId()).orElse(null);
    Patient patient = patientRepository.findById(request.getPatientId()).orElse(null);

    p.setDoctor(doctor);
    p.setPatient(patient);
    p.setIssueDate(LocalDate.now());
    p.setExpirationDate(LocalDate.now().plusMonths(1));

    p = prescriptionRepository.save(p);
//here drug impl
//    Drug drug = drugRepository.findById(drug.getId());
    Drug drug = new Drug();
    drug.setName(drug.getName());
    drug.setDescription(drug.getDescription());
    drug.setAmount(drug.getAmount());

    drug = drugRepository.save(drug);

    //prescriptionDrug??

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
        .setDoctorId(p.getDoctor().getId())
        .setPatientId(p.getPatient().getId())
        .setIssueDate(issueTs)
        .setExpirationDate(expTs)
        .build();

    responseObserver.onNext(reply);
    responseObserver.onCompleted();
  }

  @Override
  public void getPrescriptions(PrescriptionsRequest request,
                               StreamObserver<GetPrescriptionsReply> responseObserver) {
    List<Prescription> prescriptions =
        prescriptionRepository.findByPatient_Id(request.getPatientId());

    List<PrescriptionReply> items = prescriptions.stream()
        .map(p -> PrescriptionReply.newBuilder()
            .setId(p.getId())
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

  @Override
  public void checkCredentials(CheckCredentialsRequest request,
                               io.grpc.stub.StreamObserver<CheckCredentialsReply> responseObserver) {
    long cpr = Long.parseLong(String.valueOf(request.getUserId()));

    User doctor = userRepository
          .findById(new UserId(cpr, "doctor")).orElse(null);

    boolean doctorValid = doctor != null && doctor.getPassword().equals(request.getPassword());

    if (doctorValid) {
      CheckCredentialsReply reply = CheckCredentialsReply.newBuilder()
          .setRole(UserRoles.Doctor)
          .build();
      responseObserver.onNext(reply);
      responseObserver.onCompleted();
      return;
    }

    User patient = userRepository
        .findById(new UserId(cpr, "patient")).orElse(null);

    boolean patientValid = patient != null && patient.getPassword().equals(request.getPassword());

    if (patientValid) {
      CheckCredentialsReply reply = CheckCredentialsReply.newBuilder()
          .setRole(UserRoles.Patient)
          .build();
      responseObserver.onNext(reply);
      responseObserver.onCompleted();
      return;
    }

    CheckCredentialsReply reply = CheckCredentialsReply.newBuilder()
        .setRole(UserRoles.Invalid)
        .build();
    responseObserver.onNext(reply);
    responseObserver.onCompleted();
  }
}