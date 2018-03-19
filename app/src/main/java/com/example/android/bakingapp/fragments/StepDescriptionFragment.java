package com.example.android.bakingapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.bakingapp.adapters.StepAdapter;
import com.example.android.bakingapp.data.Instructions_Data;
import com.example.android.bakingapp.R;

import java.util.ArrayList;


public class StepDescriptionFragment extends Fragment implements StepAdapter.StepClickListener {


    private Context context;
    private ArrayList<Instructions_Data> instructions_data;
    private OnFragmentInteractionListener mListener;

    public StepDescriptionFragment() {
        // Required empty public constructor
    }


    public void setData(Context context, ArrayList<Instructions_Data> instructions_data) {
        this.context = context;
        this.instructions_data = instructions_data;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            instructions_data = (ArrayList<Instructions_Data>) savedInstanceState.getSerializable("insList");
        }
        View view = inflater.inflate(R.layout.fragment_step_description, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.stepRecycle);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        StepAdapter stepAdapter = new StepAdapter(context, instructions_data, this);
        recyclerView.setAdapter(stepAdapter);
        return view;

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("insList", instructions_data);
    }

    @Override
    public void onItemClicked(int position) {
        mListener.onFragmentInteraction(position);
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(int position);
    }
}
