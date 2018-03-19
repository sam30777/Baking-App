package com.example.android.bakingapp.fragments;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveVideoTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;

/**
 * Created by Santosh on 16-10-2017.
 */

public class VideoFragment extends Fragment {
    private static  Context context;
    private String mediaUri;
    private String desc;
    private String shortDesc;
    private SimpleExoPlayer simpleExoPlayer;
    private SimpleExoPlayerView simpleExoPlayerView;
    private String thumbNail;
    private Boolean player_State=true;
    private Long position=0L;
    private String userAgent;


    public VideoFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video_fragment, container, false);


        simpleExoPlayerView = (SimpleExoPlayerView) view.findViewById(R.id.simpleExoPlayer);
        TextView textView = (TextView) view.findViewById(R.id.textViewDesc);
        TextView textView1 = (TextView) view.findViewById(R.id.textViewDescShort);
        ImageView thumbnailImageView=(ImageView)view.findViewById(R.id.thumbnailImage);
        ImageView noVideoImage=(ImageView)view.findViewById(R.id.NoVideoAvailable);

         if(savedInstanceState!=null){
             mediaUri=savedInstanceState.getString("mediaUrl");
             desc=savedInstanceState.getString("descState");
             shortDesc=savedInstanceState.getString("ShortDescState");
             player_State=savedInstanceState.getBoolean("playerState");
             position=savedInstanceState.getLong("position");

         }
        textView1.setText(desc);
        textView.setText(shortDesc);
        if(thumbNail!=null&&!thumbNail.isEmpty()){
            Picasso.with(context).load(thumbNail).into(thumbnailImageView);
        }else{
            thumbnailImageView.setVisibility(View.GONE);

        }
         if(mediaUri!=null&&!mediaUri.isEmpty()){
             Uri uri = Uri.parse(mediaUri);
             DefaultBandwidthMeter defaultBandwidthMeter = new DefaultBandwidthMeter();
             BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
             TrackSelection.Factory factory = new AdaptiveVideoTrackSelection.Factory(bandwidthMeter);
             TrackSelector trackSelector = new DefaultTrackSelector(factory);
             LoadControl loadControl = new DefaultLoadControl();
             simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(context, trackSelector, loadControl);

             simpleExoPlayerView.setPlayer(simpleExoPlayer);

             userAgent = Util.getUserAgent(context, "BakingApp");
             DataSource.Factory dataSource = new DefaultDataSourceFactory(context, userAgent, defaultBandwidthMeter);
             ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

             MediaSource mediaSource = new ExtractorMediaSource(uri, dataSource, extractorsFactory, null, null);
             simpleExoPlayer.prepare(mediaSource);
             simpleExoPlayer.setPlayWhenReady(player_State);
             simpleExoPlayer.seekTo(position);
         }else{
             simpleExoPlayerView.setVisibility(View.GONE);
             noVideoImage.setVisibility(View.VISIBLE);
         }


        return view;
    }

    @Override
    public void onStop() {
        if(simpleExoPlayer!=null){
            simpleExoPlayer.setPlayWhenReady(false);
            simpleExoPlayer.release();
        }

        super.onStop();
    }

    @Override
    public void onPause() {
        if(simpleExoPlayer!=null){
            simpleExoPlayer.setPlayWhenReady(false);
            simpleExoPlayer.release();
        }

        super.onPause();
    }

    public void setData(Context context, String x, String y, String uri, String z) {
        this.context = context;
        this.desc = x;
        this.shortDesc = y;
        this.mediaUri = uri;
        this.thumbNail=z;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(simpleExoPlayer!=null){
            outState.putLong("position", simpleExoPlayer.getCurrentPosition());
            outState.putBoolean("playerState",simpleExoPlayer.getPlayWhenReady());
        }

        outState.putString("mediaUrl", mediaUri);
        outState.putString("ShortDescState",shortDesc);
        outState.putString("descState",desc);
    }


}
