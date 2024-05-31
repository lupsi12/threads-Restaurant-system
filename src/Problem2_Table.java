public class Problem2_Table {
    private int tableId;
    private boolean table;

    public Problem2_Table() {
    }

    public Problem2_Table(int tableId, boolean table) {
        this.tableId = tableId;
        this.table = table;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public boolean isTable() {
        return table;
    }

    public void setTable(boolean table) {
        this.table = table;
    }
}
