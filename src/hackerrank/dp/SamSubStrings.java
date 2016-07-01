package hackerrank.dp;

import java.util.Scanner;

public class SamSubStrings {
	
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			String row = in.nextLine();
			int[] A = new int[row.length()];
			for (int i = 0; i < A.length; i++) {
				A[i] = Integer.parseInt(String.valueOf(row.charAt(i)));
			}
			System.out.println(sumOfSubstring(A));
		}
	}
	
	public static long sumOfSubstring(int[] A) {
//		print(A);
		long B = Long.valueOf(1000000007);
		long[] s = new long[A.length];
		s[0] = A[0];
		long total = s[0];
		for (int i = 1; i < A.length; i++) {
			s[i] = (s[i-1] * Long.valueOf(10) + Long.valueOf(A[i] * (i+1))) % B;
			total = (total + s[i]) % B;
		}
		return total;
	}

	private static void print(int[] A) {
		for (int i = 0; i < A.length; i++) {
			System.out.print(A[i]);
		}
		System.out.println();
	}
}
