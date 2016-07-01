package strings.arrays;

import org.junit.Test;

public class ArrangeArray {

	public void arrange(int[] A) {
		int first0 = -1;
		int firstPos = -1;
		for (int p = 0; p < A.length; p++) {
			if (A[p] == 0) {
				if (firstPos > -1) {
					swap(A, firstPos, p);
					if (first0 == -1) {
						first0 = firstPos;						
					}
					firstPos++;
				} else {
					if (first0 == -1) {
						first0 = p;
					}
				}
			} else if (A[p] < 0) {
				if (firstPos > -1) {
					swap(A, firstPos, p);
					if (first0 > -1) {
						swap(A, firstPos, first0);
						first0++;
					}
					firstPos++;
				} else {
					if (first0 > -1) {
						swap(A, first0, p);
						first0++;
					}
				}
			} else {
				if (firstPos == -1) {
					firstPos = p;
				}
			}
//			print(A);
		}
	}
	
	private void print(int[] A) {
		for(int i = 0; i < A.length; i++) {
			System.out.print(A[i] + ", ");
		}
		System.out.println();
	}
	
	private void swap(int[] A, int p1, int p2) {
		int tmp = A[p1];
		A[p1] = A[p2];
		A[p2] = tmp;
	}
	
	@Test
	public void test() {
		int[][] arrays = new int[][] {{}, {0,1}, {0,-1}, {-1,0}, {-1,1}, {1,0}, {1,-1},
			{-1,0,1}, {-1,1,0},{0,-1,1}, {0,1,-1}, {1,0,-1}, {1,-1,0},
			{2,-9,34,0,232,-78,0,0,9,-5,32,-5,0}};
		for (int i=0; i < arrays.length; i++) {
			int[] array = arrays[i];
			arrange(array);
			for (int j = 0; j < array.length; j++) {
				System.out.print(array[j] + ", ");
			}
			System.out.println();
		}		
 	}
	
//	@Test
	public void testSingle() {
//		int[] A = new int[] {1,-1,0};
		int[] A = new int[] {1,0,-1};
		arrange(A);
		for (int j = 0; j < A.length; j++) {
			System.out.print(A[j] + ", ");
		}
	}
}
