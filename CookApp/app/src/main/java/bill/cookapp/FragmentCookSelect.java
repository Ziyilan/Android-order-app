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
public class FragmentCookSelect extends Fragment {
    @BindView(R.id.edit_menu) Button editMenuButton;
    @BindView(R.id.view_orders) Button viewOrdersButton;

    public FragmentCookSelect() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cook_select, container, false);
        ButterKnife.bind(this, v);

        editMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new FragmentEditMenu();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, fragment, "EditMenu");
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        viewOrdersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Switch to orders fragment

            }
        });

        return v;
    }

}
