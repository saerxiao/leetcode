package strings.arrays;

import org.junit.Test;

public class AnagramOfPalindrome {
	
	public static boolean isAnagramOfPalindrome(String A) {
		int[] h = new int[26];
		for (int i=0; i<A.length(); i++) {
			h[A.charAt(i) - 'a']++;
		}
		
		boolean hasOdd = false;
		for (int i=0; i<26; i++) {
			if (h[i]%2 == 1) {
				if (!hasOdd) {
					hasOdd = true;
				} else {
					return false;
				}
			}
		}
		return true;
 	}

	@Test
	public void test() {
		String A = "cdefghmnopqrstuvw";
		System.out.println(isAnagramOfPalindrome(A));
	}
}
