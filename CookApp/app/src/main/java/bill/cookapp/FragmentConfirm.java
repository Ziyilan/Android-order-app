package bill.cookapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentConfirm extends Fragment {
    OrderService orderService;
    ArrayList<OrderItem> orderList;
    @BindView(R.id.OrderDetail) TextView orderDetail;

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

        //gets the order info the customer just put in for him to review
        OrderItem current = orderList.get(orderList.size()-1);
        ArrayList<Integer> quantityList = current.getQuantity();
        ArrayList<String> foodNameList = current.getFood();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i<foodNameList.size(); i++) {
            if (quantityList.get(i) != 0) {
                sb.append(foodNameList.get(i));
                sb.append(" : ");
                sb.append(quantityList.get(i));
                sb.append("\n");
            }
        }
        orderDetail.setText(sb.toString());

        return v;
    }
}
