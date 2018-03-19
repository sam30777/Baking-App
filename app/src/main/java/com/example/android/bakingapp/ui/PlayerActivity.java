package com.example.android.bakingapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.data.Instructions_Data;
import com.example.android.bakingapp.data.Recipe_data;
import com.example.android.bakingapp.fragments.VideoFragment;

import java.util.ArrayList;


public class PlayerActivity extends AppCompatActivity {

    static private int position;
    TextView nextVideo;
    TextView prevVideo;
    FragmentManager fragmentManager;
    private Recipe_data recipeData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState != null) {
            recipeData = (Recipe_data) savedInstanceState.getSerializable("recipeDataKey");
            position = savedInstanceState.getInt("pos");
        } else {
            Intent intent = getIntent();
            recipeData = (Recipe_data) intent.getSerializableExtra("recipeObject");
            position = intent.getIntExtra("stepPosition", 0);
            ArrayList<Instructions_Data> instructions_data = recipeData.getInstructions_data();
            final Instructions_Data iObject = instructions_data.get(position);
            String url = iObject.getVideoUrl();
            String desc = iObject.getDesc();
            String thumb = iObject.getThumbNailUrl();
            String shortDesc = iObject.getShort_desc();
            VideoFragment videoFragment = new VideoFragment();
            videoFragment.setData(this, desc, shortDesc, url, thumb);

            fragmentManager.beginTransaction().add(R.id.videoContainer, videoFragment).commit();
        }

        nextVideo = (TextView) findViewById(R.id.nextText);
        nextVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position < recipeData.getInstructions_data().size() - 1) {
                    position = position + 1;
                    PlayVideo();
                }

            }
        });
        prevVideo = (TextView) findViewById(R.id.previousVideo);
        prevVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (position > 0) {
                    position = position - 1;
                    PlayVideo();
                }
            }
        });
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putSerializable("recipeDataKey", recipeData);
        savedInstanceState.putInt("pos", position);
    }

    private void PlayVideo() {
        VideoFragment videoFragment = new VideoFragment();
        ArrayList<Instructions_Data> list = recipeData.getInstructions_data();
        Instructions_Data instructions_data1 = list.get(position);
        String url = instructions_data1.getVideoUrl();
        String desc = instructions_data1.getDesc();
        String shortDesc = instructions_data1.getShort_desc();
        String thumb = instructions_data1.getThumbNailUrl();
        videoFragment.setData(PlayerActivity.this, desc, shortDesc, url, thumb);
        Toast.makeText(PlayerActivity.this, "Position is" + position, Toast.LENGTH_SHORT).show();
        fragmentManager.beginTransaction().replace(R.id.videoContainer, videoFragment).commit();
    }
}


