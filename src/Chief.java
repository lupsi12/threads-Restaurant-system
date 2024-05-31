public class Chief
{
    private ChiefState chiefState;
    private HumanInRestaurant humanInRestaurant;
    private int labelNo;

    public Chief() {}

    public Chief(ChiefState chiefState, HumanInRestaurant humanInRestaurant, int labelNo)
    {
        this.chiefState = chiefState;
        this.humanInRestaurant = humanInRestaurant;
        this.labelNo = labelNo;
    }

    public ChiefState getChiefState() {
        return chiefState;
    }

    public void setChiefState(ChiefState chiefState) {
        this.chiefState = chiefState;
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
