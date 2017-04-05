package com.example.sydney.recipebook;

/**
 * Created by Sydney on 02/04/2017.
 */

public class Ingredients {
    int ingredient_id;
    String ingredients;
    String quantity;
    String instructions;
    String recipe_name;

    public Ingredients() {

    }

    public Ingredients(String ingredients, String quantity, String instructions, String recipe_name) {
        this.ingredients = ingredients;
        this.quantity = quantity;
        this.instructions = instructions;
        this.recipe_name = recipe_name;
    }

    public Ingredients(int ingredient_id, String ingredients, String quantity, String instructions, int recipe_id, String recipe_name) {
        this.ingredient_id = ingredient_id;
        this.ingredients = ingredients;
        this.quantity = quantity;
        this.instructions = instructions;
        this.recipe_name = recipe_name;
    }

    public int getIngredient_id() {
        return ingredient_id;
    }

    public void setIngredient_id(int ingredient_id) {
        this.ingredient_id = ingredient_id;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getRecipe_name() {
        return recipe_name;
    }

    public void setRecipe_name(String recipe_name) {
        this.recipe_name = recipe_name;
    }
}
