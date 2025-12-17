package via.pro3.prescriptionsgrpc.entities.hospital;

import jakarta.persistence.*;

@Entity
@Table(name = "prescription_drug", schema = "hospital")
public class PrescriptionDrug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "drug_id")
    private Drug drug;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;

    @Column(name = "note", length = 500)
    private String note;

    @Column(name = "availability_count")
    private Integer availabilityCount;

    @Column (name = "starting_amount")
    private Integer startingAmount;

    public PrescriptionDrug(Drug drug, Prescription prescription, String note, int availabilityCount, int startingAmount)
    {
        this.drug = drug;
        this.prescription = prescription;
        this.note = note;
        this.availabilityCount = availabilityCount;
        this.startingAmount = startingAmount;
    }

    public PrescriptionDrug(){

    }

    public PrescriptionDrug(Drug drug, String note, Integer availabilityCount, Prescription prescription, int startingAmount)
    {
        this.drug = drug;
        this.note = note;
        this.availabilityCount = availabilityCount;
        this.prescription = prescription;
        this.startingAmount = startingAmount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getAvailabilityCount() {
        return availabilityCount;
    }

    public void setAvailabilityCount(Integer availabilityCount) {
        this.availabilityCount = availabilityCount;
    }

    public void setPrescription(Prescription p)
    {
        this.prescription = p;
    }

    public Integer getStartingAmount() {
    return startingAmount;
    }

    public void setStartingAmount(Integer startingAmount)
    {
      this.startingAmount = startingAmount;
    }
}