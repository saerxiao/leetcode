package strings.arrays;

import org.junit.Test;

public class CommonSubstrings {

	public boolean containCommonSubstrings(String A, String B) {
		if (A == null || B == null) return false;
		boolean[] tested = new boolean[26];
		for (int i = 0; i < A.length(); i++) {
			for (int j = 0; j < B.length(); j++) {
				if (!tested[A.charAt(i) - 'a']) {
					if (A.charAt(i) == B.charAt(j)) {
						return true;
					}
				} else {
					j = B.length();
				}				
			}
			tested[A.charAt(i) - 'a'] = true;
		}
		return false;
	}
	
	@Test
	public void test() {
		String A = "heello";
		String B = "world";
//		String A = "hi";
//		String B = "world";
		System.out.println(containCommonSubstrings(A,B));
				
	}
}
