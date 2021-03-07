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
	// private static Stage mainStage;
	HashMap<String, Scene> sceneMap; 
	IntroScene intro;
	GameScene game;
	RulesScene rules;
	Themes themes;
	//Button newGameButton;
	//EventHandler<ActionEvent> myHandler;
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
		GameScene game = new GameScene();
		RulesScene rules = new RulesScene();
		Themes themes = new Themes();
		
		// Create and populate sceneMap
		sceneMap = new HashMap<String,Scene>();
		sceneMap = newGame(primaryStage, sceneMap, intro, game, rules, themes);
			
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
				game.board.setBackground(new Background(image));
				game.eventLogList.setStyle("-fx-padding: 10;" +
		                "-fx-border-style: solid inside;" +
		                "-fx-border-width: 0;" +
		                "-fx-border-radius: 0;");
				game.menuList.setStyle("-fx-padding: 10;" +
		                "-fx-border-style: solid inside;" +
		                "-fx-border-width: 5;" +
		                "-fx-border-radius: 2;" +
		                "-fx-border-color: green;");
				game.gboard.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 5;" +
                "-fx-border-radius: 2;" +
                "-fx-border-color: blue;");
				
				game.board.setStyle("-fx-padding: 10;" +
		                "-fx-border-style: solid inside;" +
		                "-fx-border-width: 5;" +
		                "-fx-border-radius: 2;" +
		                "-fx-border-color: grey;");
				themes.earthButton.setDisable(true);
				themes.mars.setDisable(false);
				themes.regular.setDisable(false);
				game.h1.setFill(Color.WHITE);
				//primaryStage.setScene(s);
				primaryStage.setScene(sceneMap.get("game"));
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
				game.eventLogList.setStyle("-fx-padding: 10;" +
		                "-fx-border-style: solid inside;" +
		                "-fx-border-width: 0;" +
		                "-fx-border-radius: 0;");
				game.menuList.setStyle("-fx-padding: 10;" +
		                "-fx-border-style: solid inside;" +
		                "-fx-border-width: 5;" +
		                "-fx-border-radius: 2;" +
		                "-fx-border-color: orange;");
				game.gboard.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 5;" +
                "-fx-border-radius: 2;" +
                "-fx-border-color: red;");
				
				game.board.setStyle("-fx-padding: 10;" +
		                "-fx-border-style: solid inside;" +
		                "-fx-border-width: 5;" +
		                "-fx-border-radius: 2;" +
		                "-fx-border-color: brown;");
				
				game.board.setBackground(new Background(image));
				themes.mars.setDisable(true);
				themes.earthButton.setDisable(false);
				themes.regular.setDisable(false);
				game.h1.setFill(Color.RED);
				primaryStage.setScene(sceneMap.get("game"));
			}
		};
		
		// Regular theme event handler
		EventHandler<ActionEvent> regularHandle = new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) {				
				intro.board.setBackground(null);
				game.board.setBackground(null);
				themes.mars.setDisable(false);
				themes.earthButton.setDisable(false);
				themes.regular.setDisable(true);
				game.h1.setFill(Color.BLACK);
				
				game.eventLogList.setStyle("-fx-padding: 10;" +
		                "-fx-border-style: solid inside;" +
		                "-fx-border-width: 5;" +
		                "-fx-border-radius: 2;" +
		                "-fx-border-color: green;" +
		                "-fx-background-color: beige");
				game.menuList.setStyle("-fx-padding: 10;" +
		                "-fx-border-style: solid inside;" +
		                "-fx-border-width: 5;" +
		                "-fx-border-radius: 2;" +
		                "-fx-border-color: blue;" +
		                "-fx-background-color: beige");
				game.gboard.setStyle("-fx-padding: 10;" +
						"-fx-border-style: solid inside;" +
						"-fx-border-width: 5;" +
						"-fx-border-radius: 2;" +
						"-fx-border-color: yellow;" +
						"-fx-background-color: beige");
				
				game.board.setStyle("-fx-padding: 10;" +
		                "-fx-border-style: solid inside;" +
		                "-fx-border-width: 5;" +
		                "-fx-border-radius: 2;" +
		                "-fx-border-color: red;" +
		                "-fx-background-color: beige");
				primaryStage.setScene(sceneMap.get("game"));
			}
		};
		
		// Set button actions involving theme changes
		themes.earthButton.setOnAction(earthHandle);
		themes.mars.setOnAction(marsHandle);
		themes.regular.setOnAction(regularHandle);
		
		// Set stage
		primaryStage.setScene(sceneMap.get("intro"));
		primaryStage.show();
	}
	
	public HashMap<String,Scene> newGame(Stage primaryStage, HashMap<String,Scene> sceneMap, 
			IntroScene intro, GameScene game, RulesScene rules, Themes themes)
	{
		// Init Scenes from the classes that contain them
		Scene introScene = intro.createIntroScene();	
		Scene gameScene = game.createGameScene();
		Scene rulesScene = rules.createRulesScene();
		Scene themeScene = themes.createThemeScene();

		// Set button actions that involve changing scenes
		intro.themes.setOnAction(e->primaryStage.setScene(themeScene));
		intro.startGame.setOnAction(e->primaryStage.setScene(gameScene));
		intro.howToPlay.setOnAction(e->primaryStage.setScene(rulesScene));
		intro.exitGame.setOnAction(e->primaryStage.close());
		game.themeBut.setOnAction(e->primaryStage.setScene(themeScene));
		game.exitBut.setOnAction(e->primaryStage.close());
		rules.back.setOnAction(e->primaryStage.setScene(introScene));
		rules.startGame.setOnAction(e->primaryStage.setScene(gameScene));
		
		// Add scenes to sceneMap
		sceneMap.put("intro", introScene);
		sceneMap.put("game", gameScene);
		sceneMap.put("rules", rulesScene);
		sceneMap.put("themes", themeScene);
		
		return sceneMap;
	}
}	// End JavaFXTemplate
