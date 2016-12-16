package com.appyapps.appyapps.pente;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //MobileAds.initialize(getApplicationContext(), "ca-app-pub-4041696999574779/5202552046");

        //AdRequest request = new AdRequest.Builder()
        //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)// All emulators
        //.addTestDevice("AC98C820A50B4AD8A2106EDE96FB87D4")// An example device ID
        //.build();
        AdRequest request = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)// All emulators
                .addTestDevice("AC98C820A50B4AD8A2106EDE96FB87D4")// An example device ID
                .build();
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