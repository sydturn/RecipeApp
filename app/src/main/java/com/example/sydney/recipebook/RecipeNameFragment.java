package com.example.sydney.recipebook;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sydney on 03/04/2017.
 */

public class RecipeNameFragment extends ListFragment {

    public static String[] recipeNamesArray;
    DatabaseHandler db;
//    Recipes currentSelection;
//    Button btnMake = (Button) getView().findViewById(R.id.btnMake);
//    Button btnDelete = (Button) getView().findViewById(R.id.btnDelete);
    public RecipeNameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);

        db =  new DatabaseHandler(getActivity());
        List<Recipes> recipes = db.getAllRecipes();
        final List<String> recipeNames = new ArrayList<>();

        for(Recipes rp : recipes) {
            recipeNames.add(rp.getName());
        }
        recipeNamesArray = new String[recipeNames.size()];
        for(int i = 0; i < recipeNamesArray.length; i++) {
            recipeNamesArray[i] = recipeNames.get(i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                recipeNamesArray);
        setListAdapter(adapter);

//        btnDelete.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                db.deleteRecipe(currentSelection);
//                recipeNames.remove(currentSelection.getName());
//                boolean found = false;
//                for(int i = 0; i < recipeNamesArray.length; i++) {
//                    if(recipeNamesArray[i].equals(currentSelection.getName())) {
//                        found = true;
//                        recipeNamesArray[i] = null;
//                    }
//                }
//                if(found) {
//                    int counter = 0;
//                    String[] temp = new String[recipeNamesArray.length - 1];
//                    for (int i = 0; i < recipeNamesArray.length; i++) {
//                        if(recipeNamesArray[i] != null) {
//                            temp[counter] = recipeNamesArray[i];
//                            counter++;
//                        }
//                    }
//                    recipeNamesArray = new String[temp.length];
//                    for(int j = 0; j < temp.length; j++) {
//                        recipeNamesArray[j] = temp[j];
//                    }
//                }
//                else {
//                    Toast.makeText(getActivity(), "Select something to delete!", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//        btnMake.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//
//            }
//        });

    }

    // Called when the user selects an item from the List
    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {

        RecipeDescriptionFragment mDescriptionsFragment = (RecipeDescriptionFragment) getFragmentManager()
                .findFragmentById(R.id.descriptions);
        mDescriptionsFragment.showDescriptionAtIndex(pos);
        //currentSelection = recipes.get(pos);

    }
}

