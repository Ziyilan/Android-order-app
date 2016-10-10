package bill.cookapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zlan on 10/10/16.
 */
public class Ingredient_Adapter extends ArrayAdapter<String> {
    @BindView(R.id.ingredientAmount) EditText ingredientAmount;
    @BindView(R.id.ingredientName) TextView ingredientName;

    private ArrayList<String> tasklist;

    public Ingredient_Adapter(Context context, ArrayList<String> tasks) {
        super(context, 0, tasks);
        tasklist = tasks;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
//        final String task = tasklist.get(position);
//        ingredientName.setText(task);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ingredient_item, parent, false);
        }
        ButterKnife.bind(this, convertView);

        // Return the completed view to render on screen
        return convertView;
    }



}