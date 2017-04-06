package com.example.sydney.recipebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

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

            TextView ingOne = (TextView) findViewById(R.id.txtIngOne);
            TextView quanOne = (TextView) findViewById(R.id.txtQuantOne);
            TextView stepOne = (TextView) findViewById(R.id.txtStepOne);

            int row = 5;
            int step = 1;
            for(Ingredients ingredient : recipeIngredients) {
                ingOne.setText(ingredient.getIngredients());
                quanOne.setText(ingredient.getQuantity());
                stepOne.setText(ingredient.getInstructions());

                TextView tv = new TextView(this);
                TextView tv2 = new TextView(this);

                tv.setText(ingredient.getIngredients());
                tv2.setText(ingredient.getQuantity());
                Log.d(":INGREDIENT: ", ingredient.getIngredients());

                GridLayoutManager.LayoutParams params1 = new GridLayoutManager.LayoutParams(row++, 0);
                GridLayoutManager.LayoutParams params2 = new GridLayoutManager.LayoutParams(row++, 1);

                gl.addView(tv, params1);
                gl.addView(tv2, params2);
                step++;
            }
        }
    }
}
