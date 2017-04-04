package com.example.sydney.recipebook;

import android.graphics.Point;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.util.List;

public class ViewRecipes extends AppCompatActivity {
    DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipes);
        db =  new DatabaseHandler(this);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenWidth = size.x;
        int screenHeight = size.y;

        ScrollView sv = new ScrollView(this);
        sv.setLayoutParams(new SlidingPaneLayout.LayoutParams(SlidingPaneLayout.LayoutParams.MATCH_PARENT, SlidingPaneLayout.LayoutParams.MATCH_PARENT));
        LinearLayout mainLayout = new LinearLayout(this);
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setPadding(50, 50, 50, 50);
        sv.addView(mainLayout);
        final TextView recipeDescription = new TextView(this);
        List<Recipes> recipes = db.getAllRecipes();
        List<Ingredients> ingredients = db.getAllIngredients();

        for(Recipes rp : recipes) {
            final Button button = new Button(this);
            button.setText(rp.getName());
            mainLayout.addView(button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Recipes x;
                    x = db.getRecipes(button.getText().toString());
                    recipeDescription.setText(x.getDescription());
                }
            });
        }
        mainLayout.addView(recipeDescription);
        setContentView(sv);
    }
}
