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
	EventHandler<ActionEvent> myHandler;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	public Scene createNewScene() {
		GameScene g5 = new GameScene();
		Scene s5 = g5.createGameScene();
		return s5;
	}
	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("Connect Four by Cody & Krish");
		
		IntroScene g2 = new IntroScene();
		Scene s2 = g2.createIntroScene();
		GameScene g = new GameScene();
		Scene s = g.createGameScene();
		RulesScene g3 = new RulesScene();
		Scene s3 = g3.createRulesScene();
		Themes t = new Themes();
		Scene s4 = t.createThemeScene();
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
				
				g2.board.setBackground(new Background(image));
				g.board.setBackground(new Background(image));
				g.eventLogList.setStyle("-fx-padding: 10;" +
		                "-fx-border-style: solid inside;" +
		                "-fx-border-width: 0;" +
		                "-fx-border-radius: 0;");
				g.menuList.setStyle("-fx-padding: 10;" +
		                "-fx-border-style: solid inside;" +
		                "-fx-border-width: 0;" +
		                "-fx-border-radius: 0;");
				t.earthButton.setDisable(true);
				t.mars.setDisable(false);
				t.regular.setDisable(false);
				g.h1.setFill(Color.WHITE);
				//primaryStage.setScene(s);
			}
		};
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
				
				g2.board.setBackground(new Background(image));
				g.eventLogList.setStyle("-fx-padding: 10;" +
		                "-fx-border-style: solid inside;" +
		                "-fx-border-width: 0;" +
		                "-fx-border-radius: 0;");
				g.menuList.setStyle("-fx-padding: 10;" +
		                "-fx-border-style: solid inside;" +
		                "-fx-border-width: 0;" +
		                "-fx-border-radius: 0;");
				g.board.setBackground(new Background(image));
				t.mars.setDisable(true);
				t.earthButton.setDisable(false);
				t.regular.setDisable(false);
				g.h1.setFill(Color.RED);
			}
		};
		
		EventHandler<ActionEvent> regularHandle = new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) {
				System.out.println("PRESSED Regular");
				
				g2.board.setBackground(null);
				g.board.setBackground(null);
				t.mars.setDisable(false);
				t.earthButton.setDisable(false);
				t.regular.setDisable(true);
				g.h1.setFill(Color.BLACK);
			}
		};
		

		t.earthButton.setOnAction(earthHandle);
		t.mars.setOnAction(marsHandle);
		t.regular.setOnAction(regularHandle);
		t.back.setOnAction(e->primaryStage.setScene(s2));
		t.startGame.setOnAction(e->primaryStage.setScene(s));
		g2.earth.setOnAction(earthHandle);
		g2.themes.setOnAction(e->primaryStage.setScene(s4));
		g.themeBut.setOnAction(e->primaryStage.setScene(s4));
		g.exitBut.setOnAction(e->primaryStage.close());
		g.newGameBut.setOnAction(e->primaryStage.setScene(createNewScene()));
		g2.startGame.setOnAction(e->primaryStage.setScene(s));
		g2.howToPlay.setOnAction(e->primaryStage.setScene(s3));
		g2.exitGame.setOnAction(e->primaryStage.close());
		g3.back.setOnAction(e->primaryStage.setScene(s2));
		g3.startGame.setOnAction(e->primaryStage.setScene(s));
		primaryStage.setScene(s2);
		primaryStage.show();
	}
	// End JavaFXTemplate
}	
