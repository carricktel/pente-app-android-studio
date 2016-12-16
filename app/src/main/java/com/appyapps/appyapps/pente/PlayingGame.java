package com.appyapps.appyapps.pente;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.RelativeLayout;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;

/**
 * Created by Joseph Carrick on 11/26/2016.
 */

public class PlayingGame extends Activity {
    public static Pente penteObj = new Pente();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //MobileAds.initialize(getApplicationContext(), "ca-app-pub-4041696999574779/5202552046");

        penteObj = (Pente) getIntent().getSerializableExtra("Pente");
        //penteObj.logPenteObj();
        setContentView(R.layout.playing_game);
        //this.sv = (SurfaceView) findViewById(R.id.surfaceview);
        //this.holder = this.sv.getHolder();
        //this.holder.addCallback(this);

        RelativeLayout playgame = (RelativeLayout) findViewById(R.id.playinggame);
        GameView gameView = new GameView(this);
        playgame.addView(gameView);
        gameView.setId(0);
        //GameView.getHolder().setFixedSize(410,410);

        //SurfaceView sv = (SurfaceView) findViewById(R.id.surfaceview);
        Log.i("JC","surface created 1");
        //SurfaceHolder holder = sv.getHolder();

    }

    static public Pente returnPente(){
        Pente pente;
        pente = penteObj;
        return pente;
    }

/*    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.i("JC", "surface created 2");
        onDraw(holder);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int arg1, int arg2, int arg3) {
        //Log.i("JC", "surface changed 2");
        //onDraw(holder);
    }*/


/*    private void onDraw(SurfaceHolder sh) {
        Log.i("JC", "surface created 3");
        Canvas canvas = sh.lockCanvas();
        try {
            Log.i("JC", "surface created 4");
            synchronized (sh) {
                Log.i("JC", "surface created 6");
                Bitmap icon = BitmapFactory.decodeResource(getResources(),R.drawable.humanicon);
                //Canvas canvas2 = new Canvas();
                Log.i("JC","draw1");
                //sv.setBackgroundColor(Color.BLACK);
                canvas.drawColor(Color.BLACK);
                Log.i("JC","draw2");
                //canvas2.drawBitmap(icon, 1, 10, new Paint());
                //sv.draw(canvas2);
                canvas.drawBitmap(icon, 1, 10, new Paint());
                Log.i("JC","draw3");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (canvas != null) {
                Log.i("JC", "surface created 7");
            }
        }
        sh.unlockCanvasAndPost(canvas);

    }*/
}
