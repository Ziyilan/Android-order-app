package bill.cookapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FragmentConfirm extends Fragment {
    OrderService orderService;
    ArrayList<OrderItem> orderList;
    ArrayAdapter<String> orderFoodNameAdapter;
    ArrayAdapter<Integer> orderFoodQuantityAdapter;
    @BindView(R.id.orderFoodName) ListView orderFoodName;
    @BindView(R.id.orderFoodQuantity) ListView orderFoodQuantity;

    public FragmentConfirm() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_checkout, container, false);
        ButterKnife.bind(this,v);
        orderService = new OrderService(getContext());
        orderList = orderService.getAll();
        OrderItem current = orderList.get(orderList.size()-1);
        ArrayList<Integer> quantityList = current.getQuantity();
        ArrayList<String> foodNameList = current.getFood();
        orderFoodNameAdapter = new ArrayAdapter<String>(getContext(),R.layout.fragment_checkout,foodNameList);
        orderFoodQuantityAdapter = new ArrayAdapter<Integer>(getContext(),R.layout.fragment_checkout,quantityList);

        orderFoodName.setAdapter(orderFoodNameAdapter);
        orderFoodQuantity.setAdapter(orderFoodQuantityAdapter);




        return v;
    }

}
