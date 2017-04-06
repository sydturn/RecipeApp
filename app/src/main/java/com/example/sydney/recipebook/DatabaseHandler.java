package com.example.sydney.recipebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sydney on 02/04/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    //database name
    private static final String DATABASE_NAME = "recipeBook";

    //recipe table
    private static final String TABLE_RECIPES = "recipes";

    //recipe column names
    public static final String KEY_ID = "recipe_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_PREP_TIME_MINUTES = "prep_time_minutes";
    public static final String KEY_NUM_SERVINGS = "Servings";

    //ingredients table
    private static final String TABLE_INGREDIENTS = "ingredients";

    //incredient column names
    public static final String KEY_INGR_ID = "ingredient_id";
    public static final String KEY_INGREDIENTS = "Ingredients";
    public static final String KEY_QUANTITY = "Quantity";
    public static final String KEY_INSTRUCTIONS = "Instrutions";
    public static final String KEY_RECIPE_NAME = "recipe_name";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_RECIPES_TABLE = "CREATE TABLE " + TABLE_RECIPES + "(" + KEY_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME + " TEXT, " + KEY_DESCRIPTION +
                " TEXT, " + KEY_PREP_TIME_MINUTES + " INTEGER, " +  KEY_NUM_SERVINGS + " INTEGER) ";
        db.execSQL(CREATE_RECIPES_TABLE);

        String CREATE_INGREDIENTS_TABLE = "CREATE TABLE " + TABLE_INGREDIENTS + " (" + KEY_INGR_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " +  KEY_INGREDIENTS + " TEXT, " + KEY_QUANTITY +
                " TEXT, " + KEY_INSTRUCTIONS + " TEXT, " + KEY_RECIPE_NAME + " TEXT) ";
        db.execSQL(CREATE_INGREDIENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop older tables if it exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INGREDIENTS);

        //create tables again
        onCreate(db);
    }
    //add new recipe
    public void addRecipe(Recipes recipe) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, recipe.getName());
        values.put(KEY_DESCRIPTION, recipe.getDescription());
        values.put(KEY_PREP_TIME_MINUTES, recipe.getPrepTimeMinutes());
        values.put(KEY_NUM_SERVINGS, recipe.getNumOfServings());

        db.insert(TABLE_RECIPES, null, values);
        db.close();
    }

    //get single recipe
    public Recipes getRecipes(String name) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_RECIPES, new String[] {KEY_ID, KEY_NAME, KEY_DESCRIPTION,
                KEY_PREP_TIME_MINUTES, KEY_NUM_SERVINGS}, KEY_NAME + "=?", new String[]
                        {String.valueOf(name)}, null, null, null, null);
        if(cursor != null) {
            cursor.moveToFirst();
        }

        Recipes recipe = new Recipes(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),
                Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)));
        return recipe;
    }

    //get all recipes
    public List<Recipes> getAllRecipes() {
        List<Recipes> recipeList = new ArrayList<Recipes>();
        String selectQuery = "SELECT * FROM " + TABLE_RECIPES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                Recipes recipe = new Recipes();
                recipe.setId(Integer.parseInt(cursor.getString(0)));
                recipe.setName(cursor.getString(1));
                recipe.setDescription(cursor.getString(2));
                recipe.setPrepTimeMinutes(Integer.parseInt(cursor.getString(3)));
                recipe.setNumOfServings(Integer.parseInt(cursor.getString(4)));

                recipeList.add(recipe);
            } while (cursor.moveToNext());
        }
        return recipeList;
    }

    //get recipe count
    public int getRecipesCount() {
        String countQuery = "SELECT * FROM " + TABLE_RECIPES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    //update single recipe
    public int updateRecipe(Recipes recipe){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, recipe.getName());
        values.put(KEY_DESCRIPTION, recipe.getDescription());
        values.put(KEY_PREP_TIME_MINUTES, recipe.getPrepTimeMinutes());
        values.put(KEY_NUM_SERVINGS, recipe.getNumOfServings());

        return db.update(TABLE_RECIPES, values, KEY_ID + " = ? ", new String[] {
                String.valueOf(recipe.getId())
        });
    }

    //delete single recipe
    public void deleteRecipe(Recipes recipe) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RECIPES, KEY_ID + " = ? ", new String[] {
                String.valueOf(recipe.getId())
        });
        db.delete(TABLE_INGREDIENTS, KEY_RECIPE_NAME + " = ? ", new String[] {
                recipe.getName()
        });
        db.close();
    }
    //empty table of recipes
    public void emptyRecipes() {
        SQLiteDatabase db = this.getWritableDatabase();
        String CLEAR_TABLE_RECIPE = "DELETE FROM recipes";
        db.execSQL(CLEAR_TABLE_RECIPE);
        db.execSQL("VACUUM");
    }


    //add new ingredient
    public void addIngredient(Ingredients ingredient) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_INGREDIENTS, ingredient.getIngredients());
        values.put(KEY_QUANTITY, ingredient.getQuantity());
        values.put(KEY_INSTRUCTIONS, ingredient.getInstructions());
        values.put(KEY_RECIPE_NAME, ingredient.getRecipe_name());

        db.insert(TABLE_INGREDIENTS, null, values);
        db.close();
    }

    //get single ingredient
    public Ingredients getIngredients(String name) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_INGREDIENTS, new String[] {KEY_INGR_ID, KEY_INGREDIENTS, KEY_QUANTITY,
                        KEY_INSTRUCTIONS, KEY_RECIPE_NAME}, KEY_INGREDIENTS + "=?", new String[]
                        {String.valueOf(name)}, null, null, null, null);

        if(cursor != null) {
            cursor.moveToFirst();
        }

        Ingredients ingredient = new Ingredients(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),
                cursor.getString(3), Integer.parseInt(cursor.getString(4)), cursor.getString(5));
        return ingredient;
    }

    //get all Ingredients
    public List<Ingredients> getAllIngredients() {
        List<Ingredients> ingredientList = new ArrayList<Ingredients>();
        String selectQuery = "SELECT * FROM " + TABLE_INGREDIENTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                Ingredients ingredient = new Ingredients();
                ingredient.setIngredient_id(Integer.parseInt(cursor.getString(0)));
                ingredient.setIngredients(cursor.getString(1));
                ingredient.setQuantity(cursor.getString(2));
                ingredient.setInstructions(cursor.getString(3));
                ingredient.setRecipe_name(cursor.getString(4));
                ingredientList.add(ingredient);
            } while (cursor.moveToNext());
        }
        return ingredientList;
    }

    //get ingredient count
    public int getIngredientsCount() {
        String countQuery = "SELECT * FROM " + TABLE_INGREDIENTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    //update single ingredient
    public int updateIngredient(Ingredients ingredient){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_INGR_ID, ingredient.getIngredient_id());
        values.put(KEY_INGREDIENTS, ingredient.getIngredients());
        values.put(KEY_QUANTITY, ingredient.getQuantity());
        values.put(KEY_INSTRUCTIONS, ingredient.getInstructions());
        values.put(KEY_RECIPE_NAME, ingredient.getRecipe_name());

        return db.update(TABLE_INGREDIENTS, values, KEY_INGR_ID + " = ? ", new String[] {
                String.valueOf(ingredient.getIngredient_id())
        });
    }

    //delete single ingredient
    public void deleteIngredient(Ingredients ingredient) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_INGREDIENTS, KEY_INGR_ID + " = ? ", new String[] {
                String.valueOf(ingredient.getIngredient_id())
        });
        db.close();
    }
    //empty table of recipes
    public void emptyIngredients() {
        SQLiteDatabase db = this.getWritableDatabase();
        String CLEAR_TABLE_INGREDIENTS = "DELETE FROM ingredients";
        db.execSQL(CLEAR_TABLE_INGREDIENTS);
        db.execSQL("VACUUM");
    }
    //get ingridents for specified recipe
    public List<Ingredients> getRecipeIngredients(String recipeName) {
        List<Ingredients> ingredientList = new ArrayList<Ingredients>();

        String selectQuery = "SELECT * FROM " + TABLE_INGREDIENTS + " WHERE " + KEY_RECIPE_NAME + " = '" + recipeName + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                Ingredients ingredient = new Ingredients();
                ingredient.setIngredient_id(Integer.parseInt(cursor.getString(0)));
                ingredient.setIngredients(cursor.getString(1));
                ingredient.setQuantity(cursor.getString(2));
                ingredient.setInstructions(cursor.getString(3));
                ingredient.setRecipe_name(cursor.getString(4));
                ingredientList.add(ingredient);
            } while (cursor.moveToNext());
        }
        return ingredientList;
    }

}
