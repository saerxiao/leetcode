package strings.arrays;

import org.junit.Test;

public class RotatedArray {

	public int findMin(int[] A) {
		int low = 0;
		int hi = A.length -1;
		int minIndex = 0;
		while (low < hi) {
			int mid = (low + hi) / 2;
			if (A[low] <= A[mid]) {
				if (A[low] < A[minIndex]) {
					minIndex = low;
				}
				low = mid + 1;
			} else {
				hi = mid;
			}
		}
		
		if (low < A.length && A[low] < A[minIndex]) {
			minIndex = low;
		}
		
		return A[minIndex];
	}
	
	public void rotate(int[] A, int minIndex) {
		reverse(A, 0, minIndex);
		reverse(A, minIndex, A.length);
		reverse(A, 0, A.length);
	}
	
	public void reverse(int[] A, int startInclusive, int endExclusive) {
		int p = startInclusive;
		int q = endExclusive -1;
		while (p < q) {
			int tmp = A[p];
			A[p] = A[q];
			A[q] = tmp;
			p++;
			q--;
		}
	}
	
	public int find(int[] A, int t) {
		int low = 0;
		int hi = A.length - 1;
		while (low < hi) {
			int mid = (low + hi) / 2;
			if (A[low] <= A[mid]) {
				if (A[low] == t) {
					return low;
				}
				else if (A[mid] == t) {
					return mid;
				} else if (A[low] < t && t < A[mid]){
					hi = mid - 1;
				} else {
					low = mid + 1;
				}
			} else {
				if (t == A[low]) {
					return low;
				}
				else if (t == A[mid]) {
					return mid;
				} else if ( t > A[low] || t < A[mid]) {
					hi = mid -1;
				} else {
					low = mid +1;
				}
			}
		}
		if (low >= A.length || A[low] != t) {
			return -1;
		} else {
			return low;
		}
	}
	
	public int findFirst(int[] A, int t) {
		int low = 0;
		int hi = A.length - 1;
		while (low < hi) {
			int mid = (low + hi) /2;
			if (A[low] <= A[mid]) {
				if (A[low] == t) {
					return low;
				} else if (A[low] < t && t <= A[mid]) {
					hi = mid;
				} else {
					low = mid + 1;
				}
			} else {
				if (A[low] <= t || A[mid] >= t) {
					hi = mid;
				} else {
					low = mid + 1;
				}
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
//		int[] A = new int[]{0}; //t=0; t=1;
//		int[] A = new int[]{1,2}; //t=0; t=1; t=2; t=3;
//		int[] A = new int[]{1,2,4}; // t=0, 1,2,3,4, 5;
//		int[] A = new int[]{2,1}; //t=0, 1,2,3;
//		int[] A = new int[]{4,1,2};
//		int[] A = new int[]{5,8,1,4};
		int[] A = new int[]{5,8,12,1,4};
		System.out.println(findMin(A));
		
		int t = 3;
		System.out.println(find(A, t));
	}
	
	@Test
	public void testWithDuplicate() {
//		int[] A = new int[]{0,0};
//		int[] A = new int[]{0,0,1};
//		int[] A = new int[]{1,1,0};
//		int[] A = new int[]{1,1,0,0};
//		int[] A = new int[]{1,1,1,1,1,1};
		int[] A = new int[]{3,4,5,5,1,1,1,1,2};
//		int[] A = new int[]{2,2,3,1,2};
//		int[] A = new int[]{2,2,3,4,5,5,1,1,1,1,2};
//		System.out.println(findMin(A));
		
		int t = 1;
		System.out.println(find(A, t));
		System.out.println(findFirst(A, t));
	}
}
