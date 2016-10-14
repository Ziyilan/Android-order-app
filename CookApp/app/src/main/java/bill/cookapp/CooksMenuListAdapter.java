package bill.cookapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by DHZ_Bill on 10/6/16.
 */
public class CooksMenuListAdapter extends ArrayAdapter<MenuItem>{
    ItemService service; // create new database service
    public static int pos;

    public CooksMenuListAdapter(Context context, ArrayList<MenuItem> items) {
        super(context, 0, items);
        service = new ItemService(context);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final MenuItemHolder holder = new MenuItemHolder();
        //myDb = new DatabaseHelper(this.getContext());
        // Get the data item for this position
        holder.menuItem = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cooks_menu_item, parent, false);
        }
        // I didn't figure out how to implement ButterKnife here.
        // Because all elements here is a property of the holder class
        holder.editButton = (ImageButton)convertView.findViewById(R.id.menuItemEditButton);
        holder.editButton.setTag(holder.menuItem);
        holder.deleteButton = (ImageButton)convertView.findViewById(R.id.menuItemDeleteButton);
        holder.listItem = (TextView)convertView.findViewById(R.id.menuItemName);
        // create onClickListener for edit Button (goes to Fragment Ingredient
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new Ingredient();
                pos = position;
                if (newFragment != null)
                    switchFragment(newFragment);
            }
        });

        // Create onClickListener for delete Button
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(holder.menuItem); //remove the item from the adapter
                service.deleteMenuItem(holder.menuItem); //delete the item from the database
                notifyDataSetChanged();
            }
        });
        convertView.setTag(holder);
        setupItem(holder);
        // Return the completed view to render on screen
        return convertView;
    }
    // set the TextView
    private void setupItem(MenuItemHolder holder){
        holder.listItem.setText(holder.menuItem.getItemName());
    }
    public static class MenuItemHolder {
        ImageButton editButton;
        TextView listItem;
        ImageButton deleteButton;
        MenuItem menuItem;
    }
    // A method to switch between fragments
    private void switchFragment(Fragment newFragment) {
        if (this.getContext() == null)
            return;
        if (this.getContext() instanceof MainActivity) {
            MainActivity feeds = (MainActivity) this.getContext();
            feeds.replaceFragment(newFragment);
        }
    }

}
