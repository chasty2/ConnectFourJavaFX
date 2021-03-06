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
	private Integer row;
	private Integer column;
	// Event Handler, will move to GameScene.java
	//public EventHandler<ActionEvent> handler;
	// Boolean for valid move
	private boolean valid;
	// Integer to denote who pushed this button
	private Integer player;
	// Pointer to XYButton above this one
	private XYButton nextButton;
	
	/*
	 *  Constructor
	 */
	public XYButton(Integer newRow, Integer newColumn) 
	{
		// Set next to null. Will be set on GridPane Init
		nextButton = null;
		// Set x,y coordinates
		row = newRow;
		column = newColumn;
		// Set player as 0. Reset as 1 or 2 based on who presses this button
		player = 0;
		// Set as valid move if button is on the bottom of the GridPane
		if (this.row == 5)
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
	public Integer getRow()
	{
		return this.row;
	}
	
	public Integer getColumn()
	{
		return this.column;
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
	public void setRow(Integer newRow)
	{
		this.row = newRow;
	}
	
	public void setY(Integer newColumn)
	{
		this.column = newColumn;
	}
	
	// Marks this XYButton as a valid move
	public void setValid(boolean bool)
	{
		this.valid = bool;
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
	
	// Front-end for Getters/Setters of XYButton
	public void pushButton(Integer currentPlayer)
	{
		// Disable button
		this.valid = false;
		// Set move 'above' current one as valid
		if (this.getNext() != null)
		{
			this.getNext().setValid(true);
		}
		this.setColor(currentPlayer);
		this.setPlayer(currentPlayer);
	}
	
	// Used in reverse move
	public void unPress()
	{
		// enable button
		this.valid = true;
		// Set move 'above' current one as valid
		if (this.getNext() != null)
		{
			this.getNext().setValid(false);
		}
		this.setStyle("-fx-background-color: grey;");
		this.setPlayer(0);
	}

}
