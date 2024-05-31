public class CookingData
{
    public Customer customerToCook;
    public int cookETA;

    public CookingData()
    {
        this.cookETA = 0;
        this.customerToCook = null;
    }

    public CookingData(Customer customerToCook, int cookETA)
    {
        this.cookETA = cookETA;
        this.customerToCook = customerToCook;
    }
}
