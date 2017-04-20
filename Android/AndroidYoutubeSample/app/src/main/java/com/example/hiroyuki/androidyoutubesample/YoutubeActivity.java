package com.example.hiroyuki.androidyoutubesample;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class YoutubeActivity extends YouTubeBaseActivity
        implements YouTubePlayer.OnInitializedListener, YouTubePlayer.OnFullscreenListener {
    private static final int RECOVERY_DIALOG_REQUEST = 1;

    private YouTubePlayerView playerView;
    private YouTubePlayer player;
    public static final String DEVELOPER_KEY = "AIzaSyBP_DkoKL9S62e_PFwCExg3ppgAEepIhrg";

    private String YoutubeId = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        View decor = this.getWindow().getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        playerView = (YouTubePlayerView) findViewById(R.id.player);
        playerView.initialize(DEVELOPER_KEY, this);

        YoutubeId = getIntent().getStringExtra("youtubeid");
    }

    @Override
    public void onFullscreen(boolean b) {

        Log.d("TAG", "----[" + b + "]");
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        this.player = youTubePlayer;
        // Specify that we want to handle fullscreen behavior ourselves.
        player.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT);
        player.setOnFullscreenListener(this);
        if (!b) {
//            player.cueVideo(YoutubeId);
            Observable.just(1).subscribeOn(Schedulers.newThread())
                    .map(l -> {
                        player.loadVideo(YoutubeId);
                        return l;
                    })
                    .subscribe();
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = String.format(getString(R.string.error_player), youTubeInitializationResult.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            playerView.initialize(DEVELOPER_KEY, this);
        }
    }

    public void onClose(View view) {
        finish();
    }
}
