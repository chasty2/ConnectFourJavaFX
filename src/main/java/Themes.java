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

public class Themes {
	BorderPane board = new BorderPane();
	VBox earth;
	HBox bottom;
	Button regular;
	Button earthButton;
	Button mars;
	Button back;
	Button startGame;
	Text t1;
	public Themes() {
		// TODO Auto-generated constructor stub
	}
	
	public Scene createThemeScene() {
		BackgroundSize backgroundSize = new BackgroundSize(750,
		        500,
		        true,
		        true,
		        true,
		        false);
		BackgroundImage image = new BackgroundImage(new Image("earth2.png"),
		        BackgroundRepeat.REPEAT,
		        BackgroundRepeat.REPEAT,
		        BackgroundPosition.CENTER,
		        backgroundSize);
		regular = new Button("Regular");
		earthButton = new Button("Earth");
		mars = new Button("Mars");
		Text h2 = new Text("Themes");
		earth = new VBox(30, h2, regular, earthButton, mars);
		earth.setBackground(new Background(image));
		back = new Button("Home Screen");
		startGame = new Button("Game Screen");
		t1 = new Text();
		bottom = new HBox(238, back, t1, startGame);
		bottom.setBackground(new Background(image));
		
		h2.setStyle("-fx-font: 90px Tahoma;" +
				"-fx-fill: linear-gradient(from 0% 60% to 150% 200%, repeat, blue 10%, yellow 50%);" +
				"-fx-stroke: black;");
		
		regular.setStyle("-fx-font: 18px Tahoma;" +
				"-fx-fill: linear-gradient(from 0% 60% to 150% 200%, repeat, blue 10%, yellow 50%);" +
				"-fx-stroke: black;");
		earthButton.setStyle("-fx-font: 20px Tahoma;" +
				"-fx-fill: linear-gradient(from 0% 60% to 150% 200%, repeat, blue 10%, yellow 50%);" +
				"-fx-stroke: black;");
		mars.setStyle("-fx-font: 20px Tahoma;" +
				"-fx-fill: linear-gradient(from 0% 60% to 150% 200%, repeat, blue 10%, yellow 50%);" +
				"-fx-stroke: black;");
		
		earth.setAlignment(Pos.CENTER);
		regular.setDisable(true);
		board.setCenter(earth);
		board.setBottom(bottom);
		Scene themeScene = new Scene(board, 650,400);
		return themeScene;
	}

}
