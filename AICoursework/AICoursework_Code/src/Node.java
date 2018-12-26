import java.util.*;

public class Node {
	private int row = 4;
	private int col = 4;
	private char [][] positions = new char [row][col];
	private Node parent;

	private List<Node> children;
	private int agentRow;
	private int agentCol;
	private int level;
	
	public Node(char[][] positions) {
		this.positions = positions;
		this.children = new ArrayList<Node>();
	}
	
	public void allMoves(char[][] positions) {
		moveAgentRight(positions);
		moveAgentLeft(positions);
		moveAgentUp(positions);
		moveAgentDown(positions);
	}
	
	private void moveAgentRight(char[][] positions) {
		if(agentCol % col < col - 1) {
			char [][] newPositions = new char [row][col];
			makeCopy(positions, newPositions);
			
			char temp = newPositions[agentRow][agentCol + 1];
			newPositions[agentRow][agentCol + 1] = newPositions[agentRow][agentCol];
			newPositions[agentRow][agentCol] = temp;
			
			Node child = new Node(newPositions);
			child.setAgentLocation(agentRow, agentCol + 1);
			child.parent = this;
			child.setLevel(child.parent.getLevel() + 1);
			children.add(child);
		}
	}
	
	private void moveAgentLeft(char[][] positions) {
		if(agentCol % col > 0) {
			char[][] newPositions = new char [row][col];
			makeCopy(positions, newPositions);
			
			char temp = newPositions[agentRow][agentCol - 1];
			newPositions[agentRow][agentCol - 1] = newPositions[agentRow][agentCol];
			newPositions[agentRow][agentCol] = temp;
			
			Node child = new Node(newPositions);
			child.setAgentLocation(agentRow, agentCol - 1);
			child.parent = this;
			child.setLevel(child.parent.getLevel() + 1);
			children.add(child);
		}
	}
	
	private void moveAgentUp(char[][] positions) {
		if(agentRow % row > 0) {
			char[][] newPositions = new char [row][col];
			makeCopy(positions, newPositions);
			
			char temp = newPositions[agentRow - 1][agentCol];
			newPositions[agentRow - 1][agentCol] = newPositions [agentRow][agentCol];
			newPositions[agentRow][agentCol] = temp;
			
			Node child = new Node(newPositions);
			child.setAgentLocation(agentRow - 1, agentCol);
			child.parent = this;
			child.setLevel(child.parent.getLevel() + 1);
			children.add(child);
		}
	}
	
	private void moveAgentDown(char[][] positions) {
		if(agentRow % row < row - 1) {
			char[][] newPositions = new char [row][col];
			makeCopy(positions, newPositions);
			
			char temp = newPositions[agentRow + 1][agentCol];
			newPositions[agentRow + 1][agentCol] = newPositions[agentRow][agentCol];
			newPositions[agentRow][agentCol] = temp;
			
			Node child = new Node(newPositions);
			child.setAgentLocation(agentRow + 1, agentCol);
			child.parent = this;
			child.setLevel(child.parent.getLevel() + 1);
			children.add(child);
		}
		
	}
	
	public boolean gridExists(char [][] positions) {
		boolean isSame = true;
		for(int i=0; i<row; i++) {
			for(int j=0; j<col; j++) {
				if(this.positions[i][j] != positions[i][j]) {
					isSame = false;
				}
			}
		}
		return isSame;
	}
	
	public boolean isFinalState() {
		
		boolean flag = false;
		if(this.positions[1][1] == 'A' && this.positions[2][1] == 'B' && this.positions[3][1] == 'C') {
			return true;
		}
		return flag;
	}
	
	private void makeCopy(char[][] a, char[][] b) {
		for (int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				b[i][j] = a[i][j];
			}
		}
	}
	
	public void setAgentLocation(int r, int c) {
		this.agentRow = r;
		this.agentCol = c;
	}
	
	public void printGrid() {
		for(int i=0; i<positions.length; i++) {
			for(int j=0; j< positions.length; j++) {
				System.out.print(positions[i][j]);
			}
			System.out.println();
		}
		
	}
	
	public List<Node> getChildren(){
		return this.children;
	}
	
	public char[][] getPositions(){
		return this.positions;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public int manhattanDistance(char [][] positions) {
		int a = 0;
		int b = 0;
		int c = 0;
		
		for(int i=0; i<row; i++) {
			for(int j=0; j< col; j++) {
				if (positions[i][j] == 'A') {
					a = Math.abs(1 - i) + Math.abs(1 - j);
				}
				if (positions[i][j] == 'B') {
					b = Math.abs(2 - i) + Math.abs(1 - j);
				}
				if (positions[i][j] == 'C') {
					c = Math.abs(3 - i) + Math.abs(1 - j);
				}
			}
		}
		return a + b + c;
	}
	
	public Node getParent() {
		return parent;
	}
}
