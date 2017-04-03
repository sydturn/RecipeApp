package com.example.sydney.recipebook;

/**
 * Created by Sydney on 02/04/2017.
 */

public class Recipes {
    int recipe_id;
    String name;
    String description;
    int prepTimeMinutes;
    int numOfServings;

    public Recipes(int recipe_id, String name, String description, int prepTimeMinutes, int numOfServings) {
        this.recipe_id = recipe_id;
        this.name = name;
        this.description = description;
        this.prepTimeMinutes = prepTimeMinutes;
        this.numOfServings = numOfServings;
    }

    public Recipes(String name, String description, int prepTimeMinutes, int numOfServings) {
        this.name = name;
        this.description = description;
        this.prepTimeMinutes = prepTimeMinutes;
        this.numOfServings = numOfServings;
    }
    public Recipes() {

    }
    public int getId() {
        return recipe_id;
    }

    public void setId(int id) {
        this.recipe_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrepTimeMinutes() {
        return prepTimeMinutes;
    }

    public void setPrepTimeMinutes(int prepTimeMinutes) {
        this.prepTimeMinutes = prepTimeMinutes;
    }

    public int getNumOfServings() {
        return numOfServings;
    }

    public void setNumOfServings(int numOfServings) {
        this.numOfServings = numOfServings;
    }
}
