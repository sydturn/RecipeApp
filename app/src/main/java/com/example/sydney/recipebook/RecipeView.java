package com.example.sydney.recipebook;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import static android.R.attr.button;
import static android.R.id.list;

public class RecipeView extends ActionBarActivity {
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

                AlertDialog.Builder confirmDeleteDialogBuilder = new AlertDialog.Builder(this);
                confirmDeleteDialogBuilder.setTitle("Remove Recipes?");
                confirmDeleteDialogBuilder.setMessage("Are you sure you want to delete all your recipes?");

                confirmDeleteDialogBuilder.setPositiveButton("YES",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        db.emptyIngredients();
                        db.emptyRecipes();
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });

                confirmDeleteDialogBuilder.setNegativeButton("NO",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

                AlertDialog confirmDeleteDialog = confirmDeleteDialogBuilder.create();
                confirmDeleteDialog.show();

                break;
            case R.id.create:
                db.close();
                startActivity(new Intent(RecipeView.this, AddRecipe.class));
                break;

        }
        db.close();
        return false;
    }

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_view);
    }
}
