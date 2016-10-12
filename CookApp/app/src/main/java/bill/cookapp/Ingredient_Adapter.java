package bill.cookapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zlan on 10/10/16.
 */
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
//        convertView.setTag(holder);
//        setupItem(holder);
        // Return the completed view to render on screen
        return convertView;
    }
    // set the TextView
//    private void setupItem(MenuItemHolder holder){
//        holder.listItem.setText(holder.menuItem.getItemName());
//    }
//    public static class MenuItemHolder {
//        TextView listItem;
//        ImageButton deleteButton;
//
//
//    }
    private void switchFragment(Fragment newFragment) {
        if (this.getContext() == null)
            return;
        if (this.getContext() instanceof MainActivity) {
            MainActivity feeds = (MainActivity) this.getContext();
            feeds.replaceFragment(newFragment);
        }
    }

    public void addIngredient(String ing) {
        ingredients.add(ing);
        notifyDataSetChanged();
    }

    public ArrayList<String> getIngredients(){
        return ingredients;
    }


}