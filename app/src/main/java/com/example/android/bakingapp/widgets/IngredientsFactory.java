package com.example.android.bakingapp.widgets;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import android.widget.TextView;
import android.widget.Toast;

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
    private int appWidgwtId;
    private String recipe_name;



    public IngredientsFactory(Context context,Intent i) {
        this.context = context;
        this.appWidgwtId=i.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,AppWidgetManager.INVALID_APPWIDGET_ID);
        recipe_name=i.getStringExtra("Recipe_name");
        setData();

    }
    private void setData(){
        ingredients_dataArrayList=(ArrayList<Ingredients_Data>)Recipe.ing.clone();
    }
    @Override
    public void onDataSetChanged() {

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

        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public RemoteViews getViewAt(int i) {

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.list_item_widget);
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
