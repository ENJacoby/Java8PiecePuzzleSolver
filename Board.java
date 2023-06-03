
public class Board {
	//Initalize State (the Puzzle) and its X and Y value (The area the 0 is currently) 
	int[][] State = new int[3][3];
	int x = Integer.MAX_VALUE;
	int y = Integer.MAX_VALUE;
	
	//Board constructor
	Board(int[][] State) {
		this.State = State;
		this.x = findX();
		this.y = findY();
	}
	
	//Simple function to copy 2 boards
	int[][] copyState(int[][] Subject){
		int[][] copy = new int[3][3];
		for(int y = 0; y < 3; y++) {
			for(int x  = 0; x < 3; x++) {
				copy[x][y] = Subject[x][y];
			}
		}
		return copy;
	}
	
	//Function to print out the boards puzzle
	public String printPuzzle() {
		String out = "";
		for(int y = 0; y < this.State.length; y++) {
			for(int x = 0; x < this.State.length; x++) {
				out += this.State[x][y] + " ";
			}
			out += "\n";
		}
		out += "\n";
		return out;
	}
	
	//Find X function, finds the X value of 0.
	public int findX(){
		int find = Integer.MAX_VALUE;
		for(int j = 0; j < this.State.length; j++) {
			for(int i = 0; i < this.State.length; i++) {
				if(this.State[i][j] == 0) {
					find = i;
				}
			}
		}
		return find;
	}
	
	//Find Y function, finds the Y value of 0.
	public int findY() {
		int find = Integer.MAX_VALUE;
		for(int j = 0; j < this.State.length; j++) {
			for(int i = 0; i < this.State.length; i++) {
				if(this.State[i][j] == 0) {
					find = j;
				}
			}
		}
		return find;
	}
	
	//Comparison function, returns true if the Compare board's puzzle is equal to this Boards puzzle.
	public boolean isEqual(Board Compare) {
		int count = 0;
		for(int col = 0; col < 3; col++) {
			for(int row = 0; row < 3; row++) {
				if(this.State[row][col] == Compare.State[row][col]) {
					count++;
				}
			}
		}
		if(count == 9) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	//Generates a new board where the values are swapped left, if it cannot, it returns Null.
	Board generateLeft(){
		int[][] tempState = copyState(this.State);
		if(this.x-1 == 0 || this.x-1 == 1) {
			tempState[x][y] = this.State[x-1][y];
			tempState[x-1][y] = 0;
			Board newBoard = new Board(tempState);
			return newBoard;
		}
		else return null;
	}
	
	//Generates a new board where the values are swapped right, if it cannot, it returns Null.
	Board generateRight(){
		int[][] tempState = copyState(this.State);
		if(this.x+1 == 1 || this.x+1 == 2) {
			tempState[x][y] = this.State[x+1][y];
			tempState[x+1][y] = 0;
			Board newBoard = new Board(tempState);
			return newBoard;
		}
		else return null;
	}
	
	//Generates a new board where the values are swapped up, if it cannot, it returns Null.
	Board generateUp(){
		int[][] tempState = copyState(this.State);
		if(this.y-1 == 0 || this.y-1 == 1) {
			tempState[x][y] = this.State[x][y-1];
			tempState[x][y-1] = 0;
			Board newBoard = new Board(tempState);
			return newBoard;
		}
		else return null;
	}
	
	//Generates a new board where the values are swapped down, if it cannot, it returns Null.
	Board generateDown(){
		int[][] tempState = copyState(this.State);
		if(this.y+1 == 1 || this.y+1 == 2) {
			tempState[x][y] = this.State[x][y+1];
			tempState[x][y+1] = 0;
			Board newBoard = new Board(tempState);
			return newBoard;
		}
		else return null;
		}
}
