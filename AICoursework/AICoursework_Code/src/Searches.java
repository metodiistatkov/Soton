import java.util.*;

public class Searches {

	public Searches() {

	}

	public void bFS (Node root) {
		List<Node> checked = new ArrayList<Node>();
		Queue<Node> q = new LinkedList<Node>();
		int cnt = 0;
		root.getChildren().clear();
		q.add(root);
		while(!q.isEmpty()) {
			ArrayList<Node> others = new ArrayList<Node>();
			Node node = q.poll();
			cnt++;
			checked.add(node);
			if (node.isFinalState()) {
				System.out.println(cnt);
				break;
			}

			node.allMoves(node.getPositions());

			for(Node n: node.getChildren()) {
				//this is the graph search version, in order to make it tree search - remove the if condition 
				if(!contained(checked, n)) {
					q.add(n);
				}
			}
		}
	}

	private boolean contained(List<Node> checked, Node curr) {
		boolean flag = false;

		for(Node n: checked) {
			if(n.gridExists(curr.getPositions())) {
				flag = true;
			}
		}
		return flag;
	}

	public int dFS(Node root) {
		List<Node> checked = new ArrayList<Node>();
		Stack<Node> stack = new Stack<Node>();
		root.getChildren().clear();
		stack.push(root);
		int cnt = 0;
		while (!stack.isEmpty()) {
			Node node = stack.pop();
			cnt++;
			checked.add(node);
			if(node.isFinalState()) {
				break;
			}

			node.allMoves(node.getPositions());
			//Collections.shuffle(node.getChildren()); // randomizing the next expanded node so that looping is prevented
			for(Node n: node.getChildren()) {
				if(!contained(checked,n)) {
		//this is the graph search version, in order to make it tree search - remove the if condition and uncomment Collections.shuffle()
					stack.push(n);
				}
			}
		}
		return cnt;
	}


	public int iDS(Node root) {
		List<Node> checked = new ArrayList<Node>();
		Stack<Node> stack = new Stack<Node>();
		stack.push(root);
		int cnt = 0;
		int limit = 0;
		while(!stack.isEmpty()) {
			Node node = stack.pop();
			checked.add(node);
			cnt++;
			if(node.isFinalState()) {
				break;
			}

			if(node.getLevel() < limit) {
				node.allMoves(node.getPositions());

				for(Node n: node.getChildren()) {
					//this is the graph search version, in order to make it tree search - remove the if condition
				//	if(!contained(checked,n)) {
						stack.push(n);
				//	}
				}
			}

			if(stack.isEmpty()) {
				root.getChildren().clear();
				stack.push(root);
				checked.clear();
				limit++;
			}
		}
		return cnt;
	}

	public void aStar(Node root){
		List<Node> checked = new ArrayList<Node>();
		//new priority queue with custom comparator that compares nodes based on their heuristic value
		PriorityQueue<Node> nodes = new PriorityQueue<>(11, (n1,n2) -> heuristics(n1) - heuristics(n2));
		root.getChildren().clear();
		nodes.add(root);
		int cnt = 0;
		while(!nodes.isEmpty()) {
			Node node = nodes.poll();
			checked.add(node);
			cnt++;
			if(node.isFinalState()) {
				System.out.println(cnt);
				break;
			}

			node.allMoves(node.getPositions());
			for(Node n: node.getChildren()) {
				//this is the graph search version, in order to make it tree search - remove the if condition
				if(!contained(checked,n)) {
					nodes.add(n);
				}
			}
		}
	}

	private int heuristics(Node n) {
		return n.getLevel() + n.manhattanDistance(n.getPositions());
	}


}
