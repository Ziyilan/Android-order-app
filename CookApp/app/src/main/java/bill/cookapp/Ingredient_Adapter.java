package bill.cookapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;


/**
 * Created by zlan on 10/10/16.
 */
// list adapter class for ingredient list
public class Ingredient_Adapter extends ArrayAdapter<String> {

    private ArrayList<String> ingredients;
    public Ingredient_Adapter(Context context, ArrayList<String> items) {
        super(context, 0, items);
        this.ingredients = items;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ingredient_item, parent, false);
        }
        ImageButton deleteButton = (ImageButton)convertView.findViewById(R.id.deleteIngredient);
        TextView listItem = (TextView)convertView.findViewById(R.id.ingredientName);
        listItem.setText(ingredients.get(position));
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingredients.remove(position); //remove the item
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    // add ingredient in the adapter
    public void addIngredient(String ing) {
        ingredients.add(ing);
        notifyDataSetChanged();
    }

    public ArrayList<String> getIngredients(){
        return ingredients;
    }
}