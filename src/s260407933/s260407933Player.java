package s260407933;

import halma.CCBoard;
import halma.CCMove;

import java.util.ArrayList;
import java.util.Random;
//import java.util.Vector;


import boardgame.Board;
import boardgame.Move;
import boardgame.Player;
import s260407933.mytools.ValueAndPath;

//my halma player

public class s260407933Player extends Player
{
    Random rand = new Random();
    
    /** Provide a default public constructor */
    public s260407933Player() { super("260407933"); }
    public s260407933Player(String s) { super(s); }
    
    public Board createBoard() { return new CCBoard(); }
    
    //heuristic grids for all four players
    int[][] boardValuesP0 = {	
    							{20,18,18,18,15,15,15,15,15,15,15,15,15,15,15,15},
								{18,18,17,17,14,14,14,14,14,14,14,14,14,14,14,14},
								{18,17,16,13,13,13,13,13,13,13,13,13,13,13,13,13},
								{18,17,13,12,12,12,12,12,12,12,12,12,12,12,12,12},
								{15,14,13,12,11,11,11,11,11,11,11,11,11,11,11,11},
								{15,14,13,12,11,10,10,10,10,10,10,10,10,10,10,10},
								{15,14,13,12,11,10,9,9,9,9,9,9,9,9,9,9},
								{15,14,13,12,11,10,9,8,8,8,8,8,8,8,8,8},
								{15,14,13,12,11,10,9,8,7,7,7,7,7,7,7,7},
								{15,14,13,12,11,10,9,8,7,6,6,6,6,6,6,6},
								{15,14,13,12,11,10,9,8,7,6,5,5,5,5,5,5},
								{15,14,13,12,11,10,9,8,7,6,5,4,4,4,4,4},
								{15,14,13,12,11,10,9,8,7,6,5,4,3,3,2,2},
								{15,14,13,12,11,10,9,8,7,6,5,4,3,1,1,1},
								{15,14,13,12,11,10,9,8,7,6,5,4,2,1,0,0},
    							{15,14,13,12,11,10,9,8,7,6,5,4,2,1,0,-1}
							};

    int[][] boardValuesP1 = {	
    							{15,14,13,12,11,10,9,8,7,6,5,4,2,1,0,-1},
    							{15,14,13,12,11,10,9,8,7,6,5,4,2,1,0,0},
    							{15,14,13,12,11,10,9,8,7,6,5,4,3,1,1,1},
    							{15,14,13,12,11,10,9,8,7,6,5,4,3,3,2,2},
    							{15,14,13,12,11,10,9,8,7,6,5,4,4,4,4,4},
    							{15,14,13,12,11,10,9,8,7,6,5,5,5,5,5,5},
    							{15,14,13,12,11,10,9,8,7,6,6,6,6,6,6,6},
    							{15,14,13,12,11,10,9,8,7,7,7,7,7,7,7,7},
    							{15,14,13,12,11,10,9,8,8,8,8,8,8,8,8,8},
    							{15,14,13,12,11,10,9,9,9,9,9,9,9,9,9,9},
    							{15,14,13,12,11,10,10,10,10,10,10,10,10,10,10,10},
    							{15,14,13,12,11,11,11,11,11,11,11,11,11,11,11,11},
    							{18,17,13,12,12,12,12,12,12,12,12,12,12,12,12,12},
    							{18,17,16,13,13,13,13,13,13,13,13,13,13,13,13,13},
    							{18,18,17,17,14,14,14,14,14,14,14,14,14,14,14,14},
    							{20,18,18,18,15,15,15,15,15,15,15,15,15,15,15,15}
    						};
    
