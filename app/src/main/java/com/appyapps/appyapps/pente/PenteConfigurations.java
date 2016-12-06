package com.appyapps.appyapps.pente;

import java.util.ArrayList;

public class PenteConfigurations {

    /* open two = 1*/
    public int confType;

    /* direction is degree between 0 and 315 where 0 is right,
     *  and degrees increase counter-clockwise*/
    public int direction;
    public boolean isWhite;
    public int whitePriority;
    public int blackPriority;
    public ArrayList<int[]> whiteCounterMoves = new ArrayList<int[]>();
    public ArrayList<int[]> blackCounterMoves = new ArrayList<int[]>();
    public int[] originCoord;
    public int whiteEval;
    public int blackEval;

    public void PenteConfigurations(){

        this.direction = direction;
        this.confType = confType;
        this.isWhite = isWhite;
        this.whitePriority = whitePriority;
        this.blackPriority = blackPriority;
        this.whiteCounterMoves = whiteCounterMoves;
        this.blackCounterMoves = blackCounterMoves;
        this.originCoord = originCoord;
        this.whiteEval =  whiteEval;
        this.blackEval = blackEval;
    }
}

