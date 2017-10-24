package stack_maze;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class MazeGui extends BorderPane {
	private GridPane gp;
	private Maze maze;
	private Label[][] labels;
	private HBox footer;
	private boolean solved = false;

	public void createMap() {
		maze = new Maze();
		Label title = new Label("Stack Maze");
		title.getStyleClass().add("title");
		setUpGridPane();
		setUpFooter();
		setTop(title);
		setCenter(gp);
		setBottom(footer);
	}

	public void startMenu() {
		HBox menu = new HBox();
		HBox mid = new HBox();

		Button loadButton = new Button("Load A Maze Text File");
		menu.getStyleClass().add("footer");
		loadButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				createMap();
			}
		} );

		String txtFileReq = ("Maze Text File Requirements: "
				+ "\n1. The first line of the file lists dimensions of the maze. "
				+ "\n2. The second line of the file lists the coordinates for the start of the maze. "
				+ "\n3. The rest of the file contains lines of characters which are as follows: "
				+ "\n\t0 : This indicates a wall that cannot be passed through. "
				+ "\n\t1 : This indicates a path that can be walked on. "
				+ "\n\tS : This indicates the start of the maze. "
				+ "\n\tE : This indicates the exit of the maze.");

		Label ta = new Label(txtFileReq);
		mid.getChildren().add(ta);
		mid.getStyleClass().add("req");
		Label menuTitle = new Label("Start Menu");
		menuTitle.getStyleClass().add("title");
		menu.getChildren().add(loadButton);
		setTop(menuTitle);
		setCenter(ta);
		setBottom(menu);
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
		if (maze.map[row][col].getTileType() == '0') {
			tile.getStyleClass().add("wall"); // sets up inside walls
		} else if (maze.map[row][col].getTileType() == '1') {
			tile.getStyleClass().add("space"); // sets up empty spaces
		} else if (maze.map[row][col].getTileType() == 'E'){
			tile.getStyleClass().add("end"); // sets up the end tile
			tile.setText("E");
		} else {
			tile.getStyleClass().add("start"); // sets up the end tile
			tile.setText("S");
		}		
	}

	private void setUpFooter() {
		Label status = new Label("");
		status.getStyleClass().add("status");

		Button solveButton = new Button("Solve the Maze");
		solveButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				if (solved) {
					status.setText("Maze Already Solved!");
					status.getStyleClass().clear();
					status.getStyleClass().add("notSolved");
				} else {
					solved = maze.solve(maze.start);
					if (solved) {
						showPath();
						status.setText("Maze Solved!");
						status.getStyleClass().clear();
						status.getStyleClass().add("solved");
					} 
					else {
						status.setText("Maze Cannot Be Solved!");
						status.getStyleClass().clear();
						status.getStyleClass().add("notSolved");
					}		
				}
			}
		});

		footer = new HBox();
		footer.getChildren().add(solveButton);
		footer.getChildren().add(status);
		footer.getStyleClass().add("footer");
	}

	private void showPath() {
		LinkedStack<Coordinate> path = new LinkedStack<>();
		path = maze.stack;
		path.pop();

		while (path.size() > 1) {
			Coordinate c = path.pop();
			labels[c.getRow()][c.getCol()].getStyleClass().clear();
			labels[c.getRow()][c.getCol()].getStyleClass().add("path");		
		}

	}
}
