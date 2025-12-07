package via.pro3.prescriptionsgrpc.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "prescription_drug", schema = "hospital")
public class PrescriptionDrug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drug_id")
    private Drug drug;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;

    @Column(name = "note", length = 500)
    private String note;

    @Column(name = "availability_count")
    private Integer availabilityCount;

    public PrescriptionDrug(Drug drug, Prescription prescription, String note, int availabilityCount)
    {
        this.drug = drug;
        this.prescription = prescription;
        this.note = note;
        this.availabilityCount = availabilityCount;
    }

    public PrescriptionDrug(){

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
}