package com.example.android.bakingapp.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.adapters.ViewPagerAdapter;
import com.example.android.bakingapp.data.Ingredients_Data;
import com.example.android.bakingapp.data.Instructions_Data;
import com.example.android.bakingapp.data.Recipe_data;
import com.example.android.bakingapp.fragments.StepDescriptionFragment;
import com.example.android.bakingapp.fragments.VideoFragment;

import java.util.ArrayList;

public class StepDesc extends AppCompatActivity implements StepDescriptionFragment.OnFragmentInteractionListener {

    private Recipe_data recipe_data;
    private ArrayList<Instructions_Data> instructions_datas;
    private Boolean tabSelected;
    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_desc);

          Intent  intent = getIntent();
            recipe_data = (Recipe_data) intent.getSerializableExtra("Object");


        if (findViewById(R.id.tabletLayout) == null) {
            CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout_Steps);
            tabSelected = false;

            Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
            setSupportActionBar(toolbar);

            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            int pos = intent.getIntExtra("Position", 0);
            ImageView imageView = (ImageView) findViewById(R.id.cakeImage);
            if (pos == 0) {
                imageView.setImageResource(R.drawable.nutella);
            } else if (pos == 1) {
                imageView.setImageResource(R.drawable.brownies);
            } else if (pos == 2) {
                imageView.setImageResource(R.drawable.yellowcake);
            } else {
                imageView.setImageResource(R.drawable.chhesecake);
            }
            collapsingToolbarLayout.setTitle(recipe_data.getRecipe_Name());

            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
            final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPagerThis);
            tabLayout.setupWithViewPager(viewPager);
            tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
            tabLayout.setTabTextColors(Color.parseColor("#727272"), Color.parseColor("#ffffff"));
            tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#ffffff"));
            tabLayout.setSelectedTabIndicatorHeight((int) (5 * getResources().getDisplayMetrics().density));

            instructions_datas = recipe_data.getInstructions_data();
            ArrayList<Ingredients_Data> ingredients_data = recipe_data.getIngredients_data();

            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), StepDesc.this, ingredients_data, instructions_datas);
            viewPager.setAdapter(viewPagerAdapter);
            viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    viewPager.setCurrentItem(position);
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        } else {
            tabSelected = true;
            ArrayList<Instructions_Data> instructions_datas = recipe_data.getInstructions_data();
            StepDescriptionFragment stepDescriptionFragment = new StepDescriptionFragment();
            stepDescriptionFragment.setData(this, instructions_datas);
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().add(R.id.listContainer, stepDescriptionFragment).commit();

        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putSerializable("recipeData", recipe_data);
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onFragmentInteraction(int position) {
        if (tabSelected) {
            VideoFragment videoFragment = new VideoFragment();
            ArrayList<Instructions_Data> list = recipe_data.getInstructions_data();
            Instructions_Data instructions_data1 = list.get(position);
            String url = instructions_data1.getVideoUrl();
            String desc = instructions_data1.getDesc();
            String shortDesc = instructions_data1.getShort_desc();
            String thumb = instructions_data1.getThumbNailUrl();
            videoFragment.setData(StepDesc.this, desc, shortDesc, url, thumb);
            fragmentManager.beginTransaction().replace(R.id.detailContainer, videoFragment).commit();

        } else {
            Intent intent = new Intent(StepDesc.this, PlayerActivity.class);
            intent.putExtra("recipeObject", recipe_data);
            intent.putExtra("stepPosition", position);
            startActivity(intent);

        }

    }

}
