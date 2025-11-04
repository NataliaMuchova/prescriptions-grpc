package via.pro3.prescriptionsgrpc.repository;

import java.util.List;
import java.util.Optional;

public interface PrescriptionRepository<T>
{
  int create(int doctorID, int patientID);
  Optional<T> findById(int id);
  List<T> findAll();
}