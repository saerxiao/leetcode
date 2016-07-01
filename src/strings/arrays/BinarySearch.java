package strings.arrays;

import org.junit.Test;

public class BinarySearch {

	public int findNoDupe(int[] A, int t) {
		int low = 0;
		int hi = A.length - 1;
		
		while ( low < hi) {
			int mid = (low + hi) / 2;
			if (A[mid] < t) {
				low = mid + 1;
			} else {
				hi = mid;
			}
		}
		if (A[low] == t) {
			return low;
		} else {
			return -1;
		}
	}
	
	public int findFirst(int[] A, int t) {
		int low = 0;
		int hi = A.length - 1;
		while ( low < hi) {
			int mid = (low + hi) / 2;
			if ( t <= A[mid]) {
				hi = mid;
			} else {
				low = mid + 1;
			}
		}
		if (A[low] == t) {
			return low;
		} else {
			return -1;
		}
	}
	
	public int findLast(int[] A, int t) {
		int low = 0;
		int hi = A.length - 1;
		while (low < hi) {
			int mid = (low + hi) / 2 + 1;
			if (t >= A[mid]) {
				low = mid;
			} else {
				hi = mid - 1;
			}
		}
		if (A[low] == t) {
			return low;
		} else {
			return -1;
		}
	}
	
//	@Test
	public void test() {
//		int[] A = new int[]{1};
//		int[] A = new int[]{1, 2};
//		int[] A = new int[]{1, 12, 20};
		int[] A = new int[]{1, 12, 20, 31};
		System.out.println(findNoDupe(A, 31));
	}
	
	@Test
	public void testDupe() {
//		int[] A = new int[]{1};
//		int[] A = new int[]{1,1};
//		int[] A = new int[]{0,1,1};
		int[] A = new int[]{0,0,1,1,1,3,3};
		System.out.println(findFirst(A, 0));
		System.out.println(findLast(A, 0));
	}
}
