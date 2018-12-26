/**
 * If you want to run any of the searches just uncomment the corresponding one
 * If you want to see the path to the nearest solution uncomment the list, which stores it
 */
import java.util.*;

public class Main {


	public static void main(String[] args) {
		char[][] startState = {
				{'*','*','*','*'},
				{'*','*','*','*'},
				{'*','*','*','*'},
				{'A','B','C','@'}
				
		};
		Node root = new Node(startState);
		root.setAgentLocation(3,3);
		root.setLevel(0);

		Searches search = new Searches();
		List<Node> path = findPath(root);
		
		
		//search.bFS(root);
		//search.dFS(root);
		//search.iDS(root);
		search.aStar(root);



	}

	private static List<Node> findPath(Node root){
		List<Node> path = new ArrayList<>();
		List<Node> checked = new ArrayList<Node>();
		Queue<Node> q = new LinkedList<Node>();
		int cnt = 0;
		q.add(root);
		while(!q.isEmpty()) {
			Node node = q.poll();

			cnt++;
			checked.add(node);
			if (node.isFinalState()) {
				int lvl = node.getLevel();
				for (int i =0; i < lvl + 1; i++) {
					path.add(node);
					node = node.getParent();
				}
				System.out.println("Solution Found!");
				break;
			}

			node.allMoves(node.getPositions());
			for(Node n: node.getChildren()) {
				if(!contained(checked, n)) {
					q.add(n);
				}
			}
		}
		return path;
	}

	private static boolean contained(List<Node> checked, Node curr) {
		boolean flag = false;

		for(Node n: checked) {
			if(n.gridExists(curr.getPositions())) {
				flag = true;
			}
		}
		return flag;
	}
}
