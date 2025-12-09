package via.pro3.prescriptionsgrpc.entities.hospital;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "drug", schema = "hospital")
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "description", length = 200)
    private String description;

    @Column(name = "amount")
    private Integer amount;

    @OneToMany(mappedBy = "drug")
    private Set<PrescriptionDrug> prescriptionDrugs;

    public Drug(int id, String name, String description, int amount)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amount = amount;
    }

    public Drug(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

}