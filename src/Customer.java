import java.awt.font.TextHitInfo;

public class Customer {
    private CustomerState customerState;
    private CustomerType customerType;
    private HumanInRestaurant humanInRestaurant;
    private int labelNo;
    public Customer() {
    }
    public Customer(CustomerState customerState, CustomerType customerType, HumanInRestaurant humanInRestaurant,int labelNo) {
        this.customerState = customerState;
        this.customerType = customerType;
        this.humanInRestaurant = humanInRestaurant;
        this.labelNo = labelNo;
    }

    public CustomerState getCustomerState() {
        return customerState;
    }

    public void setCustomerState(CustomerState customerState) {
        this.customerState = customerState;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public HumanInRestaurant getHumanInRestaurant() {
        return humanInRestaurant;
    }

    public void setHumanInRestaurant(HumanInRestaurant humanInRestaurant) {
        this.humanInRestaurant = humanInRestaurant;
    }

    public int getLabelNo() {
        return labelNo;
    }

    public void setLabelNo(int labelNo) {
        this.labelNo = labelNo;
    }
}
