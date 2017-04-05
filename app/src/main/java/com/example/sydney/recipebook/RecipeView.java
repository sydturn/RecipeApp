package com.example.sydney.recipebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class RecipeView extends AppCompatActivity {
    DatabaseHandler db =  new DatabaseHandler(this);
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (item.getItemId()) {
            case R.id.clear:
                db.emptyIngredients();
                db.emptyRecipes();
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                break;
            case R.id.create:
                db.close();
                startActivity(new Intent(RecipeView.this, AddRecipe.class));
                break;

        }
        return false;
    }

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_view);
    }
}
