package via.pro3.prescriptionsgrpc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import via.pro3.prescriptionsgrpc.entities.Drug;

@Repository
public interface IDatabaseDrugRepository extends JpaRepository<Drug, Integer> {
}
