package com.example.android.bakingapp.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Santosh on 25-09-2017.
 */

public class Recipe_data implements Serializable {
    String recipe_Name;
    ArrayList<Ingredients_Data> ingredients_data;
    ArrayList<Instructions_Data> instructions_data;

    public Recipe_data(String name, ArrayList<Ingredients_Data> i_data, ArrayList<Instructions_Data> i1_data) {
        this.recipe_Name = name;
        this.ingredients_data = i_data;
        this.instructions_data = i1_data;
    }

    public String getRecipe_Name() {
        return recipe_Name;
    }

    public ArrayList<Ingredients_Data> getIngredients_data() {
        return ingredients_data;
    }

    public ArrayList<Instructions_Data> getInstructions_data() {
        return instructions_data;
    }

}
