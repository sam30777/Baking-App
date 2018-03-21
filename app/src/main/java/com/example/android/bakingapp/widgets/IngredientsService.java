package com.example.android.bakingapp.widgets;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

/**
 * Created by Santosh on 20-03-2018.
 */

public class IngredientsService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new IngredientsFactory(getApplicationContext(),intent);
    }
}
