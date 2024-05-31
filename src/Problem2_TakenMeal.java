public class Problem2_TakenMeal {
    private int customerId;
    private Boolean taken ;

    public Problem2_TakenMeal() {

    }

    public Problem2_TakenMeal(int customerId, Boolean taken) {
        this.customerId = customerId;
        this.taken = taken;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Boolean getTaken() {
        return taken;
    }

    public void setTaken(Boolean taken) {
        this.taken = taken;
    }
}
