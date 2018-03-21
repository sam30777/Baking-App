package com.example.android.bakingapp.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.bakingapp.adapters.RecipeAdapter;
import com.example.android.bakingapp.data.Ingredients_Data;
import com.example.android.bakingapp.data.Instructions_Data;
import com.example.android.bakingapp.data.Recipe_data;
import com.example.android.bakingapp.R;

import com.example.android.bakingapp.widgets.IngredientsService;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Recipe extends AppCompatActivity implements RecipeAdapter.ListItemClickListner {


    static final String Log_Tag = Recipe.class.getSimpleName();
    RecyclerView recyclerView;
    private String recipe_Url = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
    private RecyclerView.Adapter rAdapter;
    private ArrayList<Recipe_data> re;
    private Integer recipe_widget_id;
    public static ArrayList<Ingredients_Data> ing;
    private Boolean isWidgetIntent=false;
    private String  Widget_Action ="android.appwidget.action.APPWIDGET_CONFIGURE";
    public Recipe() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
         Intent intent=getIntent();
          if(intent.getAction()==Widget_Action){
              isWidgetIntent=true;
              Bundle extras=intent.getExtras();
              recipe_widget_id=extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,AppWidgetManager.INVALID_APPWIDGET_ID);

          }
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        collapsingToolbarLayout.setTitle("Baking App");
        re = new ArrayList<Recipe_data>();
        recyclerView = (RecyclerView) findViewById(R.id.Recipe_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);

        re = getData();
        rAdapter = new RecipeAdapter(this, re, this);
        recyclerView.setAdapter(rAdapter);


    }

    private ArrayList<Recipe_data> getData() {

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        final ArrayList<Recipe_data> recipe_datas = new ArrayList<Recipe_data>();
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, recipe_Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Ingredients_Data ingredients_data = null;
                    Instructions_Data instructions_data = null;
                    JSONArray jasonArray = new JSONArray(response);
                    for (int i = 0; i < jasonArray.length(); i++) {
                        ArrayList<Instructions_Data> arrayListInstructions = new ArrayList<>();
                        ArrayList<Ingredients_Data> arrayListIngredients = new ArrayList<>();
                        JSONObject base = jasonArray.getJSONObject(i);
                        String name = base.getString("name");
                        JSONArray ingArray = base.getJSONArray("ingredients");
                        for (int j = 0; j < ingArray.length(); j++) {
                            JSONObject baseI = ingArray.getJSONObject(j);
                            int q = baseI.getInt("quantity");
                            String me = baseI.getString("measure");
                            String ingName = baseI.getString("ingredient");
                            ingredients_data = new Ingredients_Data(q, ingName, me);
                            arrayListIngredients.add(ingredients_data);
                        }
                        JSONArray instArray = base.getJSONArray("steps");
                        for (int k = 0; k < instArray.length(); k++) {
                            JSONObject baseIs = instArray.getJSONObject(k);
                            String sd = baseIs.getString("shortDescription");
                            String d = baseIs.getString("description");
                            String url = null;
                            String thumbNail=null;
                            if (baseIs.has("videoURL")) {
                                url = baseIs.getString("videoURL");
                            }
                            if(baseIs.has("thumbnailURL")){
                                thumbNail=baseIs.getString("thumbnailURL");
                            }

                            instructions_data = new Instructions_Data(url, sd, d,thumbNail);
                            arrayListInstructions.add(instructions_data);
                        }
                        Recipe_data recipe = new Recipe_data(name, arrayListIngredients, arrayListInstructions);
                        recipe_datas.add(recipe);
                        rAdapter.notifyDataSetChanged();

                    }

                } catch (JSONException e) {
                    Log.e(Log_Tag, "no data found");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Recipe.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        requestQueue.add(stringRequest);
        return recipe_datas;
    }


    @Override
    public void onItemClicked(int listItemIndex) {
        Recipe_data recipe_data = re.get(listItemIndex);
        if(isWidgetIntent){
                ing=re.get(listItemIndex).getIngredients_data();
           Intent intent=new Intent();
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,recipe_widget_id);
            setResult(Activity.RESULT_OK,intent);
            Intent serviceIntent = new Intent(Recipe.this, IngredientsService.class);
            serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,recipe_widget_id);
            serviceIntent.setData(Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME)));
            RemoteViews views = new RemoteViews(getPackageName(), R.layout.cake_widget);
            views.setTextViewText(R.id.recipe_widget_name,re.get(listItemIndex).getRecipe_Name());
            views.setRemoteAdapter(R.id.ingredients_widgets_list, serviceIntent);
            AppWidgetManager appWidgetManager=AppWidgetManager.getInstance(Recipe.this);
            appWidgetManager.updateAppWidget(recipe_widget_id, views);
            finish();
        }else{

        Intent intent = new Intent(Recipe.this, StepDesc.class);
        intent.putExtra("Object", recipe_data);
        intent.putExtra("Position", listItemIndex);
        startActivity(intent);
        }
    }
}
