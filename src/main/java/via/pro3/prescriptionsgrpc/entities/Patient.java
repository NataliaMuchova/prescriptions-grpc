package via.pro3.prescriptionsgrpc.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity @Table(name = "patient", schema = "hospital") public class Patient
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id", nullable = false) private Integer id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumns({
        @JoinColumn(name = "role", referencedColumnName = "role"),
        @JoinColumn(name = "cpr", referencedColumnName = "cpr")}) private User user;

    @Column(name = "gender", length = Integer.MAX_VALUE) private String gender;

    @Column(name = "birthday") private LocalDate birthday;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public LocalDate getBirthday()
    {
        return birthday;
    }

    public void setBirthday(LocalDate birthday)
    {
        this.birthday = birthday;
    }

}