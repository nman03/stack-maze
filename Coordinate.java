package stack_maze;

public class Coordinate {
	private int row;
	private int col;
	private char tileType;
	private boolean haveTried;
	private LinkedStack<Coordinate> children;
	
	public Coordinate(int row, int col, char tileType) {
		this.row = row;
		this.col = col;
		this.tileType = tileType;
		this.haveTried = false;
	}
	
	public LinkedStack<Coordinate> getChildren() {
		return children;
	}
	
	
	public void setChildren(LinkedStack<Coordinate> children) {
		this.children = children;
	}
	
	public boolean haveTried(){
		return haveTried;
	}
	
	public void setTried() {
		haveTried = true;
	}
	
	public int getRow(){
		return row;
	}
	
	public void setRow(int row) {
		this.row = row;
	}

	public int getCol(){
		return col;		
	}
	public void setCol(int col) {
		this.col = col;
	}

	public char getTileType() {
		return tileType;
	}

	public void setTileType(char tileType) {
		this.tileType = tileType;
	}
}
