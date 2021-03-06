import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.function.Function;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;
import java.util.regex.*;

/*
 * A Class to manage the logic of the Connect 4 Game
 * 
 * e.g Sets turns, checks for win conditions, etc.
 */
public class GameLogic
{
	private final Integer playerOne;
	private final Integer playerTwo;
	private static Integer currentPlayer;
	private ObservableList<String> moveList; 
	
	// Constructor
	public GameLogic()
	{
		// Game starts on playerOne's turn
		playerOne = 1;
		playerTwo = 2;
		currentPlayer = 1;
		moveList = FXCollections.observableArrayList("Player 1 is up");
	}
	
	public void changeTurn()
	{
		if (currentPlayer == playerOne)
		{
			currentPlayer = playerTwo;
			moveList.add("Player 2 is up");
		}
		else
		{
			currentPlayer = playerOne;
			moveList.add("Player 1 is up");
		}
	}
	
	public Integer getCurrentPlayer()
	{
		return currentPlayer;
	}
	
	public ObservableList<String> getMoveList()
	{
		return moveList;
	}
	
	/*
	 *  Adds move to log, returns reversed observable list to gameScene
	 *  Updates the game's event logs as if they were a stack of Strings
	 */
	public void updateLogs(XYButton button)
	{
		if (button.getValid() == true)
		{
			String move = "Player " + currentPlayer +  " pressed " + button.getX() 
					+  ", "	+ button.getY();
			moveList.add(move);
		}
		else
		{
			moveList.add(button.getX() + " ," + button.getY() +" is an "
					+ "invalid move. Try again");
			moveList.add("Player " + currentPlayer + " is up");
		}
	}
	
	// Traverse gameBoard (Gridpane of XYButtons), returns button at (x,y)
	public XYButton getMove(GridPane board, Integer x, Integer y)
	{
		XYButton button = null;
		for (Node node : board.getChildren())
		{
			if(GridPane.getRowIndex(node) == x &&
					GridPane.getColumnIndex(node) == y)
			{
				button = (XYButton) node;
			}
		}
		return button;
	}
	
	/*
	 *  Iterate through moveList in reverse, pruning event logs from previous
	 *  turn. Return array with [x,y] coordinates of last valid move
	 */
	public Integer[] pruneLogs()
	{
		int i = moveList.size();
		String s;
		Pattern validMove = Pattern.compile("^Player \\d{1} pressed (\\d{1}), (\\d{1})$");
		Matcher matchMove;
		Integer[] lastMove = new Integer[2];
		
		ListIterator<String> logIter = moveList.listIterator(moveList.size());
		while(logIter.hasPrevious())
		{
			s = logIter.previous();
			matchMove = validMove.matcher(s);
			i--;
			
			if(matchMove.matches())
			{
				lastMove[0] = Integer.parseInt(matchMove.group(1));
				lastMove[1] = Integer.parseInt(matchMove.group(2));
				System.out.println("Found " + lastMove[0] + ", " + lastMove[1]);
				break;
			}
		}
		moveList.remove(i-1, moveList.size());
		return lastMove;
	}
	
	
	/*
	 *  Recursively search in cardinal and intercardinal directions for
	 *  4 consecutive pieces of one color 
	 */
	

}