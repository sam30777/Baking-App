package com.example.android.bakingapp.widgets;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.android.bakingapp.data.Ingredients_Data;
import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.Recipe_data;
import com.example.android.bakingapp.ui.Recipe;

import java.util.ArrayList;

/**
 * Created by Santosh on 10-02-2018.
 */

public class IngredientsFactory implements RemoteViewsService.RemoteViewsFactory {
    Context context;
    private static  ArrayList<Ingredients_Data> ingredients_dataArrayList;
    private Intent intent;

    public IngredientsFactory(Context context,Intent i) {
        this.context = context;
        this.intent = i;
    }
    @Override
    public void onDataSetChanged() {
        if(ingredients_dataArrayList==null){
            Recipe_data recipe_data=(Recipe_data)intent.getSerializableExtra("Object");
            ingredients_dataArrayList=recipe_data.getIngredients_data();
        }
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        if(ingredients_dataArrayList!=null)return ingredients_dataArrayList.size();
        return 0;
    }

    @Override
    public void onCreate() {


    }

    @Override
    public long getItemId(int i) {

        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public RemoteViews getViewAt(int i) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.ingredients_item);
        remoteViews.setTextViewText(R.id.ingName1, ingredients_dataArrayList.get(i).getIngredient_name());
        remoteViews.setTextViewText(R.id.quantity, String.valueOf(ingredients_dataArrayList.get(i).getQuantity()));
        remoteViews.setTextViewText(R.id.measureIng1, ingredients_dataArrayList.get(i).getMeas());
        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }
}
