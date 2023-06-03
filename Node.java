import java.util.*;

public class Node {
	//Initializes initial board, the solution, g as current distance to board, h, and f, a childList, and a parent Node.
	Board Start;
	Board Solution;
	int g = 0,h,f;
	List<Node> childList;
	Node parent;
	
	//Constructor for the board, sets all the needed states, g = 0 as initial board is farthest away, sets h as total Manhattan distance to solution,
	//f to g + h, and initializes the childList as a new list.
	Node(Board Start, Board Solution){
		this.Start = Start;
		this.Solution = Solution;
		this.g=0;
		this.h = distance(Solution);
		this.f = g + h;
		this.childList = new LinkedList<>();
	}
	
	//Calculates the total Manhattan distance of each misplaced piece on the board to the solution board.
	public int distance(Board Solution){
		int d=0;
		int x1=0,x2=0,y2=0,y1=0;
		int find;
		for(int j = 0; j < 3 ; j++) {
			for(int i = 0; i < 3; i++)
			{
				x1 = i;
				y1 = j;
				find = this.Start.State[i][j];
				for(int col = 0; col < 3; col++) {
					for(int row = 0; row < 3; row++) {
						if(Solution.State[row][col] == find) {
							x2 = row;
							y2 = col;
						}
					}
				}
				d += Math.abs(x2 - x1) + Math.abs(y2 - y1);
			}	
		}
		return d;
	}
	
	//Check for if the board is the solution of the puzzle.
	public boolean isSolution() {
		if(this.Start.isEqual(Solution)){
			return true;
		}
		else return false;
	}
	
	//A* Algorithm
	public String aStar() {
		//Initializes 3 lists, openList, closedList, and usedPuzzles.
		List<Node> openList = new LinkedList<>();
		List<Node> closedList = new LinkedList<>();
		List<Board> usedPuzzles = new LinkedList<>();
		//adds initial board 'this' to the open list, and sets it to current.
		openList.add(this);
		Node current = this;

		//Starts loop for algorithm, while the openList is not empty this will continue running.
		while(!openList.isEmpty()) {
			//This block will find the node in openList with the lowest f value.
			int minF = Integer.MAX_VALUE;
			int nodeNum = Integer.MAX_VALUE;
			int count = 0;
			for(Node n : openList) {
				if(n.f < minF) {
					minF = n.f;
					nodeNum = openList.indexOf(n);
					System.out.println(nodeNum + " !!");
				}
			}
			
			//Set current to node with lowest f value, and add it to the usedPuzzles, as we dont want to keep checking puzzles we've already seen.
			current = openList.get(nodeNum);
			usedPuzzles.add(current.Start);
			
			//Check if current is the solution.
			if(current.isSolution()){
				String out = "";
				out += "Solution Found!\n";
				//Initializes list which will store all the nodes to the solution.
				List<Node> finalList = new LinkedList<>();
				while(current.parent != null) {
					finalList.add(current);
					current = current.parent;
				}
				
				//prints the finalList backwards, as the starting node will be the solution, and the end is the beginning.
				Start.printPuzzle();
				for(int z = finalList.size() - 1; z > -1; z--) {
					out += finalList.get(z).Start.printPuzzle();
				}
				//Returns full string of path
				return out;
			}
			
			//Generates left if possible, sets its parent to the current node, increments g value, and if the puzzle hasn't been used, add it to childList.
			if(current.Start.generateLeft() != null){
				Node l = new Node(current.Start.generateLeft(),Solution);
				l.parent = current;
				l.g++;
				if(!usedPuzzles.contains(l.Start)) {
					current.childList.add(l);
				}
			}
			
			//Generates right if possible, sets its parent to the current node, increments g value, and if the puzzle hasn't been used, add it to childList.
			if(current.Start.generateRight() != null){
				Node r = new Node(current.Start.generateRight(),Solution);
				r.parent = current;
				r.g++;
				if(!usedPuzzles.contains(r.Start)) {
					current.childList.add(r);
				}
			}
			
			//Generates up if possible, sets its parent to the current node, increments g value, and if the puzzle hasn't been used, add it to childList.
			if(current.Start.generateUp() != null){
				Node u = new Node(current.Start.generateUp(),Solution);
				u.parent = current;
				u.g++;
				if(!usedPuzzles.contains(u.Start)) {
					current.childList.add(u);
				}
			}
			
			//Generates down if possible, sets its parent to the current node, increments g value, and if the puzzle hasn't been used, add it to childList.
			if(current.Start.generateDown() != null){
				Node d = new Node(current.Start.generateDown(),Solution);
				d.parent = current;
				d.g++;
				if(!usedPuzzles.contains(d.Start)) {
					current.childList.add(d);
				}
			}
			
			//runs through childList, and adds them to the open list.
			for(Node child : current.childList) {
				
				openList.add(child);
				count++;
				System.out.println(count);
			}
			
			//Removes current node from openList and adds it to the closedList.
			openList.remove(current);
			closedList.add(current);
		}
		return "No Solution";
	}
}

