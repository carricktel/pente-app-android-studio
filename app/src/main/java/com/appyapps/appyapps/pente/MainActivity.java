package com.appyapps.appyapps.pente;

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

    public void onPlayClick(View view) {
        Intent getScreenIntent = new Intent(this, ConfigureGame.class);
        final int result = 1;
        startActivity(getScreenIntent);
    }

    public void onHowToPlayClick(View view) {
        Intent getScreenIntent = new Intent(this, HowToPlay.class);
        final int result = 1;
        startActivity(getScreenIntent);
    }
}