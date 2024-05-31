public class Waiter
{
    private WaiterState waiterState;
    private HumanInRestaurant humanInRestaurant;
    private int labelNo;

    public Waiter() {}

    public Waiter(WaiterState waiterState, HumanInRestaurant humanInRestaurant, int labelNo)
    {
        this.waiterState = waiterState;
        this.humanInRestaurant = humanInRestaurant;
        this.labelNo = labelNo;
    }

    public WaiterState getWaiterState() {
        return waiterState;
    }

    public void setWaiterState(WaiterState waiterState) {
        this.waiterState = waiterState;
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
