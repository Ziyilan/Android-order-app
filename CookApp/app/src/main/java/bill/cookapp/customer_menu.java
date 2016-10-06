package bill.cookapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class customer_menu extends Fragment {


    public customer_menu() {
        // Required empty public constructor
    }

    @BindView(R.id.listView_container) ListView listView;
    @BindView(R.id.checkout) Button button;

    ListAdapter adapter;                //declares a list adapter called adapter
    ArrayList<MenuItem> menu;          //declares an array list called menu
    DBService service;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_list_adapter, container, false);
        service = new DBService(getContext());
        menu = service.getAll();

        adapter = new ListAdapter(getActivity(), menu);             //applies the adapter

        ButterKnife.bind(this, mainView);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                adapter.dialogMaker();
                ListAdapter.dialogMaker(menu, view, menu.get(position), adapter);
                adapter.notifyDataSetChanged();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder input = new AlertDialog.Builder(getContext());
                input.setTitle("Input");
                input.setCancelable(false);

                final EditText food = new EditText(getContext());
                food.setHint("What do you need to do?");

                input.setView(food);

                input.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String userString = food.getText().toString();
                        MenuItem newItem = new MenuItem(userString, 0);
//                        service.addToDo(button);
//                        adapter.setItems(service.getAll());
                    }
                });

                input.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog userInput = input.create();
                userInput.show();                                                       //when the button is clicked view goes back to list fragment
            }
        });

        return mainView;

    }
}
