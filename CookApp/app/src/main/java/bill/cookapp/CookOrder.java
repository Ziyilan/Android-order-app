package bill.cookapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CookOrder extends Fragment {

    @BindView(R.id.cookOrderList) ListView ordertListView;
    OrderService orderService;

    private CookOrderAdapter adapter;
    private ArrayList<OrderItem> orderlist;

    public CookOrder() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cook_order, container, false);
        ButterKnife.bind(this, view);
        orderService = new OrderService(getContext());

        orderlist = orderService.getAll();
        adapter = new CookOrderAdapter(getActivity(),orderlist);
        ordertListView.setAdapter(adapter);
        return view;
    }


}
