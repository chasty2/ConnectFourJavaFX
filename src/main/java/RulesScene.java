import java.util.HashMap;
import java.util.Iterator;
import java.util.function.Function;
import com.sun.prism.paint.Color;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RulesScene {
	
	Button back;
	Button startGame;
	public HBox RulesBox() {
		Text h1 = new Text("Rules");
		HBox title = new HBox(h1);
		title.setAlignment(Pos.TOP_CENTER);
		title.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +	
                "-fx-border-width: 5;" +
                "-fx-border-radius: 2;" +
                "-fx-border-color: blue;");
		h1.setStyle("-fx-font: 80px Tahoma;" +
					"-fx-fill: linear-gradient(from 0% 60% to 150% 200%, red, blue 40%, green 5%);" +
					"-fx-stroke: black;");
		return title;
	}
	
	
	public HBox menu() {
		back = new Button("Back");
		startGame = new Button("Start Game");
		Text logo = new Text("Connect Four By Cody and Krish");
		logo.setStyle("-fx-font: 15px Tahoma;" +
				"-fx-fill: linear-gradient(from 0% 90% to 150% 200%, blue, red 40%, green 15%);");
		HBox menu = new HBox(100, back, logo, startGame);
		
		menu.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 5;" +
                "-fx-border-radius: 2;" +
                "-fx-border-color: red;");
		menu.setAlignment(Pos.CENTER);
		return menu;
	}
	
	public VBox rulesList() {
		Text a = new Text("1. The Two Player alternate between both of them");
		Text b = new Text("2. Click on the gird to where you want to place your coin");
		Text c = new Text("3. You can't place a coin, if there is no coin the below");
		Text d = new Text("4. If you place the coin without having a coin the bottom, its a invalid move");
		Text e = new Text("5. You can win the game, if there four coins, either diagnolly, vertically or Horzinotal");
		Text f = new Text("6. Once you win, you cant make any moves");
		Text g = new Text("7. Hit, start new game if you want to play again");
		Text h = new Text("8. Please go back to change the theme");
		a.setStyle("-fx-font: 20px Tahoma;");
		b.setStyle("-fx-font: 20px Tahoma;");
		c.setStyle("-fx-font: 20px Tahoma;");
		d.setStyle("-fx-font: 18px Tahoma;");
		e.setStyle("-fx-font: 16.8px Tahoma;");
		f.setStyle("-fx-font: 20px Tahoma;");
		g.setStyle("-fx-font: 20px Tahoma;");
		h.setStyle("-fx-font: 20px Tahoma;");
		VBox test = new VBox(a, b, c, d, e, f, g, h);
		test.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 5;" +
                "-fx-border-radius: 2;" +
                "-fx-border-color: yellow;");
		return test;
	}
	
	public Scene createRulesScene() {
		BorderPane rules = new BorderPane();
		rules.setTop(RulesBox());
		rules.setCenter(rulesList());
		rules.setBottom(menu());
		Scene rulesScene = new Scene(rules, 650, 400);
		return rulesScene;
	}
}
