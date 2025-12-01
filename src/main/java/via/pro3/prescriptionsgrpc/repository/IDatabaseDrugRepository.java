package via.pro3.prescriptionsgrpc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import via.pro3.prescriptionsgrpc.entities.Drug;
import via.pro3.prescriptionsgrpc.entities.Prescription;
import via.pro3.prescriptionsgrpc.entities.PrescriptionDrug;

import java.util.List;

@Repository
public interface IDatabaseDrugRepository extends JpaRepository<Drug, Integer> {
    @Query( value= "SELECT drug.id, drug.name, drug.description, drug.amount FROM drug inner join hospital.prescription_drug pd on drug.id = pd.drug_id inner join hospital.prescription p on p.id = pd.prescription_id WHERE p.id = ?", nativeQuery = true)
     List<Drug> findByPrescriptionId(int id);
}
