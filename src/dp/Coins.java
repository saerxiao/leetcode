package dp;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class Coins {
	
	public static int smallestNumber(List<Integer> coins, int t) {
		int[] m = new int[t + 1];
		for (int i = 1; i < t + 1; i++) {
			for (int c : coins) {
				if (i - c == 0) {
					m[i] = 1;
				} else if ( i - c > 0) {
					if (m[i-c] > 0) {
						if (m[i] > 0) {
							m[i] = Math.min(m[i], m[i-c] + 1);
						} else {
							m[i] = m[i-c] + 1;
						}
					}
				}
			}
		}
		return m[t] == 0 ? -1 : m[t];
	}
	
	@Test
	public void test() {
		List<Integer> set = Arrays.asList(2,7);
		int t = 2;
		System.out.println(smallestNumber(set, t));
	}

}
