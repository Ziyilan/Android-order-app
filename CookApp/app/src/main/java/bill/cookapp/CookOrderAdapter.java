package bill.cookapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zlan on 10/10/16.
 */
public class CookOrderAdapter extends ArrayAdapter<String> {


    private ArrayList<String> orderlist;

    public CookOrderAdapter(Context context, ArrayList<String> orders) {
        super(context, 0, orders);
        orderlist = orders;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final String order = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cook_order_template, parent, false);
        }

        // Return the completed view to render on screen
        return convertView;
    }

}
