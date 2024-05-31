public class Table {
    private int satir;
    private int sutun;
    private boolean dolu;
    public Table(int satir, int sutun, boolean dolu) {
        this.satir = satir;
        this.sutun = sutun;
        this.dolu = dolu;
    }
    public Table() {

    }
    public int getSatir() {
        return satir;
    }
    public void setSatir(int satir) {
        this.satir = satir;
    }
    public int getSutun() {
        return sutun;
    }
    public void setSutun(int sutun) {
        this.sutun = sutun;
    }

    public boolean isDolu() {
        return dolu;
    }

    public void setDolu(boolean dolu) {
        this.dolu = dolu;
    }
}
