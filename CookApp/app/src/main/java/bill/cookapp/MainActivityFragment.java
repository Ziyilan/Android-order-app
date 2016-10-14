package bill.cookapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    @BindView(R.id.cook) Button cookButton;
    @BindView(R.id.customer) Button customerButton;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, v);
        cookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Switch from MainActivityFragment to FragmentCookSelect.
                Fragment fragment = new FragmentCookSelect();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, fragment, "CookSelect");
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        customerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Switch from MainActivityFragment to FragmentCustomerMenu
                Fragment fragment = new customer_menu();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, fragment, "CustomerMenu");
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return v;
    }


}
