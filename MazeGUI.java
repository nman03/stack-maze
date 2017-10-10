package stack_maze;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class MazeGui extends BorderPane {
	private GridPane gp;
	private Maze maze;
	private Label[][] labels;
	
	public void createMap() {
		maze = new Maze();
		setUpGridPane();
		setTop(gp);
		setCenter(null);
		setBottom(null);
	}
	
	private void setUpGridPane() {
		gp = new GridPane();
		labels = new Label[maze.rows][maze.cols];

		for (int row = 0; row < maze.rows; row++) {
			for (int col = 0; col < maze.cols; col++) {
				Label tile = new Label(" ");
				labels[row][col] = tile;
				setUpLabel(tile, row, col);
				gp.add(tile, col, row);
			}
		}
	}

	private void setUpLabel(final Label tile, final int row, final int col) {
		if (maze.map[row].charAt(col) == '0') {
			tile.getStyleClass().add("wall"); // sets up inside walls
		} else if (maze.map[row].charAt(col) == '1') {
			tile.getStyleClass().add("space"); // sets up empty spaces
		} else if (maze.map[row].charAt(col) == 'E'){
			tile.getStyleClass().add("end"); // sets up the end tile
			tile.setText("E");
		} else {
			tile.getStyleClass().add("start"); // sets up the end tile
			tile.setText("S");
		}
			
	}	
}
