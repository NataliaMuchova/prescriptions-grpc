package via.pro3.prescriptionsgrpc.entities.hospital;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "drug", schema = "hospital")
public class Drug {
    @Id
    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "description", length = 200)
    private String description;

    @OneToMany(mappedBy = "drug")
    private Set<PrescriptionDrug> prescriptionDrugs;

    public Drug(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    public Drug(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}