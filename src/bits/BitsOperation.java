package bits;

import org.junit.Test;

public class BitsOperation {

	@Test
	public void test() {
		int i = 1, j=2, k=3;
		int leftBy1 = i << 1;
		int lefti = i << 23;
		int leftj = j << 23;
		int leftk = k << 23;
		System.out.println(lefti);
		System.out.println(leftj);
		System.out.println(leftk);
	}
}
