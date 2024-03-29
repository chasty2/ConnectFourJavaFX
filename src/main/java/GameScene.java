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
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;


public class GameScene 
{	
	
	// Manage scene transitions
	Scene thisScene;
	HashMap<Integer, Scene> endScenes;
	Stage primaryStage;
	Scene tieScene;
	Scene oneWinScene;
	Scene twoWinScene;
	// Manage game state
	GameLogic game = new GameLogic();
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
	PauseTransition pause = new PauseTransition(Duration.seconds(3));
	
	//XYButton event handler
	EventHandler<ActionEvent> moveHandler = new EventHandler<ActionEvent>() 
	{
		@Override
		public void handle(ActionEvent event) {
			// Get info on button pressed
			XYButton button = (XYButton) event.getSource();
			game.addMoveToLogs(button);
			// Press if valid move
			if (button.getValid() == true)
			{
				button.press(game.getCurrentPlayer());
				if (game.checkWin(gameBoard, button) == true) 
				{
					game.disableButtons(gameBoard);
					Integer winner = game.getCurrentPlayer();
					pause.setOnFinished(e->{primaryStage.setScene(endScenes.get(winner));});
					pause.play();
					System.out.println("Player " + game.getCurrentPlayer() + " WON");
				}
				else if (game.checkTie(gameBoard) == true)
				{
					game.disableButtons(gameBoard);
					pause.setOnFinished(e->{primaryStage.setScene(tieScene);});
					pause.play();
					System.out.println("Tie game");
				}
				game.changeTurn();
			}
			// Display logs
			list.setItems(game.getMoveList());
		}
	};
	
	// Reverse button handler
	EventHandler<ActionEvent> revHandler = new EventHandler<ActionEvent>()
	{
		@Override
		public void handle(ActionEvent event)
		{
			if (game.getMoveList().size() == 1)
			{
				System.out.println("No move to reverse");
			}
			else
			{
				game.reverseMove(gameBoard);
			}
			// Display logs
			list.setItems(game.getMoveList());
		}
	};
	
	// NewGameBut handler
	EventHandler<ActionEvent> newGameHandler = new EventHandler<ActionEvent>()
	{
		@Override
		public void handle(ActionEvent event)
		{
			if (game.getMoveList().size() == 1)
			{
				System.out.println("Game is already new");
			}
			else
			{
				game.clearBoard(gameBoard);
			}
			// Enable buttons and display fresh board
			game.enableButtons(gameBoard);
			board.setCenter(gameBoard);
			// Display logs
			list.setItems(game.getMoveList());
		}
	};
	
	
	
	/*
	 * Constructor, allows game to access gameBoard and scene transitions
	 */
	public GameScene(Stage mainStage, TieGame tie, PlayerOneScene oneWin, PlayerTwoScene twoWin)
	{
		primaryStage = mainStage;
		// Create end-game scenes
		tieScene = tie.createTieScene();
		oneWinScene = oneWin.createWinOneScene();
		twoWinScene = twoWin.createWinTwoScene();
		gameBoard = gameBoard();
		list = new ListView<>();
		// Make scenemap to set scene with proper winner
		endScenes = new HashMap<Integer, Scene>();
		endScenes.put(1,oneWinScene);
		endScenes.put(2, twoWinScene);
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
				b.setOnAction(moveHandler);			
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
                "-fx-border-color: yellow;" +
                "-fx-background-color: beige");
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
                "-fx-border-color: green;" +
                "-fx-background-color: beige");
		
		eventLogList.setAlignment(Pos.TOP_CENTER);
		return eventLogList;
	}
	
	public HBox menu() {
		// themeBut and exitBut handlers set in start()
		themeBut = new Button("Themes");
		exitBut = new Button("Exit");
		
		revBut = new Button("Reverse");
		revBut.setOnAction(revHandler);
		newGameBut = new Button ("New Game");
		newGameBut.setOnAction(newGameHandler);

		menuList = new HBox(100,revBut, themeBut, newGameBut,exitBut);
		menuList.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 5;" +
                "-fx-border-radius: 2;" +
                "-fx-border-color: blue;" +
                "-fx-background-color: beige");
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
                "-fx-border-color: red;" +
                "-fx-background-color: beige");
		thisScene = gameScene;
		return gameScene;
	}
	
}