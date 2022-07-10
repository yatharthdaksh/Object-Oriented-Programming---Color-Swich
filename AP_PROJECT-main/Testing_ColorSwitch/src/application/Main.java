package application;
	
import java.io.Serializable;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;


public class Main extends Application implements Serializable{
	@Override
	public void start(Stage primaryStage) {
		try {
			Game game=new Game();
			primaryStage=game.getStage();
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}
