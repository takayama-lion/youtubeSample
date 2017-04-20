package com.example.hiroyuki.androidyoutubesample;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MainActivity extends YouTubeBaseActivity
        implements YouTubePlayer.OnInitializedListener, YouTubePlayer.OnFullscreenListener {
    private static final int RECOVERY_DIALOG_REQUEST = 1;

    private YouTubePlayerView playerView;
    private YouTubePlayer player;
    public static final String DEVELOPER_KEY = "AIzaSyBP_DkoKL9S62e_PFwCExg3ppgAEepIhrg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerView = (YouTubePlayerView) findViewById(R.id.player);
        playerView.initialize(DEVELOPER_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        this.player = youTubePlayer;
        setControlsEnabled();
        // Specify that we want to handle fullscreen behavior ourselves.
//        player.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT);
        player.addFullscreenControlFlag(YouTubePlayer.FULLSCREEN_FLAG_CUSTOM_LAYOUT);
        player.setOnFullscreenListener(this);
        if (!b) {
            player.cueVideo("avP5d16wEp0");
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
            getYouTubePlayerProvider().initialize(DEVELOPER_KEY, this);
        }
    }

    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return playerView;
    }

    private void setControlsEnabled() {
    }

    @Override
    public void onFullscreen(boolean b) {
        Log.d("TAG", "----[" + b + "]");
    }

    private boolean isPlay = false;

    public void onButton(View view) {
        if (isPlay) {
            this.player.pause();
            isPlay = !isPlay;
        } else {
            this.player.play();
            isPlay = !isPlay;
        }
    }
}
