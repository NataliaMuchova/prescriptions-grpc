package via.pro3.prescriptionsgrpc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import via.pro3.prescriptionsgrpc.entities.Patient;

import java.util.Optional;

@Repository
public interface IDatabasePatientRepository extends JpaRepository<Patient, Integer> {

}
