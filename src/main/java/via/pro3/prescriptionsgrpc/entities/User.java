package via.pro3.prescriptionsgrpc.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity @Table(name = "\"user\"", schema = "hospital") public class User
{
    @EmbeddedId private UserId id;

    @Column(name = "name", length = 100) private String name;

    @Column(name = "surname", length = 100) private String surname;

    @Column(name = "password", length = 50) private String password;

    @Column(name = "phone", length = 20) private String phone;

    public enum Roles
    {
        PATIENT("patient"),
        DOCTOR("doctor");

        public final String role;

        Roles(String role)
        {
            this.role = role;
        }


    }

    public User(String name, String surname, String password, String phone, long cpr, Roles role)
    {
        this.id = new UserId(cpr, role.role);
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.phone = phone;
    }

    public User(String name, String surname, String password, String phone, long cpr){
        this(name, surname, password, phone, cpr, Roles.PATIENT);
    }

    public User(){

    }

    public UserId getId()
    {
        return id;
    }

    public void setId(UserId id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

}