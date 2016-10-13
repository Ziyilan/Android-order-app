package bill.cookapp;

import android.content.Context;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * This adapter takes one layout for one element in the list view and applies it to
 * all elements in the list view for all of the array list.
 */

public class CustomerMenuAdapter extends ArrayAdapter<MenuItem> {

    private ArrayList<MenuItem> menu;
//    @BindView(R.id.customer_menu_item_name) TextView itemName;
//    @BindView(R.id.quantity) TextView quantity;
    int[] quantityList;

    public CustomerMenuAdapter(Context context, ArrayList<MenuItem> menuItems) {
        super(context, 0, menuItems);
        menu = menuItems;

    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent) {
        final FoodHolder holder = new FoodHolder();
        holder.menuItem = getItem(pos);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.customer_menu_item, null);
        }
//        ButterKnife.bind(this,convertView);
        holder.quantity = (TextView)convertView.findViewById(R.id.quantity);
        holder.menuText = (TextView)convertView.findViewById(R.id.customer_menu_item_name);



        quantityList = new int[menu.size()];
//        Log.d("menu", menu.size()+"");
//        holder.menuText.setText(menu.getItemName());
//        quantity.setText("0");

        holder.quantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder input = new AlertDialog.Builder(getContext());
                input.setTitle("Input");
                input.setCancelable(false);

                final EditText number = new EditText(getContext());
                number.setHint("What do you need to do?");

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
                userInput.show();                                                       //when the button is clicked view goes back to list fragment

            }
        });

//        quantity.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {}
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
//            @Override
//            public void afterTextChanged(Editable s) {
//                Log.d("quantity", quantity.getText().toString());
//                Log.d("size", menu.size()+"");
//                quantityList[pos] = Integer.parseInt(quantity.getText().toString());
//            }
//        });

        return convertView;
    }

    public static class FoodHolder{
        TextView quantity;
        TextView menuText;
        MenuItem menuItem;

    }

    public ArrayList<Integer> getQuantityList(){
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i<quantityList.length;i++) {
            res.add(quantityList[i]);
        }
        return res;
    }

}