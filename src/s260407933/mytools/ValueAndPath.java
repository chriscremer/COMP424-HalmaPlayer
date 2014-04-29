package s260407933.mytools;

import java.util.ArrayList;
//import halma.CCBoard;
import halma.CCMove;
//import boardgame.Board;
//import boardgame.Move;
//import boardgame.Player;



public class ValueAndPath 
{
	public CCMove startingMove;
	public int valueOfPath;
	public ArrayList<CCMove> path = new ArrayList<CCMove>();
	
	public ValueAndPath ()
	{
		startingMove = null;
		valueOfPath = -99;
		path = new ArrayList<CCMove>();
	}
	public ValueAndPath (CCMove firstMove, int value, ArrayList<CCMove> p)
	{
		startingMove = firstMove;
		valueOfPath = value;
		path = p;
	}
	
	public int getValue()
	{
		return valueOfPath;
	}
	
	public ArrayList<CCMove> getPath()
	{
		return path;
	}

	public ValueAndPath clone()
	{
		return new ValueAndPath(startingMove, valueOfPath, path);
	}
}
