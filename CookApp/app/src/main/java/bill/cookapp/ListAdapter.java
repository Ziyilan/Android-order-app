package bill.cookapp;

import android.content.Context;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
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

public class ListAdapter extends ArrayAdapter<MenuItem> {
    ListAdapter adapter = this;

    private ArrayList<MenuItem> menu;
    @BindView(R.id.menuItemDeleteButton) Button delete;
    @BindView(R.id.menuItemName) TextView textView;
    @BindView(R.id.checkbox) CheckBox checkBox;

    public ListAdapter(Context context, ArrayList<MenuItem> menuItems) {
        super(context, 0, menuItems);
        menu = menuItems;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent) {
        final MenuItem item = getItem(pos);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cooks_menu_item, null);
        }

        ButterKnife.bind(this,convertView);
        textView.setText(item.getItemName());                                     //sets text in the textView to the string at the position
//        checkBox.setChecked(item.getStatus() == 1);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View convertView) {                             //when textView is clicked, a dialog box is generated using the function below
//                dialogMaker(menu,textView, item, adapter);
//            }
//        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu.remove(item);
                notifyDataSetChanged();                                          //notifies the adapter that the array list has been modified so that it will regenerate the view

            }
        });


//        checkBox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                item.setStatus(((CheckBox) v).isChecked() ? 1 : 0);
//            }
//        });

        return convertView;


    }



    /**
     * creates an AlertDialog that takes in a TextView in order to set the user
     * input as the text for the TextView
     */
    public static void dialogMaker(final ArrayList<MenuItem> al, View v, final MenuItem menuItem,
                                   final ArrayAdapter a) {

//        final DBService service = new DBService(v.getContext());

        AlertDialog.Builder input = new AlertDialog.Builder(v.getContext());
        input.setTitle("Input");
        input.setCancelable(false);

        final EditText item = new EditText(v.getContext());
        item.setHint("What do you need to do?");

        input.setView(item);
        item.setText(menuItem.getItemName()); // This makes it so when you click a to-do the edittext is pre-filled.

        input.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                String userString = item.getText().toString();
                menuItem.setItemName(userString);
//                service.updateToDo(menuItem);
                a.notifyDataSetChanged();
            }
        });

        input.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog userInput = input.create();
        userInput.show();
    }

    public void setItems(ArrayList<MenuItem> newList) {
        menu.clear();
        menu.addAll(newList);
        notifyDataSetChanged();
    }
}