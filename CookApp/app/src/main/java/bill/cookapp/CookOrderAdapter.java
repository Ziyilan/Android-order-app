package bill.cookapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zlan on 10/10/16.
 */
public class CookOrderAdapter extends ArrayAdapter<OrderItem> {
    @BindView(R.id.CustomerName) TextView custName;
    @BindView(R.id.orderComplete) Button done;

    OrderService orderService;

    private ArrayList<OrderItem> orderlist;

    public CookOrderAdapter(Context context, ArrayList<OrderItem> orders) {
        super(context, 0, orders);
        orderlist = orders;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final OrderItem order = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cook_order_template, parent, false);
        }
        ButterKnife.bind(this,convertView);
        orderService = new OrderService(getContext());

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderItem item = orderlist.get(position);
                orderService.deleteOrderItem(item);
                orderlist.remove(position);
                notifyDataSetChanged();

            }
        });

        String orderContent = "";
        for (int i = 0; i < order.getFood().size();i++){
            orderContent += order.getFood().get(i) + ": " + order.getQuantity().get(i) + " ";
        }
        custName.setText(order.getCustomer_name()+ "  "  + orderContent  + order.getComment());

        // Return the completed view to render on screen
        return convertView;
    }

}
