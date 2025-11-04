package via.pro3.prescriptionsgrpc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import via.pro3.prescriptionsgrpc.entities.Doctor;

import java.util.Optional;

@Repository
public interface IDatabaseDoctorRepository extends JpaRepository<Doctor, Integer> {

}
