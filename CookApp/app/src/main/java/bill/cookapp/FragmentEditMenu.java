package bill.cookapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
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

    ItemService itemService;

    public FragmentEditMenu() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_edit_menu, container, false);
        ButterKnife.bind(this,v);
        // Construct the data source
        itemService = new ItemService(getContext());
        ArrayList<MenuItem> food = itemService.getAll();

//        ArrayList<MenuItem> arrayOfItems = new ArrayList<>();
        // Create the adapter to convert the array to views
        final CooksMenuListAdapter adapter = new CooksMenuListAdapter(this.getContext(), food);
        // Attach the adapter to a ListView
        cooksMenuListView.setAdapter(adapter);
        addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<Integer> amount = new ArrayList<Integer>();
                amount.add(1);
                ArrayList<String> ingredient = new ArrayList<String>();
                ingredient.add("stuff");

                MenuItem menuItem = new MenuItem("Food", amount, ingredient);
//                CooksMenuItem cooksMenuItem = new CooksMenuItem("");
                adapter.add(menuItem);

                itemService.addMenuItem(menuItem);
            }
        });
        setHasOptionsMenu(true);


        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_edit_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
        android.view.MenuItem item = menu.findItem(R.id.new_food);
        item.setOnMenuItemClickListener(new android.view.MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(android.view.MenuItem item) {
                CooksMenuItem cooksMenuItem = new CooksMenuItem("");
                return true;
            }
        });
    }



}
