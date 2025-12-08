package via.pro3.prescriptionsgrpc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import via.pro3.prescriptionsgrpc.entities.hospital.Prescription;
import via.pro3.prescriptionsgrpc.entities.hospital.PrescriptionDrug;

import java.util.List;

@Repository public interface IDatabasePrescriptionDrugRepository extends JpaRepository<PrescriptionDrug, Integer>
{
    List<PrescriptionDrug> findByPrescription(Prescription prescription);
}
