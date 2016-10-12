package bill.cookapp;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Ingredient extends Fragment {
    @BindView(R.id.ingredientList) ListView ingredientList;
    @BindView(R.id.addIngredient) Button addIng;
    @BindView(R.id.done) Button done;
    @BindView(R.id.foodName) EditText foodName;
    ItemService service;
    Ingredient_Adapter adapter;

//    private ArrayList<String> ingredients;

    public Ingredient() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ingredient, container, false);
        ButterKnife.bind(this, v);
        service = new ItemService(getContext());
        int pos = CooksMenuListAdapter.pos;
        MenuItem current = service.getAll().get(pos);
        foodName.setHint(current.getItemName());
        final long id = current.getId();
        final ArrayList<String> currentIng = current.getIngredients();
        adapter = new Ingredient_Adapter(getContext(),currentIng);
        ingredientList.setAdapter(adapter);
        addIng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder input = new AlertDialog.Builder(getContext());
                input.setTitle("Input");
                input.setCancelable(false);

                final EditText newIngredient = new EditText(getContext());
                newIngredient.setHint("What do you need to do?");

                input.setView(newIngredient);

                input.setPositiveButton("Create", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String userString = newIngredient.getText().toString();
                        adapter.addIngredient(userString);
                        adapter.notifyDataSetChanged();
                    }
                });

                input.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog userInput = input.create();
                userInput.show();                                                       //when the button is clicked view goes back to list fragment
            }
        });
        done.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                service.updateIngredients(id,adapter.getIngredients());
                if (!foodName.getText().toString().equals("")){
                    service.updateFoodName(id,foodName.getText().toString());
                }
                Fragment newFragment = new FragmentEditMenu();
                    MainActivity feeds = (MainActivity) getContext();
                    feeds.replaceFragment(newFragment);
                }

        });



//        ingredient_adapter = new Ingredient_Adapter(getActivity(),ingredients);
//        ingredientList.setAdapter(ingredient_adapter);
        return v;
    }


}
