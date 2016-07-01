package data.structure;

import java.util.LinkedList;

public class Node<T> {

	public T value;
	public Node<T> left;
	public Node<T> right;
	
	public Node(T value) {
		this.value = value;
	}
	
	public boolean isLeaf() {
		return left == null && right == null;
	}
	
	public static Node<Integer> createTestTree() {
		Node<Integer> root = new Node<Integer>(1);
		root.left = new Node<Integer>(5);		
		root.right = new Node<Integer>(3);
		root.left.left = new Node<Integer>(6);
		root.left.right = new Node<Integer>(4);
		return root;
	}
	
	public void printTree() {
		LinkedList<Node<T>> queue = new LinkedList<>();
		queue.add(this);
		
		int maxDepth = 2;
		LinkedList<Node<T>> children = new LinkedList<>();
		int level = 0;
		while(level <= maxDepth) {
			children.addAll(queue);
			queue.clear();
			while(!children.isEmpty()) {
				Node<T> p = children.poll();
				printSpace((int) (Math.pow(2, maxDepth)/(Math.pow(2, level)+1)-1));
				if (p == null) {
					queue.add(null);
					queue.add(null);
					printSpace(1);
				} else {
					queue.add(p.left);
					queue.add(p.right);
					System.out.print(p.value);
				}	
			}
			System.out.println();
			level++;
		}
	}
	
	private void printSpace(int n) {
		for(int i=0; i<n; i++) {
			System.out.print(" ");
		}
	}
}
