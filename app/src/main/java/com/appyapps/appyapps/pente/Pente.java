package com.appyapps.appyapps.pente;

import android.util.Log;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Pente implements Cloneable, Serializable {
    public boolean isStandardRules;
    public boolean isTournamentRules;
    public boolean isKeryoRules;
    public boolean isFreestyleRules;
    public boolean is5InARowRules;
    public boolean isNoCapturesRules;

    public boolean is19Board;
    public boolean is13Board;

    public boolean is1PlayerMode;
    public boolean is2PlayerMode;

    public boolean isSingleMatch;
    public boolean is50PointsToWin;
    public boolean is21PointsToWin;

    public boolean isPlayer1White;

    public boolean isPlayer1Turn;
    public boolean isPlayer2Turn;

    public boolean isGameInProgress;
    public boolean isMatchOver;

    public boolean isWhiteWinner;
    public boolean isBlackWinner;

    public boolean isAIWhite;

    public int player1Type;
    public int player2Type;

    public int blacksMatchPoints;
    public int whitesMatchPoints;

    public int blacksTotalPoints;
    public int whitesTotalPoints;

    public int blacksTotalCaptures;
    public int whitesTotalCaptures;

    public int blacksCapturedStones;
    public int whitesCapturedStones;

    public int previousPlayer;

    public ArrayList<int[]> whiteMoves = new ArrayList<int[]>();
    public ArrayList<int[]> blackMoves = new ArrayList<int[]>();

    public int movesCount;

    char[][] board19x19 = {{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}};

    char[][] board13x13 = {{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}};

    char[][] gameBoard = board13x13;

    public Pente(){

        isStandardRules = true;
        isTournamentRules = false;
        isKeryoRules = false;
        isFreestyleRules = false;
        is5InARowRules = false;
        isNoCapturesRules = false;
        is19Board = false;
        is13Board = true;
        is2PlayerMode = false;
        is1PlayerMode = true;

        isSingleMatch = true;
        is50PointsToWin = false;
        is21PointsToWin = false;

        isPlayer1White = true;
        isPlayer1Turn = true;
        isPlayer2Turn = false;
        isGameInProgress = true;
        isMatchOver = false;
        isWhiteWinner = false;
        isBlackWinner = false;
        isAIWhite = false;
        player1Type = 0;
        player2Type = 1;

        blacksMatchPoints = 0;
        whitesMatchPoints = 0;
        blacksTotalPoints = 0;
        whitesTotalPoints = 0;
        blacksTotalCaptures = 0;
        whitesTotalCaptures = 0;
        movesCount = 0;

        previousPlayer = 0;

        this.gameBoard = board13x13;


    }

    public Object clone(){
        try{
            return super.clone();
        }catch(Exception e){
            return null;
        }
    }

    /**
     * Logs pente object attributes to console
     *
     * @param
     * @return void
     */
    public void logPenteObj(){
        Log.i("SingleMatch",String.valueOf(isSingleMatch));
        Log.i("21Match",String.valueOf(is21PointsToWin));
        Log.i("50Match",String.valueOf(is50PointsToWin));

        Log.i("P1White",String.valueOf(isPlayer1White));
        Log.i("aiWhite",String.valueOf(isAIWhite));

        Log.i("aiLevel",Integer.toString(player2Type));

    }

    /**
     * Setter for game variation.
     *
     * Sets attributes isStandardRules, isTournamentRules,
     * isKeryoRules, isFreestyleRules, is5InARowRules and
     * isNoCapturesRules accordingly to reflect the game
     * variation given an integer from 0 to 5.
     *
     * 0 = Standard Rules
     * 1 = Tournament Rules
     * 2 = Keryo Rules
     * 3 = Freestyle Rules
     * 4 = 5-in-a-row Rules
     * 5 = No Captures Rules
     *
     * @param int variation
     * @return void
     */
    public void setGameVariation(int variation){
        isStandardRules = false;
        isTournamentRules = false;
        isKeryoRules = false;
        isFreestyleRules = false;
        is5InARowRules = false;
        isNoCapturesRules = false;
        if (variation == 0){
            isStandardRules = true;
        }
        else if (variation == 1){
            isTournamentRules = true;
        }
        else if (variation == 2){
            isKeryoRules = true;
        }
        else if (variation == 3){
            isFreestyleRules = true;
        }
        else if (variation == 4){
            is5InARowRules = true;
        }
        else if (variation == 5){
            isNoCapturesRules = true;
        }
        else {
            System.out.println("Incorrect input for setGameVariation, must be integer from 0 to 5.");
        }
    }


    /**
     * Setter for board size.
     *
     * Sets attributes is19Board, is13Board and gameBoard,
     * accordingly to reflect the chosen board size given
     * an integer of either 0 or 1.
     *
     * 0 = 19x19
     * 1 = 13x13
     *
     * @param int boardtype
     * @return void
     */
    public void setBoardSize(int boardtype){
        if (boardtype == 0){
            gameBoard = board19x19;
            is19Board = true;
            is13Board = false;
        }
        else if (boardtype == 1) {
            gameBoard = board13x13;
            is19Board = false;
            is13Board = true;
        }
        else {
            System.out.println("Incorrect input for setBoardSize, must be integer 0 or 1.");
        }
    }


    /**
     * Setter for player mode.
     *
     * Sets attributes is1PlayerMode and is2PlayerMode
     * accordingly to reflect the player mode (either
     * single or 2 player) given an integer 0 or 1.
     * In single player mode, the player plays against
     * the CPU. In 2 player mode, the player plays against
     * another human player.
     *
     * 0 = Single Player
     * 1 = 2 Player
     *
     * @param int playermode
     * @return void
     */
    public void setPlayerMode(int playermode){
        if (playermode == 0){
            is1PlayerMode = true;
            is2PlayerMode = false;
        }
        else if (playermode == 1){
            is1PlayerMode = false;
            is2PlayerMode = true;
        }
        else {
            System.out.println("Incorrect input for setPlayerMode, must be integer 0 or 1.");
        }
    }


    /**
     * Setter for amount of points to win the game.
     *
     * Sets attributes is50PointsToWin and is21PointsToWin
     * accordingly to reflect the amount of points to win
     * the game by given an integer 0 or 1.
     *
     * 0 = 50 points
     * 1 = 21 points
     *
     * @param int pointsmode
     * @return void
     */
    public void setPointsToWin(int pointsmode){
        if (pointsmode == 0){
            is50PointsToWin = true;
            is21PointsToWin = false;
            isSingleMatch = false;
        }
        else if(pointsmode == 1){
            is50PointsToWin = false;
            is21PointsToWin = true;
            isSingleMatch = false;
        }
        else if(pointsmode == 2){
            is50PointsToWin = false;
            is21PointsToWin = false;
            isSingleMatch = true;
        }
        else {
            System.out.println("Incorrect input for setPointsToWin, must be integer 0, 1 or 2.");
        }
    }


    /**
     * Setter for players' colors.
     *
     * Sets attribute isPlayer1White accordingly to reflect
     * which player is white and which is black given a
     * boolean.
     *
     * true = Player 1 is white, player 2 is black
     * false = Player 1 is black, player 2 is white
     *
     * @param boolean isP1White
     * @return void
     */
    public void setPlayerColors(boolean isP1White){
        if(isP1White == true){
            isPlayer1White = true;
        }
        else if(isP1White == false){
            isPlayer1White = false;
        }
    }

    /**
     * Setter for AI color.
     *
     * Sets attribute isAIWhite accordingly to reflect
     * the color of the AI.
     *
     * true = AI is white
     * false = AI is black
     *
     * @param boolean isAIWhite
     * @return void
     */
    public void setAIColor(boolean isWhite){
        if(isWhite){
            isAIWhite = true;
        }
        else if(!isWhite){
            isAIWhite = false;
        }
    }

    /**
     * Setter for player1 type.
     *
     * Sets attribute player1Type
     *
     * 0 = human
     * 1 = level 1 AI
     * 2 = level 2 AI
     * 3 = level 3 AI
     *
     * @param int playertype
     * @return void
     */
    public void setPlayer1Type(int playertype){
        player1Type = playertype;
    }

    /**
     * Setter for player2 type.
     *
     * Sets attribute player2Type
     *
     * 0 = human
     * 1 = level 1 AI
     * 2 = level 2 AI
     * 3 = level 3 AI
     *
     * @param int playertype
     * @return void
     */
    public void setPlayer2Type(int playertype){
        player2Type = playertype;
    }



    /**
     * Setter for player turn.
     *
     * Sets attribute isPlayer1Turn accordingly to reflect
     * which player's turn it currently is given a
     * boolean.
     *
     * true = It is player 1's turn.
     * false = It is player 2's turn.
     *
     * @param boolean isP1Turn
     * @return void
     */
    public void setPlayer1Turn(boolean isP1Turn){
        if(isP1Turn == true){
            isPlayer1Turn = true;
            isPlayer2Turn = false;
        }
        else if(isP1Turn == false){
            isPlayer1Turn = false;
            isPlayer2Turn = true;
        }
    }


    /**
     * Getter for game variation
     *
     * returns integer between 0 and 5 relating to the
     * current game variation.
     *
     * 0 = Standard Rules
     * 1 = Tournament Rules
     * 2 = Keryo Rules
     * 3 = Freestyle Rules
     * 4 = 5-in-a-row Rules
     * 5 = No Captures Rules
     *
     * @return int variation
     */
    public int getGameVariation(){
        if (isStandardRules == true){
            return 0;
        }
        else if (isTournamentRules == true){
            return 1;
        }
        else if (isKeryoRules == true){
            return 2;
        }
        else if (isFreestyleRules == true){
            return 3;
        }
        else if (is5InARowRules == true){
            return 4;
        }
        else return 5;
    }


    /**
     * Getter for board size
     *
     * returns an integer of 0 or 1 relating to the current
     * board size.
     *
     * 0 = 19x19
     * 1 = 13x13
     *
     * @return int boardtype
     */
    public int getBoardType(){
        if(is19Board == true){
            return 0;
        }
        else return 1;
    }


    /**
     * Getter for player mode
     *
     * returns an integer of 0 or 1 relating to the current
     * player mode.
     *
     * 0 = single player (versus CPU)
     * 1 = two human players
     *
     * @return int playermode
     */
    public int getPlayerMode(){
        if(is1PlayerMode == true){
            return 0;
        }
        else return 1;
    }


    /**
     * Getter for number of points to win by
     *
     * returns an integer of 0 or 1 relating to the current
     * number of points to win by.
     *
     * 0 = 50
     * 1 = 21
     *
     * @return int pointsmode
     */
    public int getPointsToWin(){
        if(is50PointsToWin == true){
            return 0;
        }
        else return 1;
    }

    /**
     * Checks if the inputed coordinate is the first move,
     * and in the center.
     *
     * true = the move is legal
     * false = the move is not legal
     *
     * @return boolean isLegalMove
     */
    public boolean isFirstMoveOnCenter(int[] coords){
        int [] center = {(gameBoard.length - 1) / 2, (gameBoard[0].length - 1) / 2};

        if(movesCount == 0){
            if (!Arrays.equals(coords, center)){
                return false;
            }
            else return true;
        }
        else return true;

    }

    /**
     * Checks if the inputed coordinate is the first move,
     * and in the center.
     *
     * true = the move is legal
     * false = the move is not legal
     *
     * @return boolean isLegalMove
     */
    public boolean isSecondMoveLegal(int[] coords){
        int [] center = {(gameBoard.length - 1) / 2, (gameBoard[0].length - 1) / 2};

        if(movesCount == 2){
            if (!(coords[0] < center[0] - 3 || coords[0] > center[0] + 3 || coords[1] < center[1] - 3 || coords[1] > center[1] + 3)){
                return false;
            }
            else return true;
        }
        else return true;

    }

    /**
     * A temporary method that prints the game board in text.
     */
    public void drawTextBoard(){
        if (is13Board == true){
            System.out.println(" ");
            System.out.println("     0  1  2  3  4  5  6  7  8  9 10 11 12  ");
            System.out.println(" 0  " + Arrays.toString(gameBoard[0]));
            System.out.println(" 1  " + Arrays.toString(gameBoard[1]));
            System.out.println(" 2  " + Arrays.toString(gameBoard[2]));
            System.out.println(" 3  " + Arrays.toString(gameBoard[3]));
            System.out.println(" 4  " + Arrays.toString(gameBoard[4]));
            System.out.println(" 5  " + Arrays.toString(gameBoard[5]));
            System.out.println(" 6  " + Arrays.toString(gameBoard[6]));
            System.out.println(" 7  " + Arrays.toString(gameBoard[7]));
            System.out.println(" 8  " + Arrays.toString(gameBoard[8]));
            System.out.println(" 9  " + Arrays.toString(gameBoard[9]));
            System.out.println(" 10 " + Arrays.toString(gameBoard[10]));
            System.out.println(" 11 " + Arrays.toString(gameBoard[11]));
            System.out.println(" 12 " + Arrays.toString(gameBoard[12]));
            System.out.println(" ");
            System.out.println(" White's Match Points: " + Integer.toString(whitesMatchPoints));
            System.out.println(" White's Total Points: " + Integer.toString(whitesTotalPoints));
            System.out.println(" ");
            System.out.println(" Black's Match Points: " + Integer.toString(blacksMatchPoints));
            System.out.println(" Black's Total Points: " + Integer.toString(blacksTotalPoints));
            System.out.println(" ");
            //System.out.println(" White's Eval: " + Integer.toString(eval));
            System.out.println(" ");
        }
        if (is19Board == true){
            System.out.println(" ");
            System.out.println("     0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18  ");
            System.out.println(" 0  " + Arrays.toString(gameBoard[0]));
            System.out.println(" 1  " + Arrays.toString(gameBoard[1]));
            System.out.println(" 2  " + Arrays.toString(gameBoard[2]));
            System.out.println(" 3  " + Arrays.toString(gameBoard[3]));
            System.out.println(" 4  " + Arrays.toString(gameBoard[4]));
            System.out.println(" 5  " + Arrays.toString(gameBoard[5]));
            System.out.println(" 6  " + Arrays.toString(gameBoard[6]));
            System.out.println(" 7  " + Arrays.toString(gameBoard[7]));
            System.out.println(" 8  " + Arrays.toString(gameBoard[8]));
            System.out.println(" 9  " + Arrays.toString(gameBoard[9]));
            System.out.println(" 10 " + Arrays.toString(gameBoard[10]));
            System.out.println(" 11 " + Arrays.toString(gameBoard[11]));
            System.out.println(" 12 " + Arrays.toString(gameBoard[12]));
            System.out.println(" 13 " + Arrays.toString(gameBoard[13]));
            System.out.println(" 14 " + Arrays.toString(gameBoard[14]));
            System.out.println(" 15 " + Arrays.toString(gameBoard[15]));
            System.out.println(" 16 " + Arrays.toString(gameBoard[16]));
            System.out.println(" 17 " + Arrays.toString(gameBoard[17]));
            System.out.println(" 18 " + Arrays.toString(gameBoard[18]));
            System.out.println(" ");
            System.out.println(" White's Match Points: " + Integer.toString(whitesMatchPoints));
            System.out.println(" White's Total Points: " + Integer.toString(whitesTotalPoints));
            System.out.println(" ");
            System.out.println(" Black's Match Points: " + Integer.toString(blacksMatchPoints));
            System.out.println(" Black's Total Points: " + Integer.toString(blacksTotalPoints));
            System.out.println(" ");
            //System.out.println(" White's Eval: " + Integer.toString(eval));
            System.out.println(" ");
        }
    }


    public void clearBoard(){
        for(int i = 0; i < Array.getLength(gameBoard); i++){
            for (int j = 0; j < Array.getLength(gameBoard[0]); j++){
                gameBoard[i][j] = ' ';
            }
        }
    }


    /**
     * Checks if coordinates given result in a legal move
     * according to the current game variation rules.
     *
     * @param int[2] coordinate
     * @return boolean isLegal
     */
    public boolean isLegalMove(int[] coords){
        //TODO: conditions for rule variations.
        if(gameBoard[coords[0]][coords[1]] == ' '){
            return true;
        }
        else{
            return false;
        }
    }


    /**
     * Changes player turns.
     *
     * If parameter is true, isPlayer1Turn is changed
     * to false. If parameter is false, isPlayer1Turn
     * is changed to true.
     *
     * @param boolean isP1Turn
     */
    public void changeTurns(boolean isP1Turn){
        if (isP1Turn == true){
            isPlayer1Turn = false;
            isPlayer2Turn = true;
            previousPlayer = 1;
        }
        else {
            isPlayer1Turn = true;
            isPlayer2Turn = false;
            previousPlayer = 2;
        }

    }


    /**
     * Asks user for move via text input.
     *
     * @return int[2] coordinate
     */
    public int[] askUserForInput(){
        if (isPlayer1Turn == true){
            int[] coords = new int[2];
            Scanner xreader = new Scanner(System.in);
            System.out.println("Player 1, enter x: ");
            int x = xreader.nextInt();
            Scanner yreader = new Scanner(System.in);
            System.out.println("Player 1, enter y: ");
            int y = yreader.nextInt();
            coords[1] = x;
            coords[0] = y;
            return coords;
        }
        else {
            int[] coords = new int[2];
            Scanner xreader = new Scanner(System.in);
            System.out.println("Player 2, enter x: ");
            int x = xreader.nextInt();
            Scanner yreader = new Scanner(System.in);
            System.out.println("Player 2, enter y: ");
            int y = yreader.nextInt();
            coords[1] = x;
            coords[0] = y;
            return coords;
        }
    }


    /**
     * Updates game board with given legal coordinates.
     *
     * @param int[2] coordinate
     * @return void
     */
    public void placePiece(int[] coords){
        //int[] reversedCoords = {coords[1],coords[0]};
        if ((isPlayer1White == true && isPlayer1Turn == true) || (isPlayer1White == false && isPlayer1Turn == false)){
            gameBoard[coords[0]][coords[1]] = 'W';
            whiteMoves.add(coords);
        }
        else{
            gameBoard[coords[0]][coords[1]] = 'B';
            blackMoves.add(coords);
        }
        movesCount += 1;
    }

    /**
     * Determines if the game board has no spaces left to play on.
     *
     * @param
     * @return boolean
     */
    public boolean isBoardFull(){

        if (is13Board && (whiteMoves.size() + blackMoves.size() == 169)){
            return true;
        }
        if (is19Board && (whiteMoves.size() + blackMoves.size() == 361)){
            return true;
        }
        else return false;
    }


    /**
     * Determines if end of game by checking if either player
     * has obtained the number of to win.
     *
     * @return boolean isGameOver
     */
    public boolean determineIfEndOfGame(){
        if(is50PointsToWin == true){
            if(whitesTotalPoints >= 50 || blacksTotalPoints >= 50){
                if(whitesTotalPoints > blacksTotalPoints){
                    System.out.println("White Wins!");
                    isWhiteWinner = true;
                    return true;
                }
                if(blacksTotalPoints > whitesTotalPoints){
                    System.out.println("Black Wins!");
                    isWhiteWinner = false;
                    return true;
                }
                if(blacksTotalPoints == whitesTotalPoints){
                    System.out.println("It's a tie!");
                    return true;
                }
            }
        }
        if(is21PointsToWin == true){
            if(whitesTotalPoints >= 21 || blacksTotalPoints >= 21){
                if(whitesTotalPoints > blacksTotalPoints){
                    System.out.println("White Wins!");
                    isWhiteWinner = true;
                    return true;
                }
                if(blacksTotalPoints > whitesTotalPoints){
                    System.out.println("Black Wins!");
                    isWhiteWinner = false;
                    return true;
                }
                if(blacksTotalPoints == whitesTotalPoints){
                    System.out.println("It's a tie!");
                    return true;
                }
            }
        }
        if (isSingleMatch == true){
            if (isMatchOver == true){
                if((isPlayer1Turn && isPlayer1White) || (!isPlayer1Turn && !isPlayer1White)){
                    System.out.println("White Wins!");
                    return true;
                }
                else {
                    System.out.println("Black Wins!");
                    return true;
                }
            }
        }
        if (isBoardFull()){
            System.out.println("It's a tie!");
            return true;
        }
        return false;
    }


    /**
     * Looks for captures resulting from the given move and
     * updates the game info according to current variation
     * rules.
     *
     * @param int[2] coordinate
     * @return void
     */
    public void findCaptures(int[] coords){
        if ((isPlayer1Turn == true && isPlayer1White == true) || (isPlayer1Turn == false && isPlayer1White == false)){
            if (coords[0] > 2 && coords[1] > 2){
                int[] upperLeft1 = {coords[0] - 1, coords[1] - 1};
                int[] upperLeft2 = {coords[0] - 2, coords[1] - 2};
                int[] upperLeft3 = {coords[0] - 3, coords[1] - 3};
                for (int i = 0; i < blackMoves.size(); i++){
                    if(upperLeft1[0] == blackMoves.get(i)[0] && upperLeft1[1] == blackMoves.get(i)[1]){
                        for (int j = 0; j < blackMoves.size(); j++){
                            if(upperLeft2[0] == blackMoves.get(j)[0] && upperLeft2[1] == blackMoves.get(j)[1]){
                                for (int k = 0; k < whiteMoves.size(); k++){
                                    if(upperLeft3[0] == whiteMoves.get(k)[0] && upperLeft3[1] == whiteMoves.get(k)[1]){

                                        int[] iVal = blackMoves.get(i);
                                        int[] jVal = blackMoves.get(j);

                                        // The following two loops are necessary to avoid an index out of bounds error
                                        for (int l = 0; l < blackMoves.size(); l++){
                                            if (iVal == blackMoves.get(l)){
                                                blackMoves.remove(blackMoves.get(l));
                                            }
                                        }
                                        for (int l = 0; l < blackMoves.size(); l++){
                                            if (jVal == blackMoves.get(l)){
                                                blackMoves.remove(blackMoves.get(l));
                                            }
                                        }
                                        gameBoard[coords[0] - 1][coords[1] - 1] = ' ';
                                        gameBoard[coords[0] - 2][coords[1] - 2] = ' ';
                                        if (is5InARowRules == false){
                                            whitesTotalCaptures += 1;
                                        }
                                        whitesCapturedStones += 2;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (coords[1] > 2){
                int[] left1 = {coords[0], coords[1] - 1};
                int[] left2 = {coords[0], coords[1] - 2};
                int[] left3 = {coords[0], coords[1] - 3};
                for (int i = 0; i < blackMoves.size(); i++){
                    if(left1[0] == blackMoves.get(i)[0] && left1[1] == blackMoves.get(i)[1]){
                        for (int j = 0; j < blackMoves.size(); j++){
                            if(left2[0] == blackMoves.get(j)[0] && left2[1] == blackMoves.get(j)[1]){
                                for (int k = 0; k < whiteMoves.size(); k++){
                                    if(left3[0] == whiteMoves.get(k)[0] && left3[1] == whiteMoves.get(k)[1]){
                                        int[] iVal = blackMoves.get(i);
                                        int[] jVal = blackMoves.get(j);

                                        // The following two loops are necessary to avoid an index out of bounds error
                                        for (int l = 0; l < blackMoves.size(); l++){
                                            if (iVal == blackMoves.get(l)){
                                                blackMoves.remove(blackMoves.get(l));
                                            }
                                        }
                                        for (int l = 0; l < blackMoves.size(); l++){
                                            if (jVal == blackMoves.get(l)){
                                                blackMoves.remove(blackMoves.get(l));
                                            }
                                        }
                                        gameBoard[coords[0]][coords[1] - 1] = ' ';
                                        gameBoard[coords[0]][coords[1] - 2] = ' ';
                                        if (is5InARowRules == false){
                                            whitesTotalCaptures += 1;
                                        }
                                        whitesCapturedStones += 2;
                                    }
                                }
                            }
                        }
                    }
                }

            }
            if (coords[0] < gameBoard.length - 2 && coords[1] > 2){
                int[] lowerLeft1 = {coords[0] + 1, coords[1] - 1};
                int[] lowerLeft2 = {coords[0] + 2, coords[1] - 2};
                int[] lowerLeft3 = {coords[0] + 3, coords[1] - 3};
                for (int i = 0; i < blackMoves.size(); i++){
                    if(lowerLeft1[0] == blackMoves.get(i)[0] && lowerLeft1[1] == blackMoves.get(i)[1]){
                        for (int j = 0; j < blackMoves.size(); j++){
                            if(lowerLeft2[0] == blackMoves.get(j)[0] && lowerLeft2[1] == blackMoves.get(j)[1]){
                                for (int k = 0; k < whiteMoves.size(); k++){
                                    if(lowerLeft3[0] == whiteMoves.get(k)[0] && lowerLeft3[1] == whiteMoves.get(k)[1]){
                                        int[] iVal = blackMoves.get(i);
                                        int[] jVal = blackMoves.get(j);

                                        // The following two loops are necessary to avoid an index out of bounds error
                                        for (int l = 0; l < blackMoves.size(); l++){
                                            if (iVal == blackMoves.get(l)){
                                                blackMoves.remove(blackMoves.get(l));
                                            }
                                        }
                                        for (int l = 0; l < blackMoves.size(); l++){
                                            if (jVal == blackMoves.get(l)){
                                                blackMoves.remove(blackMoves.get(l));
                                            }
                                        }
                                        gameBoard[coords[0] + 1][coords[1] - 1] = ' ';
                                        gameBoard[coords[0] + 2][coords[1] - 2] = ' ';
                                        if (is5InARowRules == false){
                                            whitesTotalCaptures += 1;
                                        }
                                        whitesCapturedStones += 2;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (coords[0] < gameBoard.length - 2){
                int[] lower1 = {coords[0] + 1, coords[1]};
                int[] lower2 = {coords[0] + 2, coords[1]};
                int[] lower3 = {coords[0] + 3, coords[1]};
                for (int i = 0; i < blackMoves.size(); i++){
                    if(lower1[0] == blackMoves.get(i)[0] && lower1[1] == blackMoves.get(i)[1]){
                        for (int j = 0; j < blackMoves.size(); j++){
                            if(lower2[0] == blackMoves.get(j)[0] && lower2[1] == blackMoves.get(j)[1]){
                                for (int k = 0; k < whiteMoves.size(); k++){
                                    if(lower3[0] == whiteMoves.get(k)[0] && lower3[1] == whiteMoves.get(k)[1]){
                                        int[] iVal = blackMoves.get(i);
                                        int[] jVal = blackMoves.get(j);

                                        // The following two loops are necessary to avoid an index out of bounds error
                                        for (int l = 0; l < blackMoves.size(); l++){
                                            if (iVal == blackMoves.get(l)){
                                                blackMoves.remove(blackMoves.get(l));
                                            }
                                        }
                                        for (int l = 0; l < blackMoves.size(); l++){
                                            if (jVal == blackMoves.get(l)){
                                                blackMoves.remove(blackMoves.get(l));
                                            }
                                        }
                                        gameBoard[coords[0] + 1][coords[1]] = ' ';
                                        gameBoard[coords[0] + 2][coords[1]] = ' ';
                                        if (is5InARowRules == false){
                                            whitesTotalCaptures += 1;
                                        }
                                        whitesCapturedStones += 2;
                                    }
                                }
                            }
                        }
                    }
                }

            }
            if (coords[1] < gameBoard.length - 2 && coords[0] < gameBoard.length - 2){
                int[] lowerRight1 = {coords[0] + 1, coords[1] + 1};
                int[] lowerRight2 = {coords[0] + 2, coords[1] + 2};
                int[] lowerRight3 = {coords[0] + 3, coords[1] + 3};
                for (int i = 0; i < blackMoves.size(); i++){
                    if(lowerRight1[0] == blackMoves.get(i)[0] && lowerRight1[1] == blackMoves.get(i)[1]){
                        for (int j = 0; j < blackMoves.size(); j++){
                            if(lowerRight2[0] == blackMoves.get(j)[0] && lowerRight2[1] == blackMoves.get(j)[1]){
                                for (int k = 0; k < whiteMoves.size(); k++){
                                    if(lowerRight3[0] == whiteMoves.get(k)[0] && lowerRight3[1] == whiteMoves.get(k)[1]){
                                        int[] iVal = blackMoves.get(i);
                                        int[] jVal = blackMoves.get(j);

                                        // The following two loops are necessary to avoid an index out of bounds error
                                        for (int l = 0; l < blackMoves.size(); l++){
                                            if (iVal == blackMoves.get(l)){
                                                blackMoves.remove(blackMoves.get(l));
                                            }
                                        }
                                        for (int l = 0; l < blackMoves.size(); l++){
                                            if (jVal == blackMoves.get(l)){
                                                blackMoves.remove(blackMoves.get(l));
                                            }
                                        }
                                        gameBoard[coords[0] + 1][coords[1] + 1] = ' ';
                                        gameBoard[coords[0] + 2][coords[1] + 2] = ' ';
                                        if (is5InARowRules == false){
                                            whitesTotalCaptures += 1;
                                        }
                                        whitesCapturedStones += 2;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (coords[1] < gameBoard.length - 2){
                int[] right1 = {coords[0], coords[1] + 1};
                int[] right2 = {coords[0], coords[1] + 2};
                int[] right3 = {coords[0], coords[1] + 3};
                for (int i = 0; i < blackMoves.size(); i++){
                    if(right1[0] == blackMoves.get(i)[0] && right1[1] == blackMoves.get(i)[1]){
                        for (int j = 0; j < blackMoves.size(); j++){
                            if(right2[0] == blackMoves.get(j)[0] && right2[1] == blackMoves.get(j)[1]){
                                for (int k = 0; k < whiteMoves.size(); k++){
                                    if(right3[0] == whiteMoves.get(k)[0] && right3[1] == whiteMoves.get(k)[1]){

                                        int[] iVal = blackMoves.get(i);
                                        int[] jVal = blackMoves.get(j);

                                        // The following two loops are necessary to avoid an index out of bounds error
                                        for (int l = 0; l < blackMoves.size(); l++){
                                            if (iVal == blackMoves.get(l)){
                                                blackMoves.remove(blackMoves.get(l));
                                            }
                                        }
                                        for (int l = 0; l < blackMoves.size(); l++){
                                            if (jVal == blackMoves.get(l)){
                                                blackMoves.remove(blackMoves.get(l));
                                            }
                                        }
                                        gameBoard[coords[0]][coords[1] + 1] = ' ';
                                        gameBoard[coords[0]][coords[1] + 2] = ' ';
                                        if (is5InARowRules == false){
                                            whitesTotalCaptures += 1;
                                        }
                                        whitesCapturedStones += 2;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (coords[1] < gameBoard.length - 2 && coords[0] > 2){
                int[] upperRight1 = {coords[0] - 1, coords[1] + 1};
                int[] upperRight2 = {coords[0] - 2, coords[1] + 2};
                int[] upperRight3 = {coords[0] - 3, coords[1] + 3};
                for (int i = 0; i < blackMoves.size(); i++){
                    if(upperRight1[0] == blackMoves.get(i)[0] && upperRight1[1] == blackMoves.get(i)[1]){
                        for (int j = 0; j < blackMoves.size(); j++){
                            if(upperRight2[0] == blackMoves.get(j)[0] && upperRight2[1] == blackMoves.get(j)[1]){
                                for (int k = 0; k < whiteMoves.size(); k++){
                                    if(upperRight3[0] == whiteMoves.get(k)[0] && upperRight3[1] == whiteMoves.get(k)[1]){
                                        int[] iVal = blackMoves.get(i);
                                        int[] jVal = blackMoves.get(j);

                                        // The following two loops are necessary to avoid an index out of bounds error
                                        for (int l = 0; l < blackMoves.size(); l++){
                                            if (iVal == blackMoves.get(l)){
                                                blackMoves.remove(blackMoves.get(l));
                                            }
                                        }
                                        for (int l = 0; l < blackMoves.size(); l++){
                                            if (jVal == blackMoves.get(l)){
                                                blackMoves.remove(blackMoves.get(l));
                                            }
                                        }
                                        gameBoard[coords[0] - 1][coords[1] + 1] = ' ';
                                        gameBoard[coords[0] - 2][coords[1] + 2] = ' ';
                                        if (is5InARowRules == false){
                                            whitesTotalCaptures += 1;
                                        }
                                        whitesCapturedStones += 2;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (coords[0] > 2){
                int[] upper1 = {coords[0] - 1, coords[1]};
                int[] upper2 = {coords[0] - 2, coords[1]};
                int[] upper3 = {coords[0] - 3, coords[1]};
                for (int i = 0; i < blackMoves.size(); i++){
                    if(upper1[0] == blackMoves.get(i)[0] && upper1[1] == blackMoves.get(i)[1]){
                        for (int j = 0; j < blackMoves.size(); j++){
                            if(upper2[0] == blackMoves.get(j)[0] && upper2[1] == blackMoves.get(j)[1]){
                                for (int k = 0; k < whiteMoves.size(); k++){
                                    if(upper3[0] == whiteMoves.get(k)[0] && upper3[1] == whiteMoves.get(k)[1]){
                                        int[] iVal = blackMoves.get(i);
                                        int[] jVal = blackMoves.get(j);

                                        // The following two loops are necessary to avoid an index out of bounds error
                                        for (int l = 0; l < blackMoves.size(); l++){
                                            if (iVal == blackMoves.get(l)){
                                                blackMoves.remove(blackMoves.get(l));
                                            }
                                        }
                                        for (int l = 0; l < blackMoves.size(); l++){
                                            if (jVal == blackMoves.get(l)){
                                                blackMoves.remove(blackMoves.get(l));
                                            }
                                        }
                                        gameBoard[coords[0] - 1][coords[1]] = ' ';
                                        gameBoard[coords[0] - 2][coords[1]] = ' ';
                                        if (is5InARowRules == false){
                                            whitesTotalCaptures += 1;
                                        }
                                        whitesCapturedStones += 2;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if ((isPlayer1Turn == true && isPlayer1White == false) || (isPlayer1Turn == false && isPlayer1White == true)){
            if (coords[0] > 2 && coords[1] > 2){
                int[] upperLeft1 = {coords[0] - 1, coords[1] - 1};
                int[] upperLeft2 = {coords[0] - 2, coords[1] - 2};
                int[] upperLeft3 = {coords[0] - 3, coords[1] - 3};
                for (int i = 0; i < whiteMoves.size(); i++){
                    if(upperLeft1[0] == whiteMoves.get(i)[0] && upperLeft1[1] == whiteMoves.get(i)[1]){
                        for (int j = 0; j < whiteMoves.size(); j++){
                            if(upperLeft2[0] == whiteMoves.get(j)[0] && upperLeft2[1] == whiteMoves.get(j)[1]){
                                for (int k = 0; k < blackMoves.size(); k++){
                                    if(upperLeft3[0] == blackMoves.get(k)[0] && upperLeft3[1] == blackMoves.get(k)[1]){
                                        int[] iVal = whiteMoves.get(i);
                                        int[] jVal = whiteMoves.get(j);

                                        // The following two loops are necessary to avoid an index out of bounds error
                                        for (int l = 0; l < whiteMoves.size(); l++){
                                            if (iVal == whiteMoves.get(l)){
                                                whiteMoves.remove(whiteMoves.get(l));
                                            }
                                        }
                                        for (int l = 0; l < whiteMoves.size(); l++){
                                            if (jVal == whiteMoves.get(l)){
                                                whiteMoves.remove(whiteMoves.get(l));
                                            }
                                        }
                                        gameBoard[coords[0] - 1][coords[1] - 1] = ' ';
                                        gameBoard[coords[0] - 2][coords[1] - 2] = ' ';
                                        if (is5InARowRules == false){
                                            blacksTotalCaptures += 1;
                                        }
                                        blacksCapturedStones += 2;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (coords[1] > 2){
                int[] left1 = {coords[0], coords[1] - 1};
                int[] left2 = {coords[0], coords[1] - 2};
                int[] left3 = {coords[0], coords[1] - 3};
                for (int i = 0; i < whiteMoves.size(); i++){
                    if(left1[0] == whiteMoves.get(i)[0] && left1[1] == whiteMoves.get(i)[1]){
                        for (int j = 0; j < whiteMoves.size(); j++){
                            if(left2[0] == whiteMoves.get(j)[0] && left2[1] == whiteMoves.get(j)[1]){
                                for (int k = 0; k < blackMoves.size(); k++){
                                    if(left3[0] == blackMoves.get(k)[0] && left3[1] == blackMoves.get(k)[1]){
                                        int[] iVal = whiteMoves.get(i);
                                        int[] jVal = whiteMoves.get(j);

                                        // The following two loops are necessary to avoid an index out of bounds error
                                        for (int l = 0; l < whiteMoves.size(); l++){
                                            if (iVal == whiteMoves.get(l)){
                                                whiteMoves.remove(whiteMoves.get(l));
                                            }
                                        }
                                        for (int l = 0; l < whiteMoves.size(); l++){
                                            if (jVal == whiteMoves.get(l)){
                                                whiteMoves.remove(whiteMoves.get(l));
                                            }
                                        }
                                        gameBoard[coords[0]][coords[1] - 1] = ' ';
                                        gameBoard[coords[0]][coords[1] - 2] = ' ';
                                        if (is5InARowRules == false){
                                            blacksTotalCaptures += 1;
                                        }
                                        blacksCapturedStones += 2;
                                    }
                                }
                            }
                        }
                    }
                }

            }
            if (coords[0] < gameBoard.length - 2 && coords[1] > 2){
                int[] lowerLeft1 = {coords[0] + 1, coords[1] - 1};
                int[] lowerLeft2 = {coords[0] + 2, coords[1] - 2};
                int[] lowerLeft3 = {coords[0] + 3, coords[1] - 3};
                for (int i = 0; i < whiteMoves.size(); i++){
                    if(lowerLeft1[0] == whiteMoves.get(i)[0] && lowerLeft1[1] == whiteMoves.get(i)[1]){
                        for (int j = 0; j < whiteMoves.size(); j++){
                            if(lowerLeft2[0] == whiteMoves.get(j)[0] && lowerLeft2[1] == whiteMoves.get(j)[1]){
                                for (int k = 0; k < blackMoves.size(); k++){
                                    if(lowerLeft3[0] == blackMoves.get(k)[0] && lowerLeft3[1] == blackMoves.get(k)[1]){
                                        int[] iVal = whiteMoves.get(i);
                                        int[] jVal = whiteMoves.get(j);

                                        // The following two loops are necessary to avoid an index out of bounds error
                                        for (int l = 0; l < whiteMoves.size(); l++){
                                            if (iVal == whiteMoves.get(l)){
                                                whiteMoves.remove(whiteMoves.get(l));
                                            }
                                        }
                                        for (int l = 0; l < whiteMoves.size(); l++){
                                            if (jVal == whiteMoves.get(l)){
                                                whiteMoves.remove(whiteMoves.get(l));
                                            }
                                        }
                                        gameBoard[coords[0] + 1][coords[1] - 1] = ' ';
                                        gameBoard[coords[0] + 2][coords[1] - 2] = ' ';
                                        if (is5InARowRules == false){
                                            blacksTotalCaptures += 1;
                                        }
                                        blacksCapturedStones += 2;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (coords[0] < gameBoard.length - 2){
                int[] lower1 = {coords[0] + 1, coords[1]};
                int[] lower2 = {coords[0] + 2, coords[1]};
                int[] lower3 = {coords[0] + 3, coords[1]};
                for (int i = 0; i < whiteMoves.size(); i++){
                    if(lower1[0] == whiteMoves.get(i)[0] && lower1[1] == whiteMoves.get(i)[1]){
                        for (int j = 0; j < whiteMoves.size(); j++){
                            if(lower2[0] == whiteMoves.get(j)[0] && lower2[1] == whiteMoves.get(j)[1]){
                                for (int k = 0; k < blackMoves.size(); k++){
                                    if(lower3[0] == blackMoves.get(k)[0] && lower3[1] == blackMoves.get(k)[1]){
                                        int[] iVal = whiteMoves.get(i);
                                        int[] jVal = whiteMoves.get(j);

                                        // The following two loops are necessary to avoid an index out of bounds error
                                        for (int l = 0; l < whiteMoves.size(); l++){
                                            if (iVal == whiteMoves.get(l)){
                                                whiteMoves.remove(whiteMoves.get(l));
                                            }
                                        }
                                        for (int l = 0; l < whiteMoves.size(); l++){
                                            if (jVal == whiteMoves.get(l)){
                                                whiteMoves.remove(whiteMoves.get(l));
                                            }
                                        }
                                        gameBoard[coords[0] + 1][coords[1]] = ' ';
                                        gameBoard[coords[0] + 2][coords[1]] = ' ';
                                        if (is5InARowRules == false){
                                            blacksTotalCaptures += 1;
                                        }
                                        blacksCapturedStones += 2;
                                    }
                                }
                            }
                        }
                    }
                }

            }
            if (coords[1] < gameBoard.length - 2 && coords[0] < gameBoard.length - 2){
                int[] lowerRight1 = {coords[0] + 1, coords[1] + 1};
                int[] lowerRight2 = {coords[0] + 2, coords[1] + 2};
                int[] lowerRight3 = {coords[0] + 3, coords[1] + 3};
                for (int i = 0; i < whiteMoves.size(); i++){
                    if(lowerRight1[0] == whiteMoves.get(i)[0] && lowerRight1[1] == whiteMoves.get(i)[1]){
                        for (int j = 0; j < whiteMoves.size(); j++){
                            if(lowerRight2[0] == whiteMoves.get(j)[0] && lowerRight2[1] == whiteMoves.get(j)[1]){
                                for (int k = 0; k < blackMoves.size(); k++){
                                    if(lowerRight3[0] == blackMoves.get(k)[0] && lowerRight3[1] == blackMoves.get(k)[1]){
                                        int[] iVal = whiteMoves.get(i);
                                        int[] jVal = whiteMoves.get(j);

                                        // The following two loops are necessary to avoid an index out of bounds error
                                        for (int l = 0; l < whiteMoves.size(); l++){
                                            if (iVal == whiteMoves.get(l)){
                                                whiteMoves.remove(whiteMoves.get(l));
                                            }
                                        }
                                        for (int l = 0; l < whiteMoves.size(); l++){
                                            if (jVal == whiteMoves.get(l)){
                                                whiteMoves.remove(whiteMoves.get(l));
                                            }
                                        }
                                        gameBoard[coords[0] + 1][coords[1] + 1] = ' ';
                                        gameBoard[coords[0] + 2][coords[1] + 2] = ' ';
                                        if (is5InARowRules == false){
                                            blacksTotalCaptures += 1;
                                        }
                                        blacksCapturedStones += 2;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (coords[1] < gameBoard.length - 2){
                int[] right1 = {coords[0], coords[1] + 1};
                int[] right2 = {coords[0], coords[1] + 2};
                int[] right3 = {coords[0], coords[1] + 3};
                for (int i = 0; i < whiteMoves.size(); i++){
                    if(right1[0] == whiteMoves.get(i)[0] && right1[1] == whiteMoves.get(i)[1]){
                        for (int j = 0; j < whiteMoves.size(); j++){
                            if(right2[0] == whiteMoves.get(j)[0] && right2[1] == whiteMoves.get(j)[1]){
                                for (int k = 0; k < blackMoves.size(); k++){
                                    if(right3[0] == blackMoves.get(k)[0] && right3[1] == blackMoves.get(k)[1]){
                                        int[] iVal = whiteMoves.get(i);
                                        int[] jVal = whiteMoves.get(j);

                                        // The following two loops are necessary to avoid an index out of bounds error
                                        for (int l = 0; l < whiteMoves.size(); l++){
                                            if (iVal == whiteMoves.get(l)){
                                                whiteMoves.remove(whiteMoves.get(l));
                                            }
                                        }
                                        for (int l = 0; l < whiteMoves.size(); l++){
                                            if (jVal == whiteMoves.get(l)){
                                                whiteMoves.remove(whiteMoves.get(l));
                                            }
                                        }
                                        gameBoard[coords[0]][coords[1] + 1] = ' ';
                                        gameBoard[coords[0]][coords[1] + 2] = ' ';
                                        if (is5InARowRules == false){
                                            blacksTotalCaptures += 1;
                                        }
                                        blacksCapturedStones += 2;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (coords[1] < gameBoard.length - 2 && coords[0] > 2){
                int[] upperRight1 = {coords[0] - 1, coords[1] + 1};
                int[] upperRight2 = {coords[0] - 2, coords[1] + 2};
                int[] upperRight3 = {coords[0] - 3, coords[1] + 3};
                for (int i = 0; i < whiteMoves.size(); i++){
                    if(upperRight1[0] == whiteMoves.get(i)[0] && upperRight1[1] == whiteMoves.get(i)[1]){
                        for (int j = 0; j < whiteMoves.size(); j++){
                            if(upperRight2[0] == whiteMoves.get(j)[0] && upperRight2[1] == whiteMoves.get(j)[1]){
                                for (int k = 0; k < blackMoves.size(); k++){
                                    if(upperRight3[0] == blackMoves.get(k)[0] && upperRight3[1] == blackMoves.get(k)[1]){
                                        int[] iVal = whiteMoves.get(i);
                                        int[] jVal = whiteMoves.get(j);

                                        // The following two loops are necessary to avoid an index out of bounds error
                                        for (int l = 0; l < whiteMoves.size(); l++){
                                            if (iVal == whiteMoves.get(l)){
                                                whiteMoves.remove(whiteMoves.get(l));
                                            }
                                        }
                                        for (int l = 0; l < whiteMoves.size(); l++){
                                            if (jVal == whiteMoves.get(l)){
                                                whiteMoves.remove(whiteMoves.get(l));
                                            }
                                        }
                                        gameBoard[coords[0] - 1][coords[1] + 1] = ' ';
                                        gameBoard[coords[0] - 2][coords[1] + 2] = ' ';
                                        if (is5InARowRules == false){
                                            blacksTotalCaptures += 1;
                                        }
                                        blacksCapturedStones += 2;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (coords[0] > 2){
                int[] upper1 = {coords[0] - 1, coords[1]};
                int[] upper2 = {coords[0] - 2, coords[1]};
                int[] upper3 = {coords[0] - 3, coords[1]};
                for (int i = 0; i < whiteMoves.size(); i++){
                    if(upper1[0] == whiteMoves.get(i)[0] && upper1[1] == whiteMoves.get(i)[1]){
                        for (int j = 0; j < whiteMoves.size(); j++){
                            if(upper2[0] == whiteMoves.get(j)[0] && upper2[1] == whiteMoves.get(j)[1]){
                                for (int k = 0; k < blackMoves.size(); k++){
                                    if(upper3[0] == blackMoves.get(k)[0] && upper3[1] == blackMoves.get(k)[1]){
                                        int[] iVal = whiteMoves.get(i);
                                        int[] jVal = whiteMoves.get(j);

                                        // The following two loops are necessary to avoid an index out of bounds error
                                        for (int l = 0; l < whiteMoves.size(); l++){
                                            if (iVal == whiteMoves.get(l)){
                                                whiteMoves.remove(whiteMoves.get(l));
                                            }
                                        }
                                        for (int l = 0; l < whiteMoves.size(); l++){
                                            if (jVal == whiteMoves.get(l)){
                                                whiteMoves.remove(whiteMoves.get(l));
                                            }
                                        }
                                        gameBoard[coords[0] - 1][coords[1]] = ' ';
                                        gameBoard[coords[0] - 2][coords[1]] = ' ';
                                        if (is5InARowRules == false){
                                            blacksTotalCaptures += 1;
                                        }
                                        blacksCapturedStones += 2;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Looks for keryo captures resulting from the given move and
     * updates the game info according to current variation
     * rules.
     *
     * @param int[2] coordinate
     * @return void
     */
    public void findKeryoCaptures(int[] coords){
        if ((isPlayer1Turn == true && isPlayer1White == true) || (isPlayer1Turn == false && isPlayer1White == false)){
            if (coords[0] > 3 && coords[1] > 3){
                int[] upperLeft1 = {coords[0] - 1, coords[1] - 1};
                int[] upperLeft2 = {coords[0] - 2, coords[1] - 2};
                int[] upperLeft3 = {coords[0] - 3, coords[1] - 3};
                int[] upperLeft4 = {coords[0] - 4, coords[1] - 4};
                for (int i = 0; i < blackMoves.size(); i++){
                    if(upperLeft1[0] == blackMoves.get(i)[0] && upperLeft1[1] == blackMoves.get(i)[1]){
                        for (int j = 0; j < blackMoves.size(); j++){
                            if(upperLeft2[0] == blackMoves.get(j)[0] && upperLeft2[1] == blackMoves.get(j)[1]){
                                for (int k = 0; k < blackMoves.size(); k++){
                                    if (Arrays.equals(upperLeft3, blackMoves.get(k))){
                                        for (int l = 0; l < whiteMoves.size(); l++){
                                            if(Arrays.equals(upperLeft4, whiteMoves.get(l))){

                                                int[] iVal = blackMoves.get(i);
                                                int[] jVal = blackMoves.get(j);
                                                int[] kVal = blackMoves.get(k);

                                                // The following two loops are necessary to avoid an index out of bounds error
                                                for (int m = 0; m < blackMoves.size(); m++){
                                                    if (iVal == blackMoves.get(m)){
                                                        blackMoves.remove(blackMoves.get(m));
                                                    }
                                                }
                                                for (int m = 0; m < blackMoves.size(); m++){
                                                    if (jVal == blackMoves.get(m)){
                                                        blackMoves.remove(blackMoves.get(m));
                                                    }
                                                }
                                                for (int m = 0; m < blackMoves.size(); m++){
                                                    if (kVal == blackMoves.get(m)){
                                                        blackMoves.remove(blackMoves.get(m));
                                                    }
                                                }
                                                gameBoard[coords[0] - 1][coords[1] - 1] = ' ';
                                                gameBoard[coords[0] - 2][coords[1] - 2] = ' ';
                                                gameBoard[coords[0] - 3][coords[1] - 3] = ' ';
                                                whitesTotalCaptures += 1;
                                                whitesCapturedStones += 3;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (coords[1] > 3){
                int[] left1 = {coords[0], coords[1] - 1};
                int[] left2 = {coords[0], coords[1] - 2};
                int[] left3 = {coords[0], coords[1] - 3};
                int[] left4 = {coords[0], coords[1] - 4};
                for (int i = 0; i < blackMoves.size(); i++){
                    if(left1[0] == blackMoves.get(i)[0] && left1[1] == blackMoves.get(i)[1]){
                        for (int j = 0; j < blackMoves.size(); j++){
                            if(left2[0] == blackMoves.get(j)[0] && left2[1] == blackMoves.get(j)[1]){
                                for (int k = 0; k < blackMoves.size(); k++){
                                    if (Arrays.equals(left3, blackMoves.get(k))){
                                        for (int l = 0; l < whiteMoves.size(); l++){
                                            if(Arrays.equals(left4, whiteMoves.get(l))){

                                                int[] iVal = blackMoves.get(i);
                                                int[] jVal = blackMoves.get(j);
                                                int[] kVal = blackMoves.get(k);

                                                // The following two loops are necessary to avoid an index out of bounds error
                                                for (int m = 0; m < blackMoves.size(); m++){
                                                    if (iVal == blackMoves.get(m)){
                                                        blackMoves.remove(blackMoves.get(m));
                                                    }
                                                }
                                                for (int m = 0; m < blackMoves.size(); m++){
                                                    if (jVal == blackMoves.get(m)){
                                                        blackMoves.remove(blackMoves.get(m));
                                                    }
                                                }
                                                for (int m = 0; m < blackMoves.size(); m++){
                                                    if (kVal == blackMoves.get(m)){
                                                        blackMoves.remove(blackMoves.get(m));
                                                    }
                                                }
                                                gameBoard[coords[0]][coords[1] - 1] = ' ';
                                                gameBoard[coords[0]][coords[1] - 2] = ' ';
                                                gameBoard[coords[0]][coords[1] - 3] = ' ';
                                                whitesTotalCaptures += 1;
                                                whitesCapturedStones += 3;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (coords[0] < gameBoard.length - 2 && coords[1] > 2){
                int[] lowerLeft1 = {coords[0] + 1, coords[1] - 1};
                int[] lowerLeft2 = {coords[0] + 2, coords[1] - 2};
                int[] lowerLeft3 = {coords[0] + 3, coords[1] - 3};
                int[] lowerLeft4 = {coords[0] + 4, coords[1] - 4};
                for (int i = 0; i < blackMoves.size(); i++){
                    if(lowerLeft1[0] == blackMoves.get(i)[0] && lowerLeft1[1] == blackMoves.get(i)[1]){
                        for (int j = 0; j < blackMoves.size(); j++){
                            if(lowerLeft2[0] == blackMoves.get(j)[0] && lowerLeft2[1] == blackMoves.get(j)[1]){
                                for (int k = 0; k < blackMoves.size(); k++){
                                    if (Arrays.equals(lowerLeft3, blackMoves.get(k))){
                                        for (int l = 0; l < whiteMoves.size(); l++){
                                            if(Arrays.equals(lowerLeft4, whiteMoves.get(l))){

                                                int[] iVal = blackMoves.get(i);
                                                int[] jVal = blackMoves.get(j);
                                                int[] kVal = blackMoves.get(k);

                                                // The following two loops are necessary to avoid an index out of bounds error
                                                for (int m = 0; m < blackMoves.size(); m++){
                                                    if (iVal == blackMoves.get(m)){
                                                        blackMoves.remove(blackMoves.get(m));
                                                    }
                                                }
                                                for (int m = 0; m < blackMoves.size(); m++){
                                                    if (jVal == blackMoves.get(m)){
                                                        blackMoves.remove(blackMoves.get(m));
                                                    }
                                                }
                                                for (int m = 0; m < blackMoves.size(); m++){
                                                    if (kVal == blackMoves.get(m)){
                                                        blackMoves.remove(blackMoves.get(m));
                                                    }
                                                }
                                                gameBoard[coords[0] + 1][coords[1] - 1] = ' ';
                                                gameBoard[coords[0] + 2][coords[1] - 2] = ' ';
                                                gameBoard[coords[0] + 3][coords[1] - 3] = ' ';
                                                whitesTotalCaptures += 1;
                                                whitesCapturedStones += 3;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if (coords[0] < gameBoard.length - 2){
                int[] lower1 = {coords[0] + 1, coords[1]};
                int[] lower2 = {coords[0] + 2, coords[1]};
                int[] lower3 = {coords[0] + 3, coords[1]};
                int[] lower4 = {coords[0] + 4, coords[1]};
                for (int i = 0; i < blackMoves.size(); i++){
                    if(lower1[0] == blackMoves.get(i)[0] && lower1[1] == blackMoves.get(i)[1]){
                        for (int j = 0; j < blackMoves.size(); j++){
                            if(lower2[0] == blackMoves.get(j)[0] && lower2[1] == blackMoves.get(j)[1]){
                                for (int k = 0; k < blackMoves.size(); k++){
                                    if (Arrays.equals(lower3, blackMoves.get(k))){
                                        for (int l = 0; l < whiteMoves.size(); l++){
                                            if(Arrays.equals(lower4, whiteMoves.get(l))){

                                                int[] iVal = blackMoves.get(i);
                                                int[] jVal = blackMoves.get(j);
                                                int[] kVal = blackMoves.get(k);

                                                // The following two loops are necessary to avoid an index out of bounds error
                                                for (int m = 0; m < blackMoves.size(); m++){
                                                    if (iVal == blackMoves.get(m)){
                                                        blackMoves.remove(blackMoves.get(m));
                                                    }
                                                }
                                                for (int m = 0; m < blackMoves.size(); m++){
                                                    if (jVal == blackMoves.get(m)){
                                                        blackMoves.remove(blackMoves.get(m));
                                                    }
                                                }
                                                for (int m = 0; m < blackMoves.size(); m++){
                                                    if (kVal == blackMoves.get(m)){
                                                        blackMoves.remove(blackMoves.get(m));
                                                    }
                                                }
                                                gameBoard[coords[0] + 1][coords[1]] = ' ';
                                                gameBoard[coords[0] + 2][coords[1]] = ' ';
                                                gameBoard[coords[0] + 3][coords[1]] = ' ';
                                                whitesTotalCaptures += 1;
                                                whitesCapturedStones += 3;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if (coords[1] < gameBoard.length - 2 && coords[0] < gameBoard.length - 2){
                int[] lowerRight1 = {coords[0] + 1, coords[1] + 1};
                int[] lowerRight2 = {coords[0] + 2, coords[1] + 2};
                int[] lowerRight3 = {coords[0] + 3, coords[1] + 3};
                int[] lowerRight4 = {coords[0] + 4, coords[1] + 4};
                for (int i = 0; i < blackMoves.size(); i++){
                    if(lowerRight1[0] == blackMoves.get(i)[0] && lowerRight1[1] == blackMoves.get(i)[1]){
                        for (int j = 0; j < blackMoves.size(); j++){
                            if(lowerRight2[0] == blackMoves.get(j)[0] && lowerRight2[1] == blackMoves.get(j)[1]){
                                for (int k = 0; k < blackMoves.size(); k++){
                                    if (Arrays.equals(lowerRight3, blackMoves.get(k))){
                                        for (int l = 0; l < whiteMoves.size(); l++){
                                            if(Arrays.equals(lowerRight4, whiteMoves.get(l))){

                                                int[] iVal = blackMoves.get(i);
                                                int[] jVal = blackMoves.get(j);
                                                int[] kVal = blackMoves.get(k);

                                                // The following two loops are necessary to avoid an index out of bounds error
                                                for (int m = 0; m < blackMoves.size(); m++){
                                                    if (iVal == blackMoves.get(m)){
                                                        blackMoves.remove(blackMoves.get(m));
                                                    }
                                                }
                                                for (int m = 0; m < blackMoves.size(); m++){
                                                    if (jVal == blackMoves.get(m)){
                                                        blackMoves.remove(blackMoves.get(m));
                                                    }
                                                }
                                                for (int m = 0; m < blackMoves.size(); m++){
                                                    if (kVal == blackMoves.get(m)){
                                                        blackMoves.remove(blackMoves.get(m));
                                                    }
                                                }
                                                gameBoard[coords[0] + 1][coords[1] + 1] = ' ';
                                                gameBoard[coords[0] + 2][coords[1] + 2] = ' ';
                                                gameBoard[coords[0] + 3][coords[1] + 3] = ' ';
                                                whitesTotalCaptures += 1;
                                                whitesCapturedStones += 3;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (coords[1] < gameBoard.length - 2){
                int[] right1 = {coords[0], coords[1] + 1};
                int[] right2 = {coords[0], coords[1] + 2};
                int[] right3 = {coords[0], coords[1] + 3};
                int[] right4 = {coords[0], coords[1] + 4};
                for (int i = 0; i < blackMoves.size(); i++){
                    if(right1[0] == blackMoves.get(i)[0] && right1[1] == blackMoves.get(i)[1]){
                        for (int j = 0; j < blackMoves.size(); j++){
                            if(right2[0] == blackMoves.get(j)[0] && right2[1] == blackMoves.get(j)[1]){
                                for (int k = 0; k < blackMoves.size(); k++){
                                    if (Arrays.equals(right3, blackMoves.get(k))){
                                        for (int l = 0; l < whiteMoves.size(); l++){
                                            if(Arrays.equals(right4, whiteMoves.get(l))){

                                                int[] iVal = blackMoves.get(i);
                                                int[] jVal = blackMoves.get(j);
                                                int[] kVal = blackMoves.get(k);

                                                // The following two loops are necessary to avoid an index out of bounds error
                                                for (int m = 0; m < blackMoves.size(); m++){
                                                    if (iVal == blackMoves.get(m)){
                                                        blackMoves.remove(blackMoves.get(m));
                                                    }
                                                }
                                                for (int m = 0; m < blackMoves.size(); m++){
                                                    if (jVal == blackMoves.get(m)){
                                                        blackMoves.remove(blackMoves.get(m));
                                                    }
                                                }
                                                for (int m = 0; m < blackMoves.size(); m++){
                                                    if (kVal == blackMoves.get(m)){
                                                        blackMoves.remove(blackMoves.get(m));
                                                    }
                                                }
                                                gameBoard[coords[0]][coords[1] + 1] = ' ';
                                                gameBoard[coords[0]][coords[1] + 2] = ' ';
                                                gameBoard[coords[0]][coords[1] + 3] = ' ';
                                                whitesTotalCaptures += 1;
                                                whitesCapturedStones += 3;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }


            if (coords[1] < gameBoard.length - 2 && coords[0] > 2){
                int[] upperRight1 = {coords[0] - 1, coords[1] + 1};
                int[] upperRight2 = {coords[0] - 2, coords[1] + 2};
                int[] upperRight3 = {coords[0] - 3, coords[1] + 3};
                int[] upperRight4 = {coords[0] - 4, coords[1] + 4};
                for (int i = 0; i < blackMoves.size(); i++){
                    if(upperRight1[0] == blackMoves.get(i)[0] && upperRight1[1] == blackMoves.get(i)[1]){
                        for (int j = 0; j < blackMoves.size(); j++){
                            if(upperRight2[0] == blackMoves.get(j)[0] && upperRight2[1] == blackMoves.get(j)[1]){
                                for (int k = 0; k < blackMoves.size(); k++){
                                    if (Arrays.equals(upperRight3, blackMoves.get(k))){
                                        for (int l = 0; l < whiteMoves.size(); l++){
                                            if(Arrays.equals(upperRight4, whiteMoves.get(l))){

                                                int[] iVal = blackMoves.get(i);
                                                int[] jVal = blackMoves.get(j);
                                                int[] kVal = blackMoves.get(k);

                                                // The following two loops are necessary to avoid an index out of bounds error
                                                for (int m = 0; m < blackMoves.size(); m++){
                                                    if (iVal == blackMoves.get(m)){
                                                        blackMoves.remove(blackMoves.get(m));
                                                    }
                                                }
                                                for (int m = 0; m < blackMoves.size(); m++){
                                                    if (jVal == blackMoves.get(m)){
                                                        blackMoves.remove(blackMoves.get(m));
                                                    }
                                                }
                                                for (int m = 0; m < blackMoves.size(); m++){
                                                    if (kVal == blackMoves.get(m)){
                                                        blackMoves.remove(blackMoves.get(m));
                                                    }
                                                }
                                                gameBoard[coords[0] - 1][coords[1] + 1] = ' ';
                                                gameBoard[coords[0] - 2][coords[1] + 2] = ' ';
                                                gameBoard[coords[0] - 3][coords[1] + 3] = ' ';
                                                whitesTotalCaptures += 1;
                                                whitesCapturedStones += 3;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if (coords[0] > 2){
                int[] upper1 = {coords[0] - 1, coords[1]};
                int[] upper2 = {coords[0] - 2, coords[1]};
                int[] upper3 = {coords[0] - 3, coords[1]};
                int[] upper4 = {coords[0] - 4, coords[1]};
                for (int i = 0; i < blackMoves.size(); i++){
                    if(upper1[0] == blackMoves.get(i)[0] && upper1[1] == blackMoves.get(i)[1]){
                        for (int j = 0; j < blackMoves.size(); j++){
                            if(upper2[0] == blackMoves.get(j)[0] && upper2[1] == blackMoves.get(j)[1]){
                                for (int k = 0; k < blackMoves.size(); k++){
                                    if (Arrays.equals(upper3, blackMoves.get(k))){
                                        for (int l = 0; l < whiteMoves.size(); l++){
                                            if(Arrays.equals(upper4, whiteMoves.get(l))){

                                                int[] iVal = blackMoves.get(i);
                                                int[] jVal = blackMoves.get(j);
                                                int[] kVal = blackMoves.get(k);

                                                // The following two loops are necessary to avoid an index out of bounds error
                                                for (int m = 0; m < blackMoves.size(); m++){
                                                    if (iVal == blackMoves.get(m)){
                                                        blackMoves.remove(blackMoves.get(m));
                                                    }
                                                }
                                                for (int m = 0; m < blackMoves.size(); m++){
                                                    if (jVal == blackMoves.get(m)){
                                                        blackMoves.remove(blackMoves.get(m));
                                                    }
                                                }
                                                for (int m = 0; m < blackMoves.size(); m++){
                                                    if (kVal == blackMoves.get(m)){
                                                        blackMoves.remove(blackMoves.get(m));
                                                    }
                                                }
                                                gameBoard[coords[0] - 1][coords[1]] = ' ';
                                                gameBoard[coords[0] - 2][coords[1]] = ' ';
                                                gameBoard[coords[0] - 3][coords[1]] = ' ';
                                                whitesTotalCaptures += 1;
                                                whitesCapturedStones += 3;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if ((isPlayer1Turn == true && isPlayer1White == false) || (isPlayer1Turn == false && isPlayer1White == true)){
            if (coords[0] > 3 && coords[1] > 3){
                int[] upperLeft1 = {coords[0] - 1, coords[1] - 1};
                int[] upperLeft2 = {coords[0] - 2, coords[1] - 2};
                int[] upperLeft3 = {coords[0] - 3, coords[1] - 3};
                int[] upperLeft4 = {coords[0] - 4, coords[1] - 4};
                for (int i = 0; i < whiteMoves.size(); i++){
                    if(upperLeft1[0] == whiteMoves.get(i)[0] && upperLeft1[1] == whiteMoves.get(i)[1]){
                        for (int j = 0; j < whiteMoves.size(); j++){
                            if(upperLeft2[0] == whiteMoves.get(j)[0] && upperLeft2[1] == whiteMoves.get(j)[1]){
                                for (int k = 0; k < whiteMoves.size(); k++){
                                    if (Arrays.equals(upperLeft3, whiteMoves.get(k))){
                                        for (int l = 0; l < blackMoves.size(); l++){
                                            if(Arrays.equals(upperLeft4, blackMoves.get(l))){

                                                int[] iVal = whiteMoves.get(i);
                                                int[] jVal = whiteMoves.get(j);
                                                int[] kVal = whiteMoves.get(k);

                                                // The following two loops are necessary to avoid an index out of bounds error
                                                for (int m = 0; m < whiteMoves.size(); m++){
                                                    if (iVal == whiteMoves.get(m)){
                                                        whiteMoves.remove(whiteMoves.get(m));
                                                    }
                                                }
                                                for (int m = 0; m < whiteMoves.size(); m++){
                                                    if (jVal == whiteMoves.get(m)){
                                                        whiteMoves.remove(whiteMoves.get(m));
                                                    }
                                                }
                                                for (int m = 0; m < whiteMoves.size(); m++){
                                                    if (kVal == whiteMoves.get(m)){
                                                        whiteMoves.remove(whiteMoves.get(m));
                                                    }
                                                }
                                                gameBoard[coords[0] - 1][coords[1] - 1] = ' ';
                                                gameBoard[coords[0] - 2][coords[1] - 2] = ' ';
                                                gameBoard[coords[0] - 3][coords[1] - 3] = ' ';
                                                blacksTotalCaptures += 1;
                                                blacksCapturedStones += 3;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (coords[1] > 3){
                int[] left1 = {coords[0], coords[1] - 1};
                int[] left2 = {coords[0], coords[1] - 2};
                int[] left3 = {coords[0], coords[1] - 3};
                int[] left4 = {coords[0], coords[1] - 4};
                for (int i = 0; i < whiteMoves.size(); i++){
                    if(left1[0] == whiteMoves.get(i)[0] && left1[1] == whiteMoves.get(i)[1]){
                        for (int j = 0; j < whiteMoves.size(); j++){
                            if(left2[0] == whiteMoves.get(j)[0] && left2[1] == whiteMoves.get(j)[1]){
                                for (int k = 0; k < whiteMoves.size(); k++){
                                    if (Arrays.equals(left3, whiteMoves.get(k))){
                                        for (int l = 0; l < blackMoves.size(); l++){
                                            if(Arrays.equals(left4, blackMoves.get(l))){

                                                int[] iVal = whiteMoves.get(i);
                                                int[] jVal = whiteMoves.get(j);
                                                int[] kVal = whiteMoves.get(k);

                                                // The following two loops are necessary to avoid an index out of bounds error
                                                for (int m = 0; m < whiteMoves.size(); m++){
                                                    if (iVal == whiteMoves.get(m)){
                                                        whiteMoves.remove(whiteMoves.get(m));
                                                    }
                                                }
                                                for (int m = 0; m < whiteMoves.size(); m++){
                                                    if (jVal == whiteMoves.get(m)){
                                                        whiteMoves.remove(whiteMoves.get(m));
                                                    }
                                                }
                                                for (int m = 0; m < whiteMoves.size(); m++){
                                                    if (kVal == whiteMoves.get(m)){
                                                        whiteMoves.remove(whiteMoves.get(m));
                                                    }
                                                }
                                                gameBoard[coords[0]][coords[1] - 1] = ' ';
                                                gameBoard[coords[0]][coords[1] - 2] = ' ';
                                                gameBoard[coords[0]][coords[1] - 3] = ' ';
                                                blacksTotalCaptures += 1;
                                                blacksCapturedStones += 3;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (coords[0] < gameBoard.length - 2 && coords[1] > 2){
                int[] lowerLeft1 = {coords[0] + 1, coords[1] - 1};
                int[] lowerLeft2 = {coords[0] + 2, coords[1] - 2};
                int[] lowerLeft3 = {coords[0] + 3, coords[1] - 3};
                int[] lowerLeft4 = {coords[0] + 4, coords[1] - 4};
                for (int i = 0; i < whiteMoves.size(); i++){
                    if(lowerLeft1[0] == whiteMoves.get(i)[0] && lowerLeft1[1] == whiteMoves.get(i)[1]){
                        for (int j = 0; j < whiteMoves.size(); j++){
                            if(lowerLeft2[0] == whiteMoves.get(j)[0] && lowerLeft2[1] == whiteMoves.get(j)[1]){
                                for (int k = 0; k < whiteMoves.size(); k++){
                                    if (Arrays.equals(lowerLeft3, whiteMoves.get(k))){
                                        for (int l = 0; l < blackMoves.size(); l++){
                                            if(Arrays.equals(lowerLeft4, blackMoves.get(l))){

                                                int[] iVal = whiteMoves.get(i);
                                                int[] jVal = whiteMoves.get(j);
                                                int[] kVal = whiteMoves.get(k);

                                                // The following two loops are necessary to avoid an index out of bounds error
                                                for (int m = 0; m < whiteMoves.size(); m++){
                                                    if (iVal == whiteMoves.get(m)){
                                                        whiteMoves.remove(whiteMoves.get(m));
                                                    }
                                                }
                                                for (int m = 0; m < whiteMoves.size(); m++){
                                                    if (jVal == whiteMoves.get(m)){
                                                        whiteMoves.remove(whiteMoves.get(m));
                                                    }
                                                }
                                                for (int m = 0; m < whiteMoves.size(); m++){
                                                    if (kVal == whiteMoves.get(m)){
                                                        whiteMoves.remove(whiteMoves.get(m));
                                                    }
                                                }
                                                gameBoard[coords[0] + 1][coords[1] - 1] = ' ';
                                                gameBoard[coords[0] + 2][coords[1] - 2] = ' ';
                                                gameBoard[coords[0] + 3][coords[1] - 3] = ' ';
                                                blacksTotalCaptures += 1;
                                                blacksCapturedStones += 3;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if (coords[0] < gameBoard.length - 2){
                int[] lower1 = {coords[0] + 1, coords[1]};
                int[] lower2 = {coords[0] + 2, coords[1]};
                int[] lower3 = {coords[0] + 3, coords[1]};
                int[] lower4 = {coords[0] + 4, coords[1]};
                for (int i = 0; i < whiteMoves.size(); i++){
                    if(lower1[0] == whiteMoves.get(i)[0] && lower1[1] == whiteMoves.get(i)[1]){
                        for (int j = 0; j < whiteMoves.size(); j++){
                            if(lower2[0] == whiteMoves.get(j)[0] && lower2[1] == whiteMoves.get(j)[1]){
                                for (int k = 0; k < whiteMoves.size(); k++){
                                    if (Arrays.equals(lower3, whiteMoves.get(k))){
                                        for (int l = 0; l < blackMoves.size(); l++){
                                            if(Arrays.equals(lower4, blackMoves.get(l))){

                                                int[] iVal = whiteMoves.get(i);
                                                int[] jVal = whiteMoves.get(j);
                                                int[] kVal = whiteMoves.get(k);

                                                // The following two loops are necessary to avoid an index out of bounds error
                                                for (int m = 0; m < whiteMoves.size(); m++){
                                                    if (iVal == whiteMoves.get(m)){
                                                        whiteMoves.remove(whiteMoves.get(m));
                                                    }
                                                }
                                                for (int m = 0; m < whiteMoves.size(); m++){
                                                    if (jVal == whiteMoves.get(m)){
                                                        whiteMoves.remove(whiteMoves.get(m));
                                                    }
                                                }
                                                for (int m = 0; m < whiteMoves.size(); m++){
                                                    if (kVal == whiteMoves.get(m)){
                                                        whiteMoves.remove(whiteMoves.get(m));
                                                    }
                                                }
                                                gameBoard[coords[0] + 1][coords[1]] = ' ';
                                                gameBoard[coords[0] + 2][coords[1]] = ' ';
                                                gameBoard[coords[0] + 3][coords[1]] = ' ';
                                                blacksTotalCaptures += 1;
                                                blacksCapturedStones += 3;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            if (coords[1] < gameBoard.length - 2 && coords[0] < gameBoard.length - 2){
                int[] lowerRight1 = {coords[0] + 1, coords[1] + 1};
                int[] lowerRight2 = {coords[0] + 2, coords[1] + 2};
                int[] lowerRight3 = {coords[0] + 3, coords[1] + 3};
                int[] lowerRight4 = {coords[0] + 4, coords[1] + 4};
                for (int i = 0; i < whiteMoves.size(); i++){
                    if(lowerRight1[0] == whiteMoves.get(i)[0] && lowerRight1[1] == whiteMoves.get(i)[1]){
                        for (int j = 0; j < whiteMoves.size(); j++){
                            if(lowerRight2[0] == whiteMoves.get(j)[0] && lowerRight2[1] == whiteMoves.get(j)[1]){
                                for (int k = 0; k < whiteMoves.size(); k++){
                                    if (Arrays.equals(lowerRight3, whiteMoves.get(k))){
                                        for (int l = 0; l < blackMoves.size(); l++){
                                            if(Arrays.equals(lowerRight4, blackMoves.get(l))){

                                                int[] iVal = whiteMoves.get(i);
                                                int[] jVal = whiteMoves.get(j);
                                                int[] kVal = whiteMoves.get(k);

                                                // The following two loops are necessary to avoid an index out of bounds error
                                                for (int m = 0; m < whiteMoves.size(); m++){
                                                    if (iVal == whiteMoves.get(m)){
                                                        whiteMoves.remove(whiteMoves.get(m));
                                                    }
                                                }
                                                for (int m = 0; m < whiteMoves.size(); m++){
                                                    if (jVal == whiteMoves.get(m)){
                                                        whiteMoves.remove(whiteMoves.get(m));
                                                    }
                                                }
                                                for (int m = 0; m < whiteMoves.size(); m++){
                                                    if (kVal == whiteMoves.get(m)){
                                                        whiteMoves.remove(whiteMoves.get(m));
                                                    }
                                                }
                                                gameBoard[coords[0] + 1][coords[1] + 1] = ' ';
                                                gameBoard[coords[0] + 2][coords[1] + 2] = ' ';
                                                gameBoard[coords[0] + 3][coords[1] + 3] = ' ';
                                                blacksTotalCaptures += 1;
                                                blacksCapturedStones += 3;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (coords[1] < gameBoard.length - 2){
                int[] right1 = {coords[0], coords[1] + 1};
                int[] right2 = {coords[0], coords[1] + 2};
                int[] right3 = {coords[0], coords[1] + 3};
                int[] right4 = {coords[0], coords[1] + 4};
                for (int i = 0; i < whiteMoves.size(); i++){
                    if(right1[0] == whiteMoves.get(i)[0] && right1[1] == whiteMoves.get(i)[1]){
                        for (int j = 0; j < whiteMoves.size(); j++){
                            if(right2[0] == whiteMoves.get(j)[0] && right2[1] == whiteMoves.get(j)[1]){
                                for (int k = 0; k < whiteMoves.size(); k++){
                                    if (Arrays.equals(right3, whiteMoves.get(k))){
                                        for (int l = 0; l < blackMoves.size(); l++){
                                            if(Arrays.equals(right4, blackMoves.get(l))){

                                                int[] iVal = whiteMoves.get(i);
                                                int[] jVal = whiteMoves.get(j);
                                                int[] kVal = whiteMoves.get(k);

                                                // The following two loops are necessary to avoid an index out of bounds error
                                                for (int m = 0; m < whiteMoves.size(); m++){
                                                    if (iVal == whiteMoves.get(m)){
                                                        whiteMoves.remove(whiteMoves.get(m));
                                                    }
                                                }
                                                for (int m = 0; m < whiteMoves.size(); m++){
                                                    if (jVal == whiteMoves.get(m)){
                                                        whiteMoves.remove(whiteMoves.get(m));
                                                    }
                                                }
                                                for (int m = 0; m < whiteMoves.size(); m++){
                                                    if (kVal == whiteMoves.get(m)){
                                                        whiteMoves.remove(whiteMoves.get(m));
                                                    }
                                                }
                                                gameBoard[coords[0]][coords[1] + 1] = ' ';
                                                gameBoard[coords[0]][coords[1] + 2] = ' ';
                                                gameBoard[coords[0]][coords[1] + 3] = ' ';
                                                blacksTotalCaptures += 1;
                                                blacksCapturedStones += 3;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }


            if (coords[1] < gameBoard.length - 2 && coords[0] > 2){
                int[] upperRight1 = {coords[0] - 1, coords[1] + 1};
                int[] upperRight2 = {coords[0] - 2, coords[1] + 2};
                int[] upperRight3 = {coords[0] - 3, coords[1] + 3};
                int[] upperRight4 = {coords[0] - 4, coords[1] + 4};
                for (int i = 0; i < whiteMoves.size(); i++){
                    if(upperRight1[0] == whiteMoves.get(i)[0] && upperRight1[1] == whiteMoves.get(i)[1]){
                        for (int j = 0; j < whiteMoves.size(); j++){
                            if(upperRight2[0] == whiteMoves.get(j)[0] && upperRight2[1] == whiteMoves.get(j)[1]){
                                for (int k = 0; k < whiteMoves.size(); k++){
                                    if (Arrays.equals(upperRight3, whiteMoves.get(k))){
                                        for (int l = 0; l < blackMoves.size(); l++){
                                            if(Arrays.equals(upperRight4, blackMoves.get(l))){

                                                int[] iVal = whiteMoves.get(i);
                                                int[] jVal = whiteMoves.get(j);
                                                int[] kVal = whiteMoves.get(k);

                                                // The following two loops are necessary to avoid an index out of bounds error
                                                for (int m = 0; m < whiteMoves.size(); m++){
                                                    if (iVal == whiteMoves.get(m)){
                                                        whiteMoves.remove(whiteMoves.get(m));
                                                    }
                                                }
                                                for (int m = 0; m < whiteMoves.size(); m++){
                                                    if (jVal == whiteMoves.get(m)){
                                                        whiteMoves.remove(whiteMoves.get(m));
                                                    }
                                                }
                                                for (int m = 0; m < whiteMoves.size(); m++){
                                                    if (kVal == whiteMoves.get(m)){
                                                        whiteMoves.remove(whiteMoves.get(m));
                                                    }
                                                }
                                                gameBoard[coords[0] - 1][coords[1] + 1] = ' ';
                                                gameBoard[coords[0] - 2][coords[1] + 2] = ' ';
                                                gameBoard[coords[0] - 3][coords[1] + 3] = ' ';
                                                blacksTotalCaptures += 1;
                                                blacksCapturedStones += 3;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }


            if (coords[0] > 2){
                int[] upper1 = {coords[0] - 1, coords[1]};
                int[] upper2 = {coords[0] - 2, coords[1]};
                int[] upper3 = {coords[0] - 3, coords[1]};
                int[] upper4 = {coords[0] - 4, coords[1]};
                for (int i = 0; i < whiteMoves.size(); i++){
                    if(upper1[0] == whiteMoves.get(i)[0] && upper1[1] == whiteMoves.get(i)[1]){
                        for (int j = 0; j < whiteMoves.size(); j++){
                            if(upper2[0] == whiteMoves.get(j)[0] && upper2[1] == whiteMoves.get(j)[1]){
                                for (int k = 0; k < whiteMoves.size(); k++){
                                    if (Arrays.equals(upper3, whiteMoves.get(k))){
                                        for (int l = 0; l < blackMoves.size(); l++){
                                            if(Arrays.equals(upper4, blackMoves.get(l))){

                                                int[] iVal = whiteMoves.get(i);
                                                int[] jVal = whiteMoves.get(j);
                                                int[] kVal = whiteMoves.get(k);

                                                // The following two loops are necessary to avoid an index out of bounds error
                                                for (int m = 0; m < whiteMoves.size(); m++){
                                                    if (iVal == whiteMoves.get(m)){
                                                        whiteMoves.remove(whiteMoves.get(m));
                                                    }
                                                }
                                                for (int m = 0; m < whiteMoves.size(); m++){
                                                    if (jVal == whiteMoves.get(m)){
                                                        whiteMoves.remove(whiteMoves.get(m));
                                                    }
                                                }
                                                for (int m = 0; m < whiteMoves.size(); m++){
                                                    if (kVal == whiteMoves.get(m)){
                                                        whiteMoves.remove(whiteMoves.get(m));
                                                    }
                                                }
                                                gameBoard[coords[0] - 1][coords[1]] = ' ';
                                                gameBoard[coords[0] - 2][coords[1]] = ' ';
                                                gameBoard[coords[0] - 3][coords[1]] = ' ';
                                                blacksTotalCaptures += 1;
                                                blacksCapturedStones += 3;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }


    /**
     * Looks for white four-in-a-rows resulting from the
     * given move and returns an ArrayList of all found white
     * fours, each white four being an array of coordinates,
     * which are themselves arrays.
     *
     * ex: return {{{x1,y1},{x2,y2},{x3,y3},{x4,y4}},
     * 			   {{x5,y5},{x6,y6},{x7,y7},{x8,y8}}}
     *
     * @param int[2] coordinate
     * @return ArrayList<int[4][2]>
     */
    public ArrayList<int[][]> findWhiteFours(int[] coords, int[][] whiteFive){
        ArrayList<int[][]> allFours = new ArrayList<int[][]>();

        // ---- Upper Left Fours ---- //
        if (coords[0] > 2 && coords[1] > 2){
            int[] upperLeft1 = {coords[0] - 1, coords[1] - 1};
            int[] upperLeft2 = {coords[0] - 2, coords[1] - 2};
            int[] upperLeft3 = {coords[0] - 3, coords[1] - 3};
            if (coords[0] > 3 && coords[1] > 3){ // Fours not touching top or left.
                int[] upperLeft4 = {coords[0] - 4, coords[1] - 4};
                if (coords[0] == gameBoard.length - 2 || coords[1] == gameBoard.length - 2){ // Fours touching bottom or right.
                    for (int i = 0; i < whiteMoves.size(); i++){
                        if (Arrays.equals(whiteMoves.get(i), upperLeft1)){
                            for (int j = 0; j < whiteMoves.size(); j++){
                                if (Arrays.equals(whiteMoves.get(j), upperLeft2)){
                                    for (int k = 0; k < whiteMoves.size(); k++){
                                        if (Arrays.equals(whiteMoves.get(k), upperLeft3)){
                                            boolean inWhiteMoves = false;
                                            for (int l = 0; l < whiteMoves.size(); l++){
                                                if (Arrays.equals(whiteMoves.get(l), upperLeft4)){
                                                    inWhiteMoves = true;
                                                }
                                                if (l == whiteMoves.size() - 1 &&
                                                        (inWhiteMoves == false ||
                                                                ((Arrays.equals(upperLeft4, whiteFive[0]) && !Arrays.equals(upperLeft3, whiteFive[1])) ||
                                                                        (Arrays.equals(upperLeft4, whiteFive[1]) && !Arrays.equals(upperLeft3, whiteFive[2])) ||
                                                                        (Arrays.equals(upperLeft4, whiteFive[2]) && !Arrays.equals(upperLeft3, whiteFive[1]) && !Arrays.equals(upperLeft3, whiteFive[3])) ||
                                                                        (Arrays.equals(upperLeft4, whiteFive[3]) && !Arrays.equals(upperLeft3, whiteFive[2])) ||
                                                                        (Arrays.equals(upperLeft4, whiteFive[4]) && !Arrays.equals(upperLeft3, whiteFive[3]))))){
                                                    int[][] oneFour = {coords,upperLeft1,upperLeft2,upperLeft3};															allFours.add(oneFour);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                else { // Fours not touching top, left, bottom or right
                    int[] lowerRight = {coords[0] + 1, coords[1] + 1};
                    for (int i = 0; i < whiteMoves.size(); i++){
                        if (Arrays.equals(whiteMoves.get(i), upperLeft1)){
                            for (int j = 0; j < whiteMoves.size(); j++){
                                if (Arrays.equals(whiteMoves.get(j), upperLeft2)){
                                    for (int k = 0; k < whiteMoves.size(); k++){
                                        if (Arrays.equals(whiteMoves.get(k), upperLeft3)){
                                            boolean inWhiteMoves = false;
                                            for (int l = 0; l < whiteMoves.size(); l++){
                                                if (Arrays.equals(whiteMoves.get(l), upperLeft4)){
                                                    inWhiteMoves = true;
                                                }
                                                if (l == whiteMoves.size() - 1 && (inWhiteMoves == false ||
                                                        ((Arrays.equals(upperLeft4, whiteFive[0]) && !Arrays.equals(upperLeft3, whiteFive[1])) ||
                                                                (Arrays.equals(upperLeft4, whiteFive[1]) && !Arrays.equals(upperLeft3, whiteFive[2])) ||
                                                                (Arrays.equals(upperLeft4, whiteFive[2]) && !Arrays.equals(upperLeft3, whiteFive[1]) && !Arrays.equals(upperLeft3, whiteFive[3])) ||
                                                                (Arrays.equals(upperLeft4, whiteFive[3]) && !Arrays.equals(upperLeft3, whiteFive[2])) ||
                                                                (Arrays.equals(upperLeft4, whiteFive[4]) && !Arrays.equals(upperLeft3, whiteFive[3]))))){
                                                    inWhiteMoves = false;
                                                    for (int m = 0; m < whiteMoves.size(); m++){
                                                        if (Arrays.equals(whiteMoves.get(m), lowerRight)){
                                                            inWhiteMoves = true;
                                                            if ((Arrays.equals(lowerRight, whiteFive[0]) && !Arrays.equals(coords, whiteFive[1])) ||
                                                                    (Arrays.equals(lowerRight, whiteFive[1]) && !Arrays.equals(coords, whiteFive[2])) ||
                                                                    (Arrays.equals(lowerRight, whiteFive[2]) && !Arrays.equals(coords, whiteFive[1]) && !Arrays.equals(coords, whiteFive[3])) ||
                                                                    (Arrays.equals(lowerRight, whiteFive[3]) && !Arrays.equals(coords, whiteFive[2])) ||
                                                                    (Arrays.equals(lowerRight, whiteFive[4]) && !Arrays.equals(coords, whiteFive[3]))){
                                                                inWhiteMoves = false;
                                                            }
                                                        }
                                                        if (m == whiteMoves.size() - 1 && inWhiteMoves == false){
                                                            int[][] oneFour = {coords,upperLeft1,upperLeft2,upperLeft3};
                                                            allFours.add(oneFour);
                                                        }
                                                    }

                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else { // Fours touching top or left.
                int[] lowerRight = {coords[0] + 1, coords[1] + 1};
                for (int i = 0; i < whiteMoves.size(); i++){
                    if (Arrays.equals(whiteMoves.get(i), upperLeft1)){
                        for (int j = 0; j < whiteMoves.size(); j++){
                            if (Arrays.equals(whiteMoves.get(j), upperLeft2)){
                                for (int k = 0; k < whiteMoves.size(); k++){
                                    if (Arrays.equals(whiteMoves.get(k), upperLeft3)){
                                        boolean inWhiteMoves = false;
                                        for (int m = 0; m < whiteMoves.size(); m++){
                                            if (Arrays.equals(whiteMoves.get(m), lowerRight)){
                                                inWhiteMoves = true;
                                            }
                                            if (m == whiteMoves.size() - 1 && (inWhiteMoves == false ||
                                                    ((Arrays.equals(lowerRight, whiteFive[0]) && !Arrays.equals(coords, whiteFive[1])) ||
                                                            (Arrays.equals(lowerRight, whiteFive[1]) && !Arrays.equals(coords, whiteFive[2])) ||
                                                            (Arrays.equals(lowerRight, whiteFive[2]) && !Arrays.equals(coords, whiteFive[1]) && !Arrays.equals(coords, whiteFive[3])) ||
                                                            (Arrays.equals(lowerRight, whiteFive[3]) && !Arrays.equals(coords, whiteFive[2])) ||
                                                            (Arrays.equals(lowerRight, whiteFive[4]) && !Arrays.equals(coords, whiteFive[3]))))){
                                                int[][] oneFour = {coords,upperLeft1,upperLeft2,upperLeft3};
                                                allFours.add(oneFour);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // ---- Left Fours ---- //

        if (coords[1] > 2){
            int[] left1 = {coords[0], coords[1] - 1};
            int[] left2 = {coords[0], coords[1] - 2};
            int[] left3 = {coords[0], coords[1] - 3};
            if (coords[1] > 3){ // Fours not touching left.
                int[] left4 = {coords[0], coords[1] - 4};
                if (coords[1] == gameBoard.length - 2){ // Fours touching right.
                    for (int i = 0; i < whiteMoves.size(); i++){
                        if (Arrays.equals(whiteMoves.get(i), left1)){
                            for (int j = 0; j < whiteMoves.size(); j++){
                                if (Arrays.equals(whiteMoves.get(j), left2)){
                                    for (int k = 0; k < whiteMoves.size(); k++){
                                        if (Arrays.equals(whiteMoves.get(k), left3)){
                                            boolean inWhiteMoves = false;
                                            for (int l = 0; l < whiteMoves.size(); l++){
                                                if (Arrays.equals(whiteMoves.get(l), left4)){
                                                    inWhiteMoves = true;
                                                }
                                                if (l == whiteMoves.size() - 1 && (inWhiteMoves == false ||
                                                        ((Arrays.equals(left4, whiteFive[0]) && !Arrays.equals(left3, whiteFive[1])) ||
                                                                (Arrays.equals(left4, whiteFive[1]) && !Arrays.equals(left3, whiteFive[2])) ||
                                                                (Arrays.equals(left4, whiteFive[2]) && !Arrays.equals(left3, whiteFive[1]) && !Arrays.equals(left3, whiteFive[3])) ||
                                                                (Arrays.equals(left4, whiteFive[3]) && !Arrays.equals(left3, whiteFive[2])) ||
                                                                (Arrays.equals(left4, whiteFive[4]) && !Arrays.equals(left3, whiteFive[3]))))){
                                                    int[][] oneFour = {coords,left1,left2,left3};
                                                    allFours.add(oneFour);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                else { // Fours not touching left or right
                    int[] right = {coords[0], coords[1] + 1};
                    for (int i = 0; i < whiteMoves.size(); i++){
                        if (Arrays.equals(whiteMoves.get(i), left1)){
                            for (int j = 0; j < whiteMoves.size(); j++){
                                if (Arrays.equals(whiteMoves.get(j), left2)){
                                    for (int k = 0; k < whiteMoves.size(); k++){
                                        if (Arrays.equals(whiteMoves.get(k), left3)){
                                            boolean inWhiteMoves = false;
                                            for (int l = 0; l < whiteMoves.size(); l++){
                                                if (Arrays.equals(whiteMoves.get(l), left4)){
                                                    inWhiteMoves = true;
                                                }
                                                if (l == whiteMoves.size() - 1 && (inWhiteMoves == false ||
                                                        ((Arrays.equals(left4, whiteFive[0]) && !Arrays.equals(left3, whiteFive[1])) ||
                                                                (Arrays.equals(left4, whiteFive[1]) && !Arrays.equals(left3, whiteFive[2])) ||
                                                                (Arrays.equals(left4, whiteFive[2]) && !Arrays.equals(left3, whiteFive[1]) && !Arrays.equals(left3, whiteFive[3])) ||
                                                                (Arrays.equals(left4, whiteFive[3]) && !Arrays.equals(left3, whiteFive[2])) ||
                                                                (Arrays.equals(left4, whiteFive[4]) && !Arrays.equals(left3, whiteFive[3]))))){
                                                    inWhiteMoves = false;
                                                    for (int m = 0; m < whiteMoves.size(); m++){
                                                        if (Arrays.equals(whiteMoves.get(m), right)){
                                                            inWhiteMoves = true;
                                                            if ((Arrays.equals(right, whiteFive[0]) && !Arrays.equals(coords, whiteFive[1])) ||
                                                                    (Arrays.equals(right, whiteFive[1]) && !Arrays.equals(coords, whiteFive[2])) ||
                                                                    (Arrays.equals(right, whiteFive[2]) && !Arrays.equals(coords, whiteFive[1]) && !Arrays.equals(coords, whiteFive[3])) ||
                                                                    (Arrays.equals(right, whiteFive[3]) && !Arrays.equals(coords, whiteFive[2])) ||
                                                                    (Arrays.equals(right, whiteFive[4]) && !Arrays.equals(coords, whiteFive[3]))){
                                                                inWhiteMoves = false;
                                                            }
                                                        }
                                                        if (m == whiteMoves.size() - 1 && inWhiteMoves == false){
                                                            int[][] oneFour = {coords,left1,left2,left3};

                                                            allFours.add(oneFour);
                                                        }
                                                    }

                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    }
                }
            }

            else { // Fours touching right.
                int[] right = {coords[0], coords[1] + 1};
                for (int i = 0; i < whiteMoves.size(); i++){
                    if (Arrays.equals(whiteMoves.get(i), left1)){
                        for (int j = 0; j < whiteMoves.size(); j++){
                            if (Arrays.equals(whiteMoves.get(j), left2)){
                                for (int k = 0; k < whiteMoves.size(); k++){
                                    if (Arrays.equals(whiteMoves.get(k), left3)){
                                        boolean inWhiteMoves = false;
                                        for (int m = 0; m < whiteMoves.size(); m++){
                                            if (Arrays.equals(whiteMoves.get(m), right)){
                                                inWhiteMoves = true;
                                            }
                                            if (m == whiteMoves.size() - 1 && (inWhiteMoves == false ||
                                                    ((Arrays.equals(right, whiteFive[0]) && !Arrays.equals(coords, whiteFive[1])) ||
                                                            (Arrays.equals(right, whiteFive[1]) && !Arrays.equals(coords, whiteFive[2])) ||
                                                            (Arrays.equals(right, whiteFive[2]) && !Arrays.equals(coords, whiteFive[1]) && !Arrays.equals(coords, whiteFive[3])) ||
                                                            (Arrays.equals(right, whiteFive[3]) && !Arrays.equals(coords, whiteFive[2])) ||
                                                            (Arrays.equals(right, whiteFive[4]) && !Arrays.equals(coords, whiteFive[3]))))){
                                                int[][] oneFour = {coords,left1,left2,left3};
                                                allFours.add(oneFour);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


        //---- Upper Right ----//
        if (coords[0] < gameBoard.length - 2 && coords[1] > 2){
            int[] upperRight1 = {coords[0] + 1, coords[1] - 1};
            int[] upperRight2 = {coords[0] + 2, coords[1] - 2};
            int[] upperRight3 = {coords[0] + 3, coords[1] - 3};
            if (coords[0] < gameBoard.length - 3 && coords[1] > 3){ // Fours not touching top or right.
                int[] upperRight4 = {coords[0] + 4, coords[1] - 4};
                if (coords[0] == gameBoard.length + 2 || coords[1] == gameBoard.length - 2){ // Fours touching bottom or left.
                    for (int i = 0; i < whiteMoves.size(); i++){
                        if (Arrays.equals(whiteMoves.get(i), upperRight1)){
                            for (int j = 0; j < whiteMoves.size(); j++){
                                if (Arrays.equals(whiteMoves.get(j), upperRight2)){
                                    for (int k = 0; k < whiteMoves.size(); k++){
                                        if (Arrays.equals(whiteMoves.get(k), upperRight3)){
                                            boolean inWhiteMoves = false;
                                            for (int l = 0; l < whiteMoves.size(); l++){
                                                if (Arrays.equals(whiteMoves.get(l), upperRight4)){
                                                    inWhiteMoves = true;
                                                }
                                                if (l == whiteMoves.size() - 1 && (inWhiteMoves == false ||
                                                        ((Arrays.equals(upperRight4, whiteFive[0]) && !Arrays.equals(upperRight3, whiteFive[1])) ||
                                                                (Arrays.equals(upperRight4, whiteFive[1]) && !Arrays.equals(upperRight3, whiteFive[2])) ||
                                                                (Arrays.equals(upperRight4, whiteFive[2]) && !Arrays.equals(upperRight3, whiteFive[1]) && !Arrays.equals(upperRight3, whiteFive[3])) ||
                                                                (Arrays.equals(upperRight4, whiteFive[3]) && !Arrays.equals(upperRight3, whiteFive[2])) ||
                                                                (Arrays.equals(upperRight4, whiteFive[4]) && !Arrays.equals(upperRight3, whiteFive[3]))))){
                                                    int[][] oneFour = {coords,upperRight1,upperRight2,upperRight3};
                                                    allFours.add(oneFour);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                else { // Fours not touching top, left, bottom or right
                    int[] lowerLeft = {coords[0] - 1, coords[1] + 1};
                    for (int i = 0; i < whiteMoves.size(); i++){
                        if (Arrays.equals(whiteMoves.get(i), upperRight1)){
                            for (int j = 0; j < whiteMoves.size(); j++){
                                if (Arrays.equals(whiteMoves.get(j), upperRight2)){
                                    for (int k = 0; k < whiteMoves.size(); k++){
                                        if (Arrays.equals(whiteMoves.get(k), upperRight3)){
                                            boolean inWhiteMoves = false;
                                            for (int l = 0; l < whiteMoves.size(); l++){
                                                if (Arrays.equals(whiteMoves.get(l), upperRight4)){
                                                    inWhiteMoves = true;
                                                }
                                                if (l == whiteMoves.size() - 1 && (inWhiteMoves == false ||
                                                        ((Arrays.equals(upperRight4, whiteFive[0]) && !Arrays.equals(upperRight3, whiteFive[1])) ||
                                                                (Arrays.equals(upperRight4, whiteFive[1]) && !Arrays.equals(upperRight3, whiteFive[2])) ||
                                                                (Arrays.equals(upperRight4, whiteFive[2]) && !Arrays.equals(upperRight3, whiteFive[1]) && !Arrays.equals(upperRight3, whiteFive[3])) ||
                                                                (Arrays.equals(upperRight4, whiteFive[3]) && !Arrays.equals(upperRight3, whiteFive[2])) ||
                                                                (Arrays.equals(upperRight4, whiteFive[4]) && !Arrays.equals(upperRight3, whiteFive[3]))))){
                                                    inWhiteMoves = false;
                                                    for (int m = 0; m < whiteMoves.size(); m++){
                                                        if (Arrays.equals(whiteMoves.get(m), lowerLeft)){
                                                            inWhiteMoves = true;
                                                            if ((Arrays.equals(lowerLeft, whiteFive[0]) && !Arrays.equals(coords, whiteFive[1])) ||
                                                                    (Arrays.equals(lowerLeft, whiteFive[1]) && !Arrays.equals(coords, whiteFive[2])) ||
                                                                    (Arrays.equals(lowerLeft, whiteFive[2]) && !Arrays.equals(coords, whiteFive[1]) && !Arrays.equals(coords, whiteFive[3])) ||
                                                                    (Arrays.equals(lowerLeft, whiteFive[3]) && !Arrays.equals(coords, whiteFive[2])) ||
                                                                    (Arrays.equals(lowerLeft, whiteFive[4]) && !Arrays.equals(coords, whiteFive[3]))){
                                                                inWhiteMoves = false;
                                                            }
                                                        }
                                                        if (m == whiteMoves.size() - 1 && inWhiteMoves == false){
                                                            int[][] oneFour = {coords,upperRight1,upperRight2,upperRight3};
                                                            allFours.add(oneFour);
                                                        }
                                                    }

                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else { // Fours touching top or left.
                int[] lowerLeft = {coords[0] - 1, coords[1] + 1};
                for (int i = 0; i < whiteMoves.size(); i++){
                    if (Arrays.equals(whiteMoves.get(i), upperRight1)){
                        for (int j = 0; j < whiteMoves.size(); j++){
                            if (Arrays.equals(whiteMoves.get(j), upperRight2)){
                                for (int k = 0; k < whiteMoves.size(); k++){
                                    if (Arrays.equals(whiteMoves.get(k), upperRight3)){
                                        boolean inWhiteMoves = false;
                                        for (int m = 0; m < whiteMoves.size(); m++){
                                            if (Arrays.equals(whiteMoves.get(m), lowerLeft)){
                                                inWhiteMoves = true;
                                            }
                                            if (m == whiteMoves.size() - 1 && (inWhiteMoves == false ||
                                                    ((Arrays.equals(lowerLeft, whiteFive[0]) && !Arrays.equals(coords, whiteFive[1])) ||
                                                            (Arrays.equals(lowerLeft, whiteFive[1]) && !Arrays.equals(coords, whiteFive[2])) ||
                                                            (Arrays.equals(lowerLeft, whiteFive[2]) && !Arrays.equals(coords, whiteFive[1]) && !Arrays.equals(coords, whiteFive[3])) ||
                                                            (Arrays.equals(lowerLeft, whiteFive[3]) && !Arrays.equals(coords, whiteFive[2])) ||
                                                            (Arrays.equals(lowerLeft, whiteFive[4]) && !Arrays.equals(coords, whiteFive[3]))))){
                                                int[][] oneFour = {coords,upperRight1,upperRight2,upperRight3};
                                                allFours.add(oneFour);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (coords[0] > 2){
            int[] lower1 = {coords[0] - 1, coords[1]};
            int[] lower2 = {coords[0] - 2, coords[1]};
            int[] lower3 = {coords[0] - 3, coords[1]};
            if (coords[0] > 3){ // Fours not touching right.
                int[] lower4 = {coords[0] - 4, coords[1]};
                if (coords[0] == gameBoard.length - 2){ // Fours touching left.
                    for (int i = 0; i < whiteMoves.size(); i++){
                        if (Arrays.equals(whiteMoves.get(i), lower1)){
                            for (int j = 0; j < whiteMoves.size(); j++){
                                if (Arrays.equals(whiteMoves.get(j), lower2)){
                                    for (int k = 0; k < whiteMoves.size(); k++){
                                        if (Arrays.equals(whiteMoves.get(k), lower3)){
                                            boolean inWhiteMoves = false;
                                            for (int l = 0; l < whiteMoves.size(); l++){
                                                if (Arrays.equals(whiteMoves.get(l), lower4)){
                                                    inWhiteMoves = true;
                                                }
                                                if (l == whiteMoves.size() - 1 && (inWhiteMoves == false ||
                                                        ((Arrays.equals(lower4, whiteFive[0]) && !Arrays.equals(lower3, whiteFive[1])) ||
                                                                (Arrays.equals(lower4, whiteFive[1]) && !Arrays.equals(lower3, whiteFive[2])) ||
                                                                (Arrays.equals(lower4, whiteFive[2]) && !Arrays.equals(lower3, whiteFive[1]) && !Arrays.equals(lower3, whiteFive[3])) ||
                                                                (Arrays.equals(lower4, whiteFive[3]) && !Arrays.equals(lower3, whiteFive[2])) ||
                                                                (Arrays.equals(lower4, whiteFive[4]) && !Arrays.equals(lower3, whiteFive[3]))))){
                                                    int[][] oneFour = {coords,lower1,lower2,lower3};
                                                    allFours.add(oneFour);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                else { // Fours not touching top or bottom
                    int[] upper = {coords[0] + 1, coords[1]};
                    for (int i = 0; i < whiteMoves.size(); i++){
                        if (Arrays.equals(whiteMoves.get(i), lower1)){
                            for (int j = 0; j < whiteMoves.size(); j++){
                                if (Arrays.equals(whiteMoves.get(j), lower2)){
                                    for (int k = 0; k < whiteMoves.size(); k++){
                                        if (Arrays.equals(whiteMoves.get(k), lower3)){
                                            boolean inWhiteMoves = false;
                                            for (int l = 0; l < whiteMoves.size(); l++){
                                                if (Arrays.equals(whiteMoves.get(l), lower4)){
                                                    inWhiteMoves = true;
                                                }
                                                if (l == whiteMoves.size() - 1 && (inWhiteMoves == false ||
                                                        ((Arrays.equals(lower4, whiteFive[0]) && !Arrays.equals(lower3, whiteFive[1])) ||
                                                                (Arrays.equals(lower4, whiteFive[1]) && !Arrays.equals(lower3, whiteFive[2])) ||
                                                                (Arrays.equals(lower4, whiteFive[2]) && !Arrays.equals(lower3, whiteFive[1]) && !Arrays.equals(lower3, whiteFive[3])) ||
                                                                (Arrays.equals(lower4, whiteFive[3]) && !Arrays.equals(lower3, whiteFive[2])) ||
                                                                (Arrays.equals(lower4, whiteFive[4]) && !Arrays.equals(lower3, whiteFive[3]))))){
                                                    inWhiteMoves = false;
                                                    for (int m = 0; m < whiteMoves.size(); m++){
                                                        if (Arrays.equals(whiteMoves.get(m), upper)){
                                                            inWhiteMoves = true;
                                                            if ((Arrays.equals(upper, whiteFive[0]) && !Arrays.equals(coords, whiteFive[1])) ||
                                                                    (Arrays.equals(upper, whiteFive[1]) && !Arrays.equals(coords, whiteFive[2])) ||
                                                                    (Arrays.equals(upper, whiteFive[2]) && !Arrays.equals(coords, whiteFive[1]) && !Arrays.equals(coords, whiteFive[3])) ||
                                                                    (Arrays.equals(upper, whiteFive[3]) && !Arrays.equals(coords, whiteFive[2])) ||
                                                                    (Arrays.equals(upper, whiteFive[4]) && !Arrays.equals(coords, whiteFive[3]))){
                                                                inWhiteMoves = false;
                                                            }
                                                        }
                                                        if (m == whiteMoves.size() - 1 && inWhiteMoves == false){
                                                            int[][] oneFour = {coords,lower1,lower2,lower3};
                                                            allFours.add(oneFour);
                                                        }
                                                    }

                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else { // Fours touching right.
                int[] upper = {coords[0] + 1, coords[1]};
                for (int i = 0; i < whiteMoves.size(); i++){
                    if (Arrays.equals(whiteMoves.get(i), lower1)){
                        for (int j = 0; j < whiteMoves.size(); j++){
                            if (Arrays.equals(whiteMoves.get(j), lower2)){
                                for (int k = 0; k < whiteMoves.size(); k++){
                                    if (Arrays.equals(whiteMoves.get(k), lower3)){
                                        boolean inWhiteMoves = false;
                                        for (int m = 0; m < whiteMoves.size(); m++){
                                            if (Arrays.equals(whiteMoves.get(m), upper)){
                                                inWhiteMoves = true;
                                            }
                                            if (m == whiteMoves.size() - 1 && (inWhiteMoves == false ||
                                                    ((Arrays.equals(upper, whiteFive[0]) && !Arrays.equals(coords, whiteFive[1])) ||
                                                            (Arrays.equals(upper, whiteFive[1]) && !Arrays.equals(coords, whiteFive[2])) ||
                                                            (Arrays.equals(upper, whiteFive[2]) && !Arrays.equals(coords, whiteFive[1]) && !Arrays.equals(coords, whiteFive[3])) ||
                                                            (Arrays.equals(upper, whiteFive[3]) && !Arrays.equals(coords, whiteFive[2])) ||
                                                            (Arrays.equals(upper, whiteFive[4]) && !Arrays.equals(coords, whiteFive[3]))))){
                                                int[][] oneFour = {coords,lower1,lower2,lower3};
                                                allFours.add(oneFour);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return allFours;
    }


    /**
     * Calls findWhiteFours on all white pieces and returns
     * an array of all white fours on the board, each white
     * four being an array of coordinates, which are themselves
     * arrays.
     *
     * ex: return {{{x1,y1},{x2,y2},{x3,y3},{x4,y4}},
     * 			   {{x5,y5},{x6,y6},{x7,y7},{x8,y8}},
     * 			   {{x9,y9},{x10,y10},{x11,y11},{x12,y12}},
     * 			   {{x13,y13},{x14,y14},{x15,y15},{x16,y16}}}
     *
     * @return ArrayList<[][]>
     */
    public ArrayList<int[][]> findAllWhiteFours(){
        ArrayList<int[][]> allFours = new ArrayList<int[][]>();
        int[][] whiteFive = findWhiteFive();
        for (int i = 0; i < whiteMoves.size(); i++){
            ArrayList<int[][]> someFours = findWhiteFours(whiteMoves.get(i), whiteFive);
            if (someFours.isEmpty() == false){ //this may be null. you're trying to see if someFours is empty)
                allFours.addAll(someFours);
            }

        }
        return allFours;
    }




    /**
     * Looks for black four-in-a-rows resulting from the
     * given move and returns an ArrayList of all found black
     * fours, each black four being an array of coordinates,
     * which are themselves arrays.
     *
     * ex: return {{{x1,y1},{x2,y2},{x3,y3},{x4,y4}},
     * 			   {{x5,y5},{x6,y6},{x7,y7},{x8,y8}}}
     *
     * @param int[2] coordinate
     * @return ArrayList<int[4][2]>
     */
    public ArrayList<int[][]> findBlackFours(int[] coords, int[][] blackFive){
        ArrayList<int[][]> allFours = new ArrayList<int[][]>();

        // ---- Upper Left Fours ---- //
        if (coords[0] > 2 && coords[1] > 2){
            int[] upperLeft1 = {coords[0] - 1, coords[1] - 1};
            int[] upperLeft2 = {coords[0] - 2, coords[1] - 2};
            int[] upperLeft3 = {coords[0] - 3, coords[1] - 3};
            if (coords[0] > 3 && coords[1] > 3){ // Fours not touching top or left.
                int[] upperLeft4 = {coords[0] - 4, coords[1] - 4};
                if (coords[0] == gameBoard.length - 2 || coords[1] == gameBoard.length - 2){ // Fours touching bottom or right.
                    for (int i = 0; i < blackMoves.size(); i++){
                        if (Arrays.equals(blackMoves.get(i), upperLeft1)){
                            for (int j = 0; j < blackMoves.size(); j++){
                                if (Arrays.equals(blackMoves.get(j), upperLeft2)){
                                    for (int k = 0; k < blackMoves.size(); k++){
                                        if (Arrays.equals(blackMoves.get(k), upperLeft3)){
                                            boolean inblackMoves = false;
                                            for (int l = 0; l < blackMoves.size(); l++){
                                                if (Arrays.equals(blackMoves.get(l), upperLeft4)){
                                                    inblackMoves = true;
                                                }
                                                if (l == blackMoves.size() - 1 &&
                                                        (inblackMoves == false ||
                                                                ((Arrays.equals(upperLeft4, blackFive[0]) && !Arrays.equals(upperLeft3, blackFive[1])) ||
                                                                        (Arrays.equals(upperLeft4, blackFive[1]) && !Arrays.equals(upperLeft3, blackFive[2])) ||
                                                                        (Arrays.equals(upperLeft4, blackFive[2]) && !Arrays.equals(upperLeft3, blackFive[1]) && !Arrays.equals(upperLeft3, blackFive[3])) ||
                                                                        (Arrays.equals(upperLeft4, blackFive[3]) && !Arrays.equals(upperLeft3, blackFive[2])) ||
                                                                        (Arrays.equals(upperLeft4, blackFive[4]) && !Arrays.equals(upperLeft3, blackFive[3]))))){
                                                    int[][] oneFour = {coords,upperLeft1,upperLeft2,upperLeft3};
                                                    allFours.add(oneFour);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                else { // Fours not touching top, left, bottom or right
                    int[] lowerRight = {coords[0] + 1, coords[1] + 1};
                    for (int i = 0; i < blackMoves.size(); i++){
                        if (Arrays.equals(blackMoves.get(i), upperLeft1)){
                            for (int j = 0; j < blackMoves.size(); j++){
                                if (Arrays.equals(blackMoves.get(j), upperLeft2)){
                                    for (int k = 0; k < blackMoves.size(); k++){
                                        if (Arrays.equals(blackMoves.get(k), upperLeft3)){
                                            boolean inblackMoves = false;
                                            for (int l = 0; l < blackMoves.size(); l++){
                                                if (Arrays.equals(blackMoves.get(l), upperLeft4)){
                                                    inblackMoves = true;
                                                }
                                                if (l == blackMoves.size() - 1 && (inblackMoves == false ||
                                                        ((Arrays.equals(upperLeft4, blackFive[0]) && !Arrays.equals(upperLeft3, blackFive[1])) ||
                                                                (Arrays.equals(upperLeft4, blackFive[1]) && !Arrays.equals(upperLeft3, blackFive[2])) ||
                                                                (Arrays.equals(upperLeft4, blackFive[2]) && !Arrays.equals(upperLeft3, blackFive[1]) && !Arrays.equals(upperLeft3, blackFive[3])) ||
                                                                (Arrays.equals(upperLeft4, blackFive[3]) && !Arrays.equals(upperLeft3, blackFive[2])) ||
                                                                (Arrays.equals(upperLeft4, blackFive[4]) && !Arrays.equals(upperLeft3, blackFive[3]))))){
                                                    inblackMoves = false;
                                                    for (int m = 0; m < blackMoves.size(); m++){
                                                        if (Arrays.equals(blackMoves.get(m), lowerRight)){
                                                            inblackMoves = true;
                                                            if ((Arrays.equals(lowerRight, blackFive[0]) && !Arrays.equals(coords, blackFive[1])) ||
                                                                    (Arrays.equals(lowerRight, blackFive[1]) && !Arrays.equals(coords, blackFive[2])) ||
                                                                    (Arrays.equals(lowerRight, blackFive[2]) && !Arrays.equals(coords, blackFive[1]) && !Arrays.equals(coords, blackFive[3])) ||
                                                                    (Arrays.equals(lowerRight, blackFive[3]) && !Arrays.equals(coords, blackFive[2])) ||
                                                                    (Arrays.equals(lowerRight, blackFive[4]) && !Arrays.equals(coords, blackFive[3]))){
                                                                inblackMoves = false;
                                                            }
                                                        }
                                                        if (m == blackMoves.size() - 1 && inblackMoves == false){
                                                            int[][] oneFour = {coords,upperLeft1,upperLeft2,upperLeft3};
                                                            allFours.add(oneFour);
                                                        }
                                                    }

                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else { // Fours touching top or left.
                int[] lowerRight = {coords[0] + 1, coords[1] + 1};
                for (int i = 0; i < blackMoves.size(); i++){
                    if (Arrays.equals(blackMoves.get(i), upperLeft1)){
                        for (int j = 0; j < blackMoves.size(); j++){
                            if (Arrays.equals(blackMoves.get(j), upperLeft2)){
                                for (int k = 0; k < blackMoves.size(); k++){
                                    if (Arrays.equals(blackMoves.get(k), upperLeft3)){
                                        boolean inblackMoves = false;
                                        for (int m = 0; m < blackMoves.size(); m++){
                                            if (Arrays.equals(blackMoves.get(m), lowerRight)){
                                                inblackMoves = true;
                                            }
                                            if (m == blackMoves.size() - 1 && (inblackMoves == false ||
                                                    ((Arrays.equals(lowerRight, blackFive[0]) && !Arrays.equals(coords, blackFive[1])) ||
                                                            (Arrays.equals(lowerRight, blackFive[1]) && !Arrays.equals(coords, blackFive[2])) ||
                                                            (Arrays.equals(lowerRight, blackFive[2]) && !Arrays.equals(coords, blackFive[1]) && !Arrays.equals(coords, blackFive[3])) ||
                                                            (Arrays.equals(lowerRight, blackFive[3]) && !Arrays.equals(coords, blackFive[2])) ||
                                                            (Arrays.equals(lowerRight, blackFive[4]) && !Arrays.equals(coords, blackFive[3]))))){
                                                int[][] oneFour = {coords,upperLeft1,upperLeft2,upperLeft3};
                                                allFours.add(oneFour);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // ---- Left Fours ---- //

        if (coords[1] > 2){
            int[] left1 = {coords[0], coords[1] - 1};
            int[] left2 = {coords[0], coords[1] - 2};
            int[] left3 = {coords[0], coords[1] - 3};
            if (coords[1] > 3){ // Fours not touching left.
                int[] left4 = {coords[0], coords[1] - 4};
                if (coords[1] == gameBoard.length - 2){ // Fours touching right.
                    for (int i = 0; i < blackMoves.size(); i++){
                        if (Arrays.equals(blackMoves.get(i), left1)){
                            for (int j = 0; j < blackMoves.size(); j++){
                                if (Arrays.equals(blackMoves.get(j), left2)){
                                    for (int k = 0; k < blackMoves.size(); k++){
                                        if (Arrays.equals(blackMoves.get(k), left3)){
                                            boolean inblackMoves = false;
                                            for (int l = 0; l < blackMoves.size(); l++){
                                                if (Arrays.equals(blackMoves.get(l), left4)){
                                                    inblackMoves = true;
                                                }
                                                if (l == blackMoves.size() - 1 && (inblackMoves == false ||
                                                        ((Arrays.equals(left4, blackFive[0]) && !Arrays.equals(left3, blackFive[1])) ||
                                                                (Arrays.equals(left4, blackFive[1]) && !Arrays.equals(left3, blackFive[2])) ||
                                                                (Arrays.equals(left4, blackFive[2]) && !Arrays.equals(left3, blackFive[1]) && !Arrays.equals(left3, blackFive[3])) ||
                                                                (Arrays.equals(left4, blackFive[3]) && !Arrays.equals(left3, blackFive[2])) ||
                                                                (Arrays.equals(left4, blackFive[4]) && !Arrays.equals(left3, blackFive[3]))))){
                                                    int[][] oneFour = {coords,left1,left2,left3};
                                                    allFours.add(oneFour);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                else { // Fours not touching left or right
                    int[] right = {coords[0], coords[1] + 1};
                    for (int i = 0; i < blackMoves.size(); i++){
                        if (Arrays.equals(blackMoves.get(i), left1)){
                            for (int j = 0; j < blackMoves.size(); j++){
                                if (Arrays.equals(blackMoves.get(j), left2)){
                                    for (int k = 0; k < blackMoves.size(); k++){
                                        if (Arrays.equals(blackMoves.get(k), left3)){
                                            boolean inblackMoves = false;
                                            for (int l = 0; l < blackMoves.size(); l++){
                                                if (Arrays.equals(blackMoves.get(l), left4)){
                                                    inblackMoves = true;
                                                }
                                                if (l == blackMoves.size() - 1 && (inblackMoves == false ||
                                                        ((Arrays.equals(left4, blackFive[0]) && !Arrays.equals(left3, blackFive[1])) ||
                                                                (Arrays.equals(left4, blackFive[1]) && !Arrays.equals(left3, blackFive[2])) ||
                                                                (Arrays.equals(left4, blackFive[2]) && !Arrays.equals(left3, blackFive[1]) && !Arrays.equals(left3, blackFive[3])) ||
                                                                (Arrays.equals(left4, blackFive[3]) && !Arrays.equals(left3, blackFive[2])) ||
                                                                (Arrays.equals(left4, blackFive[4]) && !Arrays.equals(left3, blackFive[3]))))){
                                                    inblackMoves = false;
                                                    for (int m = 0; m < blackMoves.size(); m++){
                                                        if (Arrays.equals(blackMoves.get(m), right)){
                                                            inblackMoves = true;
                                                            if ((Arrays.equals(right, blackFive[0]) && !Arrays.equals(coords, blackFive[1])) ||
                                                                    (Arrays.equals(right, blackFive[1]) && !Arrays.equals(coords, blackFive[2])) ||
                                                                    (Arrays.equals(right, blackFive[2]) && !Arrays.equals(coords, blackFive[1]) && !Arrays.equals(coords, blackFive[3])) ||
                                                                    (Arrays.equals(right, blackFive[3]) && !Arrays.equals(coords, blackFive[2])) ||
                                                                    (Arrays.equals(right, blackFive[4]) && !Arrays.equals(coords, blackFive[3]))){
                                                                inblackMoves = false;
                                                            }
                                                        }
                                                        if (m == blackMoves.size() - 1 && inblackMoves == false){
                                                            int[][] oneFour = {coords,left1,left2,left3};

                                                            allFours.add(oneFour);
                                                        }
                                                    }

                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                    }
                }
            }

            else { // Fours touching right.
                int[] right = {coords[0], coords[1] + 1};
                for (int i = 0; i < blackMoves.size(); i++){
                    if (Arrays.equals(blackMoves.get(i), left1)){
                        for (int j = 0; j < blackMoves.size(); j++){
                            if (Arrays.equals(blackMoves.get(j), left2)){
                                for (int k = 0; k < blackMoves.size(); k++){
                                    if (Arrays.equals(blackMoves.get(k), left3)){
                                        boolean inblackMoves = false;
                                        for (int m = 0; m < blackMoves.size(); m++){
                                            if (Arrays.equals(blackMoves.get(m), right)){
                                                inblackMoves = true;
                                            }
                                            if (m == blackMoves.size() - 1 && (inblackMoves == false ||
                                                    ((Arrays.equals(right, blackFive[0]) && !Arrays.equals(coords, blackFive[1])) ||
                                                            (Arrays.equals(right, blackFive[1]) && !Arrays.equals(coords, blackFive[2])) ||
                                                            (Arrays.equals(right, blackFive[2]) && !Arrays.equals(coords, blackFive[1]) && !Arrays.equals(coords, blackFive[3])) ||
                                                            (Arrays.equals(right, blackFive[3]) && !Arrays.equals(coords, blackFive[2])) ||
                                                            (Arrays.equals(right, blackFive[4]) && !Arrays.equals(coords, blackFive[3]))))){
                                                int[][] oneFour = {coords,left1,left2,left3};
                                                allFours.add(oneFour);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }


        //---- Upper Right ----//
        if (coords[0] < gameBoard.length - 2 && coords[1] > 2){
            int[] upperRight1 = {coords[0] + 1, coords[1] - 1};
            int[] upperRight2 = {coords[0] + 2, coords[1] - 2};
            int[] upperRight3 = {coords[0] + 3, coords[1] - 3};
            if (coords[0] < gameBoard.length - 3 && coords[1] > 3){ // Fours not touching top or right.
                int[] upperRight4 = {coords[0] + 4, coords[1] - 4};
                if (coords[0] == gameBoard.length + 2 || coords[1] == gameBoard.length - 2){ // Fours touching bottom or left.
                    for (int i = 0; i < blackMoves.size(); i++){
                        if (Arrays.equals(blackMoves.get(i), upperRight1)){
                            for (int j = 0; j < blackMoves.size(); j++){
                                if (Arrays.equals(blackMoves.get(j), upperRight2)){
                                    for (int k = 0; k < blackMoves.size(); k++){
                                        if (Arrays.equals(blackMoves.get(k), upperRight3)){
                                            boolean inblackMoves = false;
                                            for (int l = 0; l < blackMoves.size(); l++){
                                                if (Arrays.equals(blackMoves.get(l), upperRight4)){
                                                    inblackMoves = true;
                                                }
                                                if (l == blackMoves.size() - 1 && (inblackMoves == false ||
                                                        ((Arrays.equals(upperRight4, blackFive[0]) && !Arrays.equals(upperRight3, blackFive[1])) ||
                                                                (Arrays.equals(upperRight4, blackFive[1]) && !Arrays.equals(upperRight3, blackFive[2])) ||
                                                                (Arrays.equals(upperRight4, blackFive[2]) && !Arrays.equals(upperRight3, blackFive[1]) && !Arrays.equals(upperRight3, blackFive[3])) ||
                                                                (Arrays.equals(upperRight4, blackFive[3]) && !Arrays.equals(upperRight3, blackFive[2])) ||
                                                                (Arrays.equals(upperRight4, blackFive[4]) && !Arrays.equals(upperRight3, blackFive[3]))))){
                                                    int[][] oneFour = {coords,upperRight1,upperRight2,upperRight3};
                                                    allFours.add(oneFour);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                else { // Fours not touching top, left, bottom or right
                    int[] lowerLeft = {coords[0] - 1, coords[1] + 1};
                    for (int i = 0; i < blackMoves.size(); i++){
                        if (Arrays.equals(blackMoves.get(i), upperRight1)){
                            for (int j = 0; j < blackMoves.size(); j++){
                                if (Arrays.equals(blackMoves.get(j), upperRight2)){
                                    for (int k = 0; k < blackMoves.size(); k++){
                                        if (Arrays.equals(blackMoves.get(k), upperRight3)){
                                            boolean inblackMoves = false;
                                            for (int l = 0; l < blackMoves.size(); l++){
                                                if (Arrays.equals(blackMoves.get(l), upperRight4)){
                                                    inblackMoves = true;
                                                }
                                                if (l == blackMoves.size() - 1 && (inblackMoves == false ||
                                                        ((Arrays.equals(upperRight4, blackFive[0]) && !Arrays.equals(upperRight3, blackFive[1])) ||
                                                                (Arrays.equals(upperRight4, blackFive[1]) && !Arrays.equals(upperRight3, blackFive[2])) ||
                                                                (Arrays.equals(upperRight4, blackFive[2]) && !Arrays.equals(upperRight3, blackFive[1]) && !Arrays.equals(upperRight3, blackFive[3])) ||
                                                                (Arrays.equals(upperRight4, blackFive[3]) && !Arrays.equals(upperRight3, blackFive[2])) ||
                                                                (Arrays.equals(upperRight4, blackFive[4]) && !Arrays.equals(upperRight3, blackFive[3]))))){
                                                    inblackMoves = false;
                                                    for (int m = 0; m < blackMoves.size(); m++){
                                                        if (Arrays.equals(blackMoves.get(m), lowerLeft)){
                                                            inblackMoves = true;
                                                            if ((Arrays.equals(lowerLeft, blackFive[0]) && !Arrays.equals(coords, blackFive[1])) ||
                                                                    (Arrays.equals(lowerLeft, blackFive[1]) && !Arrays.equals(coords, blackFive[2])) ||
                                                                    (Arrays.equals(lowerLeft, blackFive[2]) && !Arrays.equals(coords, blackFive[1]) && !Arrays.equals(coords, blackFive[3])) ||
                                                                    (Arrays.equals(lowerLeft, blackFive[3]) && !Arrays.equals(coords, blackFive[2])) ||
                                                                    (Arrays.equals(lowerLeft, blackFive[4]) && !Arrays.equals(coords, blackFive[3]))){
                                                                inblackMoves = false;
                                                            }
                                                        }
                                                        if (m == blackMoves.size() - 1 && inblackMoves == false){
                                                            int[][] oneFour = {coords,upperRight1,upperRight2,upperRight3};
                                                            allFours.add(oneFour);
                                                        }
                                                    }

                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else { // Fours touching top or left.
                int[] lowerLeft = {coords[0] - 1, coords[1] + 1};
                for (int i = 0; i < blackMoves.size(); i++){
                    if (Arrays.equals(blackMoves.get(i), upperRight1)){
                        for (int j = 0; j < blackMoves.size(); j++){
                            if (Arrays.equals(blackMoves.get(j), upperRight2)){
                                for (int k = 0; k < blackMoves.size(); k++){
                                    if (Arrays.equals(blackMoves.get(k), upperRight3)){
                                        boolean inblackMoves = false;
                                        for (int m = 0; m < blackMoves.size(); m++){
                                            if (Arrays.equals(blackMoves.get(m), lowerLeft)){
                                                inblackMoves = true;
                                            }
                                            if (m == blackMoves.size() - 1 && (inblackMoves == false ||
                                                    ((Arrays.equals(lowerLeft, blackFive[0]) && !Arrays.equals(coords, blackFive[1])) ||
                                                            (Arrays.equals(lowerLeft, blackFive[1]) && !Arrays.equals(coords, blackFive[2])) ||
                                                            (Arrays.equals(lowerLeft, blackFive[2]) && !Arrays.equals(coords, blackFive[1]) && !Arrays.equals(coords, blackFive[3])) ||
                                                            (Arrays.equals(lowerLeft, blackFive[3]) && !Arrays.equals(coords, blackFive[2])) ||
                                                            (Arrays.equals(lowerLeft, blackFive[4]) && !Arrays.equals(coords, blackFive[3]))))){
                                                int[][] oneFour = {coords,upperRight1,upperRight2,upperRight3};
                                                allFours.add(oneFour);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (coords[0] > 2){
            int[] lower1 = {coords[0] - 1, coords[1]};
            int[] lower2 = {coords[0] - 2, coords[1]};
            int[] lower3 = {coords[0] - 3, coords[1]};
            if (coords[0] > 3){ // Fours not touching right.
                int[] lower4 = {coords[0] - 4, coords[1]};
                if (coords[0] == gameBoard.length - 2){ // Fours touching left.
                    for (int i = 0; i < blackMoves.size(); i++){
                        if (Arrays.equals(blackMoves.get(i), lower1)){
                            for (int j = 0; j < blackMoves.size(); j++){
                                if (Arrays.equals(blackMoves.get(j), lower2)){
                                    for (int k = 0; k < blackMoves.size(); k++){
                                        if (Arrays.equals(blackMoves.get(k), lower3)){
                                            boolean inblackMoves = false;
                                            for (int l = 0; l < blackMoves.size(); l++){
                                                if (Arrays.equals(blackMoves.get(l), lower4)){
                                                    inblackMoves = true;
                                                }
                                                if (l == blackMoves.size() - 1 && (inblackMoves == false ||
                                                        ((Arrays.equals(lower4, blackFive[0]) && !Arrays.equals(lower3, blackFive[1])) ||
                                                                (Arrays.equals(lower4, blackFive[1]) && !Arrays.equals(lower3, blackFive[2])) ||
                                                                (Arrays.equals(lower4, blackFive[2]) && !Arrays.equals(lower3, blackFive[1]) && !Arrays.equals(lower3, blackFive[3])) ||
                                                                (Arrays.equals(lower4, blackFive[3]) && !Arrays.equals(lower3, blackFive[2])) ||
                                                                (Arrays.equals(lower4, blackFive[4]) && !Arrays.equals(lower3, blackFive[3]))))){
                                                    int[][] oneFour = {coords,lower1,lower2,lower3};
                                                    allFours.add(oneFour);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                else { // Fours not touching top or bottom
                    int[] upper = {coords[0] + 1, coords[1]};
                    for (int i = 0; i < blackMoves.size(); i++){
                        if (Arrays.equals(blackMoves.get(i), lower1)){
                            for (int j = 0; j < blackMoves.size(); j++){
                                if (Arrays.equals(blackMoves.get(j), lower2)){
                                    for (int k = 0; k < blackMoves.size(); k++){
                                        if (Arrays.equals(blackMoves.get(k), lower3)){
                                            boolean inblackMoves = false;
                                            for (int l = 0; l < blackMoves.size(); l++){
                                                if (Arrays.equals(blackMoves.get(l), lower4)){
                                                    inblackMoves = true;
                                                }
                                                if (l == blackMoves.size() - 1 && (inblackMoves == false ||
                                                        ((Arrays.equals(lower4, blackFive[0]) && !Arrays.equals(lower3, blackFive[1])) ||
                                                                (Arrays.equals(lower4, blackFive[1]) && !Arrays.equals(lower3, blackFive[2])) ||
                                                                (Arrays.equals(lower4, blackFive[2]) && !Arrays.equals(lower3, blackFive[1]) && !Arrays.equals(lower3, blackFive[3])) ||
                                                                (Arrays.equals(lower4, blackFive[3]) && !Arrays.equals(lower3, blackFive[2])) ||
                                                                (Arrays.equals(lower4, blackFive[4]) && !Arrays.equals(lower3, blackFive[3]))))){
                                                    inblackMoves = false;
                                                    for (int m = 0; m < blackMoves.size(); m++){
                                                        if (Arrays.equals(blackMoves.get(m), upper)){
                                                            inblackMoves = true;
                                                            if ((Arrays.equals(upper, blackFive[0]) && !Arrays.equals(coords, blackFive[1])) ||
                                                                    (Arrays.equals(upper, blackFive[1]) && !Arrays.equals(coords, blackFive[2])) ||
                                                                    (Arrays.equals(upper, blackFive[2]) && !Arrays.equals(coords, blackFive[1]) && !Arrays.equals(coords, blackFive[3])) ||
                                                                    (Arrays.equals(upper, blackFive[3]) && !Arrays.equals(coords, blackFive[2])) ||
                                                                    (Arrays.equals(upper, blackFive[4]) && !Arrays.equals(coords, blackFive[3]))){
                                                                inblackMoves = false;
                                                            }
                                                        }
                                                        if (m == blackMoves.size() - 1 && inblackMoves == false){
                                                            int[][] oneFour = {coords,lower1,lower2,lower3};
                                                            allFours.add(oneFour);
                                                        }
                                                    }

                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else { // Fours touching right.
                int[] upper = {coords[0] + 1, coords[1]};
                for (int i = 0; i < blackMoves.size(); i++){
                    if (Arrays.equals(blackMoves.get(i), lower1)){
                        for (int j = 0; j < blackMoves.size(); j++){
                            if (Arrays.equals(blackMoves.get(j), lower2)){
                                for (int k = 0; k < blackMoves.size(); k++){
                                    if (Arrays.equals(blackMoves.get(k), lower3)){
                                        boolean inblackMoves = false;
                                        for (int m = 0; m < blackMoves.size(); m++){
                                            if (Arrays.equals(blackMoves.get(m), upper)){
                                                inblackMoves = true;
                                            }
                                            if (m == blackMoves.size() - 1 && (inblackMoves == false ||
                                                    ((Arrays.equals(upper, blackFive[0]) && !Arrays.equals(coords, blackFive[1])) ||
                                                            (Arrays.equals(upper, blackFive[1]) && !Arrays.equals(coords, blackFive[2])) ||
                                                            (Arrays.equals(upper, blackFive[2]) && !Arrays.equals(coords, blackFive[1]) && !Arrays.equals(coords, blackFive[3])) ||
                                                            (Arrays.equals(upper, blackFive[3]) && !Arrays.equals(coords, blackFive[2])) ||
                                                            (Arrays.equals(upper, blackFive[4]) && !Arrays.equals(coords, blackFive[3]))))){
                                                int[][] oneFour = {coords,lower1,lower2,lower3};
                                                allFours.add(oneFour);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return allFours;
    }



    /**
     * Calls findBlackFours on all black pieces and returns
     * an array of all black fours on the board, each black
     * four being an array of coordinates, which are themselves
     * arrays.
     *
     * ex: return {{{x1,y1},{x2,y2},{x3,y3},{x4,y4}},
     * 			   {{x5,y5},{x6,y6},{x7,y7},{x8,y8}},
     * 			   {{x9,y9},{x10,y10},{x11,y11},{x12,y12}},
     * 			   {{x13,y13},{x14,y14},{x15,y15},{x16,y16}}}
     *
     * @return int[][][]
     */
    public ArrayList<int[][]> findAllBlackFours(){
        ArrayList<int[][]> allFours = new ArrayList<int[][]>();
        int[][] blackFive = findBlackFive();
        for (int i = 0; i < blackMoves.size(); i++){
            ArrayList<int[][]> someFours = findBlackFours(blackMoves.get(i), blackFive);
            if (someFours.isEmpty() == false){ //this may be null. you're trying to see if someFours is empty)
                allFours.addAll(someFours);
            }

        }
        return allFours;
    }


    /**
     * Looks for a white five, and returns it if there is one.
     * Returns an int[][] of -1's if not.
     *
     * @return int[][]
     */
    public int[][] findWhiteFive(){
        ArrayList<int[]> whiteMovesList = new ArrayList<int[]>();
        for (int i = 0; i < gameBoard.length; i++){
            for (int j = 0; j < gameBoard[i].length; j++){
                if (gameBoard[i][j] == 'W'){
                    int[] coords = {i,j};
                    whiteMovesList.add(coords);
                }
            }
        }
        for (int i = 0; i < whiteMovesList.size(); i++){
            if (whiteMovesList.get(i)[0] > 3 && whiteMovesList.get(i)[1] > 3){
                int[] upperLeft1 = {whiteMovesList.get(i)[0] - 1, whiteMovesList.get(i)[1] - 1};
                int[] upperLeft2 = {whiteMovesList.get(i)[0] - 2, whiteMovesList.get(i)[1] - 2};
                int[] upperLeft3 = {whiteMovesList.get(i)[0] - 3, whiteMovesList.get(i)[1] - 3};
                int[] upperLeft4 = {whiteMovesList.get(i)[0] - 4, whiteMovesList.get(i)[1] - 4};
                for (int j = 0; j < whiteMovesList.size(); j++){
                    if (Arrays.equals(whiteMovesList.get(j), upperLeft1)){
                        for (int k = 0; k < whiteMovesList.size(); k++){
                            if (Arrays.equals(whiteMovesList.get(k), upperLeft2)){
                                for (int l = 0; l < whiteMovesList.size(); l++){
                                    if (Arrays.equals(whiteMovesList.get(l), upperLeft3)){
                                        for (int m = 0; m < whiteMovesList.size(); m++){
                                            if (Arrays.equals(whiteMovesList.get(m), upperLeft4)){
                                                int[][] arrayOfFive = {whiteMovesList.get(i), upperLeft1, upperLeft2, upperLeft3, upperLeft4};
                                                return arrayOfFive;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }
            if (whiteMovesList.get(i)[1] > 3){
                int[] upper1 = {whiteMovesList.get(i)[0], whiteMovesList.get(i)[1] - 1};
                int[] upper2 = {whiteMovesList.get(i)[0], whiteMovesList.get(i)[1] - 2};
                int[] upper3 = {whiteMovesList.get(i)[0], whiteMovesList.get(i)[1] - 3};
                int[] upper4 = {whiteMovesList.get(i)[0], whiteMovesList.get(i)[1] - 4};
                for (int j = 0; j < whiteMovesList.size(); j++){
                    if (Arrays.equals(whiteMovesList.get(j), upper1)){
                        for (int k = 0; k < whiteMovesList.size(); k++){
                            if (Arrays.equals(whiteMovesList.get(k), upper2)){
                                for (int l = 0; l < whiteMovesList.size(); l++){
                                    if (Arrays.equals(whiteMovesList.get(l), upper3)){
                                        for (int m = 0; m < whiteMovesList.size(); m++){
                                            if (Arrays.equals(whiteMovesList.get(m), upper4)){
                                                int[][] arrayOfFive = {whiteMovesList.get(i), upper1, upper2, upper3, upper4};
                                                return arrayOfFive;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }
            if (whiteMovesList.get(i)[0] < gameBoard.length - 3 && whiteMovesList.get(i)[1] > 3){
                int[] upperRight1 = {whiteMovesList.get(i)[0] + 1, whiteMovesList.get(i)[1] - 1};
                int[] upperRight2 = {whiteMovesList.get(i)[0] + 2, whiteMovesList.get(i)[1] - 2};
                int[] upperRight3 = {whiteMovesList.get(i)[0] + 3, whiteMovesList.get(i)[1] - 3};
                int[] upperRight4 = {whiteMovesList.get(i)[0] + 4, whiteMovesList.get(i)[1] - 4};
                for (int j = 0; j < whiteMovesList.size(); j++){
                    if (Arrays.equals(whiteMovesList.get(j), upperRight1)){
                        for (int k = 0; k < whiteMovesList.size(); k++){
                            if (Arrays.equals(whiteMovesList.get(k), upperRight2)){
                                for (int l = 0; l < whiteMovesList.size(); l++){
                                    if (Arrays.equals(whiteMovesList.get(l), upperRight3)){
                                        for (int m = 0; m < whiteMovesList.size(); m++){
                                            if (Arrays.equals(whiteMovesList.get(m), upperRight4)){
                                                int[][] arrayOfFive = {whiteMovesList.get(i), upperRight1, upperRight2, upperRight3, upperRight4};
                                                return arrayOfFive;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }

            if (whiteMovesList.get(i)[0] > 3){
                int[] right1 = {whiteMovesList.get(i)[0] - 1, whiteMovesList.get(i)[1]};
                int[] right2 = {whiteMovesList.get(i)[0] - 2, whiteMovesList.get(i)[1]};
                int[] right3 = {whiteMovesList.get(i)[0] - 3, whiteMovesList.get(i)[1]};
                int[] right4 = {whiteMovesList.get(i)[0] - 4, whiteMovesList.get(i)[1]};
                for (int j = 0; j < whiteMovesList.size(); j++){
                    if (Arrays.equals(whiteMovesList.get(j), right1)){
                        for (int k = 0; k < whiteMovesList.size(); k++){
                            if (Arrays.equals(whiteMovesList.get(k), right2)){
                                for (int l = 0; l < whiteMovesList.size(); l++){
                                    if (Arrays.equals(whiteMovesList.get(l), right3)){
                                        for (int m = 0; m < whiteMovesList.size(); m++){
                                            if (Arrays.equals(whiteMovesList.get(m), right4)){
                                                int[][] arrayOfFive = {whiteMovesList.get(i), right1, right2, right3, right4};
                                                return arrayOfFive;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }

        }
        int[][] noFivesArray = {{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1}};
        return noFivesArray;
    }


    /**
     * Looks for a black five, and returns it if there is one.
     * Returns an int[][] of -1's if not.
     *
     * @return int[][]
     */
    public int[][] findBlackFive(){
        ArrayList<int[]> blackMovesList = new ArrayList<int[]>();
        for (int i = 0; i < gameBoard.length; i++){
            for (int j = 0; j < gameBoard[i].length; j++){
                if (gameBoard[i][j] == 'B'){
                    int[] coords = {i,j};
                    blackMovesList.add(coords);
                }
            }
        }
        for (int i = 0; i < blackMovesList.size(); i++){
            if (blackMovesList.get(i)[0] > 3 && blackMovesList.get(i)[1] > 3){
                int[] upperLeft1 = {blackMovesList.get(i)[0] - 1, blackMovesList.get(i)[1] - 1};
                int[] upperLeft2 = {blackMovesList.get(i)[0] - 2, blackMovesList.get(i)[1] - 2};
                int[] upperLeft3 = {blackMovesList.get(i)[0] - 3, blackMovesList.get(i)[1] - 3};
                int[] upperLeft4 = {blackMovesList.get(i)[0] - 4, blackMovesList.get(i)[1] - 4};
                for (int j = 0; j < blackMovesList.size(); j++){
                    if (Arrays.equals(blackMovesList.get(j), upperLeft1)){
                        for (int k = 0; k < blackMovesList.size(); k++){
                            if (Arrays.equals(blackMovesList.get(k), upperLeft2)){
                                for (int l = 0; l < blackMovesList.size(); l++){
                                    if (Arrays.equals(blackMovesList.get(l), upperLeft3)){
                                        for (int m = 0; m < blackMovesList.size(); m++){
                                            if (Arrays.equals(blackMovesList.get(m), upperLeft4)){
                                                int[][] arrayOfFive = {blackMovesList.get(i), upperLeft1, upperLeft2, upperLeft3, upperLeft4};
                                                return arrayOfFive;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }
            if (blackMovesList.get(i)[1] > 3){
                int[] upper1 = {blackMovesList.get(i)[0], blackMovesList.get(i)[1] - 1};
                int[] upper2 = {blackMovesList.get(i)[0], blackMovesList.get(i)[1] - 2};
                int[] upper3 = {blackMovesList.get(i)[0], blackMovesList.get(i)[1] - 3};
                int[] upper4 = {blackMovesList.get(i)[0], blackMovesList.get(i)[1] - 4};
                for (int j = 0; j < blackMovesList.size(); j++){
                    if (Arrays.equals(blackMovesList.get(j), upper1)){
                        for (int k = 0; k < blackMovesList.size(); k++){
                            if (Arrays.equals(blackMovesList.get(k), upper2)){
                                for (int l = 0; l < blackMovesList.size(); l++){
                                    if (Arrays.equals(blackMovesList.get(l), upper3)){
                                        for (int m = 0; m < blackMovesList.size(); m++){
                                            if (Arrays.equals(blackMovesList.get(m), upper4)){
                                                int[][] arrayOfFive = {blackMovesList.get(i), upper1, upper2, upper3, upper4};
                                                return arrayOfFive;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }
            if (blackMovesList.get(i)[0] < gameBoard.length - 3 && blackMovesList.get(i)[1] > 3){
                int[] upperRight1 = {blackMovesList.get(i)[0] + 1, blackMovesList.get(i)[1] - 1};
                int[] upperRight2 = {blackMovesList.get(i)[0] + 2, blackMovesList.get(i)[1] - 2};
                int[] upperRight3 = {blackMovesList.get(i)[0] + 3, blackMovesList.get(i)[1] - 3};
                int[] upperRight4 = {blackMovesList.get(i)[0] + 4, blackMovesList.get(i)[1] - 4};
                for (int j = 0; j < blackMovesList.size(); j++){
                    if (Arrays.equals(blackMovesList.get(j), upperRight1)){
                        for (int k = 0; k < blackMovesList.size(); k++){
                            if (Arrays.equals(blackMovesList.get(k), upperRight2)){
                                for (int l = 0; l < blackMovesList.size(); l++){
                                    if (Arrays.equals(blackMovesList.get(l), upperRight3)){
                                        for (int m = 0; m < blackMovesList.size(); m++){
                                            if (Arrays.equals(blackMovesList.get(m), upperRight4)){
                                                int[][] arrayOfFive = {blackMovesList.get(i), upperRight1, upperRight2, upperRight3, upperRight4};
                                                return arrayOfFive;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }

            if (blackMovesList.get(i)[0] > 3){
                int[] right1 = {blackMovesList.get(i)[0] - 1, blackMovesList.get(i)[1]};
                int[] right2 = {blackMovesList.get(i)[0] - 2, blackMovesList.get(i)[1]};
                int[] right3 = {blackMovesList.get(i)[0] - 3, blackMovesList.get(i)[1]};
                int[] right4 = {blackMovesList.get(i)[0] - 4, blackMovesList.get(i)[1]};
                for (int j = 0; j < blackMovesList.size(); j++){
                    if (Arrays.equals(blackMovesList.get(j), right1)){
                        for (int k = 0; k < blackMovesList.size(); k++){
                            if (Arrays.equals(blackMovesList.get(k), right2)){
                                for (int l = 0; l < blackMovesList.size(); l++){
                                    if (Arrays.equals(blackMovesList.get(l), right3)){
                                        for (int m = 0; m < blackMovesList.size(); m++){
                                            if (Arrays.equals(blackMovesList.get(m), right4)){
                                                int[][] arrayOfFive = {blackMovesList.get(i), right1, right2, right3, right4};
                                                return arrayOfFive;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }

        }
        int[][] noFivesArray = {{-1,-1},{-1,-1},{-1,-1},{-1,-1},{-1,-1}};
        return noFivesArray;
    }


    /**
     * Calls findAllWhiteFours and findAllBlackFours and
     * gives points to players for every four they have
     * on the board.
     *
     * @return void
     */
    public void tallyFours(){
        ArrayList<int[][]> allWhiteFours = new ArrayList<int[][]>();
        ArrayList<int[][]> allBlackFours = new ArrayList<int[][]>();
        allWhiteFours = findAllWhiteFours();
        allBlackFours = findAllBlackFours();
        whitesMatchPoints += allWhiteFours.size();
        blacksMatchPoints += allBlackFours.size();
    }


    /**
     * Gives points to players for every capture they have.
     *
     * @return void
     */
    public void tallyCaptures(){
        whitesMatchPoints += whitesTotalCaptures;
        blacksMatchPoints += blacksTotalCaptures;
    }

    /**
     * Calls tallyFours and tallyCaptures.
     *
     * @return void
     */
    public void calculateCurrentMatchScore(){
        if (!is5InARowRules && !isNoCapturesRules){
            tallyFours();
            tallyCaptures();
        }
    }


    /**
     * Resets match info and clears the board.
     *
     * @return void
     */
    public void setupNewMatch(){
        clearBoard();

        whiteMoves = new ArrayList<int[]>();
        blackMoves = new ArrayList<int[]>();
        whitesTotalPoints += whitesMatchPoints;
        blacksTotalPoints += blacksMatchPoints;
        whitesMatchPoints = 0;
        blacksMatchPoints = 0;
        whitesTotalCaptures = 0;
        blacksTotalCaptures = 0;
        whitesCapturedStones = 0;
        blacksCapturedStones = 0;
        movesCount = 0;

    }


    /**
     * Checks 5-in-a-rows and capture tallies to determine
     * if the match ends.
     *
     * @return boolean isEnd
     */
    public boolean determineIfMatchEnds(){
        int[][] fiveInARow = new int[5][2];
        if ((isPlayer1Turn && isPlayer1White) || (!isPlayer1Turn && !isPlayer1White)){
            fiveInARow = findWhiteFive();
            if (fiveInARow[0][0] != -1){
                whitesMatchPoints += 5;
                return true;

            }
            else {
                if ((whitesCapturedStones >= 10 && !isKeryoRules) || (whitesCapturedStones >= 15 && isKeryoRules)){
                    return true;
                }
                else{
                    return false;
                }
            }
        }
        else {
            fiveInARow = findBlackFive();
            if (fiveInARow[0][0] != -1){
                blacksMatchPoints += 5;
                return true;

            }
            else {
                if ((blacksCapturedStones >= 10 && !isKeryoRules) || (blacksCapturedStones >= 15 && isKeryoRules)){
                    return true;
                }
                else{
                    return false;
                }
            }
        }

    }
}

