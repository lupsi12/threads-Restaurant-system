import java.util.ArrayList;

public class Problem2_Waiter {
    private String waiterId;
    private int waiterTimer;
    private boolean waiter;//sipariş anında mı
    private int customerId;
    private boolean takenOrder ;//sipariş sonlandı mı
    private ArrayList<Problem2_TakenMeal> problem2TakenMeals;

    public Problem2_Waiter() {
    }

    public Problem2_Waiter(String waiterId, int waiterTimer, boolean waiter, int customerId, Boolean takenOrder) {
        this.waiterId = waiterId;
        this.waiterTimer = waiterTimer;
        this.waiter = waiter;
        this.customerId = customerId;
        this.takenOrder = takenOrder;
    }

    public String getWaiterId() {

        return waiterId;
    }

    public void setWaiterId(String waiterId) {
        this.waiterId = waiterId;
    }

    public int getWaiterTimer() {
        return waiterTimer;
    }

    public void setWaiterTimer(int waiterTimer) {
        this.waiterTimer = waiterTimer;
    }

    public boolean isWaiter() {
        return waiter;
    }

    public void setWaiter(boolean waiter) {
        this.waiter = waiter;
    }

    public ArrayList<Problem2_TakenMeal> getTakenMeals() {
        return problem2TakenMeals;
    }

    public void setTakenMeals(ArrayList<Problem2_TakenMeal> problem2TakenMeals) {
        this.problem2TakenMeals = problem2TakenMeals;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public boolean getTakenOrder() {
        return takenOrder;
    }

    public void setTakenOrder(Boolean takenOrder) {
        this.takenOrder = takenOrder;
    }

}
