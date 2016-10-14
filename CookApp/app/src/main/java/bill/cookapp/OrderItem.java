package bill.cookapp;

import java.util.ArrayList;

//class for each order
public class OrderItem {
    private long id;
    private String customer_name;
    private ArrayList<Integer> quantity;
    private ArrayList<String> food;
    private int status;
    private String comment;

    public OrderItem(String customer_name, ArrayList<Integer> quantity, ArrayList<String> food, String comment) {
        this.customer_name = customer_name;
        this.quantity = quantity;
        this.food = food;
        this.status = 0;            //0 for not complete and 1 for complete
        this.comment = comment;     // additional feature, add a customized comment in each order
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public ArrayList<Integer> getQuantity() {
        return quantity;
    }

    public ArrayList<String> getFood() { return food; }

    public int getStatus(){ return status; }

    public String getComment() {return comment;}
}
