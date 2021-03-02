import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class JavaFXTemplate extends Application {

	EventHandler<ActionEvent> myHandler;
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
	
	public Label test(int x, int y) {
		Label l1 = new Label();
		l1.setText("You have pressed on: " + x + " " + y);
		return l1;
	}
	public GridPane gameBoard() {
		GridPane board = new GridPane();
		VBox log = eventLog();
		myHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				XYButton button = (XYButton) event.getSource();
				test(GridPane.getColumnIndex(button), GridPane.getRowIndex(button));
			}
			
		};
		for(int x = 0; x<7; x++) {
			for(int y = 0; y<6; y++) {
				XYButton b = new XYButton(x, y);
				b.setMinSize(50, 50);
				b.setStyle("-fx-background-color: blue;");
				b.setOnAction(myHandler);
				board.add(b, x, y);
			}
		}
		board.setPrefWidth(800);
		return board;
	}
	
	public VBox eventLog() {
		Label lbl = new Label("Event Log");
		lbl.setPrefHeight(60);
		lbl.setStyle("-fx-border-style: dotted; fx-borde-width: 0 0 1 0; --fx-font-weight:bold;");
		VBox eventLogList = new VBox(lbl);
		
		
		return eventLogList;
	}
	
	public HBox menu() {
		Button revBut = new Button("Reverse");
		Button themeBut = new Button("Themes");
		Button newGameBut = new Button ("Start New Game");
		Button exitBut = new Button("Exit");
		// menu Hbox
		HBox menuList = new HBox(20,revBut,themeBut,newGameBut,exitBut);
		return menuList;
	}
	
	public Scene createGameScene() {
		BorderPane board = new BorderPane();
		GridPane center = gameBoard();
		board.setMaxWidth(500);
		board.setCenter(center);
		board.setRight(eventLog());
		board.setBottom(menu());
		Scene gameScene = new Scene(board, 500,500);
		return gameScene;
	}
	
	// End JavaFXTemplate
}	
