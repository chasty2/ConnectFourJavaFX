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
			String move = "Player " + currentPlayer +  " pressed " + button.getRow() 
					+  ", "	+ button.getColumn();
			moveList.add(move);
		}
		else
		{
			moveList.add(button.getRow() + " ," + button.getColumn() +" is an "
					+ "invalid move. Try again");
			moveList.add("Player " + currentPlayer + " is up");
		}
	}
	
	// Traverse gameBoard (Gridpane of XYButtons), returns button at (x,y)
	public XYButton getMove(GridPane board, Integer row, Integer column)
	{
		XYButton button = null;
		for (Node node : board.getChildren())
		{
			if(GridPane.getRowIndex(node) == row &&
					GridPane.getColumnIndex(node) == column)
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
	 * Clear gameBoard, unPress XYButtons, and reset game state
	 */
	public void newGame(GridPane board)
	{
		Integer[] lastMove = new Integer[2];
		while(moveList.size() > 1)
		{
			lastMove = pruneLogs();
		}
		
		/*
		XYButton button = null;
		for (Node node : board.getChildren())
		{
			button = (XYButton) node;
			if (button.getPlayer() != 0)
			{
				button.unPress();
			}
		}*/
		
		currentPlayer = playerOne;
	}
	
	/*
	 *  checkWin recursive helper functions
	 */
	public boolean _checkWinLeft(GridPane board, Integer count, XYButton button)
	{
		/*
		 *  Base Cases
		 */
		// Connect 4
		if (count == 4 && button.getPlayer() == getCurrentPlayer())
		{
			return true;
		}
		// Opponents piece
		else if (button.getPlayer() != getCurrentPlayer())
		{
			return false;
		}
		// Reached left boundary of grid
		else if (button.getColumn() == 0)
		{
			return false;
		}
		/*
		 * Recursive Case
		 */
		// Continue checking for win in left direction
		else if (button.getPlayer() == getCurrentPlayer())
		{
			count++;
			XYButton leftButton = this.getMove(board, button.getRow(), button.getColumn()-1);
			return _checkWinLeft(board, count, leftButton);
		}
		return false;
	}
	
	public boolean _checkWinRight(GridPane board, Integer count, XYButton button)
	{
		/*
		 *  Base Cases
		 */
		// Connect 4
		if (count == 4 && button.getPlayer() == getCurrentPlayer())
		{
			return true;
		}
		// Opponents piece
		else if (button.getPlayer() != getCurrentPlayer())
		{
			return false;
		}
		// Reached right boundary of grid
		else if (button.getColumn() == 6)
		{
			return false;
		}
		/*
		 * Recursive Case
		 */
		// Continue checking for win in left direction
		else if (button.getPlayer() == getCurrentPlayer())
		{
			count++;
			XYButton rightButton = this.getMove(board, button.getRow(), button.getColumn()+1);
			return _checkWinRight(board, count, rightButton);
		}
		return false;
	}
	
	public boolean _checkWinUp(GridPane board, Integer count, XYButton button)
	{
		/*
		 *  Base Cases
		 */
		// Connect 4
		if (count == 4 && button.getPlayer() == getCurrentPlayer())
		{
			return true;
		}
		// Opponents piece
		else if (button.getPlayer() != getCurrentPlayer())
		{
			return false;
		}
		// Reached upper boundary of grid
		else if (button.getRow() == 0)
		{
			return false;
		}
		/*
		 * Recursive Case
		 */
		// Continue checking for win in left direction
		else if (button.getPlayer() == getCurrentPlayer())
		{
			count++;
			XYButton upButton = this.getMove(board, button.getRow()-1, button.getColumn());
			return _checkWinUp(board, count, upButton);
		}
		return false;
	}
	
	public boolean _checkWinDown(GridPane board, Integer count, XYButton button)
	{
		/*
		 *  Base Cases
		 */
		// Connect 4
		if (count == 4 && button.getPlayer() == getCurrentPlayer())
		{
			return true;
		}
		// Opponents piece
		else if (button.getPlayer() != getCurrentPlayer())
		{
			return false;
		}
		// Reached upper boundary of grid
		else if (button.getRow() == 5)
		{
			return false;
		}
		/*
		 * Recursive Case
		 */
		// Continue checking for win in left direction
		else if (button.getPlayer() == getCurrentPlayer())
		{
			count++;
			XYButton downButton = this.getMove(board, button.getRow()+1, button.getColumn());
			return _checkWinDown(board, count, downButton);
		}
		return false;
	}
	
	public boolean _checkWinNW(GridPane board, Integer count, XYButton button)
	{
		/*
		 *  Base Cases
		 */
		// Connect 4
		if (count == 4 && button.getPlayer() == getCurrentPlayer())
		{
			return true;
		}
		// Opponents piece
		else if (button.getPlayer() != getCurrentPlayer())
		{
			return false;
		}
		// Reached upper boundary of grid
		else if (button.getRow() == 0 || button.getColumn() == 0)
		{
			return false;
		}
		/*
		 * Recursive Case
		 */
		// Continue checking for win in left direction
		else if (button.getPlayer() == getCurrentPlayer())
		{
			count++;
			XYButton nwButton = this.getMove(board, button.getRow()-1, button.getColumn()-1);
			return _checkWinNW(board, count, nwButton);
		}
		return false;
	}
	
	public boolean _checkWinNE(GridPane board, Integer count, XYButton button)
	{
		/*
		 *  Base Cases
		 */
		// Connect 4
		if (count == 4 && button.getPlayer() == getCurrentPlayer())
		{
			return true;
		}
		// Opponents piece
		else if (button.getPlayer() != getCurrentPlayer())
		{
			return false;
		}
		// Reached upper boundary of grid
		else if (button.getRow() == 0 || button.getColumn() == 6)
		{
			return false;
		}
		/*
		 * Recursive Case
		 */
		// Continue checking for win in left direction
		else if (button.getPlayer() == getCurrentPlayer())
		{
			count++;
			XYButton downButton = this.getMove(board, button.getRow()-1, button.getColumn()+1);
			return _checkWinNE(board, count, downButton);
		}
		return false;
	}
	
	public boolean _checkWinSW(GridPane board, Integer count, XYButton button)
	{
		/*
		 *  Base Cases
		 */
		// Connect 4
		if (count == 4 && button.getPlayer() == getCurrentPlayer())
		{
			return true;
		}
		// Opponents piece
		else if (button.getPlayer() != getCurrentPlayer())
		{
			return false;
		}
		// Reached lower boundary of grid
		else if (button.getRow() == 5 || button.getColumn() == 0)
		{
			return false;
		}
		/*
		 * Recursive Case
		 */
		// Continue checking for win in left direction
		else if (button.getPlayer() == getCurrentPlayer())
		{
			count++;
			XYButton downButton = this.getMove(board, button.getRow()+1, button.getColumn()-1);
			return _checkWinSW(board, count, downButton);
		}
		return false;
	}
	
	public boolean _checkWinSE(GridPane board, Integer count, XYButton button)
	{
		/*
		 *  Base Cases
		 */
		// Connect 4
		if (count == 4 && button.getPlayer() == getCurrentPlayer())
		{
			return true;
		}
		// Opponents piece
		else if (button.getPlayer() != getCurrentPlayer())
		{
			return false;
		}
		// Reached lower boundary of grid
		else if (button.getRow() == 5 || button.getColumn() == 6)
		{
			return false;
		}
		/*
		 * Recursive Case
		 */
		// Continue checking for win in left direction
		else if (button.getPlayer() == getCurrentPlayer())
		{
			count++;
			XYButton downButton = this.getMove(board, button.getRow()+1, button.getColumn()+1);
			return _checkWinSE(board, count, downButton);
		}
		return false;
	}

	/*
	 *  Recursively search in cardinal and intercardinal directions for
	 *  4 consecutive pieces of one color 
	 */
	public boolean checkWin(GridPane board, XYButton button)
	{
		// Cardinal Directions
		if (_checkWinLeft(board,1,button) || _checkWinRight(board,1,button) ||
				_checkWinUp(board,1,button) || _checkWinDown(board,1,button))
		{
			return true;
		}
		// Intercardinal Directions
		else if (_checkWinNW(board,1,button) || _checkWinNE(board,1,button) ||
				_checkWinSW(board,1,button) || _checkWinSE(board,1,button))
		{
			return true;
		}
		
		return false;
	}

}