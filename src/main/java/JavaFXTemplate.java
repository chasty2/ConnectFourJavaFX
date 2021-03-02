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
import javafx.scene.text.TextAlignment;
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
		primaryStage.setTitle("Connect Four by Cody & Krish");
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
				b.setPrefSize(50, 50);
				b.setStyle("-fx-background-color: grey;");
				b.setOnAction(myHandler);
				board.add(b, x, y);
			}
		}
		board.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 5;" +
                "-fx-border-radius: 2;" +
                "-fx-border-color: yellow;");
		return board;
	}
	
	public VBox eventLog() {
		Text h1 = new Text("Event Log");
		h1.setTextAlignment(TextAlignment.CENTER);
		VBox eventLogList = new VBox(h1);
		eventLogList.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 5;" +
                "-fx-border-radius: 2;" +
                "-fx-border-color: green;");
		eventLogList.setAlignment(Pos.TOP_CENTER);
		return eventLogList;
	}
	
	public HBox menu() {
		Button revBut = new Button("Reverse");
		Button themeBut = new Button("Themes");
		Button newGameBut = new Button ("Start New Game");
		Button exitBut = new Button("Exit");
		// menu Hbox
		HBox menuList = new HBox(100,revBut,themeBut,newGameBut,exitBut);
		menuList.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 5;" +
                "-fx-border-radius: 2;" +
                "-fx-border-color: blue;");
		return menuList;
	}
	
	public Scene createGameScene() {
		BorderPane board = new BorderPane();
		GridPane center = gameBoard();
		VBox eventLog = eventLog();
		board.setPrefHeight(200);
		eventLog.setPrefWidth(200);
		board.setCenter(center);
		board.setRight(eventLog);
		board.setBottom(menu());
		Scene gameScene = new Scene(board, 650,400);
		board.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 5;" +
                "-fx-border-radius: 2;" +
                "-fx-border-color: red;");
		return gameScene;
	}
	
	// End JavaFXTemplate
}	
