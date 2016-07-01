package tree;

import data.structure.Node;

public class CompleteTreeNodeCount {

	public static int nodeCount(Node<Integer> node) {
		if (node == null) return 0;
		int leftDepth = getLeftDepth(node);
		int rightDepth = getRightDepth(node);
		
		if (leftDepth == rightDepth) {
			return (int) (1 + (Math.pow(2, leftDepth + 1)) * 2);
		} else if (rightDepth > leftDepth){
			return (int) (1 + Math.pow(2, leftDepth + 1) + nodeCount(node.right));
		} else {
			throw new RuntimeException("Program should not come here since it's a complete tree");
		}
	}
	
	private static int getLeftDepth(Node<Integer> node) {
		int cnt = 0;
		while (node.left != null) {
			cnt++;
		}
		return cnt;
	}
	
	private static int getRightDepth(Node<Integer> node) {
		int cnt = 0;
		while (node.right != null) {
			cnt++;
		}
		return cnt;
	}
}
