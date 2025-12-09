package via.pro3.prescriptionsgrpc.entities.pharmacy;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "record", schema = "pharmacy")
public class Record
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "drug_id")
    private PharmacyDrug drug;

    public Record(){}

    public Record(Integer id, LocalDate date, Integer quantity, PharmacyDrug drug ) {
        this.id = id;
        this.date = date;
        this.quantity = quantity;
        this.drug = drug;

    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public PharmacyDrug getDrug() {
        return drug;
    }

    public void setDrug(PharmacyDrug drug) {
        this.drug = drug;
    }
}