    int[][] boardValuesP2 = {
								{15,15,15,15,15,15,15,15,15,15,15,15,18,18,18,20},
    							{14,14,14,14,14,14,14,14,14,14,14,14,17,17,18,18},
    							{13,13,13,13,13,13,13,13,13,13,13,13,13,16,17,18},
    							{12,12,12,12,12,12,12,12,12,12,12,12,12,13,17,18},
    							{11,11,11,11,11,11,11,11,11,11,11,11,12,13,14,15},
    							{10,10,10,10,10,10,10,10,10,10,10,11,12,13,14,15},
    							{9,9,9,9,9,9,9,9,9,9,10,11,12,13,14,15},
    							{8,8,8,8,8,8,8,8,8,9,10,11,12,13,14,15},
    							{7,7,7,7,7,7,7,7,8,9,10,11,12,13,14,15},
    							{6,6,6,6,6,6,6,7,8,9,10,11,12,13,14,15},
    							{5,5,5,5,5,5,6,7,8,9,10,11,12,13,14,15},
    							{4,4,4,4,4,5,6,7,8,9,10,11,12,13,14,15},
    							{2,2,3,3,4,5,6,7,8,9,10,11,12,13,14,15},
    							{1,1,1,3,4,5,6,7,8,9,10,11,12,13,14,15},
    							{0,0,1,2,4,5,6,7,8,9,10,11,12,13,14,15},
    							{-1,0,1,2,4,5,6,7,8,9,10,11,12,13,14,15}
    						};
    
    int[][] boardValuesP3 = {
								{-1,0,1,2,4,5,6,7,8,9,10,11,12,13,14,15},
								{0,0,1,2,4,5,6,7,8,9,10,11,12,13,14,15},
								{1,1,1,3,4,5,6,7,8,9,10,11,12,13,14,15},
								{2,2,3,3,4,5,6,7,8,9,10,11,12,13,14,15},
								{4,4,4,4,4,5,6,7,8,9,10,11,12,13,14,15},
								{5,5,5,5,5,5,6,7,8,9,10,11,12,13,14,15},
								{6,6,6,6,6,6,6,7,8,9,10,11,12,13,14,15},
								{7,7,7,7,7,7,7,7,8,9,10,11,12,13,14,15},
								{8,8,8,8,8,8,8,8,8,9,10,11,12,13,14,15},
								{9,9,9,9,9,9,9,9,9,9,10,11,12,13,14,15},
								{10,10,10,10,10,10,10,10,10,10,10,11,12,13,14,15},
								{11,11,11,11,11,11,11,11,11,11,11,11,12,13,14,15},
								{12,12,12,12,12,12,12,12,12,12,12,12,12,13,17,18},
								{13,13,13,13,13,13,13,13,13,13,13,13,13,16,17,18},
								{14,14,14,14,14,14,14,14,14,14,14,14,17,17,18,18},
								{15,15,15,15,15,15,15,15,15,15,15,15,18,18,18,20}
							};

