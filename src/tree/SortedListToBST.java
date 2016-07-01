package tree;

import java.util.Iterator;
import java.util.LinkedList;

import org.junit.Test;

import data.structure.Node;

public class SortedListToBST {
	
//	static class Node {
//		int value;
//		Node left;
//		Node right;
//		
//		Node (int value) {
//			this.value = value;
//		}
//	}
	
	private Node<Integer> fromArray(int[] array) {
		return arrayToBST(array, 0, array.length);
	}
	
	private Node<Integer> arrayToBST(int[] array, int low, int high) {
		if (low > high) return null;
		if (low == high) return new Node<Integer>(array[low]);
		
		int mid = (low + high) /2;
		Node<Integer> node = new Node<Integer>(array[mid]);
		node.left = arrayToBST(array, low, mid-1);
		node.right = arrayToBST(array, mid+1, high);
		return node;
	}

	private Node<Integer> fromLinkedList(LinkedList<Integer> list) {
		return linkedListToBST(list.iterator(), 0, list.size());
	}
	
	private Node<Integer> linkedListToBST(Iterator<Integer> it, int low, int high) {
		if (low < high) return null;
		if (low == high) return new Node<Integer>(it.next());
		
		int mid = (low + high) / 2;
		Node<Integer> left = linkedListToBST(it, low, mid-1);
		Node<Integer> node = new Node<Integer>(it.next());
		Node<Integer> right = linkedListToBST(it, mid+1, high);
		
		node.left = left;
		node.right = right;
		return node;
	}
	
	@Test
	public void test() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(1);
		list.add(2);
		
		Iterator<Integer> it = list.iterator();
//		int cnt = 0;
		while (it.hasNext()) {
			System.out.println(it.next());
//			System.out.println(list.size());
//			cnt++;
		}
	}
}
