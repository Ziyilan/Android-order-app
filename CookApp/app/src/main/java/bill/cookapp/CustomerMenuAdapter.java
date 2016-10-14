package bill.cookapp;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;


/**
 * This adapter takes one layout for one element in the list view and applies it to
 * all elements in the list view for all of the array list.
 */

public class CustomerMenuAdapter extends ArrayAdapter<MenuItem> {

    private ArrayList<MenuItem> menu;
    int[] quantityList;

    public CustomerMenuAdapter(Context context, ArrayList<MenuItem> menuItems) {
        super(context, 0, menuItems);
        menu = menuItems;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent) {
        final FoodHolder holder = new FoodHolder();
        holder.menuItem = getItem(pos);     //gets the menu item using position

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.customer_menu_item, null);
        }

        holder.quantity = (TextView)convertView.findViewById(R.id.quantity);
        holder.menuText = (TextView)convertView.findViewById(R.id.customer_menu_item_name);

        holder.menuText.setText(holder.menuItem.getItemName());         //sets the text of the text view to be the name of the menu item

        quantityList = new int[menu.size()];

        holder.quantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // quantity on click will give an alert dialog that takes a user input of quantity corresponding food item
                AlertDialog.Builder input = new AlertDialog.Builder(getContext());
                input.setTitle("Input");
                input.setCancelable(false);

                final EditText number = new EditText(getContext());
                number.setHint("How many do you want to order?");

                input.setView(number);

                input.setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        int userString = Integer.parseInt(number.getText().toString());
                        quantityList[pos] = userString;
                        holder.quantity.setText(number.getText().toString());
                    }
                });

                input.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog userInput = input.create();
                userInput.show();                                                   //when the button is clicked view goes back to list fragment
            }
        });

        return convertView;
    }

    public static class FoodHolder{
        //creates a holder that holds the quantity menu text of each menu item
        TextView quantity;
        TextView menuText;
        MenuItem menuItem;
    }

    public ArrayList<Integer> getQuantityList(){
        //converts the quantity list to an ArrayList
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i<quantityList.length;i++) {
            res.add(quantityList[i]);
        }
        return res;
    }
}