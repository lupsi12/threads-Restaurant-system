public class Problem2_Hesaplama {
    private int customerN;
    private int waiterN;
    private int chiefN;
    private int tableN;
    private int price;

    public Problem2_Hesaplama() {
    }

    public Problem2_Hesaplama(int customerN, int waiterN, int chiefN, int tableN) {
        this.customerN = customerN;
        this.waiterN = waiterN;
        this.chiefN = chiefN;
        this.tableN = tableN;
    }

    public int getCustomerN() {
        return customerN;
    }

    public void setCustomerN(int customerN) {
        this.customerN = customerN;
    }

    public int getWaiterN() {
        return waiterN;
    }

    public void setWaiterN(int waiterN) {
        this.waiterN = waiterN;
    }

    public int getChiefN() {
        return chiefN;
    }

    public void setChiefN(int chiefN) {
        this.chiefN = chiefN;
    }

    public int getTableN() {
        return tableN;
    }

    public void setTableN(int tableN) {
        this.tableN = tableN;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
