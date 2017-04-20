package com.example.hiroyuki.androidyoutubesample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(), YoutubeActivity.class);
        intent.putExtra("youtubeid", "e3Nl_TCQXuw");
        startActivity(intent);
    }
}
