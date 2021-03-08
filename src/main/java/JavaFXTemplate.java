import java.util.HashMap;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class JavaFXTemplate extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Connect Four by Cody & Krish");
		
		// Init Scene classes
		IntroScene intro = new IntroScene();
		RulesScene rules = new RulesScene();
		Themes themes = new Themes();
		TieGame tie = new TieGame();
		PlayerOneScene oneWin = new PlayerOneScene();
		PlayerTwoScene twoWin = new PlayerTwoScene();
		GameScene games = new GameScene(primaryStage, tie, oneWin, twoWin);
		GameLogic gameLogic = games.game;
		
		// Init Scenes from their classes
		Scene introScene = intro.createIntroScene();	
		Scene gameScene = games.createGameScene();
		Scene rulesScene = rules.createRulesScene();
		Scene themeScene = themes.createThemeScene();
			
		// Earth theme event handler
		EventHandler<ActionEvent> earthHandle = new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) {
				System.out.println("PRESSED earth");
				BackgroundSize backgroundSize = new BackgroundSize(750,
				        500,
				        true,
				        true,
				        true,
				        false);
				BackgroundImage image = new BackgroundImage(new Image("earth.jpg"),
				        BackgroundRepeat.REPEAT,
				        BackgroundRepeat.REPEAT,
				        BackgroundPosition.CENTER,
				        backgroundSize);
				
				intro.board.setBackground(new Background(image));
				games.board.setBackground(new Background(image));
				games.eventLogList.setStyle("-fx-padding: 10;" +
		                "-fx-border-style: solid inside;" +
		                "-fx-border-width: 0;" +
		                "-fx-border-radius: 0;");
				games.menuList.setStyle("-fx-padding: 10;" +
		                "-fx-border-style: solid inside;" +
		                "-fx-border-width: 5;" +
		                "-fx-border-radius: 2;" +
		                "-fx-border-color: green;");
				games.gboard.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 5;" +
                "-fx-border-radius: 2;" +
                "-fx-border-color: blue;");
				
				games.board.setStyle("-fx-padding: 10;" +
		                "-fx-border-style: solid inside;" +
		                "-fx-border-width: 5;" +
		                "-fx-border-radius: 2;" +
		                "-fx-border-color: grey;");
				themes.earthButton.setDisable(true);
				themes.mars.setDisable(false);
				themes.regular.setDisable(false);
				games.h1.setFill(Color.WHITE);
				
				primaryStage.setScene(gameScene);
			}
		};
		
		// Mars theme event handler
		EventHandler<ActionEvent> marsHandle = new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) {
				System.out.println("PRESSED Mars");
				BackgroundSize backgroundSize = new BackgroundSize(750,
				        500,
				        true,
				        true,
				        true,
				        false);
				BackgroundImage image = new BackgroundImage(new Image("mars.jpg"),
				        BackgroundRepeat.REPEAT,
				        BackgroundRepeat.REPEAT,
				        BackgroundPosition.CENTER,
				        backgroundSize);
				
				intro.board.setBackground(new Background(image));
				games.eventLogList.setStyle("-fx-padding: 10;" +
		                "-fx-border-style: solid inside;" +
		                "-fx-border-width: 0;" +
		                "-fx-border-radius: 0;");
				games.menuList.setStyle("-fx-padding: 10;" +
		                "-fx-border-style: solid inside;" +
		                "-fx-border-width: 5;" +
		                "-fx-border-radius: 2;" +
		                "-fx-border-color: orange;");
				games.gboard.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 5;" +
                "-fx-border-radius: 2;" +
                "-fx-border-color: red;");
				
				games.board.setStyle("-fx-padding: 10;" +
		                "-fx-border-style: solid inside;" +
		                "-fx-border-width: 5;" +
		                "-fx-border-radius: 2;" +
		                "-fx-border-color: brown;");
				
				games.board.setBackground(new Background(image));
				themes.mars.setDisable(true);
				themes.earthButton.setDisable(false);
				themes.regular.setDisable(false);
				games.h1.setFill(Color.RED);
				primaryStage.setScene(gameScene);
			}
		};
		
		// Regular theme event handler
		EventHandler<ActionEvent> regularHandle = new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) {				
				intro.board.setBackground(null);
				games.board.setBackground(null);
				themes.mars.setDisable(false);
				themes.earthButton.setDisable(false);
				themes.regular.setDisable(true);
				games.h1.setFill(Color.BLACK);
				
				games.eventLogList.setStyle("-fx-padding: 10;" +
		                "-fx-border-style: solid inside;" +
		                "-fx-border-width: 5;" +
		                "-fx-border-radius: 2;" +
		                "-fx-border-color: green;" +
		                "-fx-background-color: beige");
				games.menuList.setStyle("-fx-padding: 10;" +
		                "-fx-border-style: solid inside;" +
		                "-fx-border-width: 5;" +
		                "-fx-border-radius: 2;" +
		                "-fx-border-color: blue;" +
		                "-fx-background-color: beige");
				games.gboard.setStyle("-fx-padding: 10;" +
						"-fx-border-style: solid inside;" +
						"-fx-border-width: 5;" +
						"-fx-border-radius: 2;" +
						"-fx-border-color: yellow;" +
						"-fx-background-color: beige");
				
				games.board.setStyle("-fx-padding: 10;" +
		                "-fx-border-style: solid inside;" +
		                "-fx-border-width: 5;" +
		                "-fx-border-radius: 2;" +
		                "-fx-border-color: red;" +
		                "-fx-background-color: beige");
				primaryStage.setScene(gameScene);
			}
		};
		
		// Exit game button handler
		EventHandler<ActionEvent> exitHandle = new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) {				
				primaryStage.close();
			}
		};
		
		// Set button actions involving theme changes
		themes.earthButton.setOnAction(earthHandle);
		themes.mars.setOnAction(marsHandle);
		themes.regular.setOnAction(regularHandle);
		
		// Set button actions that involve scene changes
		intro.themes.setOnAction(e->primaryStage.setScene(themeScene));
		intro.startGame.setOnAction(e->primaryStage.setScene(gameScene));
		intro.howToPlay.setOnAction(e->primaryStage.setScene(rulesScene));
		intro.exitGame.setOnAction(exitHandle);
		
		games.themeBut.setOnAction(e->primaryStage.setScene(themeScene));
		games.exitBut.setOnAction(exitHandle);
		
		rules.back.setOnAction(e->primaryStage.setScene(introScene));
		rules.startGame.setOnAction(e->primaryStage.setScene(gameScene));
		
		tie.exit.setOnAction(exitHandle);
		tie.newGame.setOnAction(e-> {
			gameLogic.clearBoard(games.gameBoard);
			gameLogic.enableButtons(games.gameBoard);
			primaryStage.setScene(gameScene);
		});
		
		oneWin.exit.setOnAction(exitHandle);
		oneWin.newGame.setOnAction(e-> {
			gameLogic.clearBoard(games.gameBoard);
			gameLogic.enableButtons(games.gameBoard);
			primaryStage.setScene(gameScene);
		});
		twoWin.exit.setOnAction(exitHandle);
		twoWin.newGame.setOnAction(e-> {
			gameLogic.clearBoard(games.gameBoard);
			gameLogic.enableButtons(games.gameBoard);
			primaryStage.setScene(gameScene);
		});
		
		
		// Set stage
		primaryStage.setScene(introScene);
		primaryStage.show();

	}
	
}	// End JavaFXTemplate
