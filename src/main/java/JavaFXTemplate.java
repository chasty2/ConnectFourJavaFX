import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

public class JavaFXTemplate extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Welcome to JavaFX");
		
		Scene g = createGameScene();
		
				
		
		primaryStage.setScene(g);
		primaryStage.show();
	}
	
	public Scene createGameScene()
	{
		// Mybu
		//MyButton arr[][] = ;
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
		int x = 0; 
		int y = 0;
		for (x = 0; x < 7; x++) {
			for (y = 0; y < 6; y++) {
				MyButton b = new MyButton(x, y);
				b.setMinSize(40, 40);
				b.setStyle("-fx-background-color: lightgrey;");
				board.add(b, x, y);
			}
		}
		// Populate and return gameScene 
		Scene gameScene = new Scene(board, 700,700);
		return gameScene;
	}
	
	// End JavaFXTemplate
}	
