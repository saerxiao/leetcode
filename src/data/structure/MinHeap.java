package data.structure;

import java.util.ArrayList;
import java.util.List;

public class MinHeap<T extends Comparable<T>> implements Heap<T> {
	
	// Should really use T[], but for convenience (handle and double capacity)
	// use List util for now
	List<T> data = new ArrayList<>();

	@Override
	public int getSize() {
		return data.size();
	}

	@Override
	public void add(T t) {
		data.add(t);
		int index = data.size()-1;
		while(index > 0) {
			int parentIndex = index/2 - 1;
			if (t.compareTo(data.get(parentIndex)) < 0 ) {
				swap(index, parentIndex);
				index = parentIndex;
			} else {
				break;
			}
		}
	}

	@Override
	public T pop() {
		if (isEmpty()) return null;
		
		T result = data.get(0);
		data.set(0, data.get(data.size()));
		data.remove(data.size());
		int index = 0;
		while(index < data.size()) {
			int leftChildIndex = (index +1) * 2 -1;
			int rightChildIndex = (index + 1) * 2;
			if (leftChildIndex > data.size() - 1) {
				// is leaf
				break;
			} else if (rightChildIndex > data.size() - 1) {
				// only has left child
				if (data.get(index).compareTo(data.get(leftChildIndex)) > 0) {
					swap(index, leftChildIndex);
					break;
				}
			} else {
				// find the smallest of index, leftChild and rightChild
				int smallest = index;
				if (data.get(leftChildIndex).compareTo(data.get(smallest)) < 0) {
					smallest = leftChildIndex;
				}
				if (data.get(rightChildIndex).compareTo(data.get(smallest)) < 0) {
					smallest = rightChildIndex;
				}
				if (smallest == index) {
					// already heapified
					break;
				} else {
					swap(index, smallest);
					index = smallest;
				}
				
//				if (data.get(index).compareTo(data.get(leftChildIndex)) <= 0  && 
//						data.get(index).compareTo(data.get(rightChildIndex)) <= 0) {
//					// already heapified
//					break;
//				} else if (data.get(index).compareTo(data.get(leftChildIndex)) <= 0) {
//					// index > rightChild
//					swap(index, rightChildIndex);
//					index = rightChildIndex;
//				} else if (data.get(index).compareTo(data.get(rightChildIndex)) <= 0) {
//					// index > leftChild
//					swap(index, leftChildIndex);
//					index = leftChildIndex;
//				} else {
//					// index > leftChild and index > rightChild
//					int swapWith = data.get(leftChildIndex).compareTo(data.get(rightChildIndex)) < 0 ?
//							leftChildIndex : rightChildIndex;
//					swap(index, swapWith);
//					index = swapWith;
//				}
			}
		}
		return result;
	}
	
	private void swap(int i, int j) {
		T tmp = data.get(i);
		data.set(i, data.get(j));
		data.set(j, tmp);
	}

	@Override
	public boolean isEmpty() {
		return getSize() > 0;
	}

}
