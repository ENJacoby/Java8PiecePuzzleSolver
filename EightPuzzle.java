public class EightPuzzle {

	public static void main(String[] args) {
		
		//Initialize Puzzles
		int[][] puzzle = new int[3][3];
		int[][] solvedPuzzle = new int[3][3];
		
		puzzle[0][0] = 2;
		puzzle[1][0] = 8;
		puzzle[2][0] = 3;
		puzzle[0][1] = 1;
		puzzle[1][1] = 6;
		puzzle[2][1] = 4;
		puzzle[0][2] = 7;
		puzzle[1][2] = 0;
		puzzle[2][2] = 5;
		
		solvedPuzzle[0][0] = 1;
		solvedPuzzle[1][0] = 2;
		solvedPuzzle[2][0] = 3;
		solvedPuzzle[0][1] = 8;
		solvedPuzzle[1][1] = 0;
		solvedPuzzle[2][1] = 4;
		solvedPuzzle[0][2] = 7;
		solvedPuzzle[1][2] = 6;
		solvedPuzzle[2][2] = 5;
		
		//Create Boards for initial puzzle and solution
		Board myBoard = new Board(puzzle);
		System.out.println("Initial Board:\n" + myBoard.printPuzzle());
		Board Solved = new Board(solvedPuzzle);
		System.out.println("Solution Board:\n" + Solved.printPuzzle());
		//Create puzzleSolver to solve the puzzle and run the A* search
		Node puzzleSolver = new Node(myBoard, Solved);
		System.out.print(puzzleSolver.aStar());
		
	}

}
