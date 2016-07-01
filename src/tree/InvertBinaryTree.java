package tree;

import org.junit.Test;

import data.structure.Node;

public class InvertBinaryTree {
	
	private static Node<Integer> invert(Node<Integer> node) {
		
		if (node == null) return null;
		
		Node<Integer> invertedLeft = invert(node.left);
		Node<Integer> invertedRight = invert(node.right);
		node.right = invertedLeft;
		node.left = invertedRight;
		return node;
	}

	@Test
	public void test() {
		Node<Integer> root = Node.createTestTree();
		root.printTree();
		invert(root).printTree();
	}
}
