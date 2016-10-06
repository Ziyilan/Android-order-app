package bill.cookapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

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
        // transaction.commit();
        cookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Switch from MainActivityFragment to Settings.
                // I learned from the code here:
                // http://stackoverflow.com/questions/7793576/switching-between-fragment-view
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
                // Switch from MainActivityFragment to Settings.
                // I learned from the code here:
                // http://stackoverflow.com/questions/7793576/switching-between-fragment-view
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
