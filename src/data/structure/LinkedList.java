package data.structure;

public class LinkedList<T> {
	
	private Node<T> head;
	private Node<T> tail;
	private int size;
	
	public void add(T t) {
		Node<T> node = new Node<T>(t);
		if (head == null) {
			 head = tail = node;
		} else {
			tail.next = node;
			tail = node;
		}
		size++;
	}
	
	public void revert() {
		Node<T> previous = null;
		Node<T> current  = head;
		tail = head;
		while (current != null) {
			Node<T> next = current.next;
			current.next = previous;
			previous = current;
			current = next;
		}
		head = previous;
	}
	
	public ListIterator<T> iterator() {
		return new ListIterator<T>(head);
	}
	
	public static class ListIterator<T> {
		private Node<T> current;
		
		public ListIterator(Node<T> head) {
			 current = head;
		}
		
		public boolean hasNext() {
			return current != null;
		}
		
		public Node<T> next() {
			Node<T> node = current;
			current = current.next;
			return node;
		}
	}
	
	private static class Node<T> {
		T value;
		Node<T> next;
		
		public Node(T t) {
			value = t;
		}
	}

}
