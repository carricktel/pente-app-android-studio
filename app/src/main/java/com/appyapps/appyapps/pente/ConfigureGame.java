package com.appyapps.appyapps.pente;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by Joseph Carrick on 11/23/2016.
 */

public class ConfigureGame extends Activity{

    public Pente penteObj = new Pente();
    public int p1IconNum = 0;
    public int p2IconNum = 1;
    public int p1ColorNum = 1;
    public int p2ColorNum = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config_game);

    }

    public void onSingleMatchClick(View view) {
        ImageButton singleMatchBtn = (ImageButton) findViewById(R.id.singleMatch);
        singleMatchBtn.setImageResource(R.drawable.singlematchclicked);
        ImageButton twentyOneBtn = (ImageButton) findViewById(R.id.twentyOnePoints);
        twentyOneBtn.setImageResource(R.drawable.twentyonepoints);
        ImageButton fiftyPointsBtn = (ImageButton) findViewById(R.id.fiftyPoints);
        fiftyPointsBtn.setImageResource(R.drawable.fiftypoints);
        penteObj.setPointsToWin(2);
        final int result = 1;
    }

    public void onTwentyOnePointsClick(View view) {
        ImageButton singleMatchBtn = (ImageButton) findViewById(R.id.singleMatch);
        singleMatchBtn.setImageResource(R.drawable.singlematch);
        ImageButton twentyOneBtn = (ImageButton) findViewById(R.id.twentyOnePoints);
        twentyOneBtn.setImageResource(R.drawable.twentyonepointsclicked);
        ImageButton fiftyPointsBtn = (ImageButton) findViewById(R.id.fiftyPoints);
        fiftyPointsBtn.setImageResource(R.drawable.fiftypoints);
        penteObj.setPointsToWin(1);
        final int result = 1;
    }

    public void onFiftyPointsClick(View view) {
        ImageButton singleMatchBtn = (ImageButton) findViewById(R.id.singleMatch);
        singleMatchBtn.setImageResource(R.drawable.singlematch);
        ImageButton twentyOneBtn = (ImageButton) findViewById(R.id.twentyOnePoints);
        twentyOneBtn.setImageResource(R.drawable.twentyonepoints);
        ImageButton fiftyPointsBtn = (ImageButton) findViewById(R.id.fiftyPoints);
        fiftyPointsBtn.setImageResource(R.drawable.fiftypointsclicked);
        penteObj.setPointsToWin(0);
        final int result = 1;
    }

/*    public void onP1IconClick(View view) {
        ImageButton iconBtn = (ImageButton) findViewById(R.id.humanIcon);
        boolean wasClicked = false;
        if (p1IconNum == 3){
            iconBtn.setImageResource(R.drawable.humanicon);
            p1IconNum = 0;
            wasClicked = true;
        }
        if (p1IconNum == 2){
            iconBtn.setImageResource(R.drawable.ailvl3icon);
            p1IconNum = 3;
        }
        if (p1IconNum == 1){
            iconBtn.setImageResource(R.drawable.ailvl2icon);
            p1IconNum = 2;
        }
        if (p1IconNum == 0 && wasClicked == false){
            iconBtn.setImageResource(R.drawable.ailvl1icon);
            p1IconNum = 1;
        }
        final int result = 1;
    }*/

    public void onP2IconClick(View view) {
        ImageButton iconBtn = (ImageButton) findViewById(R.id.aiIcon);
        boolean wasClicked = false;
        if (p2IconNum == 3){
            iconBtn.setImageResource(R.drawable.humanicon);
            p2IconNum = 0;
            wasClicked = true;
            penteObj.setPlayerMode(1);
            penteObj.setPlayer2Type(0);
        }
        if (p2IconNum == 2){
            iconBtn.setImageResource(R.drawable.ailvl3icon);
            p2IconNum = 3;
            penteObj.setPlayerMode(0);
            penteObj.setPlayer2Type(3);
        }
        if (p2IconNum == 1){
            iconBtn.setImageResource(R.drawable.ailvl2icon);
            p2IconNum = 2;
            penteObj.setPlayer2Type(2);
        }
        if (p2IconNum == 0 && wasClicked == false){
            iconBtn.setImageResource(R.drawable.ailvl1icon);
            p2IconNum = 1;
            penteObj.setPlayer2Type(1);
        }
        final int result = 1;
    }

    public void onP1ColorClick(View view) {
        ImageButton colorBtn = (ImageButton) findViewById(R.id.humancolor);
        ImageButton colorBtn2 = (ImageButton) findViewById(R.id.aicolor);
        p1ColorNum = 3 - p1ColorNum;
        p2ColorNum = 3 - p2ColorNum;
        if (p1ColorNum == 1){
            colorBtn.setImageResource(R.drawable.colorwhite);
            colorBtn2.setImageResource(R.drawable.colorblack);
            penteObj.setPlayerColors(true);
            penteObj.setAIColor(false);
        }
        if (p1ColorNum == 2){
            colorBtn.setImageResource(R.drawable.colorblack);
            colorBtn2.setImageResource(R.drawable.colorwhite);
            penteObj.setPlayerColors(false);
            penteObj.setAIColor(true);
        }
    }
    public void onP2ColorClick(View view) {
        ImageButton colorBtn = (ImageButton) findViewById(R.id.aicolor);
        ImageButton colorBtn2 = (ImageButton) findViewById(R.id.humancolor);
        p2ColorNum = 3 - p2ColorNum;
        p1ColorNum = 3 - p1ColorNum;
        if (p2ColorNum == 1){
            colorBtn.setImageResource(R.drawable.colorwhite);
            colorBtn2.setImageResource(R.drawable.colorblack);
            penteObj.setPlayerColors(false);
            penteObj.setAIColor(true);
        }
        if (p2ColorNum == 2){
            colorBtn.setImageResource(R.drawable.colorblack);
            colorBtn2.setImageResource(R.drawable.colorwhite);
            penteObj.setPlayerColors(true);
            penteObj.setAIColor(false);
        }
    }

    public void onConfigBackClick(View view) {
        Intent getScreenIntent = new Intent(this, MainActivity.class);
        final int result = 1;
        startActivity(getScreenIntent);
    }

    public void onConfigPlayClick(View view) {
        Intent getScreenIntent = new Intent(this, PlayingGame.class);
        penteObj.setBoardSize(1);
        //penteObj.logPenteObj();
        final int result = 1;
        getScreenIntent.putExtra("Pente", penteObj);
        startActivity(getScreenIntent);
    }
}
