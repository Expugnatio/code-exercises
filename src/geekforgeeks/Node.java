package geekforgeeks;

import java.util.function.Function;
// INITIAL CODE
import java.util.*;
import java.lang.*;
import java.io.*;

// A Binary Tree node
class Node {
	int data;
	Node left, right;

	Node(int item) {
		data = item;
		left = right = null;
	}
}

class Level_Order_Traversal {

	// driver function to test the above functions
	public static void main(String args[]) {

		// Input the number of test cases you want to run
		Scanner sc = null;
		File input = new File("test_cases/spiral_tree.txt");
		//Scanner sc = new Scanner(System.in);
		try {
			sc = new Scanner(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		int t = sc.nextInt();
		// Node root=null;
		while (t > 0) {
			HashMap<Integer, Node> m = new HashMap<Integer, Node>();
			int n = sc.nextInt();
			Node root = null;
			while (n > 0) {
				int n1 = sc.nextInt();
				int n2 = sc.nextInt();
				char lr = sc.next().charAt(0);
				// cout << n1 << " " << n2 << " " << (char)lr << endl;
				Node parent = m.get(n1);
				if (parent == null) {
					parent = new Node(n1);
					m.put(n1, parent);
					if (root == null)
						root = parent;
				}
				Node child = new Node(n2);
				if (lr == 'L')
					parent.left = child;
				else
					parent.right = child;
				m.put(n2, child);
				n--;
			}

			Spiral g = new Spiral();
			g.printSpiral(root);
			System.out.println();
			t--;

		}
	}
}

/*
 * This is a function problem.You only need to complete the function given below
 */
/*
 * // A Binary Tree node class Node { int data; Node left, right; Node(int item)
 * { data = item; left = right = null; } }
 */
class Spiral {
	void printSpiral(Node node) {
		
		Boolean leftToRight = true;
		
		List<Node> orderedNodes = checkNextLevel(node, leftToRight, new ArrayList<>()); 
		
		
		orderedNodes.forEach(n -> System.out.println(n.data+" :: "));
				
	}
	
	private List<Node> checkNextLevel(Node node, Boolean leftToRight, List<Node> orderedNodes) {
		
		Node root = null;
		if(leftToRight) {
			if(node.left != null) {
				orderedNodes.add(node.left);
			}
			if(node.right != null) {
				orderedNodes.add(node.right);
				root = node.right;
			}
			leftToRight = false;
		}else {
			if(node.right != null) {
				orderedNodes.add(node.right);
			}
			if(node.left != null) {
				orderedNodes.add(node.left);
				root = node.left;
			}
			leftToRight = true;
		}
		
		if(root != null) {
			return checkNextLevel(root, leftToRight, orderedNodes);
		}else {
			return orderedNodes;
		}
	}
}
