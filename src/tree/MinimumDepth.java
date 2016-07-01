package tree;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.event.ListSelectionEvent;

import org.junit.Test;

import data.structure.Node;

public class MinimumDepth {

	int minDepth = Integer.MAX_VALUE;
	List<Node<Integer>> minPath = new LinkedList<Node<Integer>>();
	
	private void minimumPath(Node<Integer> node, LinkedList<Node<Integer>> path) {
		if (node.isLeaf()) {
			if (path.size() < minDepth) {
				System.out.println("size = " + path.size());
				minDepth = path.size();
				updateMinPath(path);
				return;
			}
		}
		if (node.left != null) {
			path.add(node.left);
			minimumPath(node.left, path);
			path.removeLast();
		}
		if (node.right != null) {
			path.add(node.right);
			minimumPath(node.right, path);
			path.removeLast();
		}
	}
	
	private void updateMinPath(List<Node<Integer>> path) {
		minPath.clear();
		minPath.addAll(path);
	}
	
	private static int findMinPath(Node<Integer> node, LinkedList<Node<Integer>> path, 
			List<Node<Integer>> minPath, int minDepth) {
		if (node.isLeaf()) {
			if (path.size() < minDepth) {
				System.out.println("size = " + path.size());
				minPath.clear();
				minPath.addAll(path);
				return path.size();
			} else {
				return minDepth;
			}
		}
		
		int newMinDepth = minDepth;
		if (node.left != null) {
			path.add(node.left);
			newMinDepth = findMinPath(node.left, path, minPath, newMinDepth);
			path.removeLast();
		}
		if (node.right != null) {
			path.add(node.right);
			newMinDepth = findMinPath(node.right, path, minPath, newMinDepth);
			path.removeLast();
		}
		return newMinDepth;
	}
	
	//@Test
	public void test() {
		Node<Integer> root = Node.createTestTree();
		List<Node<Integer>> minPath = new LinkedList<Node<Integer>>();
		LinkedList<Node<Integer>> path = new LinkedList<Node<Integer>>();
		path.add(root);
		findMinPath(root, path, minPath, Integer.MAX_VALUE);
		printList(minPath);
	}
	
	@Test
	public void test1() {
		Node<Integer> root = Node.createTestTree();
		MinimumDepth minimumDepth = new MinimumDepth();
		LinkedList<Node<Integer>> path = new LinkedList<Node<Integer>>();
		path.add(root);
		minimumDepth.minimumPath(root, path);
		printList(minimumDepth.minPath);
	}
	
	private void printList(List<Node<Integer>> list) {
		Iterator<Node<Integer>> it = list.iterator();
		while (it.hasNext()) {
			System.out.println(it.next().value);
		}
	}
}
