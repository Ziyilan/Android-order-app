package bill.cookapp;

public class CooksMenuItem {
    private long id;
    private String itemName;

    public CooksMenuItem(String itemName) {
        this.itemName = itemName;
    }
    // return the item id
    public long getId() {
        return id;
    }
    // set the item id
    public void setId(long id) {
        this.id = id;
    }
}
