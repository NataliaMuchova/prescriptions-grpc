package via.pro3.prescriptionsgrpc.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity @Table(name = "prescription", schema = "hospital") public class Prescription
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id", nullable = false) private Integer id;

    @Column(name = "expiration_date") private LocalDate expirationDate;

    @Column(name = "issue_date") private LocalDate issueDate;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "patient_id") private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "doctor_id") private Doctor doctor;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public LocalDate getExpirationDate()
    {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate)
    {
        this.expirationDate = expirationDate;
    }

    public LocalDate getIssueDate()
    {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate)
    {
        this.issueDate = issueDate;
    }

    public Patient getPatient()
    {
        return patient;
    }

    public void setPatient(Patient patient)
    {
        this.patient = patient;
    }

    public Doctor getDoctor()
    {
        return doctor;
    }

    public void setDoctor(Doctor doctor)
    {
        this.doctor = doctor;
    }

}