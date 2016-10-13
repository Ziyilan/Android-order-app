package bill.cookapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class customer_menu extends Fragment {
    Fragment customer_menu = this;
    CustomerMenuAdapter adapter;

    public customer_menu() {
        // Required empty public constructor
    }

    @BindView(R.id.listView_container) ListView listView;
    @BindView(R.id.confirm) Button checkout;
    @BindView(R.id.customer_name) EditText customerName;
    @BindView(R.id.comment) EditText commentSec;

//    ListAdapter adapter;                //declares a list adapter called adapter
    ArrayList<MenuItem> menu;          //declares an array list called menu
    ItemService itemService;
    OrderService orderService;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_customer_menu, container, false);
        ButterKnife.bind(this, mainView);
        itemService = new ItemService(getContext());
        orderService = new OrderService(getContext());
        menu = itemService.getAll();

        adapter = new CustomerMenuAdapter(getActivity(), menu);             //applies the adapter



        listView.setAdapter(adapter);
//

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<Integer> quantityList = adapter.getQuantityList();
                ArrayList<MenuItem> foodList = itemService.getAll();
                ArrayList<String> foodOrdered = new ArrayList<String>();
                ArrayList<Integer> quantityOrdered = new ArrayList<Integer>();

                for (int i = 0; i < quantityList.size();i++){
                    if (quantityList.get(i) != 0){
                        foodOrdered.add(foodList.get(i).getItemName());
                        quantityOrdered.add(quantityList.get(i));
                    }
                }

                OrderItem orderItem = new OrderItem(customerName.getText().toString(),quantityOrdered,
                        foodOrdered, commentSec.getText().toString());
                orderService.addOrder(orderItem);



                FragmentManager manager;                                            //initializes manager as FragmentManager
                FragmentTransaction transaction;                                    //initializes transaction as FragmentTransaction

                manager = getFragmentManager();
                transaction = manager.beginTransaction();

                Fragment checkoutPage = new FragmentConfirm();

                transaction.replace(R.id.fragment_container, checkoutPage);
                transaction.addToBackStack(null).commit();

            }
        });

        return mainView;

    }
}
