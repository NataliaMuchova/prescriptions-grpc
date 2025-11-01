package via.pro3.prescriptionsgrpc.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable public class UserId implements Serializable
{
    private static final long serialVersionUID = -1374439427039516922L;
    @Column(name = "role", nullable = false, length = 50) private String role;

    @Column(name = "cpr", nullable = false) private Long cpr;

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public Long getCpr()
    {
        return cpr;
    }

    public void setCpr(Long cpr)
    {
        this.cpr = cpr;
    }

    @Override public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        UserId entity = (UserId) o;
        return Objects.equals(this.role, entity.role) && Objects.equals(
            this.cpr, entity.cpr);
    }

    @Override public int hashCode()
    {
        return Objects.hash(role, cpr);
    }

}