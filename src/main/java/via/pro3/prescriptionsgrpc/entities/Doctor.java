package via.pro3.prescriptionsgrpc.entities;

import jakarta.persistence.*;

@Entity @Table(name = "doctor", schema = "hospital") public class Doctor
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id", nullable = false) private Integer id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumns({
        @JoinColumn(name = "role", referencedColumnName = "role"),
        @JoinColumn(name = "cpr", referencedColumnName = "cpr")}) private User user;

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

}