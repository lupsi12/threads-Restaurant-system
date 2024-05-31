import java.util.ArrayList;

public class Problem2_Chief {
    private String chiefId;
    private int mealOneId;
    private int mealOneTimer;
    private boolean mealOne ;
    private int mealTwoTimer;
    private boolean mealTwo ;
    private int mealTwoId;
    private ArrayList<Problem2_PrepareMeal> problem2PrepareMeals = new ArrayList<>();

    public Problem2_Chief() {
    }

    public Problem2_Chief(String chiefId, int mealOneTimer, boolean mealOne, int mealTwoTimer, boolean mealTwo) {
        this.chiefId = chiefId;
        this.mealOneTimer = mealOneTimer;
        this.mealOne = mealOne;
        this.mealTwoTimer = mealTwoTimer;
        this.mealTwo = mealTwo;
    }

    public int getMealOneTimer() {
        return mealOneTimer;
    }

    public void setMealOneTimer(int mealOneTimer) {
        this.mealOneTimer = mealOneTimer;
    }

    public boolean isMealOne() {
        return mealOne;
    }

    public void setMealOne(boolean mealOne) {
        this.mealOne = mealOne;
    }

    public int getMealTwoTimer() {
        return mealTwoTimer;
    }

    public void setMealTwoTimer(int mealTwoTimer) {
        this.mealTwoTimer = mealTwoTimer;
    }

    public boolean isMealTwo() {
        return mealTwo;
    }

    public void setMealTwo(boolean mealTwo) {
        this.mealTwo = mealTwo;
    }

    public String getChiefId() {
        return chiefId;
    }

    public void setChiefId(String chiefId) {
        this.chiefId = chiefId;
    }

    public int getMealOneId() {
        return mealOneId;
    }

    public void setMealOneId(int mealOneId) {
        this.mealOneId = mealOneId;
    }

    public int getMealTwoId() {
        return mealTwoId;
    }

    public void setMealTwoId(int mealTwoId) {
        this.mealTwoId = mealTwoId;
    }
}
