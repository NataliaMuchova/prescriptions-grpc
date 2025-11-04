package via.pro3.prescriptionsgrpc.service;

import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import via.pro3.prescriptionsgrpc.entities.Doctor;
import via.pro3.prescriptionsgrpc.entities.Patient;
import via.pro3.prescriptionsgrpc.entities.Prescription;
import via.pro3.prescriptionsgrpc.generated.*;
import via.pro3.prescriptionsgrpc.repository.DoctorRepository;
import via.pro3.prescriptionsgrpc.repository.IDatabasePrescriptionRepository;
import via.pro3.prescriptionsgrpc.repository.PatientRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@GRpcService
public class PrescriptionServiceImpl extends HospitalGrpc.HospitalImplBase {

  @Autowired
  private IDatabasePrescriptionRepository prescriptionRepository;

  @Autowired
  private DoctorRepository doctorRepository;

  @Autowired
  private PatientRepository patientRepository;

  public PrescriptionServiceImpl() {
  }

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

    PrescriptionReply reply = PrescriptionReply.newBuilder()
        .setId(p.getId())
        .setDoctorId(p.getDoctor().getId())
        .setPatientId(p.getPatient().getId())
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

    boolean doctorValid = doctorRepository
        .findByUser_CprAndUser_Password(cpr, request.getPassword())
        .isPresent();

    if (doctorValid) {
      CheckCredentialsReply reply = CheckCredentialsReply.newBuilder()
          .setRole(UserRoles.Doctor)
          .build();
      responseObserver.onNext(reply);
      responseObserver.onCompleted();
      return;
    }

    boolean patientValid = patientRepository
        .findByUser_CprAndUser_Password(cpr, request.getPassword())
        .isPresent();

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