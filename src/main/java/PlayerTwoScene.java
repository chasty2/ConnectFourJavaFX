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

public class PlayerTwoScene {
	BorderPane board = new BorderPane();
	VBox earth;
	HBox bottom;
	Text winDialouge;
	Button back;
	Button newGame;
	public Scene createWinTwoScene() {
		BackgroundSize backgroundSize = new BackgroundSize(750,
		        500,
		        true,
		        true,
		        true,
		        false);
		BackgroundImage image = new BackgroundImage(new Image("mars2.jpg"),
		        BackgroundRepeat.REPEAT,
		        BackgroundRepeat.REPEAT,
		        BackgroundPosition.CENTER,
		        backgroundSize);
		back = new Button("Exit");
		newGame = new Button("New Game");
		
		winDialouge = new Text("Player Two WON");
		earth = new VBox(30, winDialouge);
		bottom = new HBox(100, back, newGame);
		board.setBackground(new Background(image));
		
		winDialouge.setStyle("-fx-font: 85px Tahoma;" +
				"-fx-fill: linear-gradient(from 0% 60% to 150% 200%, repeat, blue 10%, yellow 50%);" +
				"-fx-stroke: black;");

		
		earth.setAlignment(Pos.CENTER);
		bottom.setAlignment(Pos.CENTER);
		board.setCenter(earth);
		board.setBottom(bottom);
		Scene winOneScene = new Scene(board, 650,400);
		return winOneScene;
	}
}

