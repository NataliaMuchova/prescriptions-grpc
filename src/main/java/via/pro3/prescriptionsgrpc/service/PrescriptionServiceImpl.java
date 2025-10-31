package via.pro3.prescriptionsgrpc.service;

import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import via.pro3.prescriptionsgrpc.generated.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@GRpcService
public class PrescriptionServiceImpl extends PrescriptionServiceGrpc.PrescriptionServiceImplBase {

  private int nextId = 1;
  private final Map<Integer, Prescription> store = new ConcurrentHashMap<>();

  @Override
  public void createPrescription(CreatePrescriptionRequest request,
                                 StreamObserver<CreatePrescriptionResponse> responseObserver) {

    int id = nextId++;

    Prescription prescription = Prescription.newBuilder()
        .setId(id)
        .setDoctorId(request.getDoctorId())
        .setPatientId(request.getPatientId())
        .build();

    store.put(id, prescription);

    CreatePrescriptionResponse response = CreatePrescriptionResponse.newBuilder()
        .setPrescription(prescription)
        .build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void getPrescription(GetPrescriptionRequest request,
                              StreamObserver<GetPrescriptionResponse> responseObserver) {

    Prescription prescription = store.get(request.getId());

    GetPrescriptionResponse response = GetPrescriptionResponse.newBuilder()
        .setPrescription(prescription != null ? prescription : Prescription.getDefaultInstance())
        .build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void checkCredentials(CheckCredentialsRequest request,
                               StreamObserver<CheckCredentialsResponse> responseObserver) {

    int u = request.getUsername();
    UserRoles role =
        (u == 1) ? UserRoles.Doctor :
            (u == 2) ? UserRoles.Patient :
                (u == 3) ? UserRoles.Pharmacist :
                    UserRoles.Invalid;

    responseObserver.onNext(CheckCredentialsResponse.newBuilder().setRole(role).build());
    responseObserver.onCompleted();
  }
}
