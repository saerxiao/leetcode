package hackerrank.dp;

import java.util.Scanner;

public class LongestCommonSubSeq {
	
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int N1 = in.nextInt();
			int N2 = in.nextInt();
			int[] A = new int[N1];
			for (int i = 0; i < N1; i++) {
				A[i] = in.nextInt();
			}
			int[] B = new int[N2];
			for (int i = 0; i < N2; i++) {
				B[i] = in.nextInt();
			}
			int[] rlt = longestCommonSubseq(A,B);
			for (int i = 0; i < rlt.length; i++) {
				System.out.print(rlt[i] + " ");
			}
			System.out.println();
		}
	}

	public static int[] longestCommonSubseq(int[] A, int[] B) {
		int[][] m = new int[A.length][B.length];
		int[][] d = new int[A.length][B.length]; // -1 -> i--, 0 -> i--, j--, 1 -> j--
		
		if (A[0] == B[0]) {
			m[0][0] = 1;
			d[0][0] = 0;
		}
		for (int j = 1; j < B.length; j++) {
			if (A[0] == B[j]) {
				m[0][j] = 1;
				d[0][j] = 0;
				if (m[0][j-1] == m[0][j]) {
					d[0][j] = 1;
				}
			} else {
				m[0][j] = m[0][j-1];
				d[0][j] = 1;
			}			
		}
		
		for (int i = 1; i< A.length; i++) {
			if (B[0] == A[i]) {
				m[i][0] = 1;
				d[i][0] = 0;
				if (m[i-1][0] == m[i][0]) {
					d[i][0] = -1;
				}
			} else {
				m[i][0] = m[i-1][0];
				d[i][0] = -1;
			}
		}
		
		for (int i = 1; i < A.length; i++) {
			for (int j = 1; j < B.length; j++) {
				m[i][j] = m[i][j-1];
				d[i][j] = 1;
				if (m[i-1][j] > m[i][j]) {
					m[i][j] = m[i-1][j];
					d[i][j] = -1;
				}
				if (A[i] == B[j] && m[i-1][j-1] + 1 > m[i][j]) {
					m[i][j] = m[i-1][j-1] + 1;
					d[i][j] = 0;
					
				}
			}
		}
		
		int i = A.length -1, j = B.length -1;
		int[] rlt = new int[m[A.length - 1][B.length - 1]];
		int k = rlt.length - 1;
		while (k > -1) {
			if (d[i][j] == 0) {
				rlt[k] = A[i];
				k--;
				i--;
				j--;
			} else if (d[i][j] == 1) {
				j--;
			} else {
				i--;
			}
		}
		return rlt;
	}
}
