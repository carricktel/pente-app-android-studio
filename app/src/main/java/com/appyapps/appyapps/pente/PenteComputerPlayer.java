package com.appyapps.appyapps.pente;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class PenteComputerPlayer {

    public HashMap<Integer, PenteConfigurations> configsDict = new HashMap();
    //public Pente scenario = new Pente();
    public int playerNum;
    public boolean isPlayerWhite;
    public int[] leftBounds;
    public int[] rightBounds;
    public int[] upperBounds;
    public int[] lowerBounds;
    public int MovesQueueLength = 7;
    public int Depth = 5;
    static public ArrayList<Integer> branchEvals = new ArrayList<Integer>();
    static public ArrayList<Integer> numLeaves = new ArrayList<Integer>();
    public int gameBoardLen = 13;

    public PenteComputerPlayer(){
        this.branchEvals = branchEvals;
    }

    public void setDifficulty(int level){
        //FIXME
        if (level == 1){
            Depth = 3;
            MovesQueueLength = 10;
        };
        if (level == 2){
            Depth = 4;
            MovesQueueLength = 8;
        };
        if (level == 3){
            Depth = 5;
            MovesQueueLength = 7;
        };
    }

    public void setPlayerNum(){
        //FIXME
        playerNum = 2;
    }

    public int[][] generateMovesQueue(Pente scenario, ArrayList<PenteConfigurations> configsList){
        Random randomGenerator = new Random();
        boolean forWhite = false;
        if ((scenario.isPlayer1Turn && scenario.isPlayer1White) || (!scenario.isPlayer1Turn && !scenario.isPlayer1White)){
            forWhite = true;
        }
        int[][] movesQueue = new int[MovesQueueLength][3];
        //ArrayList<PenteConfigurations> configsList = generateConfigs(scenario);
        int priorityLevels = 8;
        int confListSize = configsList.size();
        int c = 0;
        boolean priority1Exists = false;
        boolean priority2Exists = false;
        boolean priority3Exists = false;
        for (int h = 0; h <= priorityLevels; h++){
            for (int i = 0; i < confListSize; i++){
                if (forWhite){
                    if (configsList.get(i).whitePriority == h && ((h > 3 && !priority3Exists && !priority1Exists && !priority2Exists) || (h == 3 && !priority1Exists && !priority2Exists) || (h == 2 && !priority1Exists) || (h == 1))){
                        for (int j = 0; j < configsList.get(i).whiteCounterMoves.size(); j++){
                            if (c < MovesQueueLength){
                                if (!isCoordInArrayList(configsList.get(i).whiteCounterMoves.get(j),scenario.blackMoves) && (!isCoordInArrayList(configsList.get(i).whiteCounterMoves.get(j),scenario.whiteMoves)) && (isCoordInBounds(configsList.get(i).whiteCounterMoves.get(j)))){
                                    int[] move = new int[3];
                                    move[0] = configsList.get(i).whiteCounterMoves.get(j)[0];
                                    move[1] = configsList.get(i).whiteCounterMoves.get(j)[1];
                                    move[2] = 100 - ((h-1)*12);
                                    if (!isCoordInArray(move,movesQueue)){
                                        movesQueue[c] = move;
                                        c++;
                                    }
                                    if (h == 1){
                                        priority1Exists = true;
                                    }
                                    if (h == 2){
                                        priority2Exists = true;
                                    }
                                    if (h == 3){
                                        priority3Exists = true;
                                    }


                                }
                            }
                        }
                    }
                }
                else {
                    if (configsList.get(i).blackPriority == h && ((h > 3 && !priority3Exists && !priority1Exists && !priority2Exists) || (h == 3 && !priority1Exists && !priority2Exists) || (h == 2 && !priority1Exists) || (h == 1))){
                        for (int j = 0; j < configsList.get(i).blackCounterMoves.size(); j++){
                            if (c < MovesQueueLength){
                                if (!isCoordInArrayList(configsList.get(i).blackCounterMoves.get(j),scenario.blackMoves) && (!isCoordInArrayList(configsList.get(i).blackCounterMoves.get(j),scenario.whiteMoves)) && (isCoordInBounds(configsList.get(i).blackCounterMoves.get(j)))){
                                    int[] move = new int[3];
                                    move[0] = configsList.get(i).blackCounterMoves.get(j)[0];
                                    move[1] = configsList.get(i).blackCounterMoves.get(j)[1];
                                    move[2] = 100 - ((h-1)*12);
                                    if (!isCoordInArray(move,movesQueue)){
                                        movesQueue[c] = move;
                                        c++;
                                    }
                                    if (h == 1){
                                        priority1Exists = true;
                                    }
                                    if (h == 2){
                                        priority2Exists = true;
                                    }
                                    if (h == 3){
                                        priority3Exists = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        ArrayList<int[]> openMoves = new ArrayList<int[]>();
        //A new arraylist of just coords (not eval) to check if coord is in arraylist.
        int[][] coordList = new int[MovesQueueLength][2];
        for (int i = 0; i < movesQueue.length; i++){
            int[] coord = new int[2];
            coord[0] = movesQueue[i][0];
            coord[1] = movesQueue[i][1];
            coordList[i] = coord;
        }
        if (c < MovesQueueLength && !priority1Exists && !priority2Exists){
            for (int i = 0; i < scenario.gameBoard.length; i++){
                for (int j = 0; j < scenario.gameBoard.length; j++){
                    int[] move = {i,j};
                    if (((i >= upperBounds[j] && i <= lowerBounds[j]) || (j >= leftBounds[i] && j <= rightBounds[i])) && (scenario.gameBoard[i][j] == ' ') && (isCoordInArray(move,coordList) == false)){
                        int[] move2 = {i,j,15};
                        openMoves.add(move2);
                    }
                }

            }

            while (c < MovesQueueLength && openMoves.size() > 0){
                int index = randomGenerator.nextInt(openMoves.size());
                int[] chosenMove = openMoves.get(index);
                movesQueue[c] = chosenMove;
                openMoves.remove(index);
                c++;

            }
        }

        int[][] newMovesQueue = new int[c][3];
        for (int i = 0; i < c; i++){
            newMovesQueue[i] = movesQueue[i];
        }
        int midboard = scenario.gameBoard.length/2;
        if (scenario.movesCount == 0){
            int[] centerBoard = {midboard, midboard, 15};
            newMovesQueue = new int[1][3];
            newMovesQueue[0] = centerBoard;
        }
        if (scenario.movesCount == 2 && scenario.isTournamentRules){
            int[][] coordArray = new int[8][3];
            int[] coord1 = {midboard + 3, midboard, 15};
            int[] coord2 = {midboard + 3, midboard + 3, 15};
            int[] coord3 = {midboard + 3, midboard - 3, 15};
            int[] coord4 = {midboard - 3, midboard, 15};
            int[] coord5 = {midboard - 3, midboard + 3, 15};
            int[] coord6 = {midboard - 3, midboard - 3, 15};
            int[] coord7 = {midboard, midboard + 3, 15};
            int[] coord8 = {midboard, midboard - 3, 15};
            coordArray[0] = coord1;
            coordArray[1] = coord2;
            coordArray[2] = coord3;
            coordArray[3] = coord4;
            coordArray[4] = coord5;
            coordArray[5] = coord6;
            coordArray[6] = coord7;
            coordArray[7] = coord8;

            newMovesQueue = new int[8][3];
            for(int i = 0; i < 8; i++){
                if (!isCoordInArrayList(coordArray[i], scenario.whiteMoves) && !isCoordInArrayList(coordArray[i], scenario.blackMoves)){
                    newMovesQueue[i] = coordArray[i];
                }
            }
        }

        return newMovesQueue;

    }

    public void generateBounds(Pente scenario){

        leftBounds = new int[scenario.gameBoard.length];
        rightBounds = new int[scenario.gameBoard.length];
        upperBounds = new int[scenario.gameBoard.length];
        lowerBounds = new int[scenario.gameBoard.length];
        for (int i = 0; i < scenario.gameBoard.length; i++){
            leftBounds[i] = 100;
            rightBounds[i] = -1;
            upperBounds[i] = 100;
            lowerBounds[i] = -1;
        }
        for (int i = 0; i < scenario.gameBoard.length; i++){
            for (int j = 0; j < scenario.gameBoard.length; j++){
                if (scenario.gameBoard[i][j] != ' '){
                    if (i < upperBounds[j]){
                        if (i >= 2){
                            upperBounds[j] = i - 2;
                        }
                        else upperBounds[j] = 0;
                    }
                    if (i > lowerBounds[j]){
                        if (i <= scenario.gameBoard.length - 4){
                            lowerBounds[j] = i + 2;
                        }
                        else lowerBounds[j] = scenario.gameBoard.length - 2;
                    }
                    if (j < leftBounds[i]){
                        if (j >= 2){
                            leftBounds[i] = j - 2;
                        }
                        else leftBounds[i] = 0;
                    }
                    if (j > rightBounds[i]){
                        if (j <= scenario.gameBoard.length - 4){
                            rightBounds[i] = j + 2;
                        }
                        else rightBounds[i] = scenario.gameBoard.length - 2;
                    }
                }
            }

        }

		/*//START PRINT
		System.out.println("leftBounds:");
		System.out.println(Arrays.toString(leftBounds));
		for (int i = 0; i < leftBounds.length; i++){
			System.out.println(leftBounds[i]);
		}
		System.out.println("rightBounds:");
		System.out.println(Arrays.toString(rightBounds));
		for (int i = 0; i < rightBounds.length; i++){
			System.out.println(rightBounds[i]);
		}
		System.out.println("upperBounds:");
		System.out.println(Arrays.toString(upperBounds));
		for (int i = 0; i < upperBounds.length; i++){
			System.out.println(upperBounds[i]);
		}
		System.out.println("lowerBounds:");
		System.out.println(Arrays.toString(lowerBounds));*/
		/*for (int i = 0; i < lowerBounds.length; i++){
			System.out.println(lowerBounds[i]);
		}*/
        //END PRINT
    }

    public int[] makeMove(boolean isWhite, Pente scenario){
        int[] bestMove = new int[2];
        generateBounds(scenario);
        ArrayList<PenteConfigurations> configsList = generateConfigs(scenario);
        int[][] movesQueue = generateMovesQueue(scenario,configsList);
        branchEvals = new ArrayList<Integer>();
        numLeaves = new ArrayList<Integer>();
        for (int i = 0; i < movesQueue.length; i++){
            branchEvals.add(0);
            numLeaves.add(0);

        }
        System.out.println("Populated branchEvals: " + branchEvals);
        for (int i = 0; i < movesQueue.length; i++){
            Pente newScenario = new Pente();
            newScenario = copyPenteObj(scenario);
            int[] coord = {movesQueue[i][0],movesQueue[i][1]};
            double probability = ((double)movesQueue[i][2])/100;
            seekBestMove(coord, probability, i, 1, newScenario);
        }
        double bestMoveEval = 0;
        for (int i = 0; i < branchEvals.size(); i++){
            System.out.println("move: "+ Arrays.toString(movesQueue[i]) + " | numLeaves" + numLeaves.get(i) + " | branchEval" + i + ": " + branchEvals.get(i)/numLeaves.get(i)/*/numLeaves*/);
            if (i == 0){
                int[] coord = {movesQueue[i][0],movesQueue[i][1]};
                bestMove = coord;
                bestMoveEval = branchEvals.get(i)/numLeaves.get(i)/*/numLeaves*/;///numLeaves.get(i);
            }
            if (branchEvals.get(i)/numLeaves.get(i)/*/numLeaves*/ > bestMoveEval){ ///numLeaves.get(i)
                //hasValue = true;
                int[] coord = {movesQueue[i][0],movesQueue[i][1]};
                bestMove = coord;
                //System.out.println("conditional best move: " + Arrays.toString(bestMove));

                bestMoveEval = branchEvals.get(i)/numLeaves.get(i)/*/numLeaves*/;
            }
        }
        //System.out.println("bestmove: " + Arrays.toString(bestMove));
        //System.out.println("printed best move: " + Arrays.toString(bestMove));
        return bestMove;
    }



/*	public int[] getOpponLastMove(){//I THINK WE DON'T NEED THIS ANYMORE
		//getOpponLastMove must only be called if opponent moved last (not if first move and players turn).
		int[] opponLastMove;
		if (playerNum == 2 && scenario.isPlayer1White == false || playerNum == 1 && scenario.isPlayer1White == true){
			opponLastMove = scenario.blackMoves.get(scenario.blackMoves.size() - 1);
		}

		else{
			opponLastMove = scenario.whiteMoves.get(scenario.whiteMoves.size() - 1);
		}
		return opponLastMove;
	}*/

    private void seekBestMove(int[] coord, double probability, int BID, int depth, Pente scenario) {
        scenario = resolve_Standard_Tournament_5InARow_Board(coord, scenario);
		/*System.out.println("----------------------------below this point the board has been resolved--------------------------------------");
		System.out.println("Depth: " + depth);
		System.out.println("playerWhite: " + scenario.isPlayer1Turn);
		System.out.println("Move: " + Arrays.toString(coord));
		System.out.println("probability: " + probability);
		scenario.drawTextBoard();*/
        if (depth == Depth /*|| (scenario.movesCount - depth < 3 && depth == 3)*/ || scenario.isGameInProgress == false){
            //newScenario = resolveStandardBoard(coord, newScenario);
            int Eval = evaluateBoard(generateConfigs(scenario), scenario);
            //System.out.println(Eval);
            double newEval = Eval - (Math.abs(Eval)-Math.abs(Eval*probability));
            //double newEval = Eval*probability;
            //double newEval = Eval - Math.abs(Eval*probability);
            //System.out.println(newEval);
            Eval = ((int) newEval);
            //System.out.println("newEval: " + Eval);
            branchEvals.set(BID, branchEvals.get(BID) + Eval);
            numLeaves.set(BID, numLeaves.get(BID) + 1);
            //	//System.out.println("End of game board: ");
            //scenario.drawTextBoard();
            //}
            //System.out.println(branchEvals);
            //System.out.println("----------------------------bottom--------------------------------------");

        }
        else {
            generateBounds(scenario);
            ArrayList<PenteConfigurations> configsList = generateConfigs(scenario);
            int[][] movesQueue = generateMovesQueue(scenario, configsList);
            //System.out.println("----------------------------bottom--------------------------------------");
            for (int i = 0; i < movesQueue.length; i++){
                Pente newScenario = new Pente();
                newScenario = copyPenteObj(scenario);
                double newProbability = probability * (((double) movesQueue[i][2])/100);
                seekBestMove(movesQueue[i],newProbability,BID,(depth+1),newScenario);
            }
        }
    }

    public boolean isCoordInBounds(int[] coord){
        if (coord[0] >= 0 && coord[0] <= gameBoardLen - 1 && coord[1] >= 0 && coord[1] <= gameBoardLen - 1){
            return true;
        }
        else return false;
    }

    public boolean isCoordInArrayList(int[] coord, ArrayList<int[]> arraylist){
        for (int i = 0; i < arraylist.size(); i++){
            if (Arrays.equals(arraylist.get(i), coord)){
                return true;
            }
        }
        return false;
    }

    public boolean isCoordInArray(int[] coord, int[][] array){
        for (int i = 0; i < array.length; i++){
            if (Arrays.equals(array[i], coord)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<PenteConfigurations> findConfig1(boolean isPieceWhite, int[] coord, Pente scenario, int lowPriority, int highPriority, int whiteEval, int blackEval){
        //is360Check is for when a capture has taken place and all of the effected configurations need to be re-configured.
        //Config1 is an open 2-in-a-row
        ArrayList<PenteConfigurations> configsList = new ArrayList<PenteConfigurations>();

        int[] upper = {coord[0]-1,coord[1]};
        int[] upper2 = {coord[0]-2,coord[1]};
        int[] upperRight = {coord[0]-1,coord[1]+1};
        int[] upperRight2 = {coord[0]-2,coord[1]+2};
        int[] right = {coord[0],coord[1]+1};
        int[] right2 = {coord[0],coord[1]+2};
        int[] lowerRight = {coord[0]+1,coord[1]+1};
        int[] lowerRight2 = {coord[0]+2,coord[1]+2};

        int[] lower = {coord[0]+1,coord[1]};
        int[] lowerLeft = {coord[0]+1,coord[1]-1};
        int[] left = {coord[0],coord[1]-1};
        int[] upperLeft = {coord[0]-1,coord[1]-1};

		/*if (isPlayersConfig == true){
			if (isP1Turn == true && isPieceWhite == true || isP1Turn == false && isPieceWhite == false){*/
        //find white configs and put them into a player's config
        if (isPieceWhite == true){
            if (isCoordInArrayList(upper,scenario.whiteMoves) &&
                    !isCoordInArrayList(upper2,scenario.whiteMoves) &&
                    !isCoordInArrayList(lower,scenario.whiteMoves) &&
                    ((!isCoordInArrayList(upper2,scenario.blackMoves) &&
                            !isCoordInArrayList(upper2,scenario.whiteMoves) &&
                            !isCoordInArrayList(lower,scenario.blackMoves) &&
                            !isCoordInArrayList(lower,scenario.whiteMoves)) ||
                            (isCoordInArrayList(upper2,scenario.blackMoves) &&
                                    !isCoordInArrayList(lower,scenario.blackMoves)) ||
                            (!isCoordInArrayList(upper2,scenario.blackMoves) &&
                                    isCoordInArrayList(lower,scenario.blackMoves)))){
                PenteConfigurations config1 = new PenteConfigurations();
                config1.confType = 1;
                config1.direction = 90;
                config1.isWhite = true;
                config1.whitePriority = highPriority; // 7
                config1.blackPriority = lowPriority;  // 8
                config1.originCoord = coord;
                config1.whiteEval = whiteEval; // eval = 2
                config1.blackEval = blackEval; // eval =-2
                if (isCoordInArrayList(upper2,scenario.blackMoves) || isCoordInArrayList(lower,scenario.blackMoves)){
                    config1.blackPriority = config1.blackPriority - 3; // 5
                    config1.whitePriority = config1.whitePriority - 2; // 5
                    config1.blackEval = -config1.blackEval*5; // eval = 10
                    config1.whiteEval = -config1.whiteEval*5; // eval = -10
                    if ((scenario.blacksCapturedStones == 8 && (scenario.isFreestyleRules || scenario.isStandardRules)) || (scenario.blacksCapturedStones == 13 && scenario.isKeryoRules)){
                        config1.blackPriority = config1.blackPriority - 4; // 1
                        config1.whitePriority = config1.whitePriority - 3; // 2
                        config1.blackEval = config1.blackEval*2; // eval = 20
                        config1.whiteEval = config1.whiteEval*2; // eval = -20
                    }
                }
                config1.whiteCounterMoves.add(upper2);
                config1.whiteCounterMoves.add(lower);
                config1.blackCounterMoves.add(upper2);
                config1.blackCounterMoves.add(lower);

                configsList.add(config1);
            }

            if (isCoordInArrayList(upperRight,scenario.whiteMoves) &&
                    !isCoordInArrayList(upperRight2,scenario.whiteMoves) &&
                    !isCoordInArrayList(lowerLeft,scenario.whiteMoves) &&
                    ((!isCoordInArrayList(upperRight2,scenario.blackMoves) &&
                            !isCoordInArrayList(upperRight2,scenario.whiteMoves) &&
                            !isCoordInArrayList(lowerLeft,scenario.blackMoves) &&
                            !isCoordInArrayList(lowerLeft,scenario.whiteMoves)) ||
                            (isCoordInArrayList(upperRight2,scenario.blackMoves) &&
                                    !isCoordInArrayList(lowerLeft,scenario.blackMoves)) ||
                            (!isCoordInArrayList(upperRight2,scenario.blackMoves) &&
                                    isCoordInArrayList(lowerLeft,scenario.blackMoves)))){
                PenteConfigurations config2 = new PenteConfigurations();
                config2.confType = 1;
                config2.direction = 45;
                config2.isWhite = true;
                config2.whitePriority = highPriority;
                config2.blackPriority = lowPriority;
                config2.originCoord = coord;
                config2.whiteEval = whiteEval;
                config2.blackEval = blackEval;
                if (isCoordInArrayList(upperRight2,scenario.blackMoves) || isCoordInArrayList(lowerLeft,scenario.blackMoves)){
                    config2.blackPriority = config2.blackPriority - 3; // 5
                    config2.whitePriority = config2.whitePriority - 2; // 5
                    config2.blackEval = -config2.blackEval*5; // eval = 10
                    config2.whiteEval = -config2.whiteEval*5; // eval = -10
                    if ((scenario.blacksCapturedStones == 8 && (scenario.isFreestyleRules || scenario.isStandardRules)) || (scenario.blacksCapturedStones == 13 && scenario.isKeryoRules)){
                        config2.blackPriority = config2.blackPriority - 4; // 1
                        config2.whitePriority = config2.whitePriority - 3; // 2
                        config2.blackEval = config2.blackEval*2; // eval = 20
                        config2.whiteEval = config2.whiteEval*2; // eval = -20
                    }
                }
                config2.whiteCounterMoves.add(upperRight2);
                config2.whiteCounterMoves.add(lowerLeft);
                config2.blackCounterMoves.add(upperRight2);
                config2.blackCounterMoves.add(lowerLeft);

                configsList.add(config2);
            }


            if (isCoordInArrayList(right,scenario.whiteMoves) &&
                    !isCoordInArrayList(right2,scenario.whiteMoves) &&
                    !isCoordInArrayList(left,scenario.whiteMoves) &&
                    ((!isCoordInArrayList(right2,scenario.whiteMoves) &&
                            !isCoordInArrayList(right2,scenario.blackMoves) &&
                            !isCoordInArrayList(left,scenario.blackMoves) &&
                            !isCoordInArrayList(left,scenario.whiteMoves)) ||
                            (isCoordInArrayList(right2,scenario.blackMoves) &&
                                    !isCoordInArrayList(left,scenario.blackMoves)) ||
                            (!isCoordInArrayList(right2,scenario.blackMoves) &&
                                    isCoordInArrayList(left,scenario.blackMoves)))){
                PenteConfigurations config3 = new PenteConfigurations();
                config3.confType = 1;
                config3.direction = 0;
                config3.isWhite = true;
                config3.whitePriority = highPriority;
                config3.blackPriority = lowPriority;
                config3.originCoord = coord;
                config3.whiteEval = whiteEval;
                config3.blackEval = blackEval;
                if (isCoordInArrayList(right2,scenario.blackMoves) || isCoordInArrayList(left,scenario.blackMoves)){
                    config3.blackPriority = config3.blackPriority - 3; // 5
                    config3.whitePriority = config3.whitePriority - 2; // 5
                    config3.blackEval = -config3.blackEval*5; // eval = 10
                    config3.whiteEval = -config3.whiteEval*5; // eval = -10
                    if ((scenario.blacksCapturedStones == 8 && (scenario.isFreestyleRules || scenario.isStandardRules)) || (scenario.blacksCapturedStones == 13 && scenario.isKeryoRules)){
                        config3.blackPriority = config3.blackPriority - 4; // 1
                        config3.whitePriority = config3.whitePriority - 3; // 2
                        config3.blackEval = config3.blackEval*2; // eval = 20
                        config3.whiteEval = config3.whiteEval*2; // eval = -20
                    }
                }
                config3.whiteCounterMoves.add(right2);
                config3.whiteCounterMoves.add(left);
                config3.blackCounterMoves.add(right2);
                config3.blackCounterMoves.add(left);

                configsList.add(config3);
            }

            if (isCoordInArrayList(lowerRight,scenario.whiteMoves) &&
                    !isCoordInArrayList(lowerRight2,scenario.whiteMoves) &&
                    !isCoordInArrayList(upperLeft,scenario.whiteMoves) &&
                    ((!isCoordInArrayList(lowerRight2,scenario.whiteMoves) &&
                            !isCoordInArrayList(lowerRight2,scenario.blackMoves) &&
                            !isCoordInArrayList(upperLeft,scenario.blackMoves) &&
                            !isCoordInArrayList(upperLeft,scenario.whiteMoves)) ||
                            (isCoordInArrayList(lowerRight2,scenario.blackMoves) &&
                                    !isCoordInArrayList(upperLeft,scenario.blackMoves)) ||
                            (!isCoordInArrayList(lowerRight2,scenario.blackMoves) &&
                                    isCoordInArrayList(upperLeft,scenario.blackMoves)))){
                PenteConfigurations config4 = new PenteConfigurations();
                config4.confType = 1;
                config4.direction = 315;
                config4.isWhite = true;
                config4.whitePriority = highPriority;
                config4.blackPriority = lowPriority;
                config4.originCoord = coord;
                config4.whiteEval = whiteEval;
                config4.blackEval = blackEval;
                if (isCoordInArrayList(lowerRight2,scenario.blackMoves) || isCoordInArrayList(upperLeft,scenario.blackMoves)){
                    config4.blackPriority = config4.blackPriority - 3; // 5
                    config4.whitePriority = config4.whitePriority - 2; // 5
                    config4.blackEval = -config4.blackEval*5; // eval = 10
                    config4.whiteEval = -config4.whiteEval*5; // eval = -10
                    if ((scenario.blacksCapturedStones == 8 && (scenario.isFreestyleRules || scenario.isStandardRules)) || (scenario.blacksCapturedStones == 13 && scenario.isKeryoRules)){
                        config4.blackPriority = config4.blackPriority - 4; // 1
                        config4.whitePriority = config4.whitePriority - 3; // 2
                        config4.blackEval = config4.blackEval*2; // eval = 20
                        config4.whiteEval = config4.whiteEval*2; // eval = -20
                    }
                }
                config4.whiteCounterMoves.add(lowerRight2);
                config4.whiteCounterMoves.add(upperLeft);
                config4.blackCounterMoves.add(lowerRight2);
                config4.blackCounterMoves.add(upperLeft);

                configsList.add(config4);
            }

        }
        if (isPieceWhite == false){
            if (isCoordInArrayList(upper,scenario.blackMoves) &&
                    !isCoordInArrayList(upper2,scenario.blackMoves) &&
                    !isCoordInArrayList(lower,scenario.blackMoves) &&
                    ((!isCoordInArrayList(upper2,scenario.blackMoves) &&
                            !isCoordInArrayList(upper2,scenario.whiteMoves) &&
                            !isCoordInArrayList(lower,scenario.whiteMoves) &&
                            !isCoordInArrayList(lower,scenario.blackMoves)) ||
                            (isCoordInArrayList(upper2,scenario.whiteMoves) &&
                                    !isCoordInArrayList(lower,scenario.whiteMoves)) ||
                            (!isCoordInArrayList(upper2,scenario.whiteMoves) &&
                                    isCoordInArrayList(lower,scenario.whiteMoves)))){
                PenteConfigurations config5 = new PenteConfigurations();
                config5.confType = 1;
                config5.direction = 90;
                config5.isWhite = false;
                config5.blackPriority = highPriority;
                config5.whitePriority = lowPriority;
                config5.originCoord = coord;
                config5.blackEval = -blackEval;
                config5.whiteEval = -whiteEval;
                if (isCoordInArrayList(upper2,scenario.whiteMoves) || isCoordInArrayList(lower,scenario.whiteMoves)){
                    config5.blackPriority = config5.blackPriority - 2; // 5
                    config5.whitePriority = config5.whitePriority - 3; // 5
                    config5.blackEval = -config5.blackEval*5; // eval = -10
                    config5.whiteEval = -config5.whiteEval*5; // eval = 10
                    if ((scenario.blacksCapturedStones == 8 && (scenario.isFreestyleRules || scenario.isStandardRules)) || (scenario.blacksCapturedStones == 13 && scenario.isKeryoRules)){
                        config5.blackPriority = config5.blackPriority - 3; // 2
                        config5.whitePriority = config5.whitePriority - 4; // 1
                        config5.blackEval = config5.blackEval*2; // eval = -20
                        config5.whiteEval = config5.whiteEval*2; // eval = 20
                    }
                }
                config5.blackCounterMoves.add(upper2);
                config5.blackCounterMoves.add(lower);
                config5.whiteCounterMoves.add(upper2);
                config5.whiteCounterMoves.add(lower);

                configsList.add(config5);
            }

            if (isCoordInArrayList(upperRight,scenario.blackMoves) &&
                    !isCoordInArrayList(upperRight2,scenario.blackMoves) &&
                    !isCoordInArrayList(lowerLeft,scenario.blackMoves) &&
                    ((!isCoordInArrayList(upperRight2,scenario.blackMoves) &&
                            !isCoordInArrayList(upperRight2,scenario.whiteMoves) &&
                            !isCoordInArrayList(lowerLeft,scenario.whiteMoves) &&
                            !isCoordInArrayList(lowerLeft,scenario.blackMoves)) ||
                            (isCoordInArrayList(upperRight2,scenario.whiteMoves) &&
                                    !isCoordInArrayList(lowerLeft,scenario.whiteMoves)) ||
                            (!isCoordInArrayList(upperRight2,scenario.whiteMoves) &&
                                    isCoordInArrayList(lowerLeft,scenario.whiteMoves)))){
                PenteConfigurations config6 = new PenteConfigurations();
                config6.confType = 1;
                config6.direction = 45;
                config6.isWhite = false;
                config6.blackPriority = highPriority;
                config6.whitePriority = lowPriority;
                config6.originCoord = coord;
                config6.blackEval = -blackEval;
                config6.whiteEval = -whiteEval;
                if (isCoordInArrayList(upperRight2,scenario.whiteMoves) || isCoordInArrayList(lowerLeft,scenario.whiteMoves)){
                    config6.blackPriority = config6.blackPriority - 2; // 5
                    config6.whitePriority = config6.whitePriority - 3; // 5
                    config6.blackEval = -config6.blackEval*5; // eval = -10
                    config6.whiteEval = -config6.whiteEval*5; // eval = 10
                    if ((scenario.blacksCapturedStones == 8 && (scenario.isFreestyleRules || scenario.isStandardRules)) || (scenario.blacksCapturedStones == 13 && scenario.isKeryoRules)){
                        config6.blackPriority = config6.blackPriority - 3; // 2
                        config6.whitePriority = config6.whitePriority - 4; // 1
                        config6.blackEval = config6.blackEval*2; // eval = -20
                        config6.whiteEval = config6.whiteEval*2; // eval = 20
                    }
                }
                config6.blackCounterMoves.add(upperRight2);
                config6.blackCounterMoves.add(lowerLeft);
                config6.whiteCounterMoves.add(upperRight2);
                config6.whiteCounterMoves.add(lowerLeft);

                configsList.add(config6);
            }


            if (isCoordInArrayList(right,scenario.blackMoves) &&
                    !isCoordInArrayList(right2,scenario.blackMoves) &&
                    !isCoordInArrayList(left,scenario.blackMoves) &&
                    ((!isCoordInArrayList(right2,scenario.blackMoves) &&
                            !isCoordInArrayList(right2,scenario.whiteMoves) &&
                            !isCoordInArrayList(left,scenario.whiteMoves) &&
                            !isCoordInArrayList(left,scenario.blackMoves)) ||
                            (isCoordInArrayList(right2,scenario.whiteMoves) &&
                                    !isCoordInArrayList(left,scenario.whiteMoves)) ||
                            (!isCoordInArrayList(right2,scenario.whiteMoves) &&
                                    isCoordInArrayList(left,scenario.whiteMoves)))){
                PenteConfigurations config7 = new PenteConfigurations();
                config7.confType = 1;
                config7.direction = 0;
                config7.isWhite = false;
                config7.blackPriority = highPriority;
                config7.whitePriority = lowPriority;
                config7.originCoord = coord;
                config7.blackEval = -blackEval;
                config7.whiteEval = -whiteEval;
                if (isCoordInArrayList(right2,scenario.whiteMoves) || isCoordInArrayList(left,scenario.whiteMoves)){
                    config7.blackPriority = config7.blackPriority - 2; // 5
                    config7.whitePriority = config7.whitePriority - 3; // 5
                    config7.blackEval = -config7.blackEval*5; // eval = -10
                    config7.whiteEval = -config7.whiteEval*5; // eval = 10
                    if ((scenario.blacksCapturedStones == 8 && (scenario.isFreestyleRules || scenario.isStandardRules)) || (scenario.blacksCapturedStones == 13 && scenario.isKeryoRules)){
                        config7.blackPriority = config7.blackPriority - 3; // 2
                        config7.whitePriority = config7.whitePriority - 4; // 1
                        config7.blackEval = config7.blackEval*2; // eval = -20
                        config7.whiteEval = config7.whiteEval*2; // eval = 20
                    }
                }
                config7.blackCounterMoves.add(right2);
                config7.blackCounterMoves.add(left);
                config7.whiteCounterMoves.add(right2);
                config7.whiteCounterMoves.add(left);

                configsList.add(config7);
            }

            if (isCoordInArrayList(lowerRight,scenario.blackMoves) &&
                    !isCoordInArrayList(lowerRight2,scenario.blackMoves) &&
                    !isCoordInArrayList(upperLeft,scenario.blackMoves) &&
                    ((!isCoordInArrayList(lowerRight2,scenario.blackMoves) &&
                            !isCoordInArrayList(lowerRight2,scenario.whiteMoves) &&
                            !isCoordInArrayList(upperLeft,scenario.whiteMoves) &&
                            !isCoordInArrayList(upperLeft,scenario.blackMoves)) ||
                            (isCoordInArrayList(lowerRight2,scenario.whiteMoves) &&
                                    !isCoordInArrayList(upperLeft,scenario.whiteMoves)) ||
                            (!isCoordInArrayList(lowerRight2,scenario.whiteMoves) &&
                                    isCoordInArrayList(upperLeft,scenario.whiteMoves)))){
                PenteConfigurations config8 = new PenteConfigurations();
                config8.confType = 1;
                config8.direction = 315;
                config8.isWhite = false;
                config8.blackPriority = highPriority;
                config8.whitePriority = lowPriority;
                config8.originCoord = coord;
                config8.blackEval = -blackEval;
                config8.whiteEval = -whiteEval;
                if (isCoordInArrayList(lowerRight2,scenario.whiteMoves) || isCoordInArrayList(upperLeft,scenario.whiteMoves)){
                    config8.blackPriority = config8.blackPriority - 2; // 5
                    config8.whitePriority = config8.whitePriority - 3; // 5
                    config8.blackEval = -config8.blackEval*5; // eval = -10
                    config8.whiteEval = -config8.whiteEval*5; // eval = 10
                    if ((scenario.blacksCapturedStones == 8 && (scenario.isFreestyleRules || scenario.isStandardRules)) || (scenario.blacksCapturedStones == 13 && scenario.isKeryoRules)){
                        config8.blackPriority = config8.blackPriority - 3; // 2
                        config8.whitePriority = config8.whitePriority - 4; // 1
                        config8.blackEval = config8.blackEval*2; // eval = -20
                        config8.whiteEval = config8.whiteEval*2; // eval = 20
                    }
                }
                config8.blackCounterMoves.add(lowerRight2);
                config8.blackCounterMoves.add(upperLeft);
                config8.whiteCounterMoves.add(lowerRight2);
                config8.whiteCounterMoves.add(upperLeft);

                configsList.add(config8);
            }
        }
        return configsList;
    }
			/*if (isP1Turn == true && isP1White == false || isP1Turn == false && isP1White == true){
				//find black configs and put them into a player's config
			}


		if (isPlayersConfig == false){
			if (isP1Turn == true && isP1White == false || isP1Turn == false && isP1White == true){
				//find black configs and put them into a oppon's config
			}
			if (isP1Turn == true && isP1White == true || isP1Turn == false && isP1White == false){
				//find white configs and put them into a oppon's config
			}
		}*/

    public ArrayList<PenteConfigurations> findConfig2(boolean isPieceWhite, int[] coord, Pente scenario, int lowPriority, int highPriority, int whiteEval, int blackEval){
        //is360Check is for when a capture has taken place and all of the effected configurations need to be re-configured.
        //Config1 is an open 2-in-a-row
        ArrayList<PenteConfigurations> configsList = new ArrayList<PenteConfigurations>();

        int[] upper = {coord[0]-1,coord[1]};
        int[] upper2 = {coord[0]-2,coord[1]};
        int[] upper3 = {coord[0]-3,coord[1]};
        int[] upperRight = {coord[0]-1,coord[1]+1};
        int[] upperRight2 = {coord[0]-2,coord[1]+2};
        int[] upperRight3 = {coord[0]-3,coord[1]+3};
        int[] right = {coord[0],coord[1]+1};
        int[] right2 = {coord[0],coord[1]+2};
        int[] right3 = {coord[0],coord[1]+3};
        int[] lowerRight = {coord[0]+1,coord[1]+1};
        int[] lowerRight2 = {coord[0]+2,coord[1]+2};
        int[] lowerRight3 = {coord[0]+3,coord[1]+3};

        int[] lower = {coord[0]+1,coord[1]};
        int[] lowerLeft = {coord[0]+1,coord[1]-1};
        int[] left = {coord[0],coord[1]-1};
        int[] upperLeft = {coord[0]-1,coord[1]-1};

		/*if (isPlayersConfig == true){
			if (isP1Turn == true && isPieceWhite == true || isP1Turn == false && isPieceWhite == false){*/
        //find white configs and put them into a player's config
        if (isPieceWhite == true){
            if (isCoordInArrayList(upper,scenario.whiteMoves) &&
                    isCoordInArrayList(upper2,scenario.whiteMoves) &&
                    !isCoordInArrayList(upper3,scenario.whiteMoves) &&
                    !isCoordInArrayList(lower,scenario.whiteMoves) &&
                    ((!isCoordInArrayList(upper3,scenario.blackMoves) &&
                            !isCoordInArrayList(upper3,scenario.whiteMoves) &&
                            !isCoordInArrayList(lower,scenario.blackMoves) &&
                            !isCoordInArrayList(lower,scenario.whiteMoves)) ||
                            (isCoordInArrayList(upper3,scenario.blackMoves) &&
                                    !isCoordInArrayList(lower,scenario.blackMoves)) ||
                            (!isCoordInArrayList(upper3,scenario.blackMoves) &&
                                    isCoordInArrayList(lower,scenario.blackMoves)))){
                PenteConfigurations config1 = new PenteConfigurations();
                config1.confType = 1;
                config1.direction = 90;
                config1.isWhite = true;
                config1.whitePriority = highPriority;
                config1.blackPriority = lowPriority;
                config1.originCoord = coord;
                config1.whiteEval = whiteEval;
                config1.blackEval = blackEval;
                if (!isCoordInArrayList(upper3,scenario.blackMoves) && !isCoordInArrayList(lower,scenario.blackMoves)){
                    config1.whitePriority = config1.whitePriority - 2;
                    config1.blackPriority = config1.blackPriority - 2;
                    config1.whiteEval = whiteEval*2;
                    config1.blackEval = blackEval*2;
                }
                config1.whiteCounterMoves.add(upper3);
                config1.whiteCounterMoves.add(lower);
                config1.blackCounterMoves.add(upper3);
                config1.blackCounterMoves.add(lower);

                configsList.add(config1);
            }

            if (isCoordInArrayList(upperRight,scenario.whiteMoves) &&
                    isCoordInArrayList(upperRight2,scenario.whiteMoves) &&
                    !isCoordInArrayList(upperRight3,scenario.whiteMoves) &&
                    !isCoordInArrayList(lowerLeft,scenario.whiteMoves) &&
                    ((!isCoordInArrayList(upperRight3,scenario.blackMoves) &&
                            !isCoordInArrayList(upperRight3,scenario.whiteMoves) &&
                            !isCoordInArrayList(lowerLeft,scenario.blackMoves) &&
                            !isCoordInArrayList(lowerLeft,scenario.whiteMoves)) ||
                            (isCoordInArrayList(upperRight3,scenario.blackMoves) &&
                                    !isCoordInArrayList(lowerLeft,scenario.blackMoves)) ||
                            (!isCoordInArrayList(upperRight3,scenario.blackMoves) &&
                                    isCoordInArrayList(lowerLeft,scenario.blackMoves)))){
                PenteConfigurations config2 = new PenteConfigurations();
                config2.confType = 1;
                config2.direction = 45;
                config2.isWhite = true;
                config2.whitePriority = highPriority;
                config2.blackPriority = lowPriority;
                config2.originCoord = coord;
                config2.whiteEval = whiteEval;
                config2.blackEval = blackEval;
                if (!isCoordInArrayList(upperRight3,scenario.blackMoves) && !isCoordInArrayList(lowerLeft,scenario.blackMoves)){
                    config2.whitePriority = config2.whitePriority - 2;
                    config2.blackPriority = config2.blackPriority - 2;
                    config2.whiteEval = whiteEval*2;
                    config2.blackEval = blackEval*2;
                }
                config2.whiteCounterMoves.add(upperRight3);
                config2.whiteCounterMoves.add(lowerLeft);
                config2.blackCounterMoves.add(upperRight3);
                config2.blackCounterMoves.add(lowerLeft);

                configsList.add(config2);
            }


            if (isCoordInArrayList(right,scenario.whiteMoves) &&
                    isCoordInArrayList(right2,scenario.whiteMoves) &&
                    !isCoordInArrayList(right3,scenario.whiteMoves) &&
                    !isCoordInArrayList(left,scenario.whiteMoves) &&
                    ((!isCoordInArrayList(right3,scenario.whiteMoves) &&
                            !isCoordInArrayList(right3,scenario.blackMoves) &&
                            !isCoordInArrayList(left,scenario.blackMoves) &&
                            !isCoordInArrayList(left,scenario.whiteMoves)) ||
                            (isCoordInArrayList(right3,scenario.blackMoves) &&
                                    !isCoordInArrayList(left,scenario.blackMoves)) ||
                            (!isCoordInArrayList(right3,scenario.blackMoves) &&
                                    isCoordInArrayList(left,scenario.blackMoves)))){
                PenteConfigurations config3 = new PenteConfigurations();
                config3.confType = 1;
                config3.direction = 0;
                config3.isWhite = true;
                config3.whitePriority = highPriority;
                config3.blackPriority = lowPriority;
                config3.originCoord = coord;
                config3.whiteEval = whiteEval;
                config3.blackEval = blackEval;
                if (!isCoordInArrayList(right3,scenario.blackMoves) && !isCoordInArrayList(left,scenario.blackMoves)){
                    config3.whitePriority = config3.whitePriority - 2;
                    config3.blackPriority = config3.blackPriority - 2;
                    config3.whiteEval = whiteEval*2;
                    config3.blackEval = blackEval*2;
                }
                config3.whiteCounterMoves.add(right3);
                config3.whiteCounterMoves.add(left);
                config3.blackCounterMoves.add(right3);
                config3.blackCounterMoves.add(left);

                configsList.add(config3);
            }

            if (isCoordInArrayList(lowerRight,scenario.whiteMoves) &&
                    isCoordInArrayList(lowerRight2,scenario.whiteMoves) &&
                    !isCoordInArrayList(lowerRight3,scenario.whiteMoves) &&
                    !isCoordInArrayList(upperLeft,scenario.whiteMoves) &&
                    ((!isCoordInArrayList(lowerRight3,scenario.whiteMoves) &&
                            !isCoordInArrayList(lowerRight3,scenario.blackMoves) &&
                            !isCoordInArrayList(upperLeft,scenario.blackMoves) &&
                            !isCoordInArrayList(upperLeft,scenario.whiteMoves)) ||
                            (isCoordInArrayList(lowerRight3,scenario.blackMoves) &&
                                    !isCoordInArrayList(upperLeft,scenario.blackMoves)) ||
                            (!isCoordInArrayList(lowerRight3,scenario.blackMoves) &&
                                    isCoordInArrayList(upperLeft,scenario.blackMoves)))){
                PenteConfigurations config4 = new PenteConfigurations();
                config4.confType = 1;
                config4.direction = 315;
                config4.isWhite = true;
                config4.whitePriority = highPriority;
                config4.blackPriority = lowPriority;
                config4.originCoord = coord;
                config4.whiteEval = whiteEval;
                config4.blackEval = blackEval;
                if (!isCoordInArrayList(lowerRight3,scenario.blackMoves) && !isCoordInArrayList(upperLeft,scenario.blackMoves)){
                    config4.whitePriority = config4.whitePriority - 2;
                    config4.blackPriority = config4.blackPriority - 2;
                    config4.whiteEval = whiteEval*2;
                    config4.blackEval = blackEval*2;
                }
                config4.whiteCounterMoves.add(lowerRight3);
                config4.whiteCounterMoves.add(upperLeft);
                config4.blackCounterMoves.add(lowerRight3);
                config4.blackCounterMoves.add(upperLeft);

                configsList.add(config4);
            }

        }
        if (isPieceWhite == false){
            if (isCoordInArrayList(upper,scenario.blackMoves) &&
                    isCoordInArrayList(upper2,scenario.blackMoves) &&
                    !isCoordInArrayList(upper3,scenario.blackMoves) &&
                    !isCoordInArrayList(lower,scenario.blackMoves) &&
                    ((!isCoordInArrayList(upper3,scenario.blackMoves) &&
                            !isCoordInArrayList(upper3,scenario.whiteMoves) &&
                            !isCoordInArrayList(lower,scenario.whiteMoves) &&
                            !isCoordInArrayList(lower,scenario.blackMoves)) ||
                            (isCoordInArrayList(upper3,scenario.whiteMoves) &&
                                    !isCoordInArrayList(lower,scenario.whiteMoves)) ||
                            (!isCoordInArrayList(upper3,scenario.whiteMoves) &&
                                    isCoordInArrayList(lower,scenario.whiteMoves)))){
                PenteConfigurations config5 = new PenteConfigurations();
                config5.confType = 1;
                config5.direction = 90;
                config5.isWhite = false;
                config5.blackPriority = highPriority;
                config5.whitePriority = lowPriority;
                config5.originCoord = coord;
                config5.blackEval = -blackEval;
                config5.whiteEval = -whiteEval;
                if (!isCoordInArrayList(upper3,scenario.whiteMoves) && !isCoordInArrayList(lower,scenario.whiteMoves)){
                    config5.whitePriority = config5.whitePriority - 2;
                    config5.blackPriority = config5.blackPriority - 2;
                    config5.whiteEval = -whiteEval*2;
                    config5.blackEval = -blackEval*2;
                }
                config5.blackCounterMoves.add(upper3);
                config5.blackCounterMoves.add(lower);
                config5.whiteCounterMoves.add(upper3);
                config5.whiteCounterMoves.add(lower);

                configsList.add(config5);
            }

            if (isCoordInArrayList(upperRight,scenario.blackMoves) &&
                    isCoordInArrayList(upperRight2,scenario.blackMoves) &&
                    !isCoordInArrayList(upperRight3,scenario.blackMoves) &&
                    !isCoordInArrayList(lowerLeft,scenario.blackMoves) &&
                    ((!isCoordInArrayList(upperRight3,scenario.blackMoves) &&
                            !isCoordInArrayList(upperRight3,scenario.whiteMoves) &&
                            !isCoordInArrayList(lowerLeft,scenario.whiteMoves) &&
                            !isCoordInArrayList(lowerLeft,scenario.blackMoves))||
                            (isCoordInArrayList(upperRight3,scenario.whiteMoves) &&
                                    !isCoordInArrayList(lowerLeft,scenario.whiteMoves)) ||
                            (!isCoordInArrayList(upperRight3,scenario.whiteMoves) &&
                                    isCoordInArrayList(lowerLeft,scenario.whiteMoves)))){
                PenteConfigurations config6 = new PenteConfigurations();
                config6.confType = 1;
                config6.direction = 45;
                config6.isWhite = false;
                config6.blackPriority = highPriority;
                config6.whitePriority = lowPriority;
                config6.originCoord = coord;
                config6.blackEval = -blackEval;
                config6.whiteEval = -whiteEval;
                if (!isCoordInArrayList(upperRight3,scenario.whiteMoves) && !isCoordInArrayList(lowerLeft,scenario.whiteMoves)){
                    config6.whitePriority = config6.whitePriority - 2;
                    config6.blackPriority = config6.blackPriority - 2;
                    config6.whiteEval = -whiteEval*2;
                    config6.blackEval = -blackEval*2;
                }
                config6.blackCounterMoves.add(upperRight3);
                config6.blackCounterMoves.add(lowerLeft);
                config6.whiteCounterMoves.add(upperRight3);
                config6.whiteCounterMoves.add(lowerLeft);

                configsList.add(config6);
            }


            if (isCoordInArrayList(right,scenario.blackMoves) &&
                    isCoordInArrayList(right2,scenario.blackMoves) &&
                    !isCoordInArrayList(right3,scenario.blackMoves) &&
                    !isCoordInArrayList(left,scenario.blackMoves) &&
                    ((!isCoordInArrayList(right3,scenario.blackMoves) &&
                            !isCoordInArrayList(right3,scenario.whiteMoves) &&
                            !isCoordInArrayList(left,scenario.whiteMoves) &&
                            !isCoordInArrayList(left,scenario.blackMoves)) ||
                            (isCoordInArrayList(right3,scenario.whiteMoves) &&
                                    !isCoordInArrayList(left,scenario.whiteMoves)) ||
                            (!isCoordInArrayList(right3,scenario.whiteMoves) &&
                                    isCoordInArrayList(left,scenario.whiteMoves)))){
                PenteConfigurations config7 = new PenteConfigurations();
                config7.confType = 1;
                config7.direction = 0;
                config7.isWhite = false;
                config7.blackPriority = highPriority;
                config7.whitePriority = lowPriority;
                config7.originCoord = coord;
                config7.blackEval = -blackEval;
                config7.whiteEval = -whiteEval;
                if (!isCoordInArrayList(right3,scenario.whiteMoves) && !isCoordInArrayList(left,scenario.whiteMoves)){
                    config7.whitePriority = config7.whitePriority - 2;
                    config7.blackPriority = config7.blackPriority - 2;
                    config7.whiteEval = -whiteEval*2;
                    config7.blackEval = -blackEval*2;
                }
                config7.blackCounterMoves.add(right3);
                config7.blackCounterMoves.add(left);
                config7.whiteCounterMoves.add(right3);
                config7.whiteCounterMoves.add(left);

                configsList.add(config7);
            }

            if (isCoordInArrayList(lowerRight,scenario.blackMoves) &&
                    isCoordInArrayList(lowerRight2,scenario.blackMoves) &&
                    !isCoordInArrayList(lowerRight3,scenario.blackMoves) &&
                    !isCoordInArrayList(upperLeft,scenario.blackMoves) &&
                    ((!isCoordInArrayList(lowerRight3,scenario.blackMoves) &&
                            !isCoordInArrayList(lowerRight3,scenario.whiteMoves) &&
                            !isCoordInArrayList(upperLeft,scenario.whiteMoves) &&
                            !isCoordInArrayList(upperLeft,scenario.blackMoves)) ||
                            (isCoordInArrayList(lowerRight3,scenario.whiteMoves) &&
                                    !isCoordInArrayList(upperLeft,scenario.whiteMoves)) ||
                            (!isCoordInArrayList(lowerRight3,scenario.whiteMoves) &&
                                    isCoordInArrayList(upperLeft,scenario.whiteMoves)))){
                PenteConfigurations config8 = new PenteConfigurations();
                config8.confType = 1;
                config8.direction = 315;
                config8.isWhite = false;
                config8.blackPriority = highPriority;
                config8.whitePriority = lowPriority;
                config8.originCoord = coord;
                config8.blackEval = -blackEval;
                config8.whiteEval = -whiteEval;
                if (!isCoordInArrayList(lowerRight3,scenario.whiteMoves) && !isCoordInArrayList(upperLeft,scenario.whiteMoves)){
                    config8.whitePriority = config8.whitePriority - 2;
                    config8.blackPriority = config8.blackPriority - 2;
                    config8.whiteEval = -whiteEval*2;
                    config8.blackEval = -blackEval*2;
                }
                config8.blackCounterMoves.add(lowerRight3);
                config8.blackCounterMoves.add(upperLeft);
                config8.whiteCounterMoves.add(lowerRight3);
                config8.whiteCounterMoves.add(upperLeft);

                configsList.add(config8);
            }
        }
        return configsList;
    }

    public ArrayList<PenteConfigurations> findConfig3(boolean isPieceWhite, int[] coord, Pente scenario, int lowPriority, int highPriority, int whiteEval, int blackEval){
        //is360Check is for when a capture has taken place and all of the effected configurations need to be re-configured.
        //Config1 is an open 2-in-a-row
        ArrayList<PenteConfigurations> configsList = new ArrayList<PenteConfigurations>();

        int[] upper = {coord[0]-1,coord[1]};
        int[] upper2 = {coord[0]-2,coord[1]};
        int[] upper3 = {coord[0]-3,coord[1]};
        int[] upper4 = {coord[0]-4,coord[1]};
        int[] upperRight = {coord[0]-1,coord[1]+1};
        int[] upperRight2 = {coord[0]-2,coord[1]+2};
        int[] upperRight3 = {coord[0]-3,coord[1]+3};
        int[] upperRight4 = {coord[0]-4,coord[1]+4};
        int[] right = {coord[0],coord[1]+1};
        int[] right2 = {coord[0],coord[1]+2};
        int[] right3 = {coord[0],coord[1]+3};
        int[] right4 = {coord[0],coord[1]+4};
        int[] lowerRight = {coord[0]+1,coord[1]+1};
        int[] lowerRight2 = {coord[0]+2,coord[1]+2};
        int[] lowerRight3 = {coord[0]+3,coord[1]+3};
        int[] lowerRight4 = {coord[0]+4,coord[1]+4};

        int[] lower = {coord[0]+1,coord[1]};
        int[] lowerLeft = {coord[0]+1,coord[1]-1};
        int[] left = {coord[0],coord[1]-1};
        int[] upperLeft = {coord[0]-1,coord[1]-1};

		/*if (isPlayersConfig == true){
			if (isP1Turn == true && isPieceWhite == true || isP1Turn == false && isPieceWhite == false){*/
        //find white configs and put them into a player's config
        if (isPieceWhite == true){
            if (isCoordInArrayList(upper,scenario.whiteMoves) &&
                    isCoordInArrayList(upper2,scenario.whiteMoves) &&
                    isCoordInArrayList(upper3,scenario.whiteMoves) &&
                    !isCoordInArrayList(lower,scenario.whiteMoves) &&
                    !isCoordInArrayList(upper4,scenario.whiteMoves) &&
                    ((!isCoordInArrayList(upper4,scenario.blackMoves) &&
                            !isCoordInArrayList(upper4,scenario.whiteMoves) &&
                            !isCoordInArrayList(lower,scenario.blackMoves) &&
                            !isCoordInArrayList(lower,scenario.whiteMoves)) ||
                            (isCoordInArrayList(upper4,scenario.blackMoves) &&
                                    !isCoordInArrayList(lower,scenario.blackMoves)) ||
                            (!isCoordInArrayList(upper4,scenario.blackMoves) &&
                                    isCoordInArrayList(lower,scenario.blackMoves)))){
                PenteConfigurations config1 = new PenteConfigurations();
                config1.confType = 3;
                config1.direction = 90;
                config1.isWhite = true;
                config1.whitePriority = highPriority;
                config1.blackPriority = lowPriority;
                config1.originCoord = coord;
                config1.whiteEval = whiteEval;
                config1.blackEval = blackEval;
                config1.whiteCounterMoves.add(upper4);
                config1.whiteCounterMoves.add(lower);
                config1.blackCounterMoves.add(upper4);
                config1.blackCounterMoves.add(lower);

                configsList.add(config1);
            }

            if (isCoordInArrayList(upperRight,scenario.whiteMoves) &&
                    isCoordInArrayList(upperRight2,scenario.whiteMoves) &&
                    isCoordInArrayList(upperRight3,scenario.whiteMoves) &&
                    !isCoordInArrayList(upperRight4,scenario.whiteMoves) &&
                    !isCoordInArrayList(lowerLeft,scenario.whiteMoves) &&
                    ((!isCoordInArrayList(upperRight4,scenario.blackMoves) &&
                            !isCoordInArrayList(upperRight4,scenario.whiteMoves) &&
                            !isCoordInArrayList(lowerLeft,scenario.blackMoves) &&
                            !isCoordInArrayList(lowerLeft,scenario.whiteMoves)) ||
                            (isCoordInArrayList(upperRight4,scenario.blackMoves) &&
                                    !isCoordInArrayList(lowerLeft,scenario.blackMoves)) ||
                            (!isCoordInArrayList(upperRight4,scenario.blackMoves) &&
                                    isCoordInArrayList(lowerLeft,scenario.blackMoves)))){
                PenteConfigurations config2 = new PenteConfigurations();
                config2.confType = 3;
                config2.direction = 45;
                config2.isWhite = true;
                config2.whitePriority = highPriority;
                config2.blackPriority = lowPriority;
                config2.originCoord = coord;
                config2.whiteEval = whiteEval;
                config2.blackEval = blackEval;
                config2.whiteCounterMoves.add(upperRight4);
                config2.whiteCounterMoves.add(lowerLeft);
                config2.blackCounterMoves.add(upperRight4);
                config2.blackCounterMoves.add(lowerLeft);

                configsList.add(config2);
            }


            if (isCoordInArrayList(right,scenario.whiteMoves) &&
                    isCoordInArrayList(right2,scenario.whiteMoves) &&
                    isCoordInArrayList(right3,scenario.whiteMoves) &&
                    !isCoordInArrayList(right4,scenario.whiteMoves) &&
                    !isCoordInArrayList(left,scenario.whiteMoves) &&
                    ((!isCoordInArrayList(right4,scenario.whiteMoves) &&
                            !isCoordInArrayList(right4,scenario.blackMoves) &&
                            !isCoordInArrayList(left,scenario.blackMoves) &&
                            !isCoordInArrayList(left,scenario.whiteMoves)) ||
                            (isCoordInArrayList(right4,scenario.blackMoves) &&
                                    !isCoordInArrayList(left,scenario.blackMoves)) ||
                            (!isCoordInArrayList(right4,scenario.blackMoves) &&
                                    isCoordInArrayList(left,scenario.blackMoves)))){
                PenteConfigurations config3 = new PenteConfigurations();
                config3.confType = 3;
                config3.direction = 0;
                config3.isWhite = true;
                config3.whitePriority = highPriority;
                config3.blackPriority = lowPriority;
                config3.originCoord = coord;
                config3.whiteEval = whiteEval;
                config3.blackEval = blackEval;
                config3.whiteCounterMoves.add(right4);
                config3.whiteCounterMoves.add(left);
                config3.blackCounterMoves.add(right4);
                config3.blackCounterMoves.add(left);

                configsList.add(config3);
            }

            if (isCoordInArrayList(lowerRight,scenario.whiteMoves) &&
                    isCoordInArrayList(lowerRight2,scenario.whiteMoves) &&
                    isCoordInArrayList(lowerRight3,scenario.whiteMoves) &&
                    !isCoordInArrayList(lowerRight4,scenario.whiteMoves) &&
                    !isCoordInArrayList(upperLeft,scenario.whiteMoves) &&
                    ((!isCoordInArrayList(lowerRight4,scenario.whiteMoves) &&
                            !isCoordInArrayList(lowerRight4,scenario.blackMoves) &&
                            !isCoordInArrayList(upperLeft,scenario.blackMoves) &&
                            !isCoordInArrayList(upperLeft,scenario.whiteMoves)) ||
                            (isCoordInArrayList(lowerRight4,scenario.blackMoves) &&
                                    !isCoordInArrayList(upperLeft,scenario.blackMoves)) ||
                            (!isCoordInArrayList(lowerRight4,scenario.blackMoves) &&
                                    isCoordInArrayList(upperLeft,scenario.blackMoves)))){
                PenteConfigurations config4 = new PenteConfigurations();
                config4.confType = 3;
                config4.direction = 315;
                config4.isWhite = true;
                config4.whitePriority = highPriority;
                config4.blackPriority = lowPriority;
                config4.originCoord = coord;
                config4.whiteEval = whiteEval;
                config4.blackEval = blackEval;
                config4.whiteCounterMoves.add(lowerRight4);
                config4.whiteCounterMoves.add(upperLeft);
                config4.blackCounterMoves.add(lowerRight4);
                config4.blackCounterMoves.add(upperLeft);

                configsList.add(config4);
            }

        }
        if (isPieceWhite == false){
            if (isCoordInArrayList(upper,scenario.blackMoves) &&
                    isCoordInArrayList(upper2,scenario.blackMoves) &&
                    isCoordInArrayList(upper3,scenario.blackMoves) &&
                    !isCoordInArrayList(upper4,scenario.blackMoves) &&
                    !isCoordInArrayList(lower,scenario.blackMoves) &&
                    ((!isCoordInArrayList(upper4,scenario.blackMoves) &&
                            !isCoordInArrayList(upper4,scenario.whiteMoves) &&
                            !isCoordInArrayList(lower,scenario.whiteMoves) &&
                            !isCoordInArrayList(lower,scenario.blackMoves)) ||
                            (isCoordInArrayList(upper4,scenario.whiteMoves) &&
                                    !isCoordInArrayList(lower,scenario.whiteMoves)) ||
                            (!isCoordInArrayList(upper4,scenario.whiteMoves) &&
                                    isCoordInArrayList(lower,scenario.whiteMoves)))){
                PenteConfigurations config5 = new PenteConfigurations();
                config5.confType = 3;
                config5.direction = 90;
                config5.isWhite = false;
                config5.blackPriority = highPriority;
                config5.whitePriority = lowPriority;
                config5.originCoord = coord;
                config5.blackEval = -blackEval;
                config5.whiteEval = -whiteEval;
                config5.blackCounterMoves.add(upper4);
                config5.blackCounterMoves.add(lower);
                config5.whiteCounterMoves.add(upper4);
                config5.whiteCounterMoves.add(lower);

                configsList.add(config5);
            }

            if (isCoordInArrayList(upperRight,scenario.blackMoves) &&
                    isCoordInArrayList(upperRight2,scenario.blackMoves) &&
                    isCoordInArrayList(upperRight3,scenario.blackMoves) &&
                    !isCoordInArrayList(upperRight4,scenario.blackMoves) &&
                    !isCoordInArrayList(lowerLeft,scenario.blackMoves) &&
                    ((!isCoordInArrayList(upperRight4,scenario.blackMoves) &&
                            !isCoordInArrayList(upperRight4,scenario.whiteMoves) &&
                            !isCoordInArrayList(lowerLeft,scenario.whiteMoves) &&
                            !isCoordInArrayList(lowerLeft,scenario.blackMoves))||
                            (isCoordInArrayList(upperRight4,scenario.whiteMoves) &&
                                    !isCoordInArrayList(lowerLeft,scenario.whiteMoves)) ||
                            (!isCoordInArrayList(upperRight4,scenario.whiteMoves) &&
                                    isCoordInArrayList(lowerLeft,scenario.whiteMoves)))){
                PenteConfigurations config6 = new PenteConfigurations();
                config6.confType = 3;
                config6.direction = 45;
                config6.isWhite = false;
                config6.blackPriority = highPriority;
                config6.whitePriority = lowPriority;
                config6.originCoord = coord;
                config6.blackEval = -blackEval;
                config6.whiteEval = -whiteEval;
                config6.blackCounterMoves.add(upperRight4);
                config6.blackCounterMoves.add(lowerLeft);
                config6.whiteCounterMoves.add(upperRight4);
                config6.whiteCounterMoves.add(lowerLeft);

                configsList.add(config6);
            }


            if (isCoordInArrayList(right,scenario.blackMoves) &&
                    isCoordInArrayList(right2,scenario.blackMoves) &&
                    isCoordInArrayList(right3,scenario.blackMoves) &&
                    !isCoordInArrayList(right4,scenario.blackMoves) &&
                    !isCoordInArrayList(left,scenario.blackMoves) &&
                    ((!isCoordInArrayList(right4,scenario.blackMoves) &&
                            !isCoordInArrayList(right4,scenario.whiteMoves) &&
                            !isCoordInArrayList(left,scenario.whiteMoves) &&
                            !isCoordInArrayList(left,scenario.blackMoves)) ||
                            (isCoordInArrayList(right4,scenario.whiteMoves) &&
                                    !isCoordInArrayList(left,scenario.whiteMoves)) ||
                            (!isCoordInArrayList(right4,scenario.whiteMoves) &&
                                    isCoordInArrayList(left,scenario.whiteMoves)))){
                PenteConfigurations config7 = new PenteConfigurations();
                config7.confType = 3;
                config7.direction = 0;
                config7.isWhite = false;
                config7.blackPriority = highPriority;
                config7.whitePriority = lowPriority;
                config7.originCoord = coord;
                config7.blackEval = -blackEval;
                config7.whiteEval = -whiteEval;
                config7.blackCounterMoves.add(right4);
                config7.blackCounterMoves.add(left);
                config7.whiteCounterMoves.add(right4);
                config7.whiteCounterMoves.add(left);

                configsList.add(config7);
            }

            if (isCoordInArrayList(lowerRight,scenario.blackMoves) &&
                    isCoordInArrayList(lowerRight2,scenario.blackMoves) &&
                    isCoordInArrayList(lowerRight3,scenario.blackMoves) &&
                    !isCoordInArrayList(lowerRight4,scenario.blackMoves) &&
                    !isCoordInArrayList(upperLeft,scenario.blackMoves) &&
                    ((!isCoordInArrayList(lowerRight4,scenario.blackMoves) &&
                            !isCoordInArrayList(lowerRight4,scenario.whiteMoves) &&
                            !isCoordInArrayList(upperLeft,scenario.whiteMoves) &&
                            !isCoordInArrayList(upperLeft,scenario.blackMoves)) ||
                            (isCoordInArrayList(lowerRight4,scenario.whiteMoves) &&
                                    !isCoordInArrayList(upperLeft,scenario.whiteMoves)) ||
                            (!isCoordInArrayList(lowerRight4,scenario.whiteMoves) &&
                                    isCoordInArrayList(upperLeft,scenario.whiteMoves)))){
                PenteConfigurations config8 = new PenteConfigurations();
                config8.confType = 3;
                config8.direction = 315;
                config8.isWhite = false;
                config8.blackPriority = highPriority;
                config8.whitePriority = lowPriority;
                config8.originCoord = coord;
                config8.blackEval = -blackEval;
                config8.whiteEval = -whiteEval;
                config8.blackCounterMoves.add(lowerRight4);
                config8.blackCounterMoves.add(upperLeft);
                config8.whiteCounterMoves.add(lowerRight4);
                config8.whiteCounterMoves.add(upperLeft);

                configsList.add(config8);
            }
        }
        return configsList;
    }

    public ArrayList<PenteConfigurations> findConfig4(boolean isPieceWhite, int[] coord, Pente scenario, int lowPriority, int highPriority, int whiteEval, int blackEval){
        //is360Check is for when a capture has taken place and all of the effected configurations need to be re-configured.
        //Config1 is an open 2-in-a-row
        ArrayList<PenteConfigurations> configsList = new ArrayList<PenteConfigurations>();

        int[] upper = {coord[0]-1,coord[1]};
        int[] upper2 = {coord[0]-2,coord[1]};
        int[] upper3 = {coord[0]-3,coord[1]};
        int[] upperRight = {coord[0]-1,coord[1]+1};
        int[] upperRight2 = {coord[0]-2,coord[1]+2};
        int[] upperRight3 = {coord[0]-3,coord[1]+3};
        int[] right = {coord[0],coord[1]+1};
        int[] right2 = {coord[0],coord[1]+2};
        int[] right3 = {coord[0],coord[1]+3};
        int[] lowerRight = {coord[0]+1,coord[1]+1};
        int[] lowerRight2 = {coord[0]+2,coord[1]+2};
        int[] lowerRight3 = {coord[0]+3,coord[1]+3};

        int[] lower = {coord[0]+1,coord[1]};
        int[] lowerLeft = {coord[0]+1,coord[1]-1};
        int[] left = {coord[0],coord[1]-1};
        int[] upperLeft = {coord[0]-1,coord[1]-1};

		/*if (isPlayersConfig == true){
			if (isP1Turn == true && isPieceWhite == true || isP1Turn == false && isPieceWhite == false){*/
        //find white configs and put them into a player's config
        if (isPieceWhite == true){
            if (!isCoordInArrayList(upper,scenario.whiteMoves) &&
                    !isCoordInArrayList(upper,scenario.blackMoves) &&
                    isCoordInArrayList(upper2,scenario.whiteMoves) &&
                    !isCoordInArrayList(upper3,scenario.blackMoves) &&
                    !isCoordInArrayList(upper3,scenario.whiteMoves) &&
                    !isCoordInArrayList(lower,scenario.blackMoves) &&
                    !isCoordInArrayList(lower,scenario.whiteMoves)){
                PenteConfigurations config1 = new PenteConfigurations();
                config1.confType = 1;
                config1.direction = 90;
                config1.isWhite = true;
                config1.whitePriority = highPriority;
                config1.blackPriority = lowPriority;
                config1.originCoord = coord;
                config1.whiteEval = whiteEval;
                config1.blackEval = blackEval;
                config1.whiteCounterMoves.add(upper);
                config1.blackCounterMoves.add(upper);

                configsList.add(config1);
            }

            if (!isCoordInArrayList(upperRight,scenario.whiteMoves) &&
                    !isCoordInArrayList(upperRight,scenario.blackMoves) &&
                    isCoordInArrayList(upperRight2,scenario.whiteMoves) &&
                    !isCoordInArrayList(upperRight3,scenario.blackMoves) &&
                    !isCoordInArrayList(upperRight3,scenario.whiteMoves) &&
                    !isCoordInArrayList(lowerLeft,scenario.blackMoves) &&
                    !isCoordInArrayList(lowerLeft,scenario.whiteMoves)){
                PenteConfigurations config2 = new PenteConfigurations();
                config2.confType = 1;
                config2.direction = 45;
                config2.isWhite = true;
                config2.whitePriority = highPriority;
                config2.blackPriority = lowPriority;
                config2.originCoord = coord;
                config2.whiteEval = whiteEval;
                config2.blackEval = blackEval;
                config2.whiteCounterMoves.add(upperRight);
                config2.blackCounterMoves.add(upperRight);

                configsList.add(config2);
            }


            if (!isCoordInArrayList(right,scenario.whiteMoves) &&
                    !isCoordInArrayList(right,scenario.blackMoves) &&
                    isCoordInArrayList(right2,scenario.whiteMoves) &&
                    !isCoordInArrayList(right3,scenario.whiteMoves) &&
                    !isCoordInArrayList(right3,scenario.blackMoves) &&
                    !isCoordInArrayList(left,scenario.blackMoves) &&
                    !isCoordInArrayList(left,scenario.whiteMoves)){
                PenteConfigurations config3 = new PenteConfigurations();
                config3.confType = 1;
                config3.direction = 0;
                config3.isWhite = true;
                config3.whitePriority = highPriority;
                config3.blackPriority = lowPriority;
                config3.originCoord = coord;
                config3.whiteEval = whiteEval;
                config3.blackEval = blackEval;
                config3.whiteCounterMoves.add(right);
                config3.blackCounterMoves.add(right);

                configsList.add(config3);
            }

            if (!isCoordInArrayList(lowerRight,scenario.whiteMoves) &&
                    !isCoordInArrayList(lowerRight,scenario.blackMoves) &&
                    isCoordInArrayList(lowerRight2,scenario.whiteMoves) &&
                    !isCoordInArrayList(lowerRight3,scenario.whiteMoves) &&
                    !isCoordInArrayList(lowerRight3,scenario.blackMoves) &&
                    !isCoordInArrayList(upperLeft,scenario.blackMoves) &&
                    !isCoordInArrayList(upperLeft,scenario.whiteMoves)){
                PenteConfigurations config4 = new PenteConfigurations();
                config4.confType = 1;
                config4.direction = 315;
                config4.isWhite = true;
                config4.whitePriority = highPriority;
                config4.blackPriority = lowPriority;
                config4.originCoord = coord;
                config4.whiteEval = whiteEval;
                config4.blackEval = blackEval;
                config4.whiteCounterMoves.add(lowerRight);
                config4.blackCounterMoves.add(lowerRight);

                configsList.add(config4);
            }

        }
        if (isPieceWhite == false){
            if (!isCoordInArrayList(upper,scenario.blackMoves) &&
                    !isCoordInArrayList(upper,scenario.whiteMoves) &&
                    isCoordInArrayList(upper2,scenario.blackMoves) &&
                    !isCoordInArrayList(upper3,scenario.blackMoves) &&
                    !isCoordInArrayList(upper3,scenario.whiteMoves) &&
                    !isCoordInArrayList(lower,scenario.whiteMoves) &&
                    !isCoordInArrayList(lower,scenario.blackMoves)){
                PenteConfigurations config5 = new PenteConfigurations();
                config5.confType = 1;
                config5.direction = 90;
                config5.isWhite = false;
                config5.blackPriority = highPriority;
                config5.whitePriority = lowPriority;
                config5.originCoord = coord;
                config5.blackEval = -blackEval;
                config5.whiteEval = -whiteEval;
                config5.blackCounterMoves.add(upper);
                config5.whiteCounterMoves.add(upper);

                configsList.add(config5);
            }

            if (!isCoordInArrayList(upperRight,scenario.blackMoves) &&
                    !isCoordInArrayList(upperRight,scenario.whiteMoves) &&
                    isCoordInArrayList(upperRight2,scenario.blackMoves) &&
                    !isCoordInArrayList(upperRight3,scenario.blackMoves) &&
                    !isCoordInArrayList(upperRight3,scenario.whiteMoves) &&
                    !isCoordInArrayList(lowerLeft,scenario.whiteMoves) &&
                    !isCoordInArrayList(lowerLeft,scenario.blackMoves)){
                PenteConfigurations config6 = new PenteConfigurations();
                config6.confType = 1;
                config6.direction = 45;
                config6.isWhite = false;
                config6.blackPriority = highPriority;
                config6.whitePriority = lowPriority;
                config6.originCoord = coord;
                config6.blackEval = -blackEval;
                config6.whiteEval = -whiteEval;
                config6.blackCounterMoves.add(upperRight);
                config6.whiteCounterMoves.add(upperRight);

                configsList.add(config6);
            }


            if (!isCoordInArrayList(right,scenario.blackMoves) &&
                    !isCoordInArrayList(right,scenario.whiteMoves) &&
                    isCoordInArrayList(right2,scenario.blackMoves) &&
                    !isCoordInArrayList(right3,scenario.blackMoves) &&
                    !isCoordInArrayList(right3,scenario.whiteMoves) &&
                    !isCoordInArrayList(left,scenario.whiteMoves) &&
                    !isCoordInArrayList(left,scenario.blackMoves)){
                PenteConfigurations config7 = new PenteConfigurations();
                config7.confType = 1;
                config7.direction = 0;
                config7.isWhite = false;
                config7.blackPriority = highPriority;
                config7.whitePriority = lowPriority;
                config7.originCoord = coord;
                config7.blackEval = -blackEval;
                config7.whiteEval = -whiteEval;
                config7.blackCounterMoves.add(right);
                config7.whiteCounterMoves.add(right);

                configsList.add(config7);
            }

            if (!isCoordInArrayList(lowerRight,scenario.blackMoves) &&
                    !isCoordInArrayList(lowerRight,scenario.whiteMoves) &&
                    isCoordInArrayList(lowerRight2,scenario.blackMoves) &&
                    !isCoordInArrayList(lowerRight3,scenario.blackMoves) &&
                    !isCoordInArrayList(lowerRight3,scenario.whiteMoves) &&
                    !isCoordInArrayList(upperLeft,scenario.whiteMoves) &&
                    !isCoordInArrayList(upperLeft,scenario.blackMoves)){
                PenteConfigurations config8 = new PenteConfigurations();
                config8.confType = 1;
                config8.direction = 315;
                config8.isWhite = false;
                config8.blackPriority = highPriority;
                config8.whitePriority = lowPriority;
                config8.originCoord = coord;
                config8.blackEval = -blackEval;
                config8.whiteEval = -whiteEval;
                config8.blackCounterMoves.add(lowerRight);
                config8.whiteCounterMoves.add(lowerRight);

                configsList.add(config8);
            }
        }
        return configsList;
    }

    public ArrayList<PenteConfigurations> findConfig5(boolean isPieceWhite, int[] coord, Pente scenario, int lowPriority, int highPriority, int whiteEval, int blackEval){
        //is360Check is for when a capture has taken place and all of the effected configurations need to be re-configured.
        //Config1 is an open 2-in-a-row
        ArrayList<PenteConfigurations> configsList = new ArrayList<PenteConfigurations>();

        int[] upper = {coord[0]-1,coord[1]};
        int[] upper2 = {coord[0]-2,coord[1]};
        int[] upper3 = {coord[0]-3,coord[1]};
        int[] upper4 = {coord[0]-4,coord[1]};
        int[] upperRight = {coord[0]-1,coord[1]+1};
        int[] upperRight2 = {coord[0]-2,coord[1]+2};
        int[] upperRight3 = {coord[0]-3,coord[1]+3};
        int[] upperRight4 = {coord[0]-4,coord[1]+4};
        int[] right = {coord[0],coord[1]+1};
        int[] right2 = {coord[0],coord[1]+2};
        int[] right3 = {coord[0],coord[1]+3};
        int[] right4 = {coord[0],coord[1]+4};
        int[] lowerRight = {coord[0]+1,coord[1]+1};
        int[] lowerRight2 = {coord[0]+2,coord[1]+2};
        int[] lowerRight3 = {coord[0]+3,coord[1]+3};
        int[] lowerRight4 = {coord[0]+4,coord[1]+4};


        int[] lower = {coord[0]+1,coord[1]};
        int[] lowerLeft = {coord[0]+1,coord[1]-1};
        int[] left = {coord[0],coord[1]-1};
        int[] upperLeft = {coord[0]-1,coord[1]-1};
        int[] lower2 = {coord[0]+2,coord[1]};
        int[] lowerLeft2 = {coord[0]+2,coord[1]-2};
        int[] left2 = {coord[0],coord[1]-2};
        int[] upperLeft2 = {coord[0]-2,coord[1]-2};

		/*if (isPlayersConfig == true){
			if (isP1Turn == true && isPieceWhite == true || isP1Turn == false && isPieceWhite == false){*/
        //find white configs and put them into a player's config
        if (isPieceWhite == true){
            // -W-W-
            if (!isCoordInArrayList(upper,scenario.whiteMoves) &&
                    !isCoordInArrayList(upper,scenario.blackMoves) &&
                    isCoordInArrayList(upper2,scenario.whiteMoves)){
                // W-WW
                if (isCoordInArrayList(upper3,scenario.whiteMoves)){
                    // -W-WW-
                    if (!isCoordInArrayList(upper4,scenario.whiteMoves) &&
                            !isCoordInArrayList(upper4,scenario.blackMoves) &&
                            !isCoordInArrayList(lower,scenario.whiteMoves) &&
                            !isCoordInArrayList(lower,scenario.blackMoves)){
                        PenteConfigurations config1 = new PenteConfigurations();
                        config1.confType = 1;
                        config1.direction = 90;
                        config1.isWhite = true;
                        config1.whitePriority = highPriority;
                        config1.blackPriority = lowPriority;

                        config1.whiteEval = 10;
                        config1.blackEval = -10;

                        config1.whiteCounterMoves.add(lower);
                        config1.whiteCounterMoves.add(upper);
                        config1.whiteCounterMoves.add(upper4);

                        config1.blackCounterMoves.add(lower);
                        config1.blackCounterMoves.add(upper);
                        config1.blackCounterMoves.add(upper4);
                        configsList.add(config1);
                    }
                    // -W-WWB
                    if (isCoordInArrayList(upper4,scenario.blackMoves) &&
                            !isCoordInArrayList(lower,scenario.whiteMoves) &&
                            !isCoordInArrayList(lower,scenario.blackMoves)){
                        PenteConfigurations config1 = new PenteConfigurations();
                        config1.confType = 1;
                        config1.direction = 90;
                        config1.isWhite = true;
                        config1.whitePriority = highPriority + 2;
                        config1.blackPriority = lowPriority + 2;

                        config1.whiteEval = 5;
                        config1.blackEval = -5;

                        config1.whiteCounterMoves.add(lower);
                        config1.whiteCounterMoves.add(upper);

                        config1.blackCounterMoves.add(lower);
                        config1.blackCounterMoves.add(upper);
                        configsList.add(config1);
                    }
                    // BW-WW-
                    if (isCoordInArrayList(lower,scenario.blackMoves) &&
                            !isCoordInArrayList(upper4,scenario.whiteMoves) &&
                            !isCoordInArrayList(upper4,scenario.blackMoves)){
                        PenteConfigurations config1 = new PenteConfigurations();
                        config1.confType = 1;
                        config1.direction = 90;
                        config1.isWhite = true;
                        config1.whitePriority = highPriority + 2;
                        config1.blackPriority = lowPriority + 2;

                        config1.whiteEval = 5;
                        config1.blackEval = -5;

                        config1.whiteCounterMoves.add(upper);
                        config1.whiteCounterMoves.add(upper4);

                        config1.blackCounterMoves.add(upper);
                        config1.blackCounterMoves.add(upper4);
                        configsList.add(config1);
                    }
                    // W-WWW
                    if (isCoordInArrayList(upper4,scenario.whiteMoves)){
                        PenteConfigurations config1 = new PenteConfigurations();
                        config1.confType = 1;
                        config1.direction = 90;
                        config1.isWhite = true;
                        config1.whitePriority = highPriority - 2;
                        config1.blackPriority = lowPriority - 2;

                        config1.whiteEval = 20;
                        config1.blackEval = -20;

                        config1.whiteCounterMoves.add(upper);

                        config1.blackCounterMoves.add(upper);
                        configsList.add(config1);
                    }
                }
                // WW-W
                if (isCoordInArrayList(lower,scenario.whiteMoves)){
                    // BWW-W-
                    if (isCoordInArrayList(lower2,scenario.blackMoves) &&
                            !isCoordInArrayList(upper3,scenario.whiteMoves) &&
                            !isCoordInArrayList(upper3,scenario.blackMoves)){
                        PenteConfigurations config1 = new PenteConfigurations();
                        config1.confType = 1;
                        config1.direction = 90;
                        config1.isWhite = true;
                        config1.whitePriority = highPriority + 2;
                        config1.blackPriority = lowPriority + 2;

                        config1.whiteEval = 5;
                        config1.blackEval = -5;

                        config1.whiteCounterMoves.add(upper);
                        config1.whiteCounterMoves.add(upper3);

                        config1.blackCounterMoves.add(upper);
                        config1.blackCounterMoves.add(upper3);
                        configsList.add(config1);
                    }
                    // -WW-WB
                    if (isCoordInArrayList(upper3,scenario.blackMoves) &&
                            !isCoordInArrayList(lower2,scenario.whiteMoves) &&
                            !isCoordInArrayList(lower,scenario.blackMoves)){
                        PenteConfigurations config1 = new PenteConfigurations();
                        config1.confType = 1;
                        config1.direction = 90;
                        config1.isWhite = true;
                        config1.whitePriority = highPriority + 2;
                        config1.blackPriority = lowPriority + 2;

                        config1.whiteEval = 5;
                        config1.blackEval = -5;

                        config1.whiteCounterMoves.add(upper);
                        config1.whiteCounterMoves.add(lower2);

                        config1.blackCounterMoves.add(upper);
                        config1.blackCounterMoves.add(lower2);
                        configsList.add(config1);
                    }
                    // WW-WW
                    if (isCoordInArrayList(upper3,scenario.whiteMoves)){
                        PenteConfigurations config1 = new PenteConfigurations();
                        config1.confType = 1;
                        config1.direction = 90;
                        config1.isWhite = true;
                        config1.whitePriority = highPriority - 2;
                        config1.blackPriority = lowPriority - 2;

                        config1.whiteEval = 20;
                        config1.blackEval = -20;

                        config1.whiteCounterMoves.add(upper);

                        config1.blackCounterMoves.add(upper);
                        configsList.add(config1);
                    }
                    // WWW-W
                    if (isCoordInArrayList(lower2,scenario.whiteMoves)){
                        PenteConfigurations config1 = new PenteConfigurations();
                        config1.confType = 1;
                        config1.direction = 90;
                        config1.isWhite = true;
                        config1.whitePriority = highPriority - 2;
                        config1.blackPriority = lowPriority - 2;

                        config1.whiteEval = 20;
                        config1.blackEval = -20;

                        config1.whiteCounterMoves.add(upper);

                        config1.blackCounterMoves.add(upper);
                        configsList.add(config1);
                    }
                }

                // -WW-W-
                if ((isCoordInArrayList(lower,scenario.whiteMoves) &&
                        !isCoordInArrayList(upper3,scenario.whiteMoves) &&
                        !isCoordInArrayList(upper3,scenario.blackMoves) &&
                        !isCoordInArrayList(lower2,scenario.whiteMoves) &&
                        !isCoordInArrayList(lower2,scenario.blackMoves))){
                    PenteConfigurations config1 = new PenteConfigurations();
                    config1.confType = 1;
                    config1.direction = 90;
                    config1.isWhite = true;

                    config1.whitePriority = highPriority;
                    config1.blackPriority = lowPriority;

                    config1.whiteEval = 10;
                    config1.blackEval = -10;

                    config1.whiteCounterMoves.add(lower2);
                    config1.whiteCounterMoves.add(upper);
                    config1.whiteCounterMoves.add(upper3);

                    config1.blackCounterMoves.add(lower2);
                    config1.blackCounterMoves.add(upper);
                    config1.blackCounterMoves.add(upper3);
                    configsList.add(config1);
                }

                // -BW-WW-
                if ((isCoordInArrayList(lower,scenario.whiteMoves) &&
                        !isCoordInArrayList(upper3,scenario.whiteMoves) &&
                        !isCoordInArrayList(upper3,scenario.blackMoves) &&
                        !isCoordInArrayList(lower2,scenario.whiteMoves) &&
                        !isCoordInArrayList(lower2,scenario.blackMoves))){
                    PenteConfigurations config1 = new PenteConfigurations();
                    config1.confType = 1;
                    config1.direction = 90;
                    config1.isWhite = true;

                    config1.whitePriority = highPriority;
                    config1.blackPriority = lowPriority;

                    config1.whiteEval = 10;
                    config1.blackEval = -10;

                    config1.whiteCounterMoves.add(lower2);
                    config1.whiteCounterMoves.add(upper);
                    config1.whiteCounterMoves.add(upper3);

                    config1.blackCounterMoves.add(lower2);
                    config1.blackCounterMoves.add(upper);
                    config1.blackCounterMoves.add(upper3);
                    configsList.add(config1);
                }
            }

            if (!isCoordInArrayList(upperRight,scenario.whiteMoves) &&
                    !isCoordInArrayList(upperRight,scenario.blackMoves) &&
                    isCoordInArrayList(upperRight2,scenario.whiteMoves)){
                // W-WW
                if (isCoordInArrayList(upperRight3,scenario.whiteMoves)){
                    // -W-WW-
                    if (!isCoordInArrayList(upperRight4,scenario.whiteMoves) &&
                            !isCoordInArrayList(upperRight4,scenario.blackMoves) &&
                            !isCoordInArrayList(lowerLeft,scenario.whiteMoves) &&
                            !isCoordInArrayList(lowerLeft,scenario.blackMoves)){
                        PenteConfigurations config2 = new PenteConfigurations();
                        config2.confType = 1;
                        config2.direction = 90;
                        config2.isWhite = true;
                        config2.whitePriority = highPriority;
                        config2.blackPriority = lowPriority;

                        config2.whiteEval = 10;
                        config2.blackEval = -10;

                        config2.whiteCounterMoves.add(lowerLeft);
                        config2.whiteCounterMoves.add(upperRight);
                        config2.whiteCounterMoves.add(upperRight4);

                        config2.blackCounterMoves.add(lowerLeft);
                        config2.blackCounterMoves.add(upperRight);
                        config2.blackCounterMoves.add(upperRight4);
                        configsList.add(config2);
                    }
                    // -W-WWB
                    if (isCoordInArrayList(upperRight4,scenario.blackMoves) &&
                            !isCoordInArrayList(lowerLeft,scenario.whiteMoves) &&
                            !isCoordInArrayList(lowerLeft,scenario.blackMoves)){
                        PenteConfigurations config2 = new PenteConfigurations();
                        config2.confType = 1;
                        config2.direction = 90;
                        config2.isWhite = true;
                        config2.whitePriority = highPriority + 2;
                        config2.blackPriority = lowPriority + 2;

                        config2.whiteEval = 5;
                        config2.blackEval = -5;

                        config2.whiteCounterMoves.add(lowerLeft);
                        config2.whiteCounterMoves.add(upperRight);

                        config2.blackCounterMoves.add(lowerLeft);
                        config2.blackCounterMoves.add(upperRight);
                        configsList.add(config2);
                    }
                    // BW-WW-
                    if (isCoordInArrayList(lowerLeft,scenario.blackMoves) &&
                            !isCoordInArrayList(upperRight4,scenario.whiteMoves) &&
                            !isCoordInArrayList(upperRight4,scenario.blackMoves)){
                        PenteConfigurations config2 = new PenteConfigurations();
                        config2.confType = 1;
                        config2.direction = 90;
                        config2.isWhite = true;
                        config2.whitePriority = highPriority + 2;
                        config2.blackPriority = lowPriority + 2;

                        config2.whiteEval = 5;
                        config2.blackEval = -5;

                        config2.whiteCounterMoves.add(upperRight);
                        config2.whiteCounterMoves.add(upperRight4);

                        config2.blackCounterMoves.add(upperRight);
                        config2.blackCounterMoves.add(upperRight4);
                        configsList.add(config2);
                    }
                    // W-WWW
                    if (isCoordInArrayList(upperRight4,scenario.whiteMoves)){
                        PenteConfigurations config2 = new PenteConfigurations();
                        config2.confType = 1;
                        config2.direction = 90;
                        config2.isWhite = true;
                        config2.whitePriority = highPriority - 2;
                        config2.blackPriority = lowPriority - 2;

                        config2.whiteEval = 20;
                        config2.blackEval = -20;

                        config2.whiteCounterMoves.add(upperRight);

                        config2.blackCounterMoves.add(upperRight);
                        configsList.add(config2);
                    }
                }
                // WW-W
                if (isCoordInArrayList(lowerLeft,scenario.whiteMoves)){
                    // BWW-W-
                    if (isCoordInArrayList(lowerLeft2,scenario.blackMoves) &&
                            !isCoordInArrayList(upperRight3,scenario.whiteMoves) &&
                            !isCoordInArrayList(upperRight3,scenario.blackMoves)){
                        PenteConfigurations config2 = new PenteConfigurations();
                        config2.confType = 1;
                        config2.direction = 90;
                        config2.isWhite = true;
                        config2.whitePriority = highPriority + 2;
                        config2.blackPriority = lowPriority + 2;

                        config2.whiteEval = 5;
                        config2.blackEval = -5;

                        config2.whiteCounterMoves.add(upperRight);
                        config2.whiteCounterMoves.add(upperRight3);

                        config2.blackCounterMoves.add(upperRight);
                        config2.blackCounterMoves.add(upperRight3);
                        configsList.add(config2);
                    }
                    // -WW-WB
                    if (isCoordInArrayList(upperRight3,scenario.blackMoves) &&
                            !isCoordInArrayList(lowerLeft2,scenario.whiteMoves) &&
                            !isCoordInArrayList(lowerLeft,scenario.blackMoves)){
                        PenteConfigurations config2 = new PenteConfigurations();
                        config2.confType = 1;
                        config2.direction = 90;
                        config2.isWhite = true;
                        config2.whitePriority = highPriority + 2;
                        config2.blackPriority = lowPriority + 2;

                        config2.whiteEval = 5;
                        config2.blackEval = -5;

                        config2.whiteCounterMoves.add(upperRight);
                        config2.whiteCounterMoves.add(lowerLeft2);

                        config2.blackCounterMoves.add(upperRight);
                        config2.blackCounterMoves.add(lowerLeft2);
                        configsList.add(config2);
                    }
                    // WW-WW
                    if (isCoordInArrayList(upperRight3,scenario.whiteMoves)){
                        PenteConfigurations config2 = new PenteConfigurations();
                        config2.confType = 1;
                        config2.direction = 90;
                        config2.isWhite = true;
                        config2.whitePriority = highPriority - 2;
                        config2.blackPriority = lowPriority - 2;

                        config2.whiteEval = 20;
                        config2.blackEval = -20;

                        config2.whiteCounterMoves.add(upperRight);

                        config2.blackCounterMoves.add(upperRight);
                        configsList.add(config2);
                    }
                    // WWW-W
                    if (isCoordInArrayList(lowerLeft2,scenario.whiteMoves)){
                        PenteConfigurations config2 = new PenteConfigurations();
                        config2.confType = 1;
                        config2.direction = 90;
                        config2.isWhite = true;
                        config2.whitePriority = highPriority - 2;
                        config2.blackPriority = lowPriority - 2;

                        config2.whiteEval = 20;
                        config2.blackEval = -20;

                        config2.whiteCounterMoves.add(upperRight);

                        config2.blackCounterMoves.add(upperRight);
                        configsList.add(config2);
                    }
                }

                // -WW-W-
                if ((isCoordInArrayList(lowerLeft,scenario.whiteMoves) &&
                        !isCoordInArrayList(upperRight3,scenario.whiteMoves) &&
                        !isCoordInArrayList(upperRight3,scenario.blackMoves) &&
                        !isCoordInArrayList(lowerLeft2,scenario.whiteMoves) &&
                        !isCoordInArrayList(lowerLeft2,scenario.blackMoves))){
                    PenteConfigurations config2 = new PenteConfigurations();
                    config2.confType = 1;
                    config2.direction = 90;
                    config2.isWhite = true;

                    config2.whitePriority = highPriority;
                    config2.blackPriority = lowPriority;

                    config2.whiteEval = 10;
                    config2.blackEval = -10;

                    config2.whiteCounterMoves.add(lowerLeft2);
                    config2.whiteCounterMoves.add(upperRight);
                    config2.whiteCounterMoves.add(upperRight3);

                    config2.blackCounterMoves.add(lowerLeft2);
                    config2.blackCounterMoves.add(upperRight);
                    config2.blackCounterMoves.add(upperRight3);
                    configsList.add(config2);
                }

                // -BW-WW-
                if ((isCoordInArrayList(lowerLeft,scenario.whiteMoves) &&
                        !isCoordInArrayList(upperRight3,scenario.whiteMoves) &&
                        !isCoordInArrayList(upperRight3,scenario.blackMoves) &&
                        !isCoordInArrayList(lowerLeft2,scenario.whiteMoves) &&
                        !isCoordInArrayList(lowerLeft2,scenario.blackMoves))){
                    PenteConfigurations config2 = new PenteConfigurations();
                    config2.confType = 1;
                    config2.direction = 90;
                    config2.isWhite = true;

                    config2.whitePriority = highPriority;
                    config2.blackPriority = lowPriority;

                    config2.whiteEval = 10;
                    config2.blackEval = -10;

                    config2.whiteCounterMoves.add(lowerLeft2);
                    config2.whiteCounterMoves.add(upperRight);
                    config2.whiteCounterMoves.add(upperRight3);

                    config2.blackCounterMoves.add(lowerLeft2);
                    config2.blackCounterMoves.add(upperRight);
                    config2.blackCounterMoves.add(upperRight3);
                    configsList.add(config2);
                }
            }
            if (!isCoordInArrayList(right,scenario.whiteMoves) &&
                    !isCoordInArrayList(right,scenario.blackMoves) &&
                    isCoordInArrayList(right2,scenario.whiteMoves)){
                // W-WW
                if (isCoordInArrayList(right3,scenario.whiteMoves)){
                    // -W-WW-
                    if (!isCoordInArrayList(right4,scenario.whiteMoves) &&
                            !isCoordInArrayList(right4,scenario.blackMoves) &&
                            !isCoordInArrayList(left,scenario.whiteMoves) &&
                            !isCoordInArrayList(left,scenario.blackMoves)){
                        PenteConfigurations config3 = new PenteConfigurations();
                        config3.confType = 1;
                        config3.direction = 90;
                        config3.isWhite = true;
                        config3.whitePriority = highPriority;
                        config3.blackPriority = lowPriority;

                        config3.whiteEval = 10;
                        config3.blackEval = -10;

                        config3.whiteCounterMoves.add(left);
                        config3.whiteCounterMoves.add(right);
                        config3.whiteCounterMoves.add(right4);

                        config3.blackCounterMoves.add(left);
                        config3.blackCounterMoves.add(right);
                        config3.blackCounterMoves.add(right4);
                        configsList.add(config3);
                    }
                    // -W-WWB
                    if (isCoordInArrayList(right4,scenario.blackMoves) &&
                            !isCoordInArrayList(left,scenario.whiteMoves) &&
                            !isCoordInArrayList(left,scenario.blackMoves)){
                        PenteConfigurations config3 = new PenteConfigurations();
                        config3.confType = 1;
                        config3.direction = 90;
                        config3.isWhite = true;
                        config3.whitePriority = highPriority + 2;
                        config3.blackPriority = lowPriority + 2;

                        config3.whiteEval = 5;
                        config3.blackEval = -5;

                        config3.whiteCounterMoves.add(left);
                        config3.whiteCounterMoves.add(right);

                        config3.blackCounterMoves.add(left);
                        config3.blackCounterMoves.add(right);
                        configsList.add(config3);
                    }
                    // BW-WW-
                    if (isCoordInArrayList(left,scenario.blackMoves) &&
                            !isCoordInArrayList(right4,scenario.whiteMoves) &&
                            !isCoordInArrayList(right4,scenario.blackMoves)){
                        PenteConfigurations config3 = new PenteConfigurations();
                        config3.confType = 1;
                        config3.direction = 90;
                        config3.isWhite = true;
                        config3.whitePriority = highPriority + 2;
                        config3.blackPriority = lowPriority + 2;

                        config3.whiteEval = 5;
                        config3.blackEval = -5;

                        config3.whiteCounterMoves.add(right);
                        config3.whiteCounterMoves.add(right4);

                        config3.blackCounterMoves.add(right);
                        config3.blackCounterMoves.add(right4);
                        configsList.add(config3);
                    }
                    // W-WWW
                    if (isCoordInArrayList(right4,scenario.whiteMoves)){
                        PenteConfigurations config3 = new PenteConfigurations();
                        config3.confType = 1;
                        config3.direction = 90;
                        config3.isWhite = true;
                        config3.whitePriority = highPriority - 2;
                        config3.blackPriority = lowPriority - 2;

                        config3.whiteEval = 20;
                        config3.blackEval = -20;

                        config3.whiteCounterMoves.add(right);

                        config3.blackCounterMoves.add(right);
                        configsList.add(config3);
                    }
                }
                // WW-W
                if (isCoordInArrayList(left,scenario.whiteMoves)){
                    // BWW-W-
                    if (isCoordInArrayList(left2,scenario.blackMoves) &&
                            !isCoordInArrayList(right3,scenario.whiteMoves) &&
                            !isCoordInArrayList(right3,scenario.blackMoves)){
                        PenteConfigurations config3 = new PenteConfigurations();
                        config3.confType = 1;
                        config3.direction = 90;
                        config3.isWhite = true;
                        config3.whitePriority = highPriority + 2;
                        config3.blackPriority = lowPriority + 2;

                        config3.whiteEval = 5;
                        config3.blackEval = -5;

                        config3.whiteCounterMoves.add(right);
                        config3.whiteCounterMoves.add(right3);

                        config3.blackCounterMoves.add(right);
                        config3.blackCounterMoves.add(right3);
                        configsList.add(config3);
                    }
                    // -WW-WB
                    if (isCoordInArrayList(right3,scenario.blackMoves) &&
                            !isCoordInArrayList(left2,scenario.whiteMoves) &&
                            !isCoordInArrayList(left,scenario.blackMoves)){
                        PenteConfigurations config3 = new PenteConfigurations();
                        config3.confType = 1;
                        config3.direction = 90;
                        config3.isWhite = true;
                        config3.whitePriority = highPriority + 2;
                        config3.blackPriority = lowPriority + 2;

                        config3.whiteEval = 5;
                        config3.blackEval = -5;

                        config3.whiteCounterMoves.add(right);
                        config3.whiteCounterMoves.add(left2);

                        config3.blackCounterMoves.add(right);
                        config3.blackCounterMoves.add(left2);
                        configsList.add(config3);
                    }
                    // WW-WW
                    if (isCoordInArrayList(right3,scenario.whiteMoves)){
                        PenteConfigurations config3 = new PenteConfigurations();
                        config3.confType = 1;
                        config3.direction = 90;
                        config3.isWhite = true;
                        config3.whitePriority = highPriority - 2;
                        config3.blackPriority = lowPriority - 2;

                        config3.whiteEval = 20;
                        config3.blackEval = -20;

                        config3.whiteCounterMoves.add(right);

                        config3.blackCounterMoves.add(right);
                        configsList.add(config3);
                    }
                    // WWW-W
                    if (isCoordInArrayList(left2,scenario.whiteMoves)){
                        PenteConfigurations config3 = new PenteConfigurations();
                        config3.confType = 1;
                        config3.direction = 90;
                        config3.isWhite = true;
                        config3.whitePriority = highPriority - 2;
                        config3.blackPriority = lowPriority - 2;

                        config3.whiteEval = 20;
                        config3.blackEval = -20;

                        config3.whiteCounterMoves.add(right);

                        config3.blackCounterMoves.add(right);
                        configsList.add(config3);
                    }
                }

                // -WW-W-
                if ((isCoordInArrayList(left,scenario.whiteMoves) &&
                        !isCoordInArrayList(right3,scenario.whiteMoves) &&
                        !isCoordInArrayList(right3,scenario.blackMoves) &&
                        !isCoordInArrayList(left2,scenario.whiteMoves) &&
                        !isCoordInArrayList(left2,scenario.blackMoves))){
                    PenteConfigurations config3 = new PenteConfigurations();
                    config3.confType = 1;
                    config3.direction = 90;
                    config3.isWhite = true;

                    config3.whitePriority = highPriority;
                    config3.blackPriority = lowPriority;

                    config3.whiteEval = 10;
                    config3.blackEval = -10;

                    config3.whiteCounterMoves.add(left2);
                    config3.whiteCounterMoves.add(right);
                    config3.whiteCounterMoves.add(right3);

                    config3.blackCounterMoves.add(left2);
                    config3.blackCounterMoves.add(right);
                    config3.blackCounterMoves.add(right3);
                    configsList.add(config3);
                }

                // -BW-WW-
                if ((isCoordInArrayList(left,scenario.whiteMoves) &&
                        !isCoordInArrayList(right3,scenario.whiteMoves) &&
                        !isCoordInArrayList(right3,scenario.blackMoves) &&
                        !isCoordInArrayList(left2,scenario.whiteMoves) &&
                        !isCoordInArrayList(left2,scenario.blackMoves))){
                    PenteConfigurations config3 = new PenteConfigurations();
                    config3.confType = 1;
                    config3.direction = 90;
                    config3.isWhite = true;

                    config3.whitePriority = highPriority;
                    config3.blackPriority = lowPriority;

                    config3.whiteEval = 10;
                    config3.blackEval = -10;

                    config3.whiteCounterMoves.add(left2);
                    config3.whiteCounterMoves.add(right);
                    config3.whiteCounterMoves.add(right3);

                    config3.blackCounterMoves.add(left2);
                    config3.blackCounterMoves.add(right);
                    config3.blackCounterMoves.add(right3);
                    configsList.add(config3);
                }
            }
            if (!isCoordInArrayList(lowerRight,scenario.whiteMoves) &&
                    !isCoordInArrayList(lowerRight,scenario.blackMoves) &&
                    isCoordInArrayList(lowerRight2,scenario.whiteMoves)){
                // W-WW
                if (isCoordInArrayList(lowerRight3,scenario.whiteMoves)){
                    // -W-WW-
                    if (!isCoordInArrayList(lowerRight4,scenario.whiteMoves) &&
                            !isCoordInArrayList(lowerRight4,scenario.blackMoves) &&
                            !isCoordInArrayList(upperLeft,scenario.whiteMoves) &&
                            !isCoordInArrayList(upperLeft,scenario.blackMoves)){
                        PenteConfigurations config4 = new PenteConfigurations();
                        config4.confType = 1;
                        config4.direction = 90;
                        config4.isWhite = true;
                        config4.whitePriority = highPriority;
                        config4.blackPriority = lowPriority;

                        config4.whiteEval = 10;
                        config4.blackEval = -10;

                        config4.whiteCounterMoves.add(upperLeft);
                        config4.whiteCounterMoves.add(lowerRight);
                        config4.whiteCounterMoves.add(lowerRight4);

                        config4.blackCounterMoves.add(upperLeft);
                        config4.blackCounterMoves.add(lowerRight);
                        config4.blackCounterMoves.add(lowerRight4);
                        configsList.add(config4);
                    }
                    // -W-WWB
                    if (isCoordInArrayList(lowerRight4,scenario.blackMoves) &&
                            !isCoordInArrayList(upperLeft,scenario.whiteMoves) &&
                            !isCoordInArrayList(upperLeft,scenario.blackMoves)){
                        PenteConfigurations config4 = new PenteConfigurations();
                        config4.confType = 1;
                        config4.direction = 90;
                        config4.isWhite = true;
                        config4.whitePriority = highPriority + 2;
                        config4.blackPriority = lowPriority + 2;

                        config4.whiteEval = 5;
                        config4.blackEval = -5;

                        config4.whiteCounterMoves.add(upperLeft);
                        config4.whiteCounterMoves.add(lowerRight);

                        config4.blackCounterMoves.add(upperLeft);
                        config4.blackCounterMoves.add(lowerRight);
                        configsList.add(config4);
                    }
                    // BW-WW-
                    if (isCoordInArrayList(upperLeft,scenario.blackMoves) &&
                            !isCoordInArrayList(lowerRight4,scenario.whiteMoves) &&
                            !isCoordInArrayList(lowerRight4,scenario.blackMoves)){
                        PenteConfigurations config4 = new PenteConfigurations();
                        config4.confType = 1;
                        config4.direction = 90;
                        config4.isWhite = true;
                        config4.whitePriority = highPriority + 2;
                        config4.blackPriority = lowPriority + 2;

                        config4.whiteEval = 5;
                        config4.blackEval = -5;

                        config4.whiteCounterMoves.add(lowerRight);
                        config4.whiteCounterMoves.add(lowerRight4);

                        config4.blackCounterMoves.add(lowerRight);
                        config4.blackCounterMoves.add(lowerRight4);
                        configsList.add(config4);
                    }
                    // W-WWW
                    if (isCoordInArrayList(lowerRight4,scenario.whiteMoves)){
                        PenteConfigurations config4 = new PenteConfigurations();
                        config4.confType = 1;
                        config4.direction = 90;
                        config4.isWhite = true;
                        config4.whitePriority = highPriority - 2;
                        config4.blackPriority = lowPriority - 2;

                        config4.whiteEval = 20;
                        config4.blackEval = -20;

                        config4.whiteCounterMoves.add(lowerRight);

                        config4.blackCounterMoves.add(lowerRight);
                        configsList.add(config4);
                    }
                }
                // WW-W
                if (isCoordInArrayList(upperLeft,scenario.whiteMoves)){
                    // BWW-W-
                    if (isCoordInArrayList(upperLeft2,scenario.blackMoves) &&
                            !isCoordInArrayList(lowerRight3,scenario.whiteMoves) &&
                            !isCoordInArrayList(lowerRight3,scenario.blackMoves)){
                        PenteConfigurations config4 = new PenteConfigurations();
                        config4.confType = 1;
                        config4.direction = 90;
                        config4.isWhite = true;
                        config4.whitePriority = highPriority + 2;
                        config4.blackPriority = lowPriority + 2;

                        config4.whiteEval = 5;
                        config4.blackEval = -5;

                        config4.whiteCounterMoves.add(lowerRight);
                        config4.whiteCounterMoves.add(lowerRight3);

                        config4.blackCounterMoves.add(lowerRight);
                        config4.blackCounterMoves.add(lowerRight3);
                        configsList.add(config4);
                    }
                    // -WW-WB
                    if (isCoordInArrayList(lowerRight3,scenario.blackMoves) &&
                            !isCoordInArrayList(upperLeft2,scenario.whiteMoves) &&
                            !isCoordInArrayList(upperLeft,scenario.blackMoves)){
                        PenteConfigurations config4 = new PenteConfigurations();
                        config4.confType = 1;
                        config4.direction = 90;
                        config4.isWhite = true;
                        config4.whitePriority = highPriority + 2;
                        config4.blackPriority = lowPriority + 2;

                        config4.whiteEval = 5;
                        config4.blackEval = -5;

                        config4.whiteCounterMoves.add(lowerRight);
                        config4.whiteCounterMoves.add(upperLeft2);

                        config4.blackCounterMoves.add(lowerRight);
                        config4.blackCounterMoves.add(upperLeft2);
                        configsList.add(config4);
                    }
                    // WW-WW
                    if (isCoordInArrayList(lowerRight3,scenario.whiteMoves)){
                        PenteConfigurations config4 = new PenteConfigurations();
                        config4.confType = 1;
                        config4.direction = 90;
                        config4.isWhite = true;
                        config4.whitePriority = highPriority - 2;
                        config4.blackPriority = lowPriority - 2;

                        config4.whiteEval = 20;
                        config4.blackEval = -20;

                        config4.whiteCounterMoves.add(lowerRight);

                        config4.blackCounterMoves.add(lowerRight);
                        configsList.add(config4);
                    }
                    // WWW-W
                    if (isCoordInArrayList(upperLeft2,scenario.whiteMoves)){
                        PenteConfigurations config4 = new PenteConfigurations();
                        config4.confType = 1;
                        config4.direction = 90;
                        config4.isWhite = true;
                        config4.whitePriority = highPriority - 2;
                        config4.blackPriority = lowPriority - 2;

                        config4.whiteEval = 20;
                        config4.blackEval = -20;

                        config4.whiteCounterMoves.add(lowerRight);

                        config4.blackCounterMoves.add(lowerRight);
                        configsList.add(config4);
                    }
                }

                // -WW-W-
                if ((isCoordInArrayList(upperLeft,scenario.whiteMoves) &&
                        !isCoordInArrayList(lowerRight3,scenario.whiteMoves) &&
                        !isCoordInArrayList(lowerRight3,scenario.blackMoves) &&
                        !isCoordInArrayList(upperLeft2,scenario.whiteMoves) &&
                        !isCoordInArrayList(upperLeft2,scenario.blackMoves))){
                    PenteConfigurations config4 = new PenteConfigurations();
                    config4.confType = 1;
                    config4.direction = 90;
                    config4.isWhite = true;

                    config4.whitePriority = highPriority;
                    config4.blackPriority = lowPriority;

                    config4.whiteEval = 10;
                    config4.blackEval = -10;

                    config4.whiteCounterMoves.add(upperLeft2);
                    config4.whiteCounterMoves.add(lowerRight);
                    config4.whiteCounterMoves.add(lowerRight3);

                    config4.blackCounterMoves.add(upperLeft2);
                    config4.blackCounterMoves.add(lowerRight);
                    config4.blackCounterMoves.add(lowerRight3);
                    configsList.add(config4);
                }

                // -BW-WW-
                if ((isCoordInArrayList(upperLeft,scenario.whiteMoves) &&
                        !isCoordInArrayList(lowerRight3,scenario.whiteMoves) &&
                        !isCoordInArrayList(lowerRight3,scenario.blackMoves) &&
                        !isCoordInArrayList(upperLeft2,scenario.whiteMoves) &&
                        !isCoordInArrayList(upperLeft2,scenario.blackMoves))){
                    PenteConfigurations config4 = new PenteConfigurations();
                    config4.confType = 1;
                    config4.direction = 90;
                    config4.isWhite = true;

                    config4.whitePriority = highPriority;
                    config4.blackPriority = lowPriority;

                    config4.whiteEval = 10;
                    config4.blackEval = -10;

                    config4.whiteCounterMoves.add(upperLeft2);
                    config4.whiteCounterMoves.add(lowerRight);
                    config4.whiteCounterMoves.add(lowerRight3);

                    config4.blackCounterMoves.add(upperLeft2);
                    config4.blackCounterMoves.add(lowerRight);
                    config4.blackCounterMoves.add(lowerRight3);
                    configsList.add(config4);
                }
            }


        }
        if (isPieceWhite == false){
            if (!isCoordInArrayList(upper,scenario.blackMoves) &&
                    !isCoordInArrayList(upper,scenario.whiteMoves) &&
                    isCoordInArrayList(upper2,scenario.blackMoves)){
                // W-WW
                if (isCoordInArrayList(upper3,scenario.blackMoves)){
                    // -W-WW-
                    if (!isCoordInArrayList(upper4,scenario.blackMoves) &&
                            !isCoordInArrayList(upper4,scenario.whiteMoves) &&
                            !isCoordInArrayList(lower,scenario.blackMoves) &&
                            !isCoordInArrayList(lower,scenario.whiteMoves)){
                        PenteConfigurations config5 = new PenteConfigurations();
                        config5.confType = 1;
                        config5.direction = 90;
                        config5.isWhite = false;
                        config5.blackPriority = highPriority;
                        config5.whitePriority = lowPriority;

                        config5.blackEval = 10;
                        config5.whiteEval = -10;

                        config5.blackCounterMoves.add(lower);
                        config5.blackCounterMoves.add(upper);
                        config5.blackCounterMoves.add(upper4);

                        config5.whiteCounterMoves.add(lower);
                        config5.whiteCounterMoves.add(upper);
                        config5.whiteCounterMoves.add(upper4);
                        configsList.add(config5);
                    }
                    // -W-WWB
                    if (isCoordInArrayList(upper4,scenario.whiteMoves) &&
                            !isCoordInArrayList(lower,scenario.blackMoves) &&
                            !isCoordInArrayList(lower,scenario.whiteMoves)){
                        PenteConfigurations config5 = new PenteConfigurations();
                        config5.confType = 1;
                        config5.direction = 90;
                        config5.isWhite = false;
                        config5.blackPriority = highPriority + 2;
                        config5.whitePriority = lowPriority + 2;

                        config5.blackEval = 5;
                        config5.whiteEval = -5;

                        config5.blackCounterMoves.add(lower);
                        config5.blackCounterMoves.add(upper);

                        config5.whiteCounterMoves.add(lower);
                        config5.whiteCounterMoves.add(upper);
                        configsList.add(config5);
                    }
                    // BW-WW-
                    if (isCoordInArrayList(lower,scenario.whiteMoves) &&
                            !isCoordInArrayList(upper4,scenario.blackMoves) &&
                            !isCoordInArrayList(upper4,scenario.whiteMoves)){
                        PenteConfigurations config5 = new PenteConfigurations();
                        config5.confType = 1;
                        config5.direction = 90;
                        config5.isWhite = false;
                        config5.blackPriority = highPriority + 2;
                        config5.whitePriority = lowPriority + 2;

                        config5.blackEval = 5;
                        config5.whiteEval = -5;

                        config5.blackCounterMoves.add(upper);
                        config5.blackCounterMoves.add(upper4);

                        config5.whiteCounterMoves.add(upper);
                        config5.whiteCounterMoves.add(upper4);
                        configsList.add(config5);
                    }
                    // W-WWW
                    if (isCoordInArrayList(upper4,scenario.blackMoves)){
                        PenteConfigurations config5 = new PenteConfigurations();
                        config5.confType = 1;
                        config5.direction = 90;
                        config5.isWhite = false;
                        config5.blackPriority = highPriority - 2;
                        config5.whitePriority = lowPriority - 2;

                        config5.blackEval = 20;
                        config5.whiteEval = -20;

                        config5.blackCounterMoves.add(upper);

                        config5.whiteCounterMoves.add(upper);
                        configsList.add(config5);
                    }
                }
                // WW-W
                if (isCoordInArrayList(lower,scenario.blackMoves)){
                    // BWW-W-
                    if (isCoordInArrayList(lower2,scenario.whiteMoves) &&
                            !isCoordInArrayList(upper3,scenario.blackMoves) &&
                            !isCoordInArrayList(upper3,scenario.whiteMoves)){
                        PenteConfigurations config5 = new PenteConfigurations();
                        config5.confType = 1;
                        config5.direction = 90;
                        config5.isWhite = false;
                        config5.blackPriority = highPriority + 2;
                        config5.whitePriority = lowPriority + 2;

                        config5.blackEval = 5;
                        config5.whiteEval = -5;

                        config5.blackCounterMoves.add(upper);
                        config5.blackCounterMoves.add(upper3);

                        config5.whiteCounterMoves.add(upper);
                        config5.whiteCounterMoves.add(upper3);
                        configsList.add(config5);
                    }
                    // -WW-WB
                    if (isCoordInArrayList(upper3,scenario.whiteMoves) &&
                            !isCoordInArrayList(lower2,scenario.blackMoves) &&
                            !isCoordInArrayList(lower,scenario.whiteMoves)){
                        PenteConfigurations config5 = new PenteConfigurations();
                        config5.confType = 1;
                        config5.direction = 90;
                        config5.isWhite = false;
                        config5.blackPriority = highPriority + 2;
                        config5.whitePriority = lowPriority + 2;

                        config5.blackEval = 5;
                        config5.whiteEval = -5;

                        config5.blackCounterMoves.add(upper);
                        config5.blackCounterMoves.add(lower2);

                        config5.whiteCounterMoves.add(upper);
                        config5.whiteCounterMoves.add(lower2);
                        configsList.add(config5);
                    }
                    // WW-WW
                    if (isCoordInArrayList(upper3,scenario.blackMoves)){
                        PenteConfigurations config5 = new PenteConfigurations();
                        config5.confType = 1;
                        config5.direction = 90;
                        config5.isWhite = false;
                        config5.blackPriority = highPriority - 2;
                        config5.whitePriority = lowPriority - 2;

                        config5.blackEval = 20;
                        config5.whiteEval = -20;

                        config5.blackCounterMoves.add(upper);

                        config5.whiteCounterMoves.add(upper);
                        configsList.add(config5);
                    }
                    // WWW-W
                    if (isCoordInArrayList(lower2,scenario.blackMoves)){
                        PenteConfigurations config5 = new PenteConfigurations();
                        config5.confType = 1;
                        config5.direction = 90;
                        config5.isWhite = false;
                        config5.blackPriority = highPriority - 2;
                        config5.whitePriority = lowPriority - 2;

                        config5.blackEval = 20;
                        config5.whiteEval = -20;

                        config5.blackCounterMoves.add(upper);

                        config5.whiteCounterMoves.add(upper);
                        configsList.add(config5);
                    }
                }

                // -WW-W-
                if ((isCoordInArrayList(lower,scenario.blackMoves) &&
                        !isCoordInArrayList(upper3,scenario.blackMoves) &&
                        !isCoordInArrayList(upper3,scenario.whiteMoves) &&
                        !isCoordInArrayList(lower2,scenario.blackMoves) &&
                        !isCoordInArrayList(lower2,scenario.whiteMoves))){
                    PenteConfigurations config5 = new PenteConfigurations();
                    config5.confType = 1;
                    config5.direction = 90;
                    config5.isWhite = false;

                    config5.blackPriority = highPriority;
                    config5.whitePriority = lowPriority;

                    config5.blackEval = 10;
                    config5.whiteEval = -10;

                    config5.blackCounterMoves.add(lower2);
                    config5.blackCounterMoves.add(upper);
                    config5.blackCounterMoves.add(upper3);

                    config5.whiteCounterMoves.add(lower2);
                    config5.whiteCounterMoves.add(upper);
                    config5.whiteCounterMoves.add(upper3);
                    configsList.add(config5);
                }

                // -BW-WW-
                if ((isCoordInArrayList(lower,scenario.blackMoves) &&
                        !isCoordInArrayList(upper3,scenario.blackMoves) &&
                        !isCoordInArrayList(upper3,scenario.whiteMoves) &&
                        !isCoordInArrayList(lower2,scenario.blackMoves) &&
                        !isCoordInArrayList(lower2,scenario.whiteMoves))){
                    PenteConfigurations config5 = new PenteConfigurations();
                    config5.confType = 1;
                    config5.direction = 90;
                    config5.isWhite = false;

                    config5.blackPriority = highPriority;
                    config5.whitePriority = lowPriority;

                    config5.blackEval = 10;
                    config5.whiteEval = -10;

                    config5.blackCounterMoves.add(lower2);
                    config5.blackCounterMoves.add(upper);
                    config5.blackCounterMoves.add(upper3);

                    config5.whiteCounterMoves.add(lower2);
                    config5.whiteCounterMoves.add(upper);
                    config5.whiteCounterMoves.add(upper3);
                    configsList.add(config5);
                }
            }
            if (!isCoordInArrayList(upperRight,scenario.blackMoves) &&
                    !isCoordInArrayList(upperRight,scenario.whiteMoves) &&
                    isCoordInArrayList(upperRight2,scenario.blackMoves)){
                // W-WW
                if (isCoordInArrayList(upperRight3,scenario.blackMoves)){
                    // -W-WW-
                    if (!isCoordInArrayList(upperRight4,scenario.blackMoves) &&
                            !isCoordInArrayList(upperRight4,scenario.whiteMoves) &&
                            !isCoordInArrayList(lowerLeft,scenario.blackMoves) &&
                            !isCoordInArrayList(lowerLeft,scenario.whiteMoves)){
                        PenteConfigurations config6 = new PenteConfigurations();
                        config6.confType = 1;
                        config6.direction = 45;
                        config6.isWhite = false;
                        config6.blackPriority = highPriority;
                        config6.whitePriority = lowPriority;

                        config6.blackEval = 10;
                        config6.whiteEval = -10;

                        config6.blackCounterMoves.add(lowerLeft);
                        config6.blackCounterMoves.add(upperRight);
                        config6.blackCounterMoves.add(upperRight4);

                        config6.whiteCounterMoves.add(lowerLeft);
                        config6.whiteCounterMoves.add(upperRight);
                        config6.whiteCounterMoves.add(upperRight4);
                        configsList.add(config6);
                    }
                    // -W-WWB
                    if (isCoordInArrayList(upperRight4,scenario.whiteMoves) &&
                            !isCoordInArrayList(lowerLeft,scenario.blackMoves) &&
                            !isCoordInArrayList(lowerLeft,scenario.whiteMoves)){
                        PenteConfigurations config6 = new PenteConfigurations();
                        config6.confType = 1;
                        config6.direction = 45;
                        config6.isWhite = false;
                        config6.blackPriority = highPriority + 2;
                        config6.whitePriority = lowPriority + 2;

                        config6.blackEval = 5;
                        config6.whiteEval = -5;

                        config6.blackCounterMoves.add(lowerLeft);
                        config6.blackCounterMoves.add(upperRight);

                        config6.whiteCounterMoves.add(lowerLeft);
                        config6.whiteCounterMoves.add(upperRight);
                        configsList.add(config6);
                    }
                    // BW-WW-
                    if (isCoordInArrayList(lowerLeft,scenario.whiteMoves) &&
                            !isCoordInArrayList(upperRight4,scenario.blackMoves) &&
                            !isCoordInArrayList(upperRight4,scenario.whiteMoves)){
                        PenteConfigurations config6 = new PenteConfigurations();
                        config6.confType = 1;
                        config6.direction = 45;
                        config6.isWhite = false;
                        config6.blackPriority = highPriority + 2;
                        config6.whitePriority = lowPriority + 2;

                        config6.blackEval = 5;
                        config6.whiteEval = -5;

                        config6.blackCounterMoves.add(upperRight);
                        config6.blackCounterMoves.add(upperRight4);

                        config6.whiteCounterMoves.add(upperRight);
                        config6.whiteCounterMoves.add(upperRight4);
                        configsList.add(config6);
                    }
                    // W-WWW
                    if (isCoordInArrayList(upperRight4,scenario.blackMoves)){
                        PenteConfigurations config6 = new PenteConfigurations();
                        config6.confType = 1;
                        config6.direction = 45;
                        config6.isWhite = false;
                        config6.blackPriority = highPriority - 2;
                        config6.whitePriority = lowPriority - 2;

                        config6.blackEval = 20;
                        config6.whiteEval = -20;

                        config6.blackCounterMoves.add(upperRight);

                        config6.whiteCounterMoves.add(upperRight);
                        configsList.add(config6);
                    }
                }
                // WW-W
                if (isCoordInArrayList(lowerLeft,scenario.blackMoves)){
                    // BWW-W-
                    if (isCoordInArrayList(lowerLeft2,scenario.whiteMoves) &&
                            !isCoordInArrayList(upperRight3,scenario.blackMoves) &&
                            !isCoordInArrayList(upperRight3,scenario.whiteMoves)){
                        PenteConfigurations config6 = new PenteConfigurations();
                        config6.confType = 1;
                        config6.direction = 45;
                        config6.isWhite = false;
                        config6.blackPriority = highPriority + 2;
                        config6.whitePriority = lowPriority + 2;

                        config6.blackEval = 5;
                        config6.whiteEval = -5;

                        config6.blackCounterMoves.add(upperRight);
                        config6.blackCounterMoves.add(upperRight3);

                        config6.whiteCounterMoves.add(upperRight);
                        config6.whiteCounterMoves.add(upperRight3);
                        configsList.add(config6);
                    }
                    // -WW-WB
                    if (isCoordInArrayList(upperRight3,scenario.whiteMoves) &&
                            !isCoordInArrayList(lowerLeft2,scenario.blackMoves) &&
                            !isCoordInArrayList(lowerLeft,scenario.whiteMoves)){
                        PenteConfigurations config6 = new PenteConfigurations();
                        config6.confType = 1;
                        config6.direction = 45;
                        config6.isWhite = false;
                        config6.blackPriority = highPriority + 2;
                        config6.whitePriority = lowPriority + 2;

                        config6.blackEval = 5;
                        config6.whiteEval = -5;

                        config6.blackCounterMoves.add(upperRight);
                        config6.blackCounterMoves.add(lowerLeft2);

                        config6.whiteCounterMoves.add(upperRight);
                        config6.whiteCounterMoves.add(lowerLeft2);
                        configsList.add(config6);
                    }
                    // WW-WW
                    if (isCoordInArrayList(upperRight3,scenario.blackMoves)){
                        PenteConfigurations config6 = new PenteConfigurations();
                        config6.confType = 1;
                        config6.direction = 45;
                        config6.isWhite = false;
                        config6.blackPriority = highPriority - 2;
                        config6.whitePriority = lowPriority - 2;

                        config6.blackEval = 20;
                        config6.whiteEval = -20;

                        config6.blackCounterMoves.add(upperRight);

                        config6.whiteCounterMoves.add(upperRight);
                        configsList.add(config6);
                    }
                    // WWW-W
                    if (isCoordInArrayList(lowerLeft2,scenario.blackMoves)){
                        PenteConfigurations config6 = new PenteConfigurations();
                        config6.confType = 1;
                        config6.direction = 45;
                        config6.isWhite = false;
                        config6.blackPriority = highPriority - 2;
                        config6.whitePriority = lowPriority - 2;

                        config6.blackEval = 20;
                        config6.whiteEval = -20;

                        config6.blackCounterMoves.add(upperRight);

                        config6.whiteCounterMoves.add(upperRight);
                        configsList.add(config6);
                    }
                }

                // -WW-W-
                if ((isCoordInArrayList(lowerLeft,scenario.blackMoves) &&
                        !isCoordInArrayList(upperRight3,scenario.blackMoves) &&
                        !isCoordInArrayList(upperRight3,scenario.whiteMoves) &&
                        !isCoordInArrayList(lowerLeft2,scenario.blackMoves) &&
                        !isCoordInArrayList(lowerLeft2,scenario.whiteMoves))){
                    PenteConfigurations config6 = new PenteConfigurations();
                    config6.confType = 1;
                    config6.direction = 45;
                    config6.isWhite = false;

                    config6.blackPriority = highPriority;
                    config6.whitePriority = lowPriority;

                    config6.blackEval = 10;
                    config6.whiteEval = -10;

                    config6.blackCounterMoves.add(lowerLeft2);
                    config6.blackCounterMoves.add(upperRight);
                    config6.blackCounterMoves.add(upperRight3);

                    config6.whiteCounterMoves.add(lowerLeft2);
                    config6.whiteCounterMoves.add(upperRight);
                    config6.whiteCounterMoves.add(upperRight3);
                    configsList.add(config6);
                }

                // -BW-WW-
                if ((isCoordInArrayList(lowerLeft,scenario.blackMoves) &&
                        !isCoordInArrayList(upperRight3,scenario.blackMoves) &&
                        !isCoordInArrayList(upperRight3,scenario.whiteMoves) &&
                        !isCoordInArrayList(lowerLeft2,scenario.blackMoves) &&
                        !isCoordInArrayList(lowerLeft2,scenario.whiteMoves))){
                    PenteConfigurations config6 = new PenteConfigurations();
                    config6.confType = 1;
                    config6.direction = 45;
                    config6.isWhite = false;

                    config6.blackPriority = highPriority;
                    config6.whitePriority = lowPriority;

                    config6.blackEval = 10;
                    config6.whiteEval = -10;

                    config6.blackCounterMoves.add(lowerLeft2);
                    config6.blackCounterMoves.add(upperRight);
                    config6.blackCounterMoves.add(upperRight3);

                    config6.whiteCounterMoves.add(lowerLeft2);
                    config6.whiteCounterMoves.add(upperRight);
                    config6.whiteCounterMoves.add(upperRight3);
                    configsList.add(config6);
                }
            }

            if (!isCoordInArrayList(right,scenario.blackMoves) &&
                    !isCoordInArrayList(right,scenario.whiteMoves) &&
                    isCoordInArrayList(right2,scenario.blackMoves)){
                // W-WW
                if (isCoordInArrayList(right3,scenario.blackMoves)){
                    // -W-WW-
                    if (!isCoordInArrayList(right4,scenario.blackMoves) &&
                            !isCoordInArrayList(right4,scenario.whiteMoves) &&
                            !isCoordInArrayList(left,scenario.blackMoves) &&
                            !isCoordInArrayList(left,scenario.whiteMoves)){
                        PenteConfigurations config7 = new PenteConfigurations();
                        config7.confType = 1;
                        config7.direction = 45;
                        config7.isWhite = false;
                        config7.blackPriority = highPriority;
                        config7.whitePriority = lowPriority;

                        config7.blackEval = 10;
                        config7.whiteEval = -10;

                        config7.blackCounterMoves.add(left);
                        config7.blackCounterMoves.add(right);
                        config7.blackCounterMoves.add(right4);

                        config7.whiteCounterMoves.add(left);
                        config7.whiteCounterMoves.add(right);
                        config7.whiteCounterMoves.add(right4);
                        configsList.add(config7);
                    }
                    // -W-WWB
                    if (isCoordInArrayList(right4,scenario.whiteMoves) &&
                            !isCoordInArrayList(left,scenario.blackMoves) &&
                            !isCoordInArrayList(left,scenario.whiteMoves)){
                        PenteConfigurations config7 = new PenteConfigurations();
                        config7.confType = 1;
                        config7.direction = 45;
                        config7.isWhite = false;
                        config7.blackPriority = highPriority + 2;
                        config7.whitePriority = lowPriority + 2;

                        config7.blackEval = 5;
                        config7.whiteEval = -5;

                        config7.blackCounterMoves.add(left);
                        config7.blackCounterMoves.add(right);

                        config7.whiteCounterMoves.add(left);
                        config7.whiteCounterMoves.add(right);
                        configsList.add(config7);
                    }
                    // BW-WW-
                    if (isCoordInArrayList(left,scenario.whiteMoves) &&
                            !isCoordInArrayList(right4,scenario.blackMoves) &&
                            !isCoordInArrayList(right4,scenario.whiteMoves)){
                        PenteConfigurations config7 = new PenteConfigurations();
                        config7.confType = 1;
                        config7.direction = 45;
                        config7.isWhite = false;
                        config7.blackPriority = highPriority + 2;
                        config7.whitePriority = lowPriority + 2;

                        config7.blackEval = 5;
                        config7.whiteEval = -5;

                        config7.blackCounterMoves.add(right);
                        config7.blackCounterMoves.add(right4);

                        config7.whiteCounterMoves.add(right);
                        config7.whiteCounterMoves.add(right4);
                        configsList.add(config7);
                    }
                    // W-WWW
                    if (isCoordInArrayList(right4,scenario.blackMoves)){
                        PenteConfigurations config7 = new PenteConfigurations();
                        config7.confType = 1;
                        config7.direction = 45;
                        config7.isWhite = false;
                        config7.blackPriority = highPriority - 2;
                        config7.whitePriority = lowPriority - 2;

                        config7.blackEval = 20;
                        config7.whiteEval = -20;

                        config7.blackCounterMoves.add(right);

                        config7.whiteCounterMoves.add(right);
                        configsList.add(config7);
                    }
                }
                // WW-W
                if (isCoordInArrayList(left,scenario.blackMoves)){
                    // BWW-W-
                    if (isCoordInArrayList(left2,scenario.whiteMoves) &&
                            !isCoordInArrayList(right3,scenario.blackMoves) &&
                            !isCoordInArrayList(right3,scenario.whiteMoves)){
                        PenteConfigurations config7 = new PenteConfigurations();
                        config7.confType = 1;
                        config7.direction = 45;
                        config7.isWhite = false;
                        config7.blackPriority = highPriority + 2;
                        config7.whitePriority = lowPriority + 2;

                        config7.blackEval = 5;
                        config7.whiteEval = -5;

                        config7.blackCounterMoves.add(right);
                        config7.blackCounterMoves.add(right3);

                        config7.whiteCounterMoves.add(right);
                        config7.whiteCounterMoves.add(right3);
                        configsList.add(config7);
                    }
                    // -WW-WB
                    if (isCoordInArrayList(right3,scenario.whiteMoves) &&
                            !isCoordInArrayList(left2,scenario.blackMoves) &&
                            !isCoordInArrayList(left,scenario.whiteMoves)){
                        PenteConfigurations config7 = new PenteConfigurations();
                        config7.confType = 1;
                        config7.direction = 45;
                        config7.isWhite = false;
                        config7.blackPriority = highPriority + 2;
                        config7.whitePriority = lowPriority + 2;

                        config7.blackEval = 5;
                        config7.whiteEval = -5;

                        config7.blackCounterMoves.add(right);
                        config7.blackCounterMoves.add(left2);

                        config7.whiteCounterMoves.add(right);
                        config7.whiteCounterMoves.add(left2);
                        configsList.add(config7);
                    }
                    // WW-WW
                    if (isCoordInArrayList(right3,scenario.blackMoves)){
                        PenteConfigurations config7 = new PenteConfigurations();
                        config7.confType = 1;
                        config7.direction = 45;
                        config7.isWhite = false;
                        config7.blackPriority = highPriority - 2;
                        config7.whitePriority = lowPriority - 2;

                        config7.blackEval = 20;
                        config7.whiteEval = -20;

                        config7.blackCounterMoves.add(right);

                        config7.whiteCounterMoves.add(right);
                        configsList.add(config7);
                    }
                    // WWW-W
                    if (isCoordInArrayList(left2,scenario.blackMoves)){
                        PenteConfigurations config7 = new PenteConfigurations();
                        config7.confType = 1;
                        config7.direction = 45;
                        config7.isWhite = false;
                        config7.blackPriority = highPriority - 2;
                        config7.whitePriority = lowPriority - 2;

                        config7.blackEval = 20;
                        config7.whiteEval = -20;

                        config7.blackCounterMoves.add(right);

                        config7.whiteCounterMoves.add(right);
                        configsList.add(config7);
                    }
                }

                // -WW-W-
                if ((isCoordInArrayList(left,scenario.blackMoves) &&
                        !isCoordInArrayList(right3,scenario.blackMoves) &&
                        !isCoordInArrayList(right3,scenario.whiteMoves) &&
                        !isCoordInArrayList(left2,scenario.blackMoves) &&
                        !isCoordInArrayList(left2,scenario.whiteMoves))){
                    PenteConfigurations config7 = new PenteConfigurations();
                    config7.confType = 1;
                    config7.direction = 45;
                    config7.isWhite = false;

                    config7.blackPriority = highPriority;
                    config7.whitePriority = lowPriority;

                    config7.blackEval = 10;
                    config7.whiteEval = -10;

                    config7.blackCounterMoves.add(left2);
                    config7.blackCounterMoves.add(right);
                    config7.blackCounterMoves.add(right3);

                    config7.whiteCounterMoves.add(left2);
                    config7.whiteCounterMoves.add(right);
                    config7.whiteCounterMoves.add(right3);
                    configsList.add(config7);
                }

                // -BW-WW-
                if ((isCoordInArrayList(left,scenario.blackMoves) &&
                        !isCoordInArrayList(right3,scenario.blackMoves) &&
                        !isCoordInArrayList(right3,scenario.whiteMoves) &&
                        !isCoordInArrayList(left2,scenario.blackMoves) &&
                        !isCoordInArrayList(left2,scenario.whiteMoves))){
                    PenteConfigurations config7 = new PenteConfigurations();
                    config7.confType = 1;
                    config7.direction = 45;
                    config7.isWhite = false;

                    config7.blackPriority = highPriority;
                    config7.whitePriority = lowPriority;

                    config7.blackEval = 10;
                    config7.whiteEval = -10;

                    config7.blackCounterMoves.add(left2);
                    config7.blackCounterMoves.add(right);
                    config7.blackCounterMoves.add(right3);

                    config7.whiteCounterMoves.add(left2);
                    config7.whiteCounterMoves.add(right);
                    config7.whiteCounterMoves.add(right3);
                    configsList.add(config7);
                }
            }
            if (!isCoordInArrayList(lowerRight,scenario.blackMoves) &&
                    !isCoordInArrayList(lowerRight,scenario.whiteMoves) &&
                    isCoordInArrayList(lowerRight2,scenario.blackMoves)){
                // W-WW
                if (isCoordInArrayList(lowerRight3,scenario.blackMoves)){
                    // -W-WW-
                    if (!isCoordInArrayList(lowerRight4,scenario.blackMoves) &&
                            !isCoordInArrayList(lowerRight4,scenario.whiteMoves) &&
                            !isCoordInArrayList(upperLeft,scenario.blackMoves) &&
                            !isCoordInArrayList(upperLeft,scenario.whiteMoves)){
                        PenteConfigurations config8 = new PenteConfigurations();
                        config8.confType = 1;
                        config8.direction = 45;
                        config8.isWhite = false;
                        config8.blackPriority = highPriority;
                        config8.whitePriority = lowPriority;

                        config8.blackEval = 10;
                        config8.whiteEval = -10;

                        config8.blackCounterMoves.add(upperLeft);
                        config8.blackCounterMoves.add(lowerRight);
                        config8.blackCounterMoves.add(lowerRight4);

                        config8.whiteCounterMoves.add(upperLeft);
                        config8.whiteCounterMoves.add(lowerRight);
                        config8.whiteCounterMoves.add(lowerRight4);
                        configsList.add(config8);
                    }
                    // -W-WWB
                    if (isCoordInArrayList(lowerRight4,scenario.whiteMoves) &&
                            !isCoordInArrayList(upperLeft,scenario.blackMoves) &&
                            !isCoordInArrayList(upperLeft,scenario.whiteMoves)){
                        PenteConfigurations config8 = new PenteConfigurations();
                        config8.confType = 1;
                        config8.direction = 45;
                        config8.isWhite = false;
                        config8.blackPriority = highPriority + 2;
                        config8.whitePriority = lowPriority + 2;

                        config8.blackEval = 5;
                        config8.whiteEval = -5;

                        config8.blackCounterMoves.add(upperLeft);
                        config8.blackCounterMoves.add(lowerRight);

                        config8.whiteCounterMoves.add(upperLeft);
                        config8.whiteCounterMoves.add(lowerRight);
                        configsList.add(config8);
                    }
                    // BW-WW-
                    if (isCoordInArrayList(upperLeft,scenario.whiteMoves) &&
                            !isCoordInArrayList(lowerRight4,scenario.blackMoves) &&
                            !isCoordInArrayList(lowerRight4,scenario.whiteMoves)){
                        PenteConfigurations config8 = new PenteConfigurations();
                        config8.confType = 1;
                        config8.direction = 45;
                        config8.isWhite = false;
                        config8.blackPriority = highPriority + 2;
                        config8.whitePriority = lowPriority + 2;

                        config8.blackEval = 5;
                        config8.whiteEval = -5;

                        config8.blackCounterMoves.add(lowerRight);
                        config8.blackCounterMoves.add(lowerRight4);

                        config8.whiteCounterMoves.add(lowerRight);
                        config8.whiteCounterMoves.add(lowerRight4);
                        configsList.add(config8);
                    }
                    // W-WWW
                    if (isCoordInArrayList(lowerRight4,scenario.blackMoves)){
                        PenteConfigurations config8 = new PenteConfigurations();
                        config8.confType = 1;
                        config8.direction = 45;
                        config8.isWhite = false;
                        config8.blackPriority = highPriority - 2;
                        config8.whitePriority = lowPriority - 2;

                        config8.blackEval = 20;
                        config8.whiteEval = -20;

                        config8.blackCounterMoves.add(lowerRight);

                        config8.whiteCounterMoves.add(lowerRight);
                        configsList.add(config8);
                    }
                }
                // WW-W
                if (isCoordInArrayList(upperLeft,scenario.blackMoves)){
                    // BWW-W-
                    if (isCoordInArrayList(upperLeft2,scenario.whiteMoves) &&
                            !isCoordInArrayList(lowerRight3,scenario.blackMoves) &&
                            !isCoordInArrayList(lowerRight3,scenario.whiteMoves)){
                        PenteConfigurations config8 = new PenteConfigurations();
                        config8.confType = 1;
                        config8.direction = 45;
                        config8.isWhite = false;
                        config8.blackPriority = highPriority + 2;
                        config8.whitePriority = lowPriority + 2;

                        config8.blackEval = 5;
                        config8.whiteEval = -5;

                        config8.blackCounterMoves.add(lowerRight);
                        config8.blackCounterMoves.add(lowerRight3);

                        config8.whiteCounterMoves.add(lowerRight);
                        config8.whiteCounterMoves.add(lowerRight3);
                        configsList.add(config8);
                    }
                    // -WW-WB
                    if (isCoordInArrayList(lowerRight3,scenario.whiteMoves) &&
                            !isCoordInArrayList(upperLeft2,scenario.blackMoves) &&
                            !isCoordInArrayList(upperLeft,scenario.whiteMoves)){
                        PenteConfigurations config8 = new PenteConfigurations();
                        config8.confType = 1;
                        config8.direction = 45;
                        config8.isWhite = false;
                        config8.blackPriority = highPriority + 2;
                        config8.whitePriority = lowPriority + 2;

                        config8.blackEval = 5;
                        config8.whiteEval = -5;

                        config8.blackCounterMoves.add(lowerRight);
                        config8.blackCounterMoves.add(upperLeft2);

                        config8.whiteCounterMoves.add(lowerRight);
                        config8.whiteCounterMoves.add(upperLeft2);
                        configsList.add(config8);
                    }
                    // WW-WW
                    if (isCoordInArrayList(lowerRight3,scenario.blackMoves)){
                        PenteConfigurations config8 = new PenteConfigurations();
                        config8.confType = 1;
                        config8.direction = 45;
                        config8.isWhite = false;
                        config8.blackPriority = highPriority - 2;
                        config8.whitePriority = lowPriority - 2;

                        config8.blackEval = 20;
                        config8.whiteEval = -20;

                        config8.blackCounterMoves.add(lowerRight);

                        config8.whiteCounterMoves.add(lowerRight);
                        configsList.add(config8);
                    }
                    // WWW-W
                    if (isCoordInArrayList(upperLeft2,scenario.blackMoves)){
                        PenteConfigurations config8 = new PenteConfigurations();
                        config8.confType = 1;
                        config8.direction = 45;
                        config8.isWhite = false;
                        config8.blackPriority = highPriority - 2;
                        config8.whitePriority = lowPriority - 2;

                        config8.blackEval = 20;
                        config8.whiteEval = -20;

                        config8.blackCounterMoves.add(lowerRight);

                        config8.whiteCounterMoves.add(lowerRight);
                        configsList.add(config8);
                    }
                }

                // -WW-W-
                if ((isCoordInArrayList(upperLeft,scenario.blackMoves) &&
                        !isCoordInArrayList(lowerRight3,scenario.blackMoves) &&
                        !isCoordInArrayList(lowerRight3,scenario.whiteMoves) &&
                        !isCoordInArrayList(upperLeft2,scenario.blackMoves) &&
                        !isCoordInArrayList(upperLeft2,scenario.whiteMoves))){
                    PenteConfigurations config8 = new PenteConfigurations();
                    config8.confType = 1;
                    config8.direction = 45;
                    config8.isWhite = false;

                    config8.blackPriority = highPriority;
                    config8.whitePriority = lowPriority;

                    config8.blackEval = 10;
                    config8.whiteEval = -10;

                    config8.blackCounterMoves.add(upperLeft2);
                    config8.blackCounterMoves.add(lowerRight);
                    config8.blackCounterMoves.add(lowerRight3);

                    config8.whiteCounterMoves.add(upperLeft2);
                    config8.whiteCounterMoves.add(lowerRight);
                    config8.whiteCounterMoves.add(lowerRight3);
                    configsList.add(config8);
                }

                // -BW-WW-
                if ((isCoordInArrayList(upperLeft,scenario.blackMoves) &&
                        !isCoordInArrayList(lowerRight3,scenario.blackMoves) &&
                        !isCoordInArrayList(lowerRight3,scenario.whiteMoves) &&
                        !isCoordInArrayList(upperLeft2,scenario.blackMoves) &&
                        !isCoordInArrayList(upperLeft2,scenario.whiteMoves))){
                    PenteConfigurations config8 = new PenteConfigurations();
                    config8.confType = 1;
                    config8.direction = 45;
                    config8.isWhite = false;

                    config8.blackPriority = highPriority;
                    config8.whitePriority = lowPriority;

                    config8.blackEval = 10;
                    config8.whiteEval = -10;

                    config8.blackCounterMoves.add(upperLeft2);
                    config8.blackCounterMoves.add(lowerRight);
                    config8.blackCounterMoves.add(lowerRight3);

                    config8.whiteCounterMoves.add(upperLeft2);
                    config8.whiteCounterMoves.add(lowerRight);
                    config8.whiteCounterMoves.add(lowerRight3);
                    configsList.add(config8);
                }
            }
        }
        return configsList;
    }




    public ArrayList<PenteConfigurations> generateConfigs(Pente scenario){
        ArrayList<PenteConfigurations> configList = new ArrayList<PenteConfigurations>();
        for (int i = 0; i < scenario.whiteMoves.size(); i++){
            ArrayList<PenteConfigurations> partialConfList = findConfig1(true,scenario.whiteMoves.get(i),scenario, 8, 7, 2, -2);
            if (partialConfList != null){
                configList.addAll(partialConfList);
            }
            ArrayList<PenteConfigurations> partialConfList2 = findConfig2(true,scenario.whiteMoves.get(i),scenario, 6, 5, 5, -5);
            if (partialConfList2 != null){
                configList.addAll(partialConfList2);
            }
            ArrayList<PenteConfigurations> partialConfList3 = findConfig3(true,scenario.whiteMoves.get(i),scenario, 2, 1, 20, -20);
            if (partialConfList3 != null){
                configList.addAll(partialConfList3);
            }
            ArrayList<PenteConfigurations> partialConfList4 = findConfig4(true,scenario.whiteMoves.get(i),scenario, 8, 7, 2, -2);
            if (partialConfList4 != null){
                configList.addAll(partialConfList4);
            }
            ArrayList<PenteConfigurations> partialConfList5 = findConfig5(true,scenario.whiteMoves.get(i),scenario, 4, 3, 15, -15);
            if (partialConfList5 != null){
                configList.addAll(partialConfList5);
            }
            //All configs go here
        }
        for (int i = 0; i < scenario.blackMoves.size(); i++){
            //-BB- , WBB-
            ArrayList<PenteConfigurations> partialConfList8= findConfig1(false,scenario.blackMoves.get(i),scenario, 8, 7, 2, -2);
            if (partialConfList8 != null){
                configList.addAll(partialConfList8);
            }

            //-BBB- , WBBB-
            ArrayList<PenteConfigurations> partialConfList9 = findConfig2(false,scenario.blackMoves.get(i),scenario, 6, 5, 5, -5);
            if (partialConfList9 != null){
                configList.addAll(partialConfList9);
            }

            //-BBBB- , WBBBB-
            ArrayList<PenteConfigurations> partialConfList10 = findConfig3(false,scenario.blackMoves.get(i),scenario, 2, 1, 20, -20);
            if (partialConfList10 != null){
                configList.addAll(partialConfList10);
            }

            //-B-B-
            ArrayList<PenteConfigurations> partialConfList11 = findConfig4(false,scenario.blackMoves.get(i),scenario, 8, 7, 2, -2);
            if (partialConfList11 != null){
                configList.addAll(partialConfList11);
            }

            //-B-BB-, WB-BB-, -B-BBB-, -BB-BB-
            ArrayList<PenteConfigurations> partialConfList12 = findConfig5(false,scenario.blackMoves.get(i),scenario, 4, 3, 15, -15);
            if (partialConfList12 != null){
                configList.addAll(partialConfList12);
            }
            //All configs go here
        }
        return configList;
    }

    public int evaluateBoard(ArrayList<PenteConfigurations> configurationsList,Pente scenario){

        int evalScore = 0;
        if (scenario.isAIWhite){
/*			if (isWhite && !scenario.isGameInProgress){
				evalScore = -100;
			}
			if(!isWhite && !scenario.isGameInProgress){
				evalScore = 100;
			}*/
            //if (scenario.isGameInProgress){
            for (int i = 0; i < configurationsList.size(); i++){
                evalScore += configurationsList.get(i).whiteEval * Math.pow(10, Depth - 3);
                //System.out.println("congEval: " + configurationsList.get(i).whiteEval * Math.pow(10, Depth - 3));
            }
            evalScore += (scenario.whitesMatchPoints * 10 * Math.pow(10, Depth - 2));
            evalScore += (scenario.whitesTotalPoints * 15 * Math.pow(10, Depth - 2));
            evalScore += -(scenario.blacksMatchPoints * 10 * Math.pow(10, Depth - 2));
            evalScore += -(scenario.blacksTotalPoints * 15 * Math.pow(10, Depth - 2));


				/*evalScore += (scenario.whitesMatchPoints * 10);
				evalScore += (scenario.whitesTotalPoints * 15);
				evalScore += -(scenario.blacksMatchPoints * 10);
				evalScore += -(scenario.blacksTotalPoints * 15);*/
            //}
        }
        else {
/*			if (isWhite && !scenario.isGameInProgress){
				evalScore = 100;
			}
			if(!isWhite && !scenario.isGameInProgress){
				evalScore = -100;
			}*/
            //if (scenario.isGameInProgress){
            for (int i = 0; i < configurationsList.size(); i++){
                evalScore += configurationsList.get(i).blackEval * Math.pow(10, Depth - 3);
                //System.out.println("congEval: " + configurationsList.get(i).blackEval * Math.pow(10, Depth - 3));
            }
            evalScore += -(scenario.whitesMatchPoints * 10 * Math.pow(10, Depth - 2));
            evalScore += -(scenario.whitesTotalPoints * 15 * Math.pow(10, Depth - 2));
            evalScore += (scenario.blacksMatchPoints * 10 * Math.pow(10, Depth - 2));
            evalScore += (scenario.blacksTotalPoints * 15 * Math.pow(10, Depth - 2));
            //System.out.println("pointsEval: " + scenario.blacksMatchPoints * 10 * Math.pow(10, Depth - 2));
			/*evalScore += -(scenario.whitesMatchPoints * 10);
			evalScore += -(scenario.whitesTotalPoints * 15);
			evalScore += (scenario.blacksMatchPoints * 10);
			evalScore += (scenario.blacksTotalPoints * 15);*/
            //}
        }
		/*if (isWhite){
			if (!scenario.isGameInProgress){
				if (!scenario.isAIWhite){
					evalScore = 500;
				}
				else {evalScore = -500;}
			}
			else{
				if (!scenario.isAIWhite){
					for (int i = 0; i < configurationsList.size(); i++){
						evalScore += configurationsList.get(i).blackEval;
					}
				e

				if (scenario.isAIWhite){
					evalScore += (scenario.whitesMatchPoints * 300);
					evalScore += (scenario.whitesTotalPoints * 350);
					evalScore += -(scenario.blacksMatchPoints * 300);
					evalScore += -(scenario.blacksTotalPoints * 350);
				}
			}
		}
		else{
			if (!scenario.isGameInProgress){
				if (!scenario.isAIWhite){
					evalScore = -500;
				}
				else {evalScore = 500;}
			}
			else {
				for (int i = 0; i < configurationsList.size(); i++){
					evalScore += configurationsList.get(i).blackEval;
				}
				if (!scenario.isAIWhite){
					evalScore += (scenario.blacksMatchPoints * 300);
					evalScore += (scenario.blacksTotalPoints * 350);
					evalScore += -(scenario.whitesMatchPoints * 300);
					evalScore += -(scenario.whitesTotalPoints * 350);
				}
			}
		}*/
        return evalScore;
    }

    public int evaluateBoardMidMove(ArrayList<PenteConfigurations> configurationsList,Pente scenario){

        int evalScore = 0;
        if ((scenario.isPlayer1White && scenario.isPlayer1Turn) || (!scenario.isPlayer1White && !scenario.isPlayer1Turn)){
            if (scenario.isGameInProgress){
				/*for (int i = 0; i < configurationsList.size(); i++){
					evalScore += configurationsList.get(i).whiteEval;
				}*/
                evalScore += (scenario.whitesMatchPoints * Math.pow(10, Depth));
                evalScore += (scenario.whitesTotalPoints * Math.pow(15, Depth));
                evalScore += -(scenario.blacksMatchPoints * Math.pow(10, Depth));
                evalScore += -(scenario.blacksTotalPoints * Math.pow(15, Depth));

				/*evalScore += (scenario.whitesMatchPoints * 10);
				evalScore += (scenario.whitesTotalPoints * 15);
				evalScore += -(scenario.blacksMatchPoints * 10);
				evalScore += -(scenario.blacksTotalPoints * 15);*/
            }
        }
        else {
            //if (scenario.isGameInProgress){
			/*	for (int i = 0; i < configurationsList.size(); i++){
					evalScore += configurationsList.get(i).blackEval;
				}*/
            evalScore += -(scenario.whitesMatchPoints * Math.pow(10, Depth));
            evalScore += -(scenario.whitesTotalPoints * Math.pow(15, Depth));
            evalScore += (scenario.blacksMatchPoints * Math.pow(10, Depth));
            evalScore += (scenario.blacksTotalPoints * Math.pow(15, Depth));

			/*evalScore += -(scenario.whitesMatchPoints * 10);
			evalScore += -(scenario.whitesTotalPoints * 15);
			evalScore += (scenario.blacksMatchPoints * 10);
			evalScore += (scenario.blacksTotalPoints * 15);*/
            //}
        }
        return evalScore;
    }

    public Pente resolve_Standard_Tournament_5InARow_Board(int[] pieceCoords, Pente scenario){
        scenario.whitesMatchPoints = 0;
        scenario.blacksMatchPoints = 0;
        scenario.placePiece(pieceCoords);
        scenario.findCaptures(pieceCoords);
        scenario.calculateCurrentMatchScore();
        scenario.isMatchOver = scenario.determineIfMatchEnds();
        if (scenario.isMatchOver == true && !scenario.isSingleMatch) {
            scenario.setupNewMatch();
        }
        if ((scenario.isMatchOver == true && scenario.isSingleMatch) || scenario.determineIfEndOfGame() == true){
            scenario.isGameInProgress = false;

        }

        scenario.changeTurns(scenario.isPlayer1Turn);
        return scenario;

    }

    public Pente resolveKeryoBoard(int[] pieceCoords, Pente scenario){
        scenario.whitesMatchPoints = 0;
        scenario.blacksMatchPoints = 0;
        scenario.placePiece(pieceCoords);
        scenario.findKeryoCaptures(pieceCoords);
        scenario.findCaptures(pieceCoords);
        scenario.calculateCurrentMatchScore();
        scenario.isMatchOver = scenario.determineIfMatchEnds();
        if (scenario.isMatchOver == true && !scenario.isSingleMatch) {
            scenario.setupNewMatch();
        }
        if (scenario.isMatchOver == true && scenario.isSingleMatch){
            scenario.isGameInProgress = false;

        }
        if (scenario.determineIfEndOfGame() == true){
            scenario.isGameInProgress = false;
        }
        scenario.changeTurns(scenario.isPlayer1Turn);
        return scenario;

    }

    public Pente copyPenteObj(Pente originalPente){
        Pente newPente = new Pente();
        newPente.isStandardRules = originalPente.isStandardRules;
        newPente.isTournamentRules = originalPente.isTournamentRules;
        newPente.isKeryoRules = originalPente.isKeryoRules;
        newPente.isFreestyleRules = originalPente.isFreestyleRules;
        newPente.is5InARowRules = originalPente.is5InARowRules;
        newPente.isNoCapturesRules = originalPente.isNoCapturesRules;
        newPente.is19Board = originalPente.is19Board;
        newPente.is13Board = originalPente.is13Board;
        newPente.is2PlayerMode = originalPente.is2PlayerMode;
        newPente.is1PlayerMode = originalPente.is1PlayerMode;

        newPente.isSingleMatch = originalPente.isSingleMatch;
        newPente.is50PointsToWin = originalPente.is50PointsToWin;
        newPente.is21PointsToWin = originalPente.is21PointsToWin;

        newPente.isPlayer1White = originalPente.isPlayer1White;
        newPente.isAIWhite = originalPente.isAIWhite;
        newPente.player1Type = originalPente.player1Type;
        newPente.player2Type = originalPente.player2Type;
        newPente.isPlayer1Turn = originalPente.isPlayer1Turn;
        newPente.isGameInProgress = originalPente.isGameInProgress;
        newPente.isMatchOver = originalPente.isMatchOver;
        newPente.isWhiteWinner = originalPente.isWhiteWinner;
        newPente.isBlackWinner = originalPente.isBlackWinner;

		/*int bmp;
		int wmp;
		int btp;
		int wtp;
		int btc;
		int wtc;
		int mc;
		bmp = originalPente.blacksMatchPoints;
		wmp = originalPente.whitesMatchPoints;
		btp = originalPente.blacksTotalPoints;
		wtp = originalPente.whitesTotalPoints;
		btc = originalPente.blacksTotalCaptures;
		wtc = originalPente.whitesTotalCaptures;
		mc = originalPente.movesCount;

		newPente.blacksMatchPoints = bmp;
		newPente.whitesMatchPoints = wmp;
		newPente.blacksTotalPoints = btp;
		newPente.whitesTotalPoints = wtp;
		newPente.blacksTotalCaptures = btc;
		newPente.whitesTotalCaptures = wtc;
		newPente.movesCount = mc;*/

        newPente.blacksMatchPoints = originalPente.blacksMatchPoints;
        newPente.whitesMatchPoints = originalPente.whitesMatchPoints;
        newPente.blacksTotalPoints = originalPente.blacksTotalPoints;
        newPente.whitesTotalPoints = originalPente.whitesTotalPoints;
        newPente.blacksTotalCaptures = originalPente.blacksTotalCaptures;
        newPente.whitesTotalCaptures = originalPente.whitesTotalCaptures;
        newPente.movesCount = originalPente.movesCount;

        newPente.previousPlayer = originalPente.previousPlayer;

        for (int i = 0; i < originalPente.whiteMoves.size(); i++){
            newPente.whiteMoves.add(originalPente.whiteMoves.get(i));
        }
        for (int i = 0; i < originalPente.blackMoves.size(); i++){
            newPente.blackMoves.add(originalPente.blackMoves.get(i));
        }
        if (originalPente.is13Board){
            newPente.setBoardSize(1);
        }
        for (int i = 0; i < originalPente.gameBoard.length; i++){
            for (int j = 0; j < originalPente.gameBoard.length; j++){
                newPente.gameBoard[i][j] = originalPente.gameBoard[i][j];
            }
        }

        return newPente;
    }

}

