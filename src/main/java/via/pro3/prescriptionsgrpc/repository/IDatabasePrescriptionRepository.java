package via.pro3.prescriptionsgrpc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import via.pro3.sep3database.entities.Prescription;

@Repository public interface IDatabasePrescriptionRepository
    extends PrescriptionRepository<Prescription>, JpaRepository<Prescription, Integer>
{

}
