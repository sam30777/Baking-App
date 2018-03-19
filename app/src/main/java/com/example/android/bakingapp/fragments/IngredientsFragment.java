package com.example.android.bakingapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.bakingapp.adapters.IngredientsAdapter;
import com.example.android.bakingapp.data.Ingredients_Data;
import com.example.android.bakingapp.R;

import java.util.ArrayList;


public class IngredientsFragment extends Fragment {

    private Context context;
    private ArrayList<Ingredients_Data> ingredients_datas;


    public IngredientsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (savedInstanceState != null) {
            ingredients_datas = (ArrayList<Ingredients_Data>) savedInstanceState.getSerializable("ingList");
        }
        View view = inflater.inflate(R.layout.fragment_ingredients, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.ingRecycler);
        IngredientsAdapter ingredientsAdapter = new IngredientsAdapter(context, ingredients_datas);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(ingredientsAdapter);
        return view;

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("ingList", ingredients_datas);
    }

    public void setdata(Context context, ArrayList<Ingredients_Data> ingredients_datas) {
        this.context = context;
        this.ingredients_datas = ingredients_datas;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
