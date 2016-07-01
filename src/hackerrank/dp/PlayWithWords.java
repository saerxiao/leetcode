package hackerrank.dp;

import java.util.Scanner;

public class PlayWithWords {
	
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			String s = in.nextLine();
			System.out.println(maxPalindromeProduct(s));
		}
	}
	
	public static int maxPalindromeProduct(String s) {
		int[][] m = new int[s.length()][s.length()];
		
		for (int i = 0; i < s.length(); i++) {
			m[i][i] = 1;
		}
		
		for (int j = 1; j < s.length(); j++) {
			for (int i = j - 1; i >= 0; i--) {
				if (s.charAt(i) != s.charAt(j)) {
					m[i][j] = Math.max(m[i+1][j], m[i][j-1]);
				} else {
					if (i+1 < j) {
						m[i][j] = m[i+1][j-1] + 2;
					} else {
						m[i][j] = 2;
					}
				}
			}
		}
		
		int maxProduct = 1;
		for (int k = 0; k < s.length() - 1; k++) {
			maxProduct = Math.max(maxProduct, m[0][k]*m[k+1][s.length()-1]);
		}
		
		return maxProduct;
	}

}
