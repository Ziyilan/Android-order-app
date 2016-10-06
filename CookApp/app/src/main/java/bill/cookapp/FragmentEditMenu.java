package bill.cookapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class FragmentEditMenu extends Fragment {
    @BindView(R.id.cooksMenuListView) ListView cooksMenuListView;

    public FragmentEditMenu() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_edit_menu, container, false);
        ButterKnife.bind(this,v);
        // Construct the data source
        ArrayList<CooksMenuItem> arrayOfItems = new ArrayList<>();
        // Create the adapter to convert the array to views
        final CooksMenuListAdapter adapter = new CooksMenuListAdapter(this.getContext(), arrayOfItems);
        // Attach the adapter to a ListVie
        cooksMenuListView.setAdapter(adapter);

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_edit_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);


    }
}
