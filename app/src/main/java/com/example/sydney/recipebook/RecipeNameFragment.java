package com.example.sydney.recipebook;

import android.app.ListFragment;
import android.graphics.Point;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sydney on 03/04/2017.
 */

public class RecipeNameFragment extends ListFragment {

    public static String[] recipeNamesArray;
    DatabaseHandler db;
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
        List<String> recipeNames = new ArrayList<>();

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

    }

    // Called when the user selects an item from the List
    @Override
    public void onListItemClick(ListView l, View v, int pos, long id) {

        RecipeDescriptionFragment mDescriptionsFragment = (RecipeDescriptionFragment) getFragmentManager()
                .findFragmentById(R.id.descriptions);
        mDescriptionsFragment.showDescriptionAtIndex(pos);

    }
}

