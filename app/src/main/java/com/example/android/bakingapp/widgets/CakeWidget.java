package com.example.android.bakingapp.widgets;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import com.example.android.bakingapp.R;

/**
 * Implementation of App Widget functionality.
 */
public class CakeWidget extends AppWidgetProvider {
   public static final String Ingredients_Updated ="com.example.android.IngredientsUpdated";



    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            Intent serviceIntent = new Intent(context, IngredientsService.class);
            serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,appWidgetId);
            serviceIntent.setData(Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME)));
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.cake_widget);
            views.setRemoteAdapter(R.id.ingredients_widgets_list, serviceIntent);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }


}

