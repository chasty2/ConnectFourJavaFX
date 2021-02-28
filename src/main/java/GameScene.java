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

public class GameScene 
{
	// Buttons used by menu Hbox
	Button revBut = new Button("Reverse");
	Button themeBut = new Button("Themes");
	Button newGameBut = new Button ("Start New Game");
	Button exitBut = new Button("Exit");
	// menu Hbox
	HBox menu = new HBox(10,revBut,themeBut,newGameBut,exitBut);
	
	// Grid Pane for the board
	GridPane board = new GridPane();
	// Add grid buttons
	//addgridbuttons()
	
	
	public void initGridPane() {
		int x = 0; 
		int y = 0;
		for (x = 0; x < 7; x++) {
			for (y = 0; y < 6; y++) {
				MyButton b = new MyButton(x, y);
				
			}
		}
	}
}