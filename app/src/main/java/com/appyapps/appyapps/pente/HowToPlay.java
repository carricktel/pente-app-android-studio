package com.appyapps.appyapps.pente;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Joseph Carrick on 11/24/2016.
 */

public class HowToPlay extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.how_to_play);

    }
    public void onBackFromHowToClick(View view) {
        Intent getScreenIntent = new Intent(this, MainActivity.class);
        final int result = 1;
        startActivity(getScreenIntent);
    }
}
