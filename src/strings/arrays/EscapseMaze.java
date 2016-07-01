package strings.arrays;

import org.junit.Test;

public class EscapseMaze {

	public boolean canEscape(int[] A) {
		int maxPos = 0;
		while (maxPos < A.length) {
			int nextMax = maxPos;
			for (int i = 1; i <= A[maxPos]; i++) {
				nextMax = Math.max(nextMax, maxPos + i + A[i]);
			}
			if (nextMax == maxPos) return false;
			maxPos = nextMax;
		}
		return true;
	}
	
	@Test
	public void test() {
		int[] A = new int[] {2,1,0};
		System.out.println(canEscape(A));
	}
}
