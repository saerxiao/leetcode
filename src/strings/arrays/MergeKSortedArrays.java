package strings.arrays;

import java.util.ArrayList;
import java.util.List;

import data.structure.Heap;
import data.structure.MinHeap;

public class MergeKSortedArrays {

	private List<Integer> merge(List<List<Integer>> arrays) {
		List<Integer> merged = new ArrayList<>();
		Heap<Item> heap = new MinHeap<> ();
		initializeHeap(arrays, heap);
		
		while(!heap.isEmpty()) {
			Item current = heap.pop();
			merged.add(current.value);
			if (current.index + 1 < arrays.get(current.arrayId).size()) {
				heap.add(new Item(current.arrayId, 
						current.index + 1, arrays.get(current.arrayId).get(current.index + 1)));
			}
		}
		return merged;
	}
	
	private void initializeHeap(List<List<Integer>> arrays, Heap<Item> heap) {
		for (int i=0; i<arrays.size(); i++) {
			heap.add(new Item(i, 0, arrays.get(i).get(0)));
		}
	}
	
	private static class Item implements Comparable<Item> {
		int arrayId;
		int index;
		int value;
		
		Item(int arrayId, int index,  int value) {
			this.arrayId = arrayId;
			this.index = index;
			this.value = value;
		}

		@Override
		public int compareTo(Item o) {
			return this.value - o.value;
		}
	}
}
