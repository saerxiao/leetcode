package strings.arrays;

import org.junit.Test;

public class MeanOfTwoSortedArray {
	
	public double findMean(int[] A, int[] B) {
		boolean isOdd = (A.length + B.length) % 2 == 1;
		if (isOdd) {
			return findKth(A, B, (A.length + B.length) / 2 + 1);
		} else {
			return (findKth(A, B, (A.length + B.length) / 2)
					+ findKth(A, B, (A.length + B.length) / 2 + 1)) * 0.5;
		}
	}

	public int findKth(int[] A, int[] B, int k) {
		int[] fromArray = A;
		int fromLow = 0;
		int fromHi = fromArray.length - 1;
		int[] searchArray = B;
		int searchLow = 0;
		int searchHi = searchArray.length - 1;		
		while (fromLow <= fromHi && searchLow <= searchHi) {
			int i = (fromLow + fromHi) / 2;
			int j = findFirstOrPredecessor(searchArray, searchLow, searchHi, fromArray[i]);
			if ((i + 1 + j + 1) == k) {
				return fromArray[i];
			} else {				
				if ((i + 1 + j + 1) > k) {
					int tmp = searchLow;
					searchLow = fromLow;
					searchHi = i - 1;
					fromLow = tmp;
					fromHi = j;
				} else {
					searchLow = i + 1;
					int tmp = searchHi;
					searchHi = fromHi;
					fromLow = j + 1;
					fromHi = tmp;
				}
				int[] tmpArray = fromArray;
				fromArray = searchArray;
				searchArray = tmpArray;
			}
		}
		if (fromLow > fromHi) {
			// mean is in searchArray
			return searchArray[k - 1];
		} else {
			// mean is in fromArray
			return fromArray[k - 1];
		}
	}
	
	private int findFirstOrPredecessor(int[] array, int low, int hi, int t) {
		int l = low;
		int h = hi;
		while (l < h) {
			int mid = (l + h) / 2;
			if (t > array[mid]) {
				l = mid + 1;
			} else {
				h = mid;
			}
		}
		if (array[l] > t) {
			return l - 1;
		} else {
			return l;
		}		
	}
	
//	@Test
	public void testBS() {
		int[] array = new int[] {3,5,5};
		int t = 5;
		System.out.println(this.findFirstOrPredecessor(array, 0, array.length - 1, t));
	}
	
	@Test
	public void test() {
		int[] A = new int[] {13,15,18};
		int[] B = new int[] {1,4,6};
		System.out.println(findMean(A, B));
	}
}
