package via.pro3.prescriptionsgrpc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import via.pro3.prescriptionsgrpc.entities.hospital.User;
import via.pro3.prescriptionsgrpc.entities.pharmacy.PharmacyDrug;

import java.util.Optional;

public interface IPharmacyDrugRepository extends JpaRepository<PharmacyDrug, String> {

}
