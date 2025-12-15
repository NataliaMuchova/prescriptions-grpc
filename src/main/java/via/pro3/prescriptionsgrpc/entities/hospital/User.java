package via.pro3.prescriptionsgrpc.entities.hospital;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "\"user\"", schema = "hospital")
public class User {
    @Id
    @Column(name = "cpr", nullable = false)
    private Long id;

    @Column(name = "role", length = 50)
    private String role;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "surname", length = 100)
    private String surname;

    @Column(name = "password", length = 50)
    private String password;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "gender", length = Integer.MAX_VALUE)
    private String gender;

    public enum Roles
    {
        PATIENT("patient"),
        DOCTOR("doctor"),
        PHARMACIST("pharmacist");

        public final String role;

        Roles(String role)
        {
            this.role = role;
        }
        }

    public User(String name, String surname, String password, String phone, long cpr, Roles role, LocalDate birthday, String gender)
    {
        this.id = cpr;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.phone = phone;
        this.role = role.role;
        this.birthday = birthday;
        this.gender = gender;
    }

    public User(String name, String surname, String password, String phone, long cpr, String gender){
        this(name, surname, password, phone, cpr, Roles.PATIENT, LocalDate.now(), gender);
    }

    public User(String name, String surname, String password, String phone, long cpr){
        this(name, surname, password, phone, cpr, Roles.PATIENT,  LocalDate.now(), null);
    }

    public User(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}