package bill.cookapp;

import java.util.ArrayList;

/**
 * Created by erica on 10/10/16.
 */
public class OrderItem {
    private long id;
    private String customer_name;
    private ArrayList<Integer> quantity;
    private ArrayList<String> food;
    private int status;

    public OrderItem(String customer_name, ArrayList<Integer> quantity, ArrayList<String> food) {
        this.customer_name = customer_name;
        this.quantity = quantity;
        this.food = food;
        this.status = 0;            //0 for not complete and 1 for complete
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

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public ArrayList<Integer> getQuantity() {
        return quantity;
    }

    public void setQuantity(ArrayList<Integer> quantity) { this.quantity = quantity; }

    public ArrayList<String> getFood() { return food; }

    public void setFood(ArrayList<String> food) {
        this.food = food;
    }

    public int getStatus(){ return status; }

    public void setStatus(int status) {this.status = status;}
}
