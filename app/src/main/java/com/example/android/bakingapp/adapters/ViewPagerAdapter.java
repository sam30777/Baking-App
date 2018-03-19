package com.example.android.bakingapp.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.example.android.bakingapp.fragments.IngredientsFragment;
import com.example.android.bakingapp.data.Ingredients_Data;
import com.example.android.bakingapp.data.Instructions_Data;
import com.example.android.bakingapp.fragments.StepDescriptionFragment;

import java.util.ArrayList;

/**
 * Created by Santosh on 27-01-2018.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    //Getting the ingredients list and instructions list to use it while dynamicaly creating the fragments
    private ArrayList<Ingredients_Data> ingredients_datas;
    private ArrayList<Instructions_Data> instructions_datas;
    private Context context;
    //Constrcutor for getting the context and list datas
    public ViewPagerAdapter(FragmentManager fm, Context context, ArrayList<Ingredients_Data> ingredients_datas,ArrayList<Instructions_Data> instructions_datas) {
        super(fm);
        this.context=context;
        this.ingredients_datas=ingredients_datas;
        this.instructions_datas=instructions_datas;

    }
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            StepDescriptionFragment stepDescriptionFragment = new StepDescriptionFragment();
            stepDescriptionFragment.setData(context, instructions_datas);
            return stepDescriptionFragment;
        } else if (position == 1) {
            IngredientsFragment ingredientsFragment = new IngredientsFragment();
            ingredientsFragment.setdata(context, ingredients_datas);
            return ingredientsFragment;
        }
        return null;
    }
    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public void startUpdate(ViewGroup container) {
        super.startUpdate(container);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position==0){
            return "Instructions";
        }else{
            if(position==1){
                return "Ingredients";
            }
        }
        return null;
    }
}