    //chooseMove is called by the server
    public Move chooseMove(Board theboard) 
    {
    	//start the timer, this is used so that I don't lose by timeout
    	long start_time = System.nanoTime();
    	
    	//get all the legal moves for this turn
    	CCBoard board = (CCBoard) theboard;
    	ArrayList<CCMove> moves = board.getLegalMoves();
    	CCMove toReturn = null;
    	int myID = board.getTurn();

    	//set the initial best values and lengths to large positive or negative numbers (99)
    	double bestTotalValue = -99;
    	int myPathLength = 99;
    	int bestTotalLength = 99;
    	
    	//for all the legal moves of this turn
    	for (CCMove m : moves)
    	{
    		//debug
    		//System.out.println("move im looking at" + m.toPrettyString());
    		
    		//check the time, return the best result if 850 milliseconds have already passed
    		long end_time = System.nanoTime();
    	  	double difference = (end_time - start_time)/1e6;
    	  	if(difference > 850)
    	  	{
    	  		return toReturn;
    	  	}
    	  	
    		//this is a restriction that ignores the moves that are worse that -1
    	  	//this occurs when there are more than 43 total moves to look at
    	  	//these are completely arbitrary numbers
    	  	//the purpose of this restriction is to increase the time to look at moves that are more likely
    	  	//to be optimal
    		if (moves.size() > 43 && moveValue(m, m.getPlayer_id()) < -1)
    		{
    			continue;
    		}
    		
    		//copy the board so that I can refer back to it during the next iteration
    		//execute the move on the copy
    		CCBoard b = (CCBoard) board.clone();
    		b.move(m);
    		
    		//this will store the best path for the move that was just executed
    		ValueAndPath myPath = new ValueAndPath();
    		
    		//if the move was a hop, ie. it is still your turn
    		//then add that move to the list of already used moves
    		//then call ValueAndPathFunction, which will return the best path 
    		if (m.isHop())
    		{
    			ValueAndPath alreadyUsedForThisMove = new ValueAndPath();
    			alreadyUsedForThisMove.path.add(m);
        		myPath = ValueAndPathFunction(b, alreadyUsedForThisMove);
        		//the value of the path is the path value plus the move that began it
        		myPath.valueOfPath = myPath.valueOfPath + moveValue(m, m.getPlayer_id());
        		//if the path does not end with a null move, add a null move to the end
        		if (myPath.path.get(myPath.path.size()-1).getFrom() != null)
        		{
        			CCMove nullMove = new CCMove (m.getPlayer_id(), null, null);
        			myPath.path.add(nullMove);
        		}
        		
        		
        		
           		//debug
        		//for (CCMove g : myPath.path)
        		//{
        		//	System.out.println("	" + g.toPrettyString());
        		//}
        		
     		
        		
        		//length of the path is the size of the list
        		myPathLength = myPath.path.size();
        		//execute the path so that it will now be the next player's turn
        		executePath(b, myPath);
    		}
    		//if the move was not a hop. ie. a single move or a null move
    		//then create a pathObject with only the value of the move, no path
    		//if its a single jump then length is one, if its a null move then length is 0
    		else
    		{	
    			myPath = new ValueAndPath(null, moveValue(m, m.getPlayer_id()), null);
    			if (m.getFrom() == null)
    			{
    				myPathLength = 0;
    			}
    			else
    			{
    				myPathLength = 1;
    			}
    			
    			//debug
        		//System.out.println("	not my turn");

    		}
    		
    		
    		//debug
    		//System.out.println("myPath value: " + myPath.getValue());
    		
    		
    		//check the time, return the best result if 850 milliseconds have already passed
    		end_time = System.nanoTime();
    	  	difference = (end_time - start_time)/1e6;
    	  	if(difference > 850)
    	  	{
    	  		return toReturn;
    	  	}
    	  	
    	  	
    		
    	  	ValueAndPath emptyPath1 = new ValueAndPath();
    		ValueAndPath opponentPath1 = ValueAndPathFunction(b, emptyPath1);
    		//debug
    		//System.out.println("opponentPath1 value: " + opponentPath1.getValue());
    		executePath(b, opponentPath1);
    		
    		
    	/*	
    		//skip turn
    		if(b.getLegalMoves().get(0).isHop())
    		{
    			b.move(b.getLegalMoves().get(0));
    			b.move(b.getLegalMoves().get(0));
    		}
    		else
    		{
    			b.move(b.getLegalMoves().get(0));
    		}
    	*/
    		
    		
    		//ValueAndPath opponentPath2 = ValueAndPathFunction(b, emptyPath);
    		//System.out.println("opponentPath2 value: " + opponentPath2.getValue());
    		//executePath(b, opponentPath2);
    		
    		//skip turn
    		if(b.getLegalMoves().get(0).isHop())
    		{
    			b.move(b.getLegalMoves().get(0));
    			b.move(b.getLegalMoves().get(0));
    		}
    		else
    		{
    			b.move(b.getLegalMoves().get(0));
    		}
    		
    		//ValueAndPath teammatePath = ValueAndPathFunction(b, emptyPath);
    		//System.out.println("teammatePath value: " + teammatePath.getValue());
    		//executePath(b, teammatePath);
    		
    		//skip turn of 3rd player after you
    		if(b.getLegalMoves().get(0).isHop())
    		{
    			b.move(b.getLegalMoves().get(0));
    			b.move(b.getLegalMoves().get(0));
    		}
    		else
    		{
    			b.move(b.getLegalMoves().get(0));
    		}
    		
    		
    		//check the time, return the best result if 850 milliseconds have already passed
    		end_time = System.nanoTime();
    	  	difference = (end_time - start_time)/1e6;
    	  	if(difference > 850)
    	  	{
    	  		return toReturn;
    	  	}
    		
    		
    		//it is now my second turn. find the best path at this turn, given all the previous moves
        	ValueAndPath emptyPath = new ValueAndPath();
    		ValueAndPath mySecondPath = ValueAndPathFunction(b, emptyPath);
    		
    		//debug
    		//System.out.println("mySecondPath value: " + mySecondPath.getValue());

    		//now calculate the value of all the best paths, given my first chosen move
    		//player 0 and 2 have two opponents after them, then a teammate
    		//player 1 and 3 have a teammate playing after them followed by two opponents
    		double theCalculation;
    		if (myID == 0 || myID == 2)
    		{
    			//double theCalculation =  ((0.5)*myPath.getValue())-((0.2)*opponentPath1.getValue())-((0.1)*opponentPath2.getValue())+((0.05)*teammatePath.getValue())+((0.15)*mySecondPath.getValue());
        		theCalculation =  ((0.5)*myPath.getValue())-((0.201)*opponentPath1.getValue())+((0.15)*mySecondPath.getValue());
        		//theCalculation =  ((0.5)*myPath.getValue())+((0.251)*mySecondPath.getValue());
    		}
    		else
    		{
    			//double theCalculation =  ((0.5)*myPath.getValue())+((0.2)*opponentPath1.getValue())-((0.1)*opponentPath2.getValue())-((0.05)*teammatePath.getValue())+((0.15)*mySecondPath.getValue());
        		theCalculation =  ((0.5)*myPath.getValue())+((0.201)*opponentPath1.getValue())+((0.15)*mySecondPath.getValue());
        		//theCalculation =  ((0.5)*myPath.getValue())+((0.251)*mySecondPath.getValue());
    		}
    		
      		//debug
    		//System.out.println("calculation value: " + theCalculation);
    		
    		//if the calculation for this move is the best, set it to the best
    		//also set the move to the move that should be returned
    		if (theCalculation > bestTotalValue)
    		{
    			bestTotalValue = theCalculation;
    			bestTotalLength = myPathLength;
    			toReturn = m;
    		}
    		//if the value of this move's calculation is equal to the best, then compare the path lengths
    		//shorter path length is better because it meams its closer to the optimal position
    		if (theCalculation == bestTotalValue && myPathLength < bestTotalLength)
    		{
    			bestTotalValue = theCalculation;
    			bestTotalLength = myPathLength;
    			toReturn = m;
    		}
    		//if equal value and equal length, pick a random one
    		if (theCalculation == bestTotalValue && myPathLength == bestTotalLength)
    		{
    			bestTotalValue = theCalculation;
    			bestTotalLength = myPathLength;
    			boolean pickTheNewOne = rand.nextBoolean();
    			if (pickTheNewOne)
    			{
    				toReturn = m;
    			}
    		}
    	}
    	
    	
    	//return the move that leads to the best calculation/length path
        return toReturn;
        
     
        
    }
    
    
    
    
    //this method returns the value of the move
    //it uses the boardValues function to get the value for each position depending on the player
    //the value is m.from minus m.to
    private int moveValue(CCMove m, int playerNumber)
    {
    	int moveValue;
    	if (m.getTo() == null)
    	{
    		moveValue = 0;
    	}
    	else
    	{
    		moveValue = boardValues(playerNumber)[(int) m.getFrom().getX()][(int) m.getFrom().getY()] - boardValues(playerNumber)[(int) m.getTo().getX()][(int) m.getTo().getY()];
    	}
    	return moveValue;
    }
    
    
    //returns the 2D array of the specified player
    private int[][] boardValues(int x)
    {
        int[][] boardValues = null;
        
        switch (x)
        {
        	case 0: boardValues = boardValuesP0;
        	break;
        	case 1: boardValues = boardValuesP1;
        	break;
        	case 2: boardValues = boardValuesP2;
        	break;
        	case 3: boardValues = boardValuesP3;
        	break;
        	default: break;
        }
        return boardValues;
    }
    
  
    //calls the move function on the current board for each move that is in the list
    //it makes sure that at the end of the execution, it is not the same person's turn
    //if it still is, then execute a null move to end his turn
    private void executePath(CCBoard b, ValueAndPath path)
    {
    	int myID = b.getTurn();
    	
        for(CCMove m : path.getPath())
        {
        	b.move(m);
        }
        
        if (myID == b.getTurn())
        {
        	CCMove nullMove = new CCMove (myID, null, null);
        	b.move(nullMove);
        }
    }
    
   
    
