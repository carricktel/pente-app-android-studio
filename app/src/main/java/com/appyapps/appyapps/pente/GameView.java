package com.appyapps.appyapps.pente;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.util.AttributeSet;
import android.view.ViewGroup;

import java.util.Arrays;

/**
 * Created by Joseph Carrick on 11/26/2016.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    public float SCREENHEIGHT;
    public float SCREENWIDTH;
    public float boardOriginW;
    public float boardOriginH;
    public float gameboardLength;
    public float widthTo0x0Coord;
    public float heightTo0x0Coord;
    public float gamepieceLength;
    public float midGPWidth;
    public float midGPHeight;
    public float distanceBetweenCoords;

    public float player1IconOriginWidth;
    public float player1IconOriginHeight;
    public float player2IconOriginWidth;
    public float player2IconOriginHeight;


    public Pente pente = PlayingGame.returnPente();
    public PenteComputerPlayer computerPlayer = new PenteComputerPlayer();
    public int[] userMove = new int[2];
    public boolean isMoveLegal;
    public boolean isMoveLegal2;
    public boolean isMoveLegal3;
    public boolean isMatchEnd;
    public boolean isListening = false;
    public long cutoffTimeForTouchEvents = 0L;
    public Canvas canvas;
    public SurfaceHolder surfaceHolder;
    public GameView gameView;


    public Bitmap background = BitmapFactory.decodeResource(getResources(),R.drawable.playgame);
    public Bitmap gameboard = BitmapFactory.decodeResource(getResources(),R.drawable.gameboard);
    public Bitmap bluepiece = BitmapFactory.decodeResource(getResources(),R.drawable.bluepiece);
    public Bitmap redpiece = BitmapFactory.decodeResource(getResources(),R.drawable.redpiece);
    public Bitmap whitepiece = BitmapFactory.decodeResource(getResources(),R.drawable.whitepiece);
    public Bitmap whitepiecetest = BitmapFactory.decodeResource(getResources(),R.drawable.whitepiecetest);
    public Bitmap blackpiece = BitmapFactory.decodeResource(getResources(),R.drawable.blackpiece);

    public GameView(Context context) {
        super(context);

        this.getHolder().addCallback(this);
        pente.logPenteObj();



    }
    protected void drawBoard() {
        //super.onDraw(canvas);
        try {
            canvas = surfaceHolder.lockCanvas(null);
            synchronized (surfaceHolder) {

                canvas.drawColor(Color.BLACK);
                canvas.drawBitmap(background, 0, 0, new Paint());
                canvas.drawBitmap(gameboard, boardOriginW, boardOriginH, new Paint());
                int[] boardCoords = new int[2];
                for (int i = 0; i < pente.whiteMoves.size(); i++) {
                    float[] screenCoords = translateBoardCoordsToScreenCoords(pente.whiteMoves.get(i));
                    drawPiece((int) screenCoords[0], (int) screenCoords[1], 0, canvas);
                }
                for (int i = 0; i < pente.blackMoves.size(); i++) {
                    float[] screenCoords = translateBoardCoordsToScreenCoords(pente.blackMoves.get(i));
                    drawPiece((int) screenCoords[0], (int) screenCoords[1], 1, canvas);
                }
                Log.i("Whites Match Points",Integer.toString(pente.whitesMatchPoints));
                Log.i("Whites Captures",Integer.toString(pente.whitesTotalCaptures));
                Log.i("Whites Total Points",Integer.toString(pente.whitesTotalPoints));
                Log.i("--------------------","-----");
                Log.i("Blacks Match Points",Integer.toString(pente.blacksMatchPoints));
                Log.i("Blacks Captures",Integer.toString(pente.blacksTotalCaptures));
                Log.i("Blacks Total Points",Integer.toString(pente.blacksTotalPoints));
//                canvas.drawBitmap(whitepiecetest, widthTo0x0Coord + distanceBetweenCoords/2 - gamepieceLength/2 + (6-1)*distanceBetweenCoords, heightTo0x0Coord + distanceBetweenCoords/2 - gamepieceLength/2 + (6-1)*distanceBetweenCoords, new Paint());
//                canvas.drawBitmap(whitepiecetest, heightTo0x0Coord + distanceBetweenCoords/2 + gamepieceLength/2 + (6-1)*distanceBetweenCoords, heightTo0x0Coord + distanceBetweenCoords/2 + gamepieceLength/2 + (6-1)*distanceBetweenCoords, new Paint());
//                canvas.drawBitmap(whitepiecetest, widthTo0x0Coord + distanceBetweenCoords/2 - gamepieceLength/2 + (7-1)*distanceBetweenCoords, heightTo0x0Coord + distanceBetweenCoords/2 - gamepieceLength/2 + (7-1)*distanceBetweenCoords, new Paint());
//                canvas.drawBitmap(whitepiecetest, heightTo0x0Coord + distanceBetweenCoords/2 + gamepieceLength/2 + (7-1)*distanceBetweenCoords, heightTo0x0Coord + distanceBetweenCoords/2 + gamepieceLength/2 + (7-1)*distanceBetweenCoords, new Paint());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (canvas != null) {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }

    }
    public void drawPiece(int w, int h, int color, Canvas canvas){
        if(color == 0) {
            canvas.drawBitmap(whitepiece, w, h, new Paint());
        }
        if(color == 1) {
            canvas.drawBitmap(blackpiece, w, h, new Paint());
        }
    }

    public float[] translateBoardCoordsToScreenCoords(int[] coords){
        float[] screenCoords = new float[2];
        float w = coords[0] * distanceBetweenCoords + widthTo0x0Coord - midGPWidth;
        float h = coords[1] * distanceBetweenCoords + heightTo0x0Coord - midGPHeight;
        screenCoords[0] = w;
        screenCoords[1] = h;
        return screenCoords;
    }
    private int[] translateScreenCoordsToBoardCoords(float[] coords) {
        float xCoord = coords[0];
        float yCoord = coords[1];
        float widthToCoord0x0center = widthTo0x0Coord + distanceBetweenCoords/2;
        float heightToCoord0x0center = heightTo0x0Coord + distanceBetweenCoords/2;
        Log.i("X",String.valueOf(coords[0]));
        Log.i("Y",String.valueOf(coords[1]));
        Log.i("0x0",String.valueOf(widthTo0x0Coord));
        Log.i("0x0",String.valueOf(heightTo0x0Coord));

        int[] boardCoords = new int[2];
        for (int i = 0; i < computerPlayer.gameBoardLen; i++){
            for (int j = 0; j < computerPlayer.gameBoardLen; j++){
                if (xCoord > (widthToCoord0x0center + ((j-1)*distanceBetweenCoords)) &&
                        xCoord < (widthToCoord0x0center + j*distanceBetweenCoords) &&
                        yCoord > (heightToCoord0x0center + ((i-1)*distanceBetweenCoords)) &&
                        yCoord < (heightToCoord0x0center) + i*distanceBetweenCoords){
                    boardCoords[0] = j;
                    boardCoords[1] = i;
                    return boardCoords;
                }
            }
        }
        return boardCoords;
    }

    public void getDrawableDimensions(Canvas canvas, SurfaceHolder surfaceHolder){
        canvas = surfaceHolder.lockCanvas();
        SCREENHEIGHT = canvas.getHeight();
        SCREENWIDTH = canvas.getWidth();
        surfaceHolder.unlockCanvasAndPost(canvas);
        boardOriginH = SCREENHEIGHT/8;
        boardOriginW = SCREENWIDTH/20;
        gameboardLength = SCREENWIDTH - (2*boardOriginW);
        widthTo0x0Coord = boardOriginW + gameboardLength/24;// + gameboardLength/50;
        heightTo0x0Coord = boardOriginH + gameboardLength/24;// + gameboardLength/50;
        gamepieceLength = gameboardLength/15;
        midGPWidth = gamepieceLength/2;
        midGPHeight = 7*gamepieceLength/19;
        distanceBetweenCoords = 77*gameboardLength/1000;

    }
    public void setNewDrawableDimensions(){
        gameboard = Bitmap.createScaledBitmap(gameboard,(int) (SCREENWIDTH - (2*boardOriginW)),(int) (SCREENWIDTH - (2*boardOriginW)),false);
        whitepiece = Bitmap.createScaledBitmap(whitepiece, (int) gamepieceLength, (int) gamepieceLength,false);
        background = Bitmap.createScaledBitmap(background,(int) SCREENWIDTH, (int) SCREENHEIGHT,false);

    }

    public void runAITurn(){
        Pente originalScenario = new Pente();
        originalScenario = computerPlayer.copyPenteObj(pente);
        userMove = computerPlayer.makeMove(false, originalScenario);
        isMoveLegal = pente.isLegalMove(userMove);
        //isMoveLegal2 = pente.isFirstMoveOnCenter(userMove);
        if (isMoveLegal == true /*&& isMoveLegal2 == true*/) {
            pente.placePiece(userMove);
            pente.findCaptures(userMove);
            pente.calculateCurrentMatchScore();
            pente.isMatchOver = pente.determineIfMatchEnds();
            drawBoard();
            if (pente.isMatchOver == true && !pente.isSingleMatch /*&& pente.determineIfEndOfGame() == false*/){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if(pente.determineIfEndOfGame() == false) {
                    drawBoard();
                }
            } else if (isMatchEnd == true && pente.isSingleMatch) {
                pente.isGameInProgress = false;

            } else {
                pente.whitesMatchPoints = 0;
                pente.blacksMatchPoints = 0;

            }

            if (pente.determineIfEndOfGame() == true) {
                pente.isGameInProgress = false;
            }

            pente.changeTurns(pente.isPlayer1Turn);
        }
    }

    public void runHumanTurn(int[] coords){
        if(pente.isPlayer1Turn) {
            userMove = coords;
            isMoveLegal = pente.isLegalMove(userMove);
            //isMoveLegal2 = pente.isFirstMoveOnCenter(userMove);
            if (isMoveLegal == true /*&& isMoveLegal2 == true*/) {
                pente.placePiece(userMove);
                pente.findCaptures(userMove);
                pente.calculateCurrentMatchScore();
                pente.isMatchOver = pente.determineIfMatchEnds();
                drawBoard();
                if (pente.isMatchOver == true && !pente.isSingleMatch/* && pente.determineIfEndOfGame() == false*/) {
                    pente.setupNewMatch();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    if(pente.determineIfEndOfGame() == false) {
                        drawBoard();
                    }
                } else if (isMatchEnd == true && pente.isSingleMatch) {
                    pente.isGameInProgress = false;

                } else {
                    pente.whitesMatchPoints = 0;
                    pente.blacksMatchPoints = 0;

                }

                if (pente.determineIfEndOfGame() == true) {
                    pente.isGameInProgress = false;
                }

                pente.changeTurns(pente.isPlayer1Turn);
            }
        }
    }
    public void runHuman2Turn(int[] coords){
        if (pente.isPlayer2Turn) {
            userMove = coords;
            isMoveLegal = pente.isLegalMove(userMove);
            //isMoveLegal2 = pente.isFirstMoveOnCenter(userMove);
            if (isMoveLegal == true /*&& isMoveLegal2 == true*/) {
                pente.placePiece(userMove);
                pente.findCaptures(userMove);
                pente.calculateCurrentMatchScore();
                pente.isMatchOver = pente.determineIfMatchEnds();
                drawBoard();
                if (pente.isMatchOver == true && !pente.isSingleMatch) {
                    pente.setupNewMatch();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    if(pente.determineIfEndOfGame() == false) {
                        drawBoard();
                    }
                } else if (isMatchEnd == true && pente.isSingleMatch) {
                    pente.isGameInProgress = false;

                } else {
                    pente.whitesMatchPoints = 0;
                    pente.blacksMatchPoints = 0;

                }

                if (pente.determineIfEndOfGame() == true) {
                    pente.isGameInProgress = false;
                }

                pente.changeTurns(pente.isPlayer1Turn);
            }
        }
    }

    public boolean touchInBounds(float[] coords)
    {
        if(coords[0] > widthTo0x0Coord && coords[0] < widthTo0x0Coord+gameboardLength && coords[1] > heightTo0x0Coord && coords[1] < heightTo0x0Coord+gameboardLength){
            return true;
        }
        else {
            return false;
        }
    }

    public void testDraw(){

        try {
            canvas = surfaceHolder.lockCanvas(null);
            synchronized (surfaceHolder) {

                canvas.drawColor(Color.BLACK);
                canvas.drawBitmap(background, 0, 0, new Paint());
                canvas.drawBitmap(gameboard, boardOriginW, boardOriginH, new Paint());
                int[] boardCoords = new int[2];
                //drawPiece((int) (boardOriginW), (int) (boardOriginH), 0, canvas);
                for(int i = 0; i < 13; i++){
                    for(int j = 0; j < 13; j++){
                        drawPiece((int) (widthTo0x0Coord - midGPWidth + distanceBetweenCoords*i), (int) (heightTo0x0Coord - midGPHeight + distanceBetweenCoords*j), 0, canvas);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (canvas != null) {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float[] coords = new float[2];
        coords[0] = event.getX();
        coords[1] = event.getY();
       //Log.i("Touch Time",String.valueOf(event.getEventTime()));

        if (event.getEventTime() > cutoffTimeForTouchEvents && isListening == true && pente.isGameInProgress == true){
            isListening = false;
            if (touchInBounds(coords)){
                int[] boardCoords = translateScreenCoordsToBoardCoords(coords);
                if(pente.is1PlayerMode) {
                    runHumanTurn(boardCoords);
                    if(pente.isGameInProgress == true) {
                        runAITurn();
                    }
                }
                if(pente.is2PlayerMode){
                    if(pente.isPlayer1Turn) {
                        runHumanTurn(boardCoords);
                    }
                    else if(pente.isPlayer2Turn) {
                        runHuman2Turn(boardCoords);
                    }
                }
            }
            else{
                runAITurn();
            }
            cutoffTimeForTouchEvents = SystemClock.uptimeMillis();
            //Log.i("Up Time",String.valueOf(SystemClock.uptimeMillis()));
            isListening = true;
            return super.onTouchEvent(event);
        }
        return true;
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        //TODO
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceholder) {
        canvas = null;
        computerPlayer.setDifficulty(pente.player2Type);
        surfaceHolder = surfaceholder;
        getDrawableDimensions(canvas, surfaceHolder);
        setNewDrawableDimensions();
        gameView = (GameView) findViewById(0);
        //testDraw();
        drawBoard();
        if(pente.isPlayer2Turn && pente.player2Type != 0){
            runAITurn();
        }
        isListening = true;
        //onDrawBG(canvas,surfaceHolder);

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        Log.i("Surface","Destroyed");
        //TODO
    }
}
