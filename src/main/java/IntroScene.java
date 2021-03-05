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

public class IntroScene {
	
	public HBox titleBox() {
		Text h1 = new Text("Connect Four");
		h1.setTextAlignment(TextAlignment.CENTER);
		HBox title = new HBox(h1);
		title.setAlignment(Pos.TOP_CENTER);
		title.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +	
                "-fx-border-width: 5;" +
                "-fx-border-radius: 2;" +
                "-fx-border-color: green;");
		h1.setStyle("-fx-font: 100px Tahoma;" +
					"-fx-fill: linear-gradient(from 0% 60% to 150% 200%, repeat, yellow 10%, red 50%);" +
					"-fx-stroke: black;");
		return title;
	}
	
	public HBox centerBox() {
		Text h2 = new Text("CS 342 Project by Cody & Krish");
		h2.setFont(Font.font ("Verdana", 40));
		HBox centBox = new HBox(h2);
		h2.setStyle("-fx-font: 45px Tahoma;" +
				"-fx-fill: linear-gradient(from 0% 60% to 150% 200%, repeat, blue 10%, yellow 50%);" +
				"-fx-stroke: black;");
		centBox.setAlignment(Pos.CENTER);
		return centBox;
	}
	
	public HBox menu() {
		Button themes = new Button("Themes");
		Button howToPlay = new Button("How to Play");
		Button startGame = new Button("Stat Game");
		Button exitGame = new Button("Exit");
		// startGame.setOnAction();
		HBox menu = new HBox(100, themes, howToPlay, startGame, exitGame);
		menu.setStyle("-fx-padding: 10;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 5;" +
                "-fx-border-radius: 2;" +
                "-fx-border-color: red;");
		menu.setAlignment(Pos.CENTER);
		return menu;
	}
	
	public Scene createIntroScene() {
		BorderPane board = new BorderPane();
		board.setTop(titleBox());
		board.setCenter(centerBox());
		board.setBottom(menu());
		Scene introScene = new Scene(board, 650,400);
		return introScene;
	}
	
}