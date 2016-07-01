package bits;

import org.junit.Test;

public class SingleNumber {

	public int singleInThrees(int[] A) {
		int ones = 0, twos = 0, threes = 0;
		for (int i = 0; i < A.length; i++) {
			twos |= ones & A[i]; // now twos has both numbers showing 2 times and 3 times
			ones ^= A[i]; // now ones has both numbers showing 1 time and 3 times
			threes = ones & twos;
			ones &= (~threes); // subtract number showing 3 times from ones
			twos &= (~threes); // subtract number showing 3 times from twos
		}
		return ones;
	}
	
	@Test
	public void test() {
		int[] A = new int[] {2,5,2,5,2,3,5};
		System.out.println(singleInThrees(A));
	}
}
