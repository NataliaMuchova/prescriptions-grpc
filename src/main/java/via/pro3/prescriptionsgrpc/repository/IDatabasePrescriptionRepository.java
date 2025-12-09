package via.pro3.prescriptionsgrpc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import via.pro3.prescriptionsgrpc.entities.hospital.Prescription;

import java.util.List;

@Repository public interface IDatabasePrescriptionRepository
    extends JpaRepository<Prescription, Integer>
{
  List<Prescription> findByPatient_Id(Integer patientId);

}