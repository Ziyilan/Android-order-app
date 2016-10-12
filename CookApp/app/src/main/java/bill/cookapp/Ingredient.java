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


public class Ingredient extends Fragment {
    @BindView(R.id.ingredientList) ListView ingredientList;

    private Ingredient_Adapter ingredient_adapter;
    private ArrayList<String> ingredients;

    public Ingredient() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ingredient, container, false);
        ButterKnife.bind(this, v);
        ingredients = new ArrayList<String>();
        ingredients.add("haha");
        ingredients.add("hehe");
        ingredient_adapter = new Ingredient_Adapter(getActivity(),ingredients);
        ingredientList.setAdapter(ingredient_adapter);
        return v;
    }


}
