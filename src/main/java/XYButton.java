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
import javafx.stage.Stage;
import javafx.util.Duration;

public class XYButton extends Button
{
	// X,Y coordinates of button in GridPane
	private Integer x;
	private Integer y;
	// Event Handler, will move to GameScene.java
	public EventHandler<ActionEvent> handler;
	// Boolean for valid move
	private boolean valid;
	// Integer to denote who pushed this button
	private Integer player;
	// Pointer to XYButton above this one
	private XYButton nextButton;
	
	/*
	 *  Constructor
	 */
	public XYButton(Integer newX, Integer newY) 
	{
		// Set next to null. Will be set on GridPane Init
		nextButton = null;
		// Set x,y coordinates
		x = newX;
		y = newY;
		// Set player as 0. Reset as 1 or 2 based on who presses this button
		player = 0;
		// Set as valid move if button is on the bottom of the GridPane
		if (this.y == 5)
		{
			this.valid = true;
		}
		else
		{
			this.valid = false;
		}
	}
	
	/*
	 *  Getters
	 */
	public Integer getX()
	{
		return this.x;
	}
	
	public Integer getY()
	{
		return this.y;
	}
	
	public boolean getValid()
	{
		return this.valid;
	}
	
	public Integer getPlayer()
	{
		return this.player;
	}
	
	public XYButton getNext()
	{
		return this.nextButton;
	}
	
	
	/*
	 * Setters
	 */
	public void setX(Integer newX)
	{
		this.x = newX;
	}
	
	public void setY(Integer newY)
	{
		this.y = newY;
	}
	
	// Marks this XYButton as a valid move
	public void setValid()
	{
		this.valid = true;
	}
	
	// Sets a button as having been pushed by player 1 or 2
	public void setPlayer(Integer currentPlayer)
	{
		this.player = currentPlayer;
	}
	
	// Sets nextButton to the XYButton above this one
	public void setNext(XYButton next)
	{
		this.nextButton = next;
	}
	
	// Sets color of button based on player that pressed it
	public void setColor(Integer currentPlayer)
	{
		if (currentPlayer == 1)
		{
			this.setStyle("-fx-background-color: red;");
		}
		else
		{
			this.setStyle("-fx-background-color: blue;");
		}
	}

}
