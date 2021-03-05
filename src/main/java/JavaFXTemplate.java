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
	Stage window = new Stage();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	public JavaFXTemplate() {
		GameScene test = new GameScene();
		Scene s = test.createGameScene();
		window.setScene(s);
	}
	public Scene setScene(Scene s) {
		window.setScene(s);
		return s;
	}
	
	
	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("Connect Four by Cody & Krish");
	
		GameScene g = new GameScene();
		Scene s = g.createGameScene();
		IntroScene g2 = new IntroScene();
		Scene s2 = g2.createIntroScene();
		RulesScene g3 = new RulesScene();
		Scene s3 = g3.createRulesScene();
		window.setScene(s2);
		window.show();
	}
	// End JavaFXTemplate
}	
