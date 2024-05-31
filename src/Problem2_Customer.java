public class Problem2_Customer {
    private String customerId;
    private String customerType;
    private String customerState;
    private int customerTimer;

    public Problem2_Customer() {
    }

    public Problem2_Customer(String customerId, String customerType, String customerState, int customerTimer) {
        this.customerId = customerId;
        this.customerType = customerType;
        this.customerState = customerState;
        this.customerTimer = customerTimer;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getCustomerTimer() {
        return customerTimer;
    }

    public void setCustomerTimer(int customerTimer) {
        this.customerTimer = customerTimer;
    }

    public String getCustomerState() {
        return customerState;
    }

    public void setCustomerState(String customerState) {
        this.customerState = customerState;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }
}
