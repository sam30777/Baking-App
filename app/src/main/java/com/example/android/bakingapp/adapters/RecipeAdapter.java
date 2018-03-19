package com.example.android.bakingapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.Recipe_data;

import java.util.ArrayList;

/**
 * Created by Santosh on 25-09-2017.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {
    private ArrayList<Recipe_data> recipe_datas = new ArrayList<>();
    private Context context;
    private ListItemClickListner listItemClickListner;


    public RecipeAdapter(Context context, ArrayList<Recipe_data> recipe_datas, ListItemClickListner listItemClickListner) {
        this.context = context;
        this.recipe_datas = recipe_datas;
        this.listItemClickListner = listItemClickListner;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recipe_list_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Recipe_data recipe_data = recipe_datas.get(position);
        holder.textView.setText(recipe_data.getRecipe_Name());
        if(position==0){
            holder.imageView.setImageResource(R.drawable.nutella);
        }else if(position==1){
            holder.imageView.setImageResource(R.drawable.brownies);
        }else if(position==2){
            holder.imageView.setImageResource(R.drawable.yellowcake);
        }else {
            holder.imageView.setImageResource(R.drawable.chhesecake);
        }

    }

    @Override
    public int getItemCount() {
        if (recipe_datas.size() == 0)
            return 0;
        else
            return recipe_datas.size();
    }

    public interface ListItemClickListner {
        void onItemClicked(int listItemIndex);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textView;
        public ImageView imageView;


        private ViewHolder(View itemView) {
            super(itemView);
           this.textView = (TextView) itemView.findViewById(R.id.recipe_name);
           this.imageView = (ImageView) itemView.findViewById(R.id.CircularImageView);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            listItemClickListner.onItemClicked(position);
        }
    }


}
