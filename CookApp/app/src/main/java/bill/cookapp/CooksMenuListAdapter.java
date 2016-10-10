package bill.cookapp;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by DHZ_Bill on 10/6/16.
 */
public class CooksMenuListAdapter extends ArrayAdapter<CooksMenuItem>{

    public CooksMenuListAdapter(Context context, ArrayList<CooksMenuItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final CooksMenuItemHolder holder = new CooksMenuItemHolder();
        //myDb = new DatabaseHelper(this.getContext());
        // Get the data item for this position
        holder.cooksMenuItem = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cooks_menu_item, parent, false);
        }
        // I didn't figure out how to implement ButterKnife here.
        // Because all elements here is a property of the holder class
        holder.editButton = (ImageButton)convertView.findViewById(R.id.menuItemEditButton);
        holder.editButton.setTag(holder.cooksMenuItem);
        holder.deleteButton = (ImageButton)convertView.findViewById(R.id.menuItemDeleteButton);
        holder.listItem = (TextView)convertView.findViewById(R.id.menuItemName);
//         Create onClickListener for edit Button
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
//                builder.setTitle("Add new Food");
//
//                // Set up the input
//                final EditText input = new EditText(v.getContext());
//                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
//                builder.setView(input);
//
//                // Set up the buttons
//                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        String m_Text = input.getText().toString();
//                        // change the text in the TextView and update the database
//                        holder.cooksMenuItem.setItemName(m_Text);
//                        //System.out.println("m_Text: " + m_Text + " cooksMenuItem text: " + holder.cooksMenuItem.getItemName() );
//                        holder.listItem.setText(m_Text);
//                        //UpdateData(holder.cooksMenuItem);
//                    }
//                });
//                // Close the dialog if pressing on Cancel button
//                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                });
//                notifyDataSetChanged();
//                builder.show();
                System.out.println("Hi");
                Fragment newFragment = new Ingredient();
                if (newFragment != null)
                    switchFragment(newFragment);
            }
        });

        // Create onClickListener for delete Button
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Hello");
                remove(holder.cooksMenuItem); //remove the item
                //DeleteData(holder.cooksMenuItem); //delete the item from the database
                notifyDataSetChanged();
            }
        });
        convertView.setTag(holder);
        setupItem(holder);
        // Return the completed view to render on screen
        return convertView;
    }
    // set the TextView
    private void setupItem(CooksMenuItemHolder holder){
        holder.listItem.setText(holder.cooksMenuItem.getItemName());
    }
    public static class CooksMenuItemHolder {
        ImageButton editButton;
        TextView listItem;
        ImageButton deleteButton;
        CooksMenuItem cooksMenuItem;

    }
    private void switchFragment(Fragment newFragment) {
        if (this.getContext() == null)
            return;
        if (this.getContext() instanceof MainActivity) {
            MainActivity feeds = (MainActivity) this.getContext();
            feeds.replaceFragment(newFragment);
        }
    }

//    // update database
//    public void UpdateData(CooksMenuItem cooksMenuItem){
//        myDb.updateData(cooksMenuItem);
//    }
//    // delete from database
//    public void DeleteData(CooksMenuItem cooksMenuItem){
//        myDb.deleteData(cooksMenuItem);
//    }
}
