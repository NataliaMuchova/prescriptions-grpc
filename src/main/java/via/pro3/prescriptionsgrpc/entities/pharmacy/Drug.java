package via.pro3.prescriptionsgrpc.entities.pharmacy;

import org.springframework.stereotype.Component;

@Component
public class Drug
{
    private String id;
    private int stock;
    private double price;

    public Drug()
    {

    }

    public Drug(String id, int stock, double price)
    {
        this.id = id;
        this.stock = stock;
        this.price = price;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
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
}
