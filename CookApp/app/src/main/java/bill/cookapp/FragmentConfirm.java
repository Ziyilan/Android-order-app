package bill.cookapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FragmentConfirm extends Fragment {
    OrderService orderService;
    ArrayList<OrderItem> orderList;
    ArrayAdapter<String> orderFoodNameAdapter;
    ArrayAdapter<Integer> orderFoodQuantityAdapter;
    @BindView(R.id.OrderDetail) TextView orderDetail;
//    @BindView(R.id.orderFoodQuantity) ListView orderFoodQuantity;

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
        Log.d("error1", current.getCustomer_name());
        Log.d("error", foodNameList.toString());

        StringBuilder sb = new StringBuilder();

        Log.d("foodlist",foodNameList.get(0));
        for (int i = 0; i<2; i++) {
//            if (quantityList.get(i) != 0) {
                sb.append(foodNameList.get(i));
                sb.append(" : ");
                sb.append(quantityList.get(i));
                sb.append("\n");
//            }
        }
        Log.d("error", sb.toString());
        orderDetail.setText(sb.toString());




        return v;
    }

}
