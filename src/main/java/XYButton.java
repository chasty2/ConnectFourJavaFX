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
	public boolean valid;
	// Integer to denote who pushed this button
	public Integer player;
	// Pointer to XYButton above this one
	public XYButton nextButton;
	
	// Constructor
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
		
		// Init Event Handler
		handler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// Get info on button pressed
				XYButton button = (XYButton) event.getSource();
				System.out.println("Player 1 has pressed " + GridPane.getColumnIndex(button) +  " " + GridPane.getRowIndex(button));
				// Press if valid move
				if (button.valid == true)
				{
					button.setStyle("-fx-background-color: blue;");
					// Set next button as a valid move
					if (nextButton != null)
					{
						nextButton.setValid();
					}
					nextButton.setValid();
					System.out.println("Valid Move");
				}
				else
				{
					System.out.println("Invalid Move");
				}
				
				// Set button above pressed button to valid
			
			}
		};
	}
	
	// TODO: getters/setters
	
	// Sets nex
	public void setNext(XYButton next)
	{
		this.nextButton = next;
	}
	
	// Sets valid
	public void setValid()
	{
		this.valid = true;
	}
	
	
}