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

public class XYButton extends Button{
	private int x;
	private int y;
	public EventHandler<ActionEvent> handler;
	public boolean valid;
	public int player;
	
	// Constructor
	public XYButton(int newX, int newY) {
		x = newX;
		y = newY;
		this.valid = false;
		this.player = 1;
		
		// Init Event Handler
		handler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				XYButton button = (XYButton) event.getSource();
				System.out.println("Player 1 has pressed " + GridPane.getColumnIndex(button) +  " " + GridPane.getRowIndex(button));
				//test(GridPane.getColumnIndex(button), GridPane.getRowIndex(button));
			}
		};
	}
	
	// TODO: getters/setters
	
	
}