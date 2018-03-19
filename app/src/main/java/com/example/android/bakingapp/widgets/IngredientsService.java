package com.example.android.bakingapp.widgets;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by Santosh on 10-02-2018.
 */

public class IngredientsService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new IngredientsFactory(getBaseContext(),intent);
    }
}
