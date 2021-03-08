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

public class PlayerOneScene {
	BorderPane oneRoot;
	VBox earth;
	HBox bottom;
	Text winDialouge;
	Button exit;
	Button newGame;
	
	public Scene createWinOneScene() {
		oneRoot = new BorderPane();
		BackgroundSize backgroundSize = new BackgroundSize(750,
		        500,
		        true,
		        true,
		        true,
		        false);
		BackgroundImage image = new BackgroundImage(new Image("earth4.jpg"),
		        BackgroundRepeat.REPEAT,
		        BackgroundRepeat.REPEAT,
		        BackgroundPosition.CENTER,
		        backgroundSize);
		exit = new Button("Exit");
		newGame = new Button("New Game");
		
		winDialouge = new Text("Player One WON");
		earth = new VBox(30, winDialouge);
		bottom = new HBox(100, exit, newGame);
		oneRoot.setBackground(new Background(image));
		
		winDialouge.setStyle("-fx-font: 85px Tahoma;" +
				"-fx-fill: linear-gradient(from 0% 60% to 150% 200%, repeat, blue 10%, yellow 50%);" +
				"-fx-stroke: black;");

		earth.setAlignment(Pos.CENTER);
		bottom.setAlignment(Pos.CENTER);
		oneRoot.setCenter(earth);
		oneRoot.setBottom(bottom);
		Scene oneWin = new Scene(oneRoot, 650,400);
		return oneWin;
	}
}
