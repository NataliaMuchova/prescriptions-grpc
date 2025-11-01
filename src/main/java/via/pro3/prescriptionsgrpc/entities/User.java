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