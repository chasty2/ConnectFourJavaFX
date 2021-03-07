import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;
import java.util.function.Function;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
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
	GameLogic game;
	GridPane gameBoard;
	Button revBut;
	Button themeBut;
	Button newGameBut;
	Button exitBut;
	Button regular;
	Button earth;
	Button mars;
	Text h1;
	GridPane gboard;
	BorderPane board = new BorderPane();
	HBox menuList;
	ObservableList<Button> options;
	ListView <String> list;
	ObservableList<String> moveList;
	VBox eventLogList;
	PauseTransition pause = new PauseTransition(Duration.seconds(1));
	//XYButton event handler
	EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() 
	{
		@Override
		public void handle(ActionEvent event) {
			// Get info on button pressed
			XYButton button = (XYButton) event.getSource();
			game.updateLogs(button);
			// Press if valid move
			if (button.getValid() == true)
			{
				button.pushButton(game.getCurrentPlayer());
				if (game.checkWin(gameBoard, button) == true) 
				{
					Text h3 = new Text("Player " + game.getCurrentPlayer() + " WON");
					h3.setStyle("-fx-font: 60px Tahoma;" +
				"-fx-fill: linear-gradient(from 0% 60% to 150% 200%, repeat, blue 10%, yellow 50%);" +
				"-fx-stroke: black;");
					pause.setOnFinished(e->{board.setCenter(h3);});
					pause.play();
					
					
					System.out.println("Player " + game.getCurrentPlayer() + " WON");
				}
				game.changeTurn();
			}
			// Display logs
			list.setItems(game.getMoveList());
		}
	};
	
	// Reverse Button handler
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
				lastButton.unPress();
				game.changeTurn();
			}
			// Display logs
			list.setItems(game.getMoveList());
		}
	};
	
	/*
	 * Constructor, allows game to access gameBoard
	 */
	public GameScene()
	{	game = new GameLogic();
		gameBoard = gameBoard();
		list = new ListView<>();
	}
	
	public GridPane gameBoard() {

		gboard = new GridPane();
		/*
		 *  Populate gameBoard with XYButtons:
		 *  
		 *  Note that the board is populated one column at a time, from 
		 *  top to bottom. Each column consists of a linked list of 
		 *  XYButtons that iterate from bottom to top
		 *  
		 */
		XYButton prev = null;
		for(int column = 0; column<7; column++) 
		{
			for(int row = 0; row<6; row++) 
			{
				// Init XYButton
				XYButton b = new XYButton(row, column);
				b.setPrefSize(50, 50);
				b.setStyle("-fx-background-color: grey;");
				b.setOnAction(handler);			
				// Link b to XYButton 'above' it, or null
				b.setNext(prev);
				// Add button to board
				gboard.add(b, column, row);
				// Set prev to b for LL construction
				prev = b;
			}
		}
		gboard.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 5;" +
                "-fx-border-radius: 2;" +
                "-fx-border-color: yellow;");
		return gboard;
	}
	
	public VBox eventLog() {
		h1 = new Text("Event Log");
		h1.setTextAlignment(TextAlignment.CENTER);
		list.setItems(moveList);
		eventLogList = new VBox(h1, list);
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
		menuList = new HBox(100,revBut, themeBut, newGameBut,exitBut);
		menuList.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 5;" +
                "-fx-border-radius: 2;" +
                "-fx-border-color: blue;");
		return menuList;
	}
	

	public Scene createGameScene() {
		
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