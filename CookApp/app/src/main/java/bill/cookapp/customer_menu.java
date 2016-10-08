package bill.cookapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
    Fragment customer_menu = this;


    public customer_menu() {
        // Required empty public constructor
    }

    @BindView(R.id.listView_container) ListView listView;
    @BindView(R.id.checkout) Button checkout;

//    ListAdapter adapter;                //declares a list adapter called adapter
    ArrayList<MenuItem> menu;          //declares an array list called menu
    DBService service;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_list_adapter, container, false);
        service = new DBService(getContext());
        menu = service.getAll();

//        adapter = new ListAdapter(getActivity(), menu);             //applies the adapter

        ButterKnife.bind(this, mainView);

//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                ListAdapter.dialogMaker(menu, view, menu.get(position), adapter);
//                adapter.notifyDataSetChanged();
//            }
//        });

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager manager;                                            //initializes manager as FragmentManager
                FragmentTransaction transaction;                                    //initializes transaction as FragmentTransaction

                manager = getFragmentManager();
                transaction = manager.beginTransaction();

                transaction.replace(R.id.fragment_container, customer_menu);
                transaction.addToBackStack(null).commit();

            }
        });

        return mainView;

    }
}
