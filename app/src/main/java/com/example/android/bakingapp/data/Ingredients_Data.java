package com.example.android.bakingapp.data;

import java.io.Serializable;

/**
 * Created by Santosh on 25-09-2017.
 */

public class Ingredients_Data implements Serializable {
    int quantity;
    String ingredient_name;
    String meas;

    public Ingredients_Data(int q, String i, String m) {
        quantity = q;
        ingredient_name = i;
        meas = m;
    }

    public String getIngredient_name() {
        return ingredient_name;
    }

    public String getMeas() {
        return meas;
    }

    public int getQuantity() {
        return quantity;
    }

}
