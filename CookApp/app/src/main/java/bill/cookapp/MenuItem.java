package bill.cookapp;

import java.util.ArrayList;

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

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public ArrayList<Integer> getAmount() {
        return amount;
    }

    public void setAmount(ArrayList<Integer> amount) { this.amount = amount; }

    public ArrayList<String> getIngredients() { return ingredients; }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }
}
