package com.example.sydney.recipebook;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class AddRecipe extends AppCompatActivity {
    String servesTxt;
    String cookTimeTxt;
    String recipeDescriptionTxt;
    String recipeNameTxt;
    String ingredientTxt;
    String quantityTxt;
    String stepTxt;
    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db =  new DatabaseHandler(this);
        //gather all the widgets up
        //BUTTONS:
        final Button btnSubmitIngredient = (Button) findViewById(R.id.btnAddIngredient);
        final Button btnSubmitRecipe = (Button) findViewById(R.id.btnAddRecipe);
        final Button btnFinished = (Button) findViewById(R.id.btnFinished);
        final ImageButton btnAddPicture = (ImageButton) findViewById(R.id.btnRecipePicture);

        //TEXT FIELDS INGREDIENTS
        final EditText ingredient = (EditText) findViewById(R.id.txtIngredient);
        final EditText quantity = (EditText) findViewById(R.id.txtQuantity);
        final EditText step = (EditText) findViewById(R.id.txtStep);

        //Ingredient default values
        final String ingredientDefault = "Ingredient (i.e Flour)";
        final String quantityDefault = "Quantity (i.e 1 cup)";
        final String stepDefault = "Step Instructions";

        //ingredient edit text clear on click
        ingredient.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean b) {
                if(ingredient.isFocusable()) {
                    if(ingredient.hasFocus()) {
                        ingredient.setText("");
                        ingredient.setTextColor(Color.parseColor("#000000"));
                    }
                    else if(!ingredient.hasFocus() && ingredient.getText().toString().equals("")){
                        ingredient.setTextColor(Color.parseColor("#cecece"));
                        ingredient.setText(ingredientDefault);
                    }
                }
            }
        });
        quantity.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean b) {
                if(quantity.isFocusable()) {
                    if(quantity.hasFocus()) {
                        quantity.setText("");
                        quantity.setTextColor(Color.parseColor("#000000"));
                    }
                    else if(!quantity.hasFocus() && quantity.getText().toString().equals("")){
                        quantity.setText(quantityDefault);
                        quantity.setTextColor(Color.parseColor("#cecece"));
                    }
                }
            }
        });
        step.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean b) {
                if(step.isFocusable()) {
                    if(step.hasFocus()) {
                        step.setText("");
                        step.setTextColor(Color.parseColor("#000000"));
                    }
                    else if(!step.hasFocus() && step.getText().toString().equals("")){
                        step.setText(stepDefault);
                        step.setTextColor(Color.parseColor("#cecece"));
                    }
                }
            }
        });

        //TEXT FIELDS RECIPE
        final EditText recipeName = (EditText) findViewById(R.id.txtRecipeName);
        final EditText recipeDescription = (EditText) findViewById(R.id.txtDescription);
        final EditText cookTime = (EditText) findViewById(R.id.txtPrepTime);
        final EditText serves = (EditText) findViewById(R.id.txtServingSize);

        //recipe default text field values
        final String nameDefault = "Recipe Name (i.e Cake)";
        final String descriptionDefault = "Recipe Description";
        final String cookTimeDefault = "Cook Time (Min.)";
        final String servesDefault = "Servings";

        //recipe on click edit text clear, on exit put default back (unless user has entered data)
        recipeName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean b) {
                if(recipeName.isFocusable()) {
                    if(recipeName.hasFocus()) {
                        recipeName.setText("");
                        recipeName.setTextColor(Color.parseColor("#000000"));
                    }
                    else if(!recipeName.hasFocus() && recipeName.getText().toString().equals("")){
                        recipeName.setText(nameDefault);
                        recipeName.setTextColor(Color.parseColor("#cecece"));
                    }
                }
            }
        });
        recipeDescription.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean b) {
                if(recipeDescription.isFocusable()) {
                    if(recipeDescription.hasFocus()) {
                        recipeDescription.setText("");
                        recipeDescription.setTextColor(Color.parseColor("#000000"));
                    }
                    else if(!recipeDescription.hasFocus() && recipeDescription.getText().toString().equals("")){
                        recipeDescription.setTextColor(Color.parseColor("#cecece"));
                        recipeDescription.setText(descriptionDefault);
                    }
                }
            }
        });
        cookTime.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean b) {
                if(cookTime.isFocusable()) {
                    if(cookTime.hasFocus()) {
                        cookTime.setText("");
                        cookTime.setTextColor(Color.parseColor("#000000"));
                    }
                    else if(!cookTime.hasFocus() && cookTime.getText().toString().equals("")){
                        cookTime.setTextColor(Color.parseColor("#cecece"));
                        cookTime.setText(cookTimeDefault);
                    }
                }
            }
        });
        serves.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View v, boolean b) {
                if(serves.isFocusable()) {
                    if(serves.hasFocus()) {
                        serves.setText("");
                        serves.setTextColor(Color.parseColor("#000000"));
                    }
                    else if(!serves.hasFocus() && serves.getText().toString().equals("")){
                        serves.setTextColor(Color.parseColor("#cecece"));
                        serves.setText(servesDefault);
                    }
                }
            }
        });

        //TEXT VIEWS
        final TextView formHelp = (TextView) findViewById(R.id.FillOutHelp);

        btnSubmitRecipe.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean stop = false;
                //get field text
                servesTxt = serves.getText().toString();
                cookTimeTxt = cookTime.getText().toString();
                recipeDescriptionTxt = recipeDescription.getText().toString();
                recipeNameTxt = recipeName.getText().toString();
                try {
                    int cookTime = Integer.parseInt(cookTimeTxt);
                    try {
                        int servingSize = Integer.parseInt(servesTxt);
                    }
                    catch (Exception e) {
                        String x = "Serving size should be a number!";
                        formHelp.setText(x);
                        servesTxt = servesDefault;
                        serves.setTextColor(Color.parseColor("#cecece"));
                        serves.setText(servesDefault);
                        stop = true;
                    }
                }
                catch (Exception e) {
                    String x = "Cooking time should be a number!";
                    formHelp.setText(x);
                    cookTimeTxt = cookTimeDefault;
                    cookTime.setTextColor(Color.parseColor("#cecece"));
                    cookTime.setText(cookTimeDefault);
                    stop = true;
                }
                //make sure fields are filled in with information other than default
                if(!servesTxt.equals("") && !servesTxt.equals(servesDefault) &&
                        !cookTimeTxt.equals("") && !cookTimeTxt.equals(cookTimeDefault) &&
                        !recipeDescriptionTxt.equals("") && !recipeDescriptionTxt.equals(descriptionDefault)
                        && !recipeNameTxt.equals("") && !recipeNameTxt.equals(nameDefault) && !stop)
                {
                    //put recipe info into database
                    db.addRecipe(new Recipes(recipeNameTxt, recipeDescriptionTxt, Integer.parseInt(cookTimeTxt), Integer.parseInt(servesTxt)));

                    String x = "Add Ingredients in order of the step that they are used in and " +
                            "include their step description.";
                    formHelp.setText(x);
                    //disable editability of recipe section
                    recipeName.setFocusable(false);
                    recipeDescription.setFocusable(false);
                    cookTime.setFocusable(false);
                    serves.setFocusable(false);

                    //show ingredient fields
                    btnSubmitRecipe.setVisibility(View.INVISIBLE);
                    btnSubmitIngredient.setVisibility(View.VISIBLE);
                    ingredient.setVisibility(View.VISIBLE);
                    quantity.setVisibility(View.VISIBLE);
                    step.setVisibility(View.VISIBLE);

                }
                else if (!stop){
                    String x = "You need to fill out all the fields! Including: Name, Cook Time, " +
                            "Description and servings! Approximations are okay.";
                    formHelp.setText(x);
                }
                else {
                    //we've skipped everything we needed to skip, we can set back to true now.
                    stop = true;
                }
            }
        });

        btnSubmitIngredient.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //get field text
                ingredientTxt = ingredient.getText().toString();
                quantityTxt = quantity.getText().toString();
                stepTxt = step.getText().toString();

                if(!ingredientTxt.equals("") && !ingredientTxt.equals(ingredientDefault) &&
                        !quantityTxt.equals("") && !quantityTxt.equals(quantityDefault) &&
                        !stepTxt.equals("") && !stepTxt.equals(stepDefault))
                {

                    //put stuff in a database
                    db.addIngredient(new Ingredients(ingredientTxt, quantityTxt, stepTxt, recipeNameTxt));
                    //make finished button available
                    if (btnFinished.getVisibility() == View.INVISIBLE) {
                        btnFinished.setVisibility(View.VISIBLE);
                    }

                    //reset contents to prepare for next entry
                    ingredient.setTextColor(Color.parseColor("#cecece"));
                    quantity.setTextColor(Color.parseColor("#cecece"));
                    step.setTextColor(Color.parseColor("#cecece"));
                    ingredient.setText(ingredientDefault);
                    quantity.setText(quantityDefault);
                    step.setText(stepDefault);
                }
                else {
                    String x = "You need to fill out all the fields! Including: Ingredient, Quantity" +
                            "and Step information. Your recipe must have at least one ingredient.";
                    formHelp.setText(x);
                }
            }
        });

        btnFinished.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("Reading: ", "Reading recipes and ingredients..");
                List<Recipes> recipes = db.getAllRecipes();
                List<Ingredients> ingredients = db.getAllIngredients();

                for (Recipes rp : recipes) {
                    String log = "Recipe Name: " + rp.getName();
                    Log.d("Recipe ", log);
                }
                for (Ingredients ing : ingredients) {
                    String log = "The ingredient: " + ing.getIngredients() + " is associated with recipe: " + ing.getRecipe_name();
                    Log.d("Ingredient: ", log);
                }
                db.close();
                startActivity(new Intent(AddRecipe.this, RecipeView.class));
            }
        });

        btnAddPicture.setOnClickListener(new View.OnClickListener()  {
            public void onClick(View v) {
                //TODO: Take in an image and save it to the database?
            }
        });
    }
}
