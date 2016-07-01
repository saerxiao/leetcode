package hackerrank.string;

import java.util.Scanner;

public class PalindromeIndex {
	
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int T = in.nextInt();
			in.nextLine();
			for (int t = 0; t < T; t++) {
				System.out.println(toRemove(in.nextLine()));
			}
		}
	}
	
	public static int toRemove(String A) {
//		System.out.println(A);
		int i = 0;
		int j = A.length() - 1;
		while (i < j) {
			if (A.charAt(i) == A.charAt(j)) {
				i++;
				j--;
			} else {
				if (isPalindrome(A, i+1, j)) {
					return i;
				} else {
					return j;
				}
			}
		}
		return -1;
	}
	
	private static boolean isPalindrome(String A, int i, int j) {
		int p = i;
		int q = j;
		while (p < q) {
			if (A.charAt(p) != A.charAt(q)) {
				return false;
			} else {
				p++;
				q--;
			}
		}
		return true;
	}

}
