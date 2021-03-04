import java.util.HashMap;
import java.util.Iterator;
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
	
	// Constructor
	public GameLogic()
	{
		// Game starts on playerOne's turn
		playerOne = 1;
		playerTwo = 2;
		currentPlayer = 1;
	}
	
	public void changeTurn()
	{
		if (currentPlayer == playerOne)
		{
			currentPlayer = playerTwo;
		}
		else
		{
			currentPlayer = playerOne;
		}
	}
	
	public Integer getCurrentPlayer()
	{
		return currentPlayer;
	}
	
	//Traverse gameBoard (Gridpane of XYButtons), returns button at (x,y)
	public XYButton getMove(GridPane board, Integer x, Integer y)
	{
		XYButton button = null;
		for (Node node : board.getChildren())
		{
			if(GridPane.getRowIndex(node) == y &&
					GridPane.getColumnIndex(node) == x)
			{
				button = (XYButton) node;
			}
		}
		return button;
	}
	
}