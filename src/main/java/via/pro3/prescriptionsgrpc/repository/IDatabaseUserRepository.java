package via.pro3.prescriptionsgrpc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import via.pro3.prescriptionsgrpc.entities.User;
import via.pro3.prescriptionsgrpc.entities.UserId;

import java.util.Optional;
import java.util.OptionalDouble;

@Repository
public interface IDatabaseUserRepository extends JpaRepository<User, UserId>
{
}
