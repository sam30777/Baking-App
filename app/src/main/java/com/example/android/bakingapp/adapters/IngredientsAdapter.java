package com.example.android.bakingapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.data.Ingredients_Data;
import com.example.android.bakingapp.R;

import java.util.ArrayList;

/**
 * Created by Santosh on 01-10-2017.
 */

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {
    Context context;
    ArrayList<Ingredients_Data> ingredients_datas;

    public IngredientsAdapter(Context context,ArrayList<Ingredients_Data> ingredients_datas){
        this.context=context;
        this.ingredients_datas=ingredients_datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredients_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
           Ingredients_Data ingredients_data=ingredients_datas.get(position);
          holder.ingName.setText(ingredients_data.getIngredient_name());
          holder.ingQname.setText(String.valueOf(ingredients_data.getQuantity()));
          holder.ingMeasure.setText(ingredients_data.getMeas());
    }

    @Override
    public int getItemCount() {
        if(ingredients_datas!=null){
            return ingredients_datas.size();
        }
        return 0;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView ingName,ingQname,ingMeasure;

        public ViewHolder(View itemView) {
            super(itemView);
            ingName=(TextView)itemView.findViewById(R.id.ingName1);
            ingQname=(TextView)itemView.findViewById(R.id.quantity);
            ingMeasure=(TextView)itemView.findViewById(R.id.measureIng1);
        }
    }
}
