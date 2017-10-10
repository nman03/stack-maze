package stack_maze;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.stage.FileChooser;

public class Maze {
	int rows;
	int cols;
	int[] startCoordinates = new int[2];
	String[] map;
	
	public Maze() {
		FileChooser fc = new FileChooser();
		File data = fc.showOpenDialog(null);
		
		if (data != null) {
			try {
				Scanner s = new Scanner(data);
				int counter = 0;
				
				while (s.hasNext()) {
					String st = String.valueOf(s.nextLine());
								
					if (counter == 0) {
						String[] split = st.split(" ");
						rows = Integer.parseInt(split[0]);
						cols = Integer.parseInt(split[1]);
						map = new String[rows];
					} else if (counter == 1) {
						String[] split = st.split(" ");
						startCoordinates[0] = Integer.parseInt(split[0]);
						startCoordinates[1] = Integer.parseInt(split[1]);
					} else {
						map[counter - 2] = st;
					}
					
					counter++;
				}
					
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
