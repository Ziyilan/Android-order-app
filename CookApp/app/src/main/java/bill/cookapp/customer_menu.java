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
    CustomerMenuAdapter adapter;

    public customer_menu() {
        // Required empty public constructor
    }

    @BindView(R.id.listView_container) ListView listView;
    @BindView(R.id.confirm) Button checkout;
    @BindView(R.id.customer_name) EditText customerName;
    @BindView(R.id.comment) EditText commentSec;

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

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //saves information to database only when checkout is pressed

                ArrayList<Integer> quantityList = adapter.getQuantityList();    //sets quantity list using a function defined in adapter
                ArrayList<MenuItem> foodList = itemService.getAll();            //gets the food from the item SQL
                ArrayList<String> foodOrdered = new ArrayList<String>();
                ArrayList<Integer> quantityOrdered = new ArrayList<Integer>();

                for (int i = 0; i < quantityList.size();i++){           //filters out the menu items that weren't ordered
                    if (quantityList.get(i) != 0){
                        foodOrdered.add(foodList.get(i).getItemName());
                        quantityOrdered.add(quantityList.get(i));
                    }
                }

                OrderItem orderItem = new OrderItem(customerName.getText().toString(),quantityOrdered,
                        foodOrdered, commentSec.getText().toString());
                orderService.addOrder(orderItem);

                //when checkout button is pressed goes to the checkout page
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
