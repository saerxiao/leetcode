package tree;

import org.junit.Test;

import data.structure.Node;

public class BinaryTreeMaxSum {
	
	int maxSum = 0;
	
	private int maxPathSum(Node<Integer> node) {		
		if (node == null) return 0;
		
		int sum = Math.max(node.value + maxPathSum(node.left) + maxPathSum(node.right), 0);
		maxSum = Math.max(sum, maxSum);
		return sum;
	}

	@Test
	public void test() {
		Node<Integer> root = Node.createTestTree();
		maxPathSum(root);
		System.out.println(maxSum);
	}
}
