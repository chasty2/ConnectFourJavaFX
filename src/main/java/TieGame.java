import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TieGame {
	
	BorderPane root;
	VBox earth;
	HBox bottom;
	Text winDialouge;
	Button exit;
	Button newGame;
	
	public Scene createTieScene() {
		BackgroundSize backgroundSize = new BackgroundSize(750,
		        500,
		        true,
		        true,
		        true,
		        false);
		BackgroundImage image = new BackgroundImage(new Image("earth3.jfif"),
		        BackgroundRepeat.REPEAT,
		        BackgroundRepeat.REPEAT,
		        BackgroundPosition.CENTER,
		        backgroundSize);
		
		exit = new Button("Exit");
		newGame = new Button("New Game");
		root = new BorderPane();
		winDialouge = new Text("TIE GAME");
		earth = new VBox(30, winDialouge);
		bottom = new HBox(100, exit, newGame);
		root.setBackground(new Background(image));
		
		winDialouge.setStyle("-fx-font: 100px Tahoma;" +
				"-fx-fill: linear-gradient(from 0% 60% to 150% 200%, repeat, red 10%, yellow 50%);" +
				"-fx-stroke: black;");

		
		earth.setAlignment(Pos.CENTER);
		bottom.setAlignment(Pos.CENTER);
		root.setCenter(earth);
		root.setBottom(bottom);
		Scene tieScene = new Scene(root, 650,400);
		return tieScene;
	}
}
