package com.example.sydney.recipebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class ReadRecipe extends AppCompatActivity {
    DatabaseHandler db =  new DatabaseHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_recipe);

        Intent intent = getIntent();
        if(intent != null) {
            int recipePosition = intent.getIntExtra("RECIPE_POSTION", 0);
            final List<Recipes> recipes = db.getAllRecipes();
            final List<String> recipeNames = new ArrayList<>();
            for(Recipes rp : recipes) {
                recipeNames.add(rp.getName());
            }
        }
    }
}
