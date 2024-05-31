public class CashRegister
{
    private CashRegisterState cashRegisterState;
    private HumanInRestaurant humanInRestaurant;
    private int labelNo;

    public CashRegister() {}

    public CashRegister(CashRegisterState cashRegisterState, HumanInRestaurant humanInRestaurant, int labelNo)
    {
        this.cashRegisterState = cashRegisterState;
        this.humanInRestaurant = humanInRestaurant;
        this.labelNo = labelNo;
    }

    public CashRegisterState getCashRegisterState() {
        return cashRegisterState;
    }

    public void setCashRegisterState(CashRegisterState cashRegisterState) {
        this.cashRegisterState = cashRegisterState;
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
