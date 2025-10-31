package via.pro3.prescriptionsgrpc.repository;

import java.util.List;
import java.util.Optional;

public interface PrescriptionRepository {
int create(int doctorID, int patientID);
Optional<Record> findById(int id);
List<Record> findAll();

record Record(int id, int doctorId, int patientId) {}
}
