package bill.cookapp;

import android.content.Context;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
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
    @BindView(R.id.customer_menu_item_name) TextView itemName;
    @BindView(R.id.quantity) EditText quantity;
    ArrayList<Integer> quantityList;

    public CustomerMenuAdapter(Context context, ArrayList<MenuItem> menuItems) {
        super(context, 0, menuItems);
        menu = menuItems;
        quantityList = new ArrayList<Integer>(menu.size());
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent) {
        final MenuItem item = getItem(pos);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.customer_menu_item, null);
        }
        ButterKnife.bind(this,convertView);
        itemName.setText(item.getItemName());

        itemName.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {
                quantityList.set(pos,Integer.parseInt(quantity.getText().toString()));
            }
        });

        return convertView;
    }

    public ArrayList<Integer> getQuantityList(){
        return quantityList;
    }

}