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
    @BindView(R.id.customer_menu_item_name) TextView itemName;
    @BindView(R.id.quantity) EditText quantity;
    int[] quantityList;

    public CustomerMenuAdapter(Context context, ArrayList<MenuItem> menuItems) {
        super(context, 0, menuItems);
        menu = menuItems;

    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent) {
        final MenuItem item = getItem(pos);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.customer_menu_item, null);
        }
        ButterKnife.bind(this,convertView);
        quantityList = new int[menu.size()];
        Log.d("menu", menu.size()+"");
        itemName.setText(item.getItemName());
        quantity.setText("0");

        quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {
                Log.d("quantity", quantity.getText().toString());
                Log.d("size", menu.size()+"");
                quantityList[pos] = Integer.parseInt(quantity.getText().toString());
            }
        });

        return convertView;
    }


    public ArrayList<Integer> getQuantityList(){
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i<quantityList.length;i++) {
            res.add(quantityList[i]);
        }
        return res;
    }

}