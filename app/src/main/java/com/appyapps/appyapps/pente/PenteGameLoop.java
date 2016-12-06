package com.appyapps.appyapps.pente;

import java.util.Arrays;

public class PenteGameLoop {

    public Pente pente = new Pente();
    public int[] userMove = new int[2];
    public boolean isMoveLegal;
    public boolean isMoveLegal2;
    public boolean isMoveLegal3;
    public boolean isMatchEnd;
    public PenteComputerPlayer computerPlayer = new PenteComputerPlayer();

    public PenteGameLoop(){

    }

    public void AIvsAILoop(){
        pente.setGameVariation(0);
        pente.setBoardSize(0);
        computerPlayer.gameBoardLen = pente.gameBoard.length;
        while (pente.isGameInProgress == true){
            if (pente.isPlayer1Turn){
                Pente originalScenario1 = new Pente();
                originalScenario1 = computerPlayer.copyPenteObj(pente);
                originalScenario1.setAIColor(true);
                userMove = computerPlayer.makeMove(true,originalScenario1);
            }
            else if (!pente.isPlayer1Turn){
                Pente originalScenario = new Pente();
                originalScenario = computerPlayer.copyPenteObj(pente);
                userMove = computerPlayer.makeMove(false,originalScenario);
            }
            isMoveLegal = pente.isLegalMove(userMove);
            //isMoveLegal2 = pente.isFirstMoveOnCenter(userMove);
            if (isMoveLegal == true /*&& isMoveLegal2 == true*/) {
                pente.placePiece(userMove);
                pente.findCaptures(userMove);
                pente.calculateCurrentMatchScore();
                pente.isMatchOver = pente.determineIfMatchEnds();
                pente.drawTextBoard();
                if (pente.isMatchOver == true && !pente.isSingleMatch) {
                    pente.setupNewMatch();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    pente.drawTextBoard();
                }
                else if (isMatchEnd == true && pente.isSingleMatch){
                    pente.isGameInProgress = false;

                }
                else {
                    pente.whitesMatchPoints = 0;
                    pente.blacksMatchPoints = 0;

                }

                if (pente.determineIfEndOfGame() == true){
                    pente.isGameInProgress = false;
                }

                pente.changeTurns(pente.isPlayer1Turn);
            }

        }
    }
    public void StandardGameLoop(){
        pente.setGameVariation(0);
        pente.setBoardSize(0);
        computerPlayer.gameBoardLen = pente.gameBoard.length;
        while (pente.isGameInProgress == true){
            if (pente.isPlayer1Turn){
                userMove = pente.askUserForInput();
                System.out.print("userMove: " + Arrays.toString(userMove));
            }
            else if (!pente.isPlayer1Turn){
                Pente originalScenario = new Pente();
                originalScenario = computerPlayer.copyPenteObj(pente);
                userMove = computerPlayer.makeMove(false,originalScenario);
            }
            isMoveLegal = pente.isLegalMove(userMove);
            //isMoveLegal2 = pente.isFirstMoveOnCenter(userMove);
            if (isMoveLegal == true /*&& isMoveLegal2 == true*/) {
                pente.placePiece(userMove);
                pente.findCaptures(userMove);
                pente.calculateCurrentMatchScore();
                pente.isMatchOver = pente.determineIfMatchEnds();
                pente.drawTextBoard();
                if (pente.isMatchOver == true && !pente.isSingleMatch) {
                    pente.setupNewMatch();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    pente.drawTextBoard();
                }
                else if (isMatchEnd == true && pente.isSingleMatch){
                    pente.isGameInProgress = false;

                }
                else {
                    pente.whitesMatchPoints = 0;
                    pente.blacksMatchPoints = 0;

                }

                if (pente.determineIfEndOfGame() == true){
                    pente.isGameInProgress = false;
                }

                pente.changeTurns(pente.isPlayer1Turn);
            }

        }
    }

    public void TournamentGameLoop(){
        pente.setGameVariation(1);
        while (pente.isGameInProgress == true){
            userMove = pente.askUserForInput();
            isMoveLegal = pente.isLegalMove(userMove);
            isMoveLegal2 = pente.isFirstMoveOnCenter(userMove);
            isMoveLegal3 = pente.isSecondMoveLegal(userMove);
            if (isMoveLegal == true && isMoveLegal2 == true && isMoveLegal3 == true) {
                pente.placePiece(userMove);
                pente.findCaptures(userMove);
                pente.calculateCurrentMatchScore();
                pente.isMatchOver = pente.determineIfMatchEnds();
                //pente.drawTextBoard();
                if (pente.isMatchOver == true && !pente.isSingleMatch) {
                    pente.setupNewMatch();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    //pente.drawTextBoard();
                }
                else if (isMatchEnd == true && pente.isSingleMatch){
                    pente.isGameInProgress = false;

                }
                else {
                    pente.whitesMatchPoints = 0;
                    pente.blacksMatchPoints = 0;

                }
                if (pente.determineIfEndOfGame() == true){
                    pente.isGameInProgress = false;
                }
                pente.changeTurns(pente.isPlayer1Turn);

            }

        }
    }

    public void KeryoGameLoop(){
        pente.setGameVariation(2);
        while (pente.isGameInProgress == true){
            userMove = pente.askUserForInput();
            isMoveLegal = pente.isLegalMove(userMove);
            isMoveLegal2 = pente.isFirstMoveOnCenter(userMove);
            if (isMoveLegal == true && isMoveLegal2 == true) {
                pente.placePiece(userMove);
                pente.findKeryoCaptures(userMove);
                pente.findCaptures(userMove);
                pente.calculateCurrentMatchScore();
                pente.isMatchOver = pente.determineIfMatchEnds();
                //pente.drawTextBoard();
                if (pente.isMatchOver == true && !pente.isSingleMatch) {
                    pente.setupNewMatch();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    //pente.drawTextBoard();
                }
                else if (isMatchEnd == true && pente.isSingleMatch){
                    pente.isGameInProgress = false;

                }
                else {
                    pente.whitesMatchPoints = 0;
                    pente.blacksMatchPoints = 0;

                }
                if (pente.determineIfEndOfGame() == true){
                    pente.isGameInProgress = false;
                }
                pente.changeTurns(pente.isPlayer1Turn);

            }

        }
    }

    public void FreestyleGameLoop(){
        pente.setGameVariation(3);
        while (pente.isGameInProgress == true){
            userMove = pente.askUserForInput();
            isMoveLegal = pente.isLegalMove(userMove);
            if (isMoveLegal == true) {
                pente.placePiece(userMove);
                pente.findCaptures(userMove);
                pente.calculateCurrentMatchScore();
                pente.isMatchOver = pente.determineIfMatchEnds();
                //pente.drawTextBoard();
                if (pente.isMatchOver == true && !pente.isSingleMatch) {
                    pente.setupNewMatch();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    //pente.drawTextBoard();
                }
                else if (isMatchEnd == true && pente.isSingleMatch){
                    pente.isGameInProgress = false;

                }
                else {
                    pente.whitesMatchPoints = 0;
                    pente.blacksMatchPoints = 0;

                }
                if (pente.determineIfEndOfGame() == true){
                    pente.isGameInProgress = false;
                }
                pente.changeTurns(pente.isPlayer1Turn);

            }

        }
    }

    public void fiveInARowGameLoop(){
        pente.setGameVariation(4);
        pente.setPointsToWin(2);
        while (pente.isGameInProgress == true){
            userMove = pente.askUserForInput();
            isMoveLegal = pente.isLegalMove(userMove);
            if (isMoveLegal == true) {
                pente.placePiece(userMove);
                pente.findCaptures(userMove);
                pente.calculateCurrentMatchScore();
                pente.isMatchOver = pente.determineIfMatchEnds();
                //pente.drawTextBoard();
                if (pente.isMatchOver == true && !pente.isSingleMatch) {
                    pente.setupNewMatch();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    //pente.drawTextBoard();
                }
                else if (isMatchEnd == true && pente.isSingleMatch){
                    pente.isGameInProgress = false;

                }
                else {
                    pente.whitesMatchPoints = 0;
                    pente.blacksMatchPoints = 0;

                }
                if (pente.determineIfEndOfGame() == true){
                    pente.isGameInProgress = false;
                }
                pente.changeTurns(pente.isPlayer1Turn);

            }

        }
    }

    public void noCapturesGameLoop(){
        pente.setGameVariation(5);
        pente.setPointsToWin(2);
        while (pente.isGameInProgress == true){
            userMove = pente.askUserForInput();
            isMoveLegal = pente.isLegalMove(userMove);
            if (isMoveLegal == true) {
                pente.placePiece(userMove);
                pente.isMatchOver = pente.determineIfMatchEnds();
                //pente.drawTextBoard();
                if (pente.isMatchOver == true && !pente.isSingleMatch) {
                    pente.setupNewMatch();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    //pente.drawTextBoard();
                }
                else if (isMatchEnd == true && pente.isSingleMatch){
                    pente.isGameInProgress = false;

                }
                else {
                    pente.whitesMatchPoints = 0;
                    pente.blacksMatchPoints = 0;

                }
                if (pente.determineIfEndOfGame() == true){
                    pente.isGameInProgress = false;
                }
                pente.changeTurns(pente.isPlayer1Turn);

            }

        }
    }
}

