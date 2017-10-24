package stack_maze;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.stage.FileChooser;

public class Maze {
	int rows;
	int cols;
	Coordinate start;
	Coordinate[][] map;
	LinkedStack<Coordinate> stack = new LinkedStack<>();
	
	public Maze() {
		FileChooser fc = new FileChooser();
		File data = fc.showOpenDialog(null);
		
		if (data != null) {
			try {
				@SuppressWarnings("resource")
				Scanner s = new Scanner(data);
				int counter = 0;
				
				while (s.hasNext()) {
					String st = String.valueOf(s.nextLine());
								
					if (counter == 0) {
						String[] split = st.split(" ");
						rows = Integer.parseInt(split[0]);
						cols = Integer.parseInt(split[1]);
						map = new Coordinate[rows][cols];
					} else if (counter == 1) {
						String[] split = st.split(" ");
						start = new Coordinate(Integer.parseInt(split[0]), Integer.parseInt(split[1]), 'S');
					} else {
						for (int j = 0 ; j < cols ; j++) {				
							map[counter-2][j] = new Coordinate(counter-2, j, st.charAt(j));
						}
					}
					
					counter++;
				}
					
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	public LinkedStack<Coordinate> getChildren(Coordinate c) {
		LinkedStack<Coordinate> children = new LinkedStack<>();
		int n = c.getRow();
		int m = c.getCol();
		
		if (isValid(n+1, m) && isClear(map[n+1][m])) {
			children.push(map[n+1][m]);
		}
		if (isValid(n-1, m) && isClear(map[n-1][m])) {
			children.push(map[n-1][m]);
		}
		if (isValid(n, m+1) && isClear(map[n][m+1])) {
			children.push(map[n][m+1]);
		}
		if (isValid(n, m-1) && isClear(map[n][m-1])) {
			children.push(map[n][m-1]);
		}
		
		return children;
	}
	
	private boolean isClear(Coordinate c) {
		if (c.getTileType() != '0' && !c.haveTried())
			return true;
		
		return false;
	}
	
	private boolean isValid(int row, int col) {
		if (row >= 0 && row < rows) 
			if (col >= 0 && col < cols)
				return true;
		
		return false;
	}
	
	public boolean solve(Coordinate c) {
		LinkedStack<Coordinate> children = new LinkedStack<>();
		stack.push(c);
		c.setTried();

		while(stack.size() > 0) {
			stack.top().setTried();
			children = getChildren(stack.top());

			if (children.size() == 0) {
				if (stack.top().getTileType() == 'E') {
					return true;
				} 
				else {
					stack.pop();
				}
			}
			else {
				if (children.size() > 0) {
					stack.push(children.top());
					children.pop().setTried();
				} 
				else {
					stack.pop();
				}
			}
		}
		
		return false;
	}	
}
