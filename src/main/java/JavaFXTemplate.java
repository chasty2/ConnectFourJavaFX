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
	// private static Stage mainStage;
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
		GameScene g = new GameScene();
		Scene s = g.createGameScene();
		/*
		IntroScene g2 = new IntroScene();
		Scene s2 = g2.createIntroScene();
		RulesScene g3 = new RulesScene();
		Scene s3 = g3.createRulesScene();
		*/
		primaryStage.setScene(s);
		primaryStage.show();
	}
	// End JavaFXTemplate
}	
