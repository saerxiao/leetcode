package tree;

import java.util.Stack;

import data.structure.Node;

public class BSTIterator<T> {
	
	Stack<Node<T>> stack = new Stack<>();
	
	public BSTIterator(Node<T> root) {
		insertStack(root);
	}
	
	public boolean hasNext() {
		return !stack.isEmpty();
	}
	
	public Node<T> next() {
		if (!hasNext()) {
			return null;
		}
		Node<T> next = stack.pop();
		insertStack(next.right);
		return next;
	}
	
	private void insertStack(Node<T> node) {
		if (node != null) {
			stack.add(node);
			while(node.left != null) {
				stack.add(node.left);
				node = node.left;
			}
		}		
	}

}
