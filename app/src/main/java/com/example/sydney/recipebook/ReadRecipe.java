package com.example.sydney.recipebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

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
            Recipes currentRecipe = recipes.get(recipePosition);
            TextView recipeName = (TextView) findViewById(R.id.txtRecipeName);
            TextView recipeServings = (TextView) findViewById(R.id.txtServing);
            TextView recipeCookTime = (TextView) findViewById(R.id.txtCookTime);
            TextView recipeDescription = (TextView) findViewById(R.id.txtDescription);

            recipeName.setText(currentRecipe.getName());
            recipeServings.setText(Integer.toString(currentRecipe.getNumOfServings()));
            recipeCookTime.setText(Integer.toString(currentRecipe.getPrepTimeMinutes()));
            recipeDescription.setText(currentRecipe.getDescription());

            //populate ingredient list with recipes ingredients
            List<Ingredients> recipeIngredients = db.getRecipeIngredients(currentRecipe.getName());

            GridLayout gl = (GridLayout) findViewById(R.id.gridLayout);
            LinearLayout ll = (LinearLayout) findViewById(R.id.StepsLayout);
            gl.setRowCount(recipeIngredients.size() + 3);
            int step = 1;

            for(Ingredients ingredient : recipeIngredients) {
                TextView tv = new TextView(this);
                TextView tv2 = new TextView(this);
                TextView tv3 = new TextView(this);

                tv.setText(ingredient.getIngredients());
                tv2.setText(ingredient.getQuantity());
                tv3.setText(step + " " + ingredient.getInstructions());

                gl.addView(tv2);
                gl.addView(tv);
                ll.addView(tv3);
                step++;
            }
        }
    }
}
