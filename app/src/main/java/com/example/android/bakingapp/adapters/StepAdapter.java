package com.example.android.bakingapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakingapp.data.Instructions_Data;
import com.example.android.bakingapp.R;

import java.util.ArrayList;

/**
 * Created by Santosh on 26-09-2017.
 */

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.ViewHolder> {

    Context context;
    ArrayList<Instructions_Data> arrayList;
    StepClickListener stepClickListener;
  public  StepAdapter(Context context,ArrayList<Instructions_Data> arrayList,StepClickListener stepClickListener)
    {
        this.context=context;
        this.arrayList=arrayList;
        this.stepClickListener=stepClickListener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.step_instructions_item,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Instructions_Data instructions_data=arrayList.get(position);
        holder.step_name.setText(instructions_data.getShort_desc());
        String s=instructions_data.getDesc();
        char[] c=s.toCharArray();
        c[13]='.';
        c[14]='.';
        c[15]='.';
        holder.short_dec.setText(c,0,15);
        holder.short_dec.setMaxLines(1);


    }

    @Override
    public int getItemCount() {
        if(arrayList.size()==0){return 0;}
        else
            return arrayList.size();
    }
    public interface StepClickListener {
        void onItemClicked(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView step_name;
        public TextView short_dec;
        public ViewHolder(View itemView) {
            super(itemView);
            step_name=(TextView)itemView.findViewById(R.id.step_name);
            short_dec=(TextView)itemView.findViewById(R.id.step_desc);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int p=getAdapterPosition();
            stepClickListener.onItemClicked(p);
        }
    }
}
