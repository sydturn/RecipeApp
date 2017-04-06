package com.example.sydney.recipebook;

/**
 * Created by Sydney on 03/04/2017.
 */

import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecipeDescriptionFragment extends Fragment {

    public static String[] recipeDescriptionsArray;
    private TextView RecipeDescription = null;
    DatabaseHandler db;
    public RecipeDescriptionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_descriptions, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        db =  new DatabaseHandler(getActivity());
        List<Recipes> recipes = db.getAllRecipes();
        List<String> recipeDescription = new ArrayList<>();

        for(Recipes rp : recipes) {
            recipeDescription.add(rp.getDescription());
        }
        recipeDescriptionsArray = new String[recipeDescription.size()];
        for(int i = 0; i < recipeDescriptionsArray.length; i++) {
            recipeDescriptionsArray[i] = recipeDescription.get(i);
        }
        RecipeDescription = (TextView) getActivity().findViewById(R.id.descriptionView);

        Button btnMake = (Button) getView().findViewById(R.id.btnMake);
        btnMake.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                RecipeNameFragment rnf = (RecipeNameFragment) fm.findFragmentById(R.id.titles);
                rnf.openRecipe();
            }
        });
    }

    // Show the course description at position newIndex
    public void showDescriptionAtIndex(int newIndex) {
        if (newIndex < 0 || newIndex >= recipeDescriptionsArray.length)
            return;

        RecipeDescription.setText(recipeDescriptionsArray[newIndex]);
    }
}