    private ValueAndPath makePath(CCBoard tempB, ValueAndPath currentPath, int playerNumber, ValueAndPath bestPath, ValueAndPath alreadyVisited)
    {
    	//get all the moves from the given board
    	ArrayList<CCMove> tempM = tempB.getLegalMoves();
    	
    	
    	//if it is no longer the player's turn ie his last move ended his turn
    	//or the current path is worse than -2 (efficiency)
    	//then check to see if the current path is better than the best
    	//finaly remove the last move and its value from the current
    	if (tempM.get(0).getPlayer_id() != playerNumber || currentPath.getValue() < -2)
    	{
    		if(currentPath.valueOfPath > bestPath.valueOfPath)
    		{
    			bestPath.path = new ArrayList<CCMove>(currentPath.path);
    			bestPath.valueOfPath = currentPath.valueOfPath;
    		}
    		if(currentPath.valueOfPath == bestPath.valueOfPath)
    		{
    			boolean pickTheNewOne = rand.nextBoolean();
    			if (pickTheNewOne)
    			{
    				bestPath.path = new ArrayList<CCMove>(currentPath.path);
        			bestPath.valueOfPath = currentPath.valueOfPath;
    			}
    		}
    		
    		currentPath.valueOfPath = currentPath.valueOfPath - moveValue(currentPath.path.get(currentPath.path.size()-1), (currentPath.path.get(currentPath.path.size()-1)).getPlayer_id());
    		currentPath.path.remove((currentPath.path.size())-1); 
    	}
    	
    	//if its still the current player's turn
    	else
    	{
    		//for all his available moves
    		for (CCMove m : tempM)
            {		
    			//skip the already visited moves
        		if (visited(m, alreadyVisited))
        		{
        			continue;
        		}
        		
        		//add the value and the move to the current 
    			int moveValue = moveValue(m, playerNumber);
        		currentPath.valueOfPath = moveValue + currentPath.getValue();
        		currentPath.path.add(m);
        		
        		//also add it to the already used list
        		alreadyVisited.path.add(m);
        		//clone the board
    			CCBoard temptempB = (CCBoard) tempB.clone();
        		temptempB.move(m);
        		//recursion on the new board with new current values and alreadyvisited list
        		//it will change the bestPath as it goes along
        		makePath(temptempB, currentPath, playerNumber, bestPath, alreadyVisited);

            }
    		//backtracking
    		//remove the move that we just did from the current
    		currentPath.valueOfPath = currentPath.valueOfPath - moveValue(currentPath.path.get(currentPath.path.size()-1), (currentPath.path.get(currentPath.path.size()-1)).getPlayer_id());
    		currentPath.path.remove((currentPath.path.size())-1);
    	}
    	return bestPath;
    }
    
    
    //checks if the given move's destination is found in the given list's moves' origin
    //ie. visited is true if the move goes to somewhere that it has already been
    //returns false it has not
    private boolean visited(CCMove m, ValueAndPath alreadyBeen)
    {
    	boolean visited = false;
		for (CCMove z : alreadyBeen.getPath())
		{
			if (z.getTo() == null)
			{
				continue;
			}
    		if (m.getTo() == null)
    		{
    			continue;
    		}
			if (m.getTo().equals(z.getFrom()))
			{
				visited = true;
				break;
			}
		}
		
		return visited;
    }
    
    
    //this function will return the best path given a board(player turn and piece positions)
    // and the moves that have been already used.
    private ValueAndPath ValueAndPathFunction(CCBoard theboard, ValueAndPath alreadyUsedForThisMove2)
    {
    	//copy the board so that the original is not modified
    	CCBoard b = (CCBoard) theboard.clone();
    	
    	//to find out if a move has already been used
    	//check the size of the path given
		boolean usedSent = false;
		CCMove a = null;
		if (alreadyUsedForThisMove2.path.size() == 1)
		{
			a = alreadyUsedForThisMove2.path.get(0);
			usedSent = true;
		}
		
		//bestPath stores the current best path
        ValueAndPath bestPath = new ValueAndPath();
        int myID = b.getTurn();
        //boolean to check if there has been two paths with equal value
        boolean equalBestMove = false;
        ArrayList<ValueAndPath> equalBestPaths = new ArrayList<ValueAndPath>();
        
        //for each move that is available at this state of the board
        for (CCMove m : b.getLegalMoves())
        {
        	//if the move that we are checking has already been used, skip it
        	if (visited(m, alreadyUsedForThisMove2))
    		{
    			continue;
    		}
        	
        	//if we started this method with a used that was already used, then add it to the list
        	ValueAndPath alreadyUsedForThisMove = new ValueAndPath(); 
        	if (usedSent)
        	{
        		alreadyUsedForThisMove.path.add(a);
        	}

        	//add the current move that we about to use to the already used list
        	alreadyUsedForThisMove.path.add(m);
        	
        	//make a path object for 'current'
        	ValueAndPath beginningOfPathForThisMove = new ValueAndPath();
        	beginningOfPathForThisMove.startingMove = m;
        	beginningOfPathForThisMove.valueOfPath =  moveValue(m, b.getTurn());
        	beginningOfPathForThisMove.path.add(m);

        	//same as last object, but it will be used to store the best path
        	ValueAndPath bestPathList = new ValueAndPath();
        	bestPathList.startingMove = m;
        	bestPathList.valueOfPath =  moveValue(m, b.getTurn());
        	bestPathList.path.add(m);
        	
        	//clone the board so that we can return to the old board next iteration
        	CCBoard tempB = (CCBoard) b.clone();
        	//execute the move
        	tempB.move(m);
        	//get the best value and path given this move
        	ValueAndPath bestPathForThisMove = makePath(tempB, beginningOfPathForThisMove, myID, bestPathList, alreadyUsedForThisMove);
        	
        	//compare the best to the current value
        	//also, if it better than the best than clear the best list and this path
        	if(bestPathForThisMove.getValue() > bestPath.getValue())
        	{
        		bestPath.valueOfPath = bestPathForThisMove.valueOfPath;
        		bestPath.path = new ArrayList<CCMove>(bestPathForThisMove.path);
        		equalBestPaths.clear();
        		equalBestPaths.add(bestPathForThisMove);
        		equalBestMove = false;
        	}
        	//if it is equal to the best, add it to the best list and set the boolean to true
        	if (bestPathForThisMove.getValue() == bestPath.getValue())
        	{
        		equalBestMove = true;
        		equalBestPaths.add(bestPathForThisMove);
        	}
        }
    	
    	//if there are multiple equal path values, return the one with the shortest length 
        if (equalBestMove == true)
		{	
			int shortestDist = 99;
			ValueAndPath toReturn = new ValueAndPath();
			boolean equalLengths = false;
			ArrayList<ValueAndPath> sameLengthPaths = new ArrayList<ValueAndPath>();
			for (ValueAndPath p : equalBestPaths)
			{
				if (p.path.size() < shortestDist)
				{
					toReturn = p;
					shortestDist = p.path.size();
					sameLengthPaths.clear();
					sameLengthPaths.add(p);
					equalLengths = false;
				}
				if (p.path.size() == shortestDist)
				{
					equalLengths = true;
					sameLengthPaths.add(p);
				}
			}
			
			//if there are paths with equal lengths, return a random one
			if (equalLengths)
			{
				Random rand = new Random();
				return (sameLengthPaths.get(rand.nextInt(sameLengthPaths.size())));
			}
			
			return toReturn;
			
		}
    	
    	return bestPath;
    }
    
} // End class