package bill.cookapp;

import java.util.ArrayList;

//class for each food item in the database
public class MenuItem {
    private long id;
    private String itemName;
    private ArrayList<Integer> amount;
    private ArrayList<String> ingredients;

    public MenuItem(String itemName, ArrayList<Integer> amount, ArrayList<String> ingredients) {
        this.itemName = itemName;
        this.amount = amount;
        this.ingredients = ingredients;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public ArrayList<Integer> getAmount() {
        return amount;
    }

    public ArrayList<String> getIngredients() { return ingredients; }
}
