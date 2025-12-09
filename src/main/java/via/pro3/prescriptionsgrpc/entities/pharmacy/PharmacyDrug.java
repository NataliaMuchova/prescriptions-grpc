package via.pro3.prescriptionsgrpc.entities.pharmacy;

import jakarta.persistence.*;


@Entity
@Table(name = "pharmacy_drug", schema = "pharmacy")
public class PharmacyDrug
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "stock")
    private int stock;

    @Column(name = "price")
    private double price;

    @Column(name = "reorder_level")
    private int reorderLevel;

    @ManyToOne
    @JoinColumn(name = "pharmacy_id")
    private Pharmacy pharmacy;

    public PharmacyDrug()
    {

    }

    public PharmacyDrug(String id, int stock, double price, int reorderLevel, Pharmacy pharmacy)
    {
        this.name = id;
        this.stock = stock;
        this.price = price;
        this.reorderLevel = reorderLevel;
        this.pharmacy = pharmacy;
    }



    public PharmacyDrug(String id, int stock, double price, int reorderLevel) {

        this.name = id;
        this.stock = stock;
        this.price = price;
        this.reorderLevel = reorderLevel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public int getStock()
    {
        return stock;
    }

    public void setStock(int stock)
    {
        this.stock = stock;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public Pharmacy getPharmacy()
    {
        return pharmacy;
    }

    public void setPharmacy(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }
}
