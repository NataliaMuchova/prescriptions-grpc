package via.pro3.prescriptionsgrpc.service;

import via.pro3.prescriptionsgrpc.entities.pharmacy.Drug;
import via.pro3.prescriptionsgrpc.repository.IPharmacyDrugRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TempPharmacyDrugRepoImpl implements IPharmacyDrugRepository
{
    List<Drug> drugs;

    public TempPharmacyDrugRepoImpl(){
        drugs = new ArrayList<>();
        drugs.add(new Drug("1", 10, 10));
        drugs.add(new Drug("2", 1, 30));
        drugs.add(new Drug("3", 0, 1000));
    }

    @Override public Optional<Drug> findById(String id)
    {
        return drugs.stream().filter(d -> d.getId().equalsIgnoreCase(id)).findAny();
    }

    @Override public Drug save(Drug drug)
    {
        drugs.add(drug);
        return drug;
    }
}
