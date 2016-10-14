package bill.cookapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class FragmentEditMenu extends Fragment {
    @BindView(R.id.cooksMenuListView) ListView cooksMenuListView;
    @BindView(R.id.addFood) Button addFood;
    ItemService itemService; // database service

    public FragmentEditMenu() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_edit_menu, container, false);
        ButterKnife.bind(this,v);
        // Construct the data source
        itemService = new ItemService(getContext());
        // get the data from database
        ArrayList<MenuItem> food = itemService.getAll();
        // Create the adapter to convert the array to views
        final CooksMenuListAdapter adapter = new CooksMenuListAdapter(this.getContext(), food);
        // Attach the adapter to a ListView
        cooksMenuListView.setAdapter(adapter);
        // set OnClickListener for addFood button. Add a food with default name "New Food"
        // and a default ingredient "New Ingredient"
        addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Integer> amount = new ArrayList<Integer>();
                amount.add(1);
                ArrayList<String> ingredient = new ArrayList<String>();
                ingredient.add("New Ingredient");
                MenuItem menuItem = new MenuItem("New Food", amount, ingredient);
                adapter.add(menuItem);
                itemService.addMenuItem(menuItem);
            }
        });
        return v;
    }
}
