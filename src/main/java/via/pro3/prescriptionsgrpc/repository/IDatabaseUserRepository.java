package via.pro3.prescriptionsgrpc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import via.pro3.prescriptionsgrpc.entities.hospital.User;

@Repository
public interface IDatabaseUserRepository extends JpaRepository<User, Long>
{
}
