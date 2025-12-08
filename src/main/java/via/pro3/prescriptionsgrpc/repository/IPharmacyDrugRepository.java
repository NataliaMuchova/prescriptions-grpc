package via.pro3.prescriptionsgrpc.repository;

import via.pro3.prescriptionsgrpc.entities.pharmacy.Drug;

import java.util.Optional;

public interface IPharmacyDrugRepository
{
    Optional<Drug> findById(String id);
    Drug save(Drug drug);
}
