import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;
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
import javafx.scene.control.ComboBox;
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


public class GameScene 
{	
	// Manage game state
	GameLogic game = new GameLogic();
	Button revBut;
	Button themeBut;
	Button newGameBut;
	Button exitBut;
	Button regular;
	Button earth;
	Button mars;
	ObservableList<Button> options;
	ListView <String> list = new ListView<>();
	ObservableList<String> moveList = FXCollections.observableArrayList("Player 1 is up");
	
	/*
	 * Constructor
	 *
	public GameScene()
	{	game = new GameLogic();
		center = new gameBoard();
	}*/
	
	
	//XYButton event handler
	EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
		@Override
		public void handle(ActionEvent event) {
			// Get info on button pressed
			XYButton button = (XYButton) event.getSource();
			System.out.println("Button pressed");
			game.updateLogs(button);
			// Press if valid move
			if (button.getValid() == true)
			{
				button.pushButton(game.getCurrentPlayer());
				//if game.checkWin() == true, end game;
				game.changeTurn();
			}
			// Display logs
			list.setItems(game.getMoveList());
		}
	};
	
	// revBut EventHandler
	EventHandler<ActionEvent> revHandler = new EventHandler<ActionEvent>()
	{
		@Override
		public void handle(ActionEvent event)
		{
			System.out.println(game.getMoveList().size());
			if (game.getMoveList().size() == 1)
			{
				System.out.println("No move to reverse");
			}
			else
			{
				Integer[] lastMove = game.pruneLogs();
				XYButton lastButton = game.getMove(gameBoard, lastMove[0], lastMove[1]);
				System.out.println("Found button");
				lastButton.unPress();
				game.changeTurn();
			}
			// Display logs
			list.setItems(game.getMoveList());
		}
	};
	
	GridPane gameBoard = gameBoard();

	public GridPane gameBoard() {

		GridPane board = new GridPane();
		/*
		 *  Populate gameBoard with XYButtons:
		 *  
		 *  Note that the board is populated one column at a time, from 
		 *  top to bottom. Each column consists of a linked list of 
		 *  XYButtons that iterate from bottom to top
		 *  
		 */
		XYButton prev = null;
		for(int y = 0; y<7; y++) 
		{
			for(int x = 0; x<6; x++) 
			{
				// Init XYButton
				XYButton b = new XYButton(x, y);
				b.setPrefSize(50, 50);
				b.setStyle("-fx-background-color: grey;");
				b.setOnAction(handler);			
				// Link b to XYButton 'above' it, or null
				b.setNext(prev);
				// Add button to board
				board.add(b, y, x);
				// Set prev to b for LL construction
				prev = b;
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
		list.setItems(moveList);
		VBox eventLogList = new VBox(h1, list);
		eventLogList.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 5;" +
                "-fx-border-radius: 2;" +
                "-fx-border-color: green;");
		eventLogList.setAlignment(Pos.TOP_CENTER);
		return eventLogList;
	}
	
	public HBox menu() {
		revBut = new Button("Reverse");
		revBut.setOnAction(revHandler);
		
		themeBut = new Button("Themes");
		newGameBut = new Button ("New Game");
		exitBut = new Button("Exit");
		regular = new Button("Regular");
		earth = new Button("Earth");
		mars = new Button("Mars");
		regular.setDisable(true);
		// menu Hbox
		options = FXCollections.observableArrayList();
		options.add(regular);
		options.add(earth);
		options.add(mars);
		ComboBox<Button> comboBox = new ComboBox<>(options);
		comboBox.setPromptText("Themes");
		HBox menuList = new HBox(100,revBut,comboBox,newGameBut,exitBut);
		menuList.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 5;" +
                "-fx-border-radius: 2;" +
                "-fx-border-color: blue;");
		return menuList;
	}
	

	public Scene createGameScene() {
		BorderPane board = new BorderPane();
		//center = gameBoard();
		VBox eventLog = eventLog();
		board.setPrefHeight(200);
		eventLog.setPrefWidth(200);
		board.setCenter(gameBoard);
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
	
}