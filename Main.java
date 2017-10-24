package application;
	
import stack_maze.MazeGui;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			MazeGui root = new MazeGui();
			Scene scene = new Scene(root,400,435);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Stack Maze");
			primaryStage.setScene(scene);
			primaryStage.show();
			root.startMenu();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
