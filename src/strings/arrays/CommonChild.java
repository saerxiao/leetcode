package strings.arrays;

import org.junit.Test;

public class CommonChild {

	public static int longestCommonChild(String A, String B) {
		int[][] m = new int[A.length()][B.length()];
		m[0][0] = A.charAt(0) == B.charAt(0) ? 1 : 0;
		for (int j = 1; j < B.length(); j++) {
			m[0][j] = m[0][j-1];
			if (A.charAt(0) == B.charAt(j)) {
				m[0][j] = 1;
			}
		}
		
		for (int i = 1; i < A.length(); i++) {
			m[i][0] = m[i-1][0];
			if (A.charAt(i) == B.charAt(0)) {
				m[i][0] = 1;
			}
		}
		
		for (int i = 1; i < A.length(); i++) {
			for (int j = 1; j < B.length(); j++) {
				m[i][j] = Math.max(m[i-1][j], m[i][j-1]);
				if (A.charAt(i) == B.charAt(j)) {
					m[i][j] = Math.max(m[i][j], m[i-1][j-1] + 1);
				}
			}
		}
		return m[A.length()-1][B.length()-1];
	}
	
	@Test
	public void test() {
		String A = "HARRY";
		String B = "SALLY";
		System.out.println(longestCommonChild(A,B));
	}
}
