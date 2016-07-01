package dp;

import java.util.Scanner;

public class CoinOnTheTable {

	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int N = in.nextInt();
			int M = in.nextInt();
			int K = in.nextInt();
			in.nextLine();
			char[][] A = new char[N][M];
			for (int i = 0; i < N; i++) {
				String row = in.nextLine();
				for (int j = 0; j < M; j++) {
					A[i][j] = row.charAt(j);
				}
			}
			System.out.println(minOperations(A, K));
		}
	}
	
	public static int minOperations(char[][] A, int K) {
		int[] t = findDestination(A, '*');
 		
		int[][][] m = new int[A.length][A[0].length][K+1];
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[0].length; j++) {
				m[i][j][0] = -1;
			}
		}
		m[0][0][0] = 0;
		
		for (int k = 1; k <= K; k++) {
			for (int i = 0; i < A.length; i++) {
				for (int j = 0; j < A[0].length; j++) {
					m[i][j][k] = m[i][j][k-1];
					if (i > 0 && m[i-1][j][k-1] != -1) {
						updateCell(m,A,i,j,k,i-1,j, 'D');
					}
					if (i < A.length -1 && m[i+1][j][k-1] != -1) {
						updateCell(m,A,i,j,k,i+1,j,'U');
					}
					if (j > 0 && m[i][j-1][k-1] != -1) {
						updateCell(m,A,i,j,k,i,j-1,'R');
					}
					if (j < A[0].length - 1 && m[i][j+1][k-1] != -1) {
						updateCell(m,A,i,j,k,i,j+1,'L');
					}
					
					if (k == K && i == t[0] && j == t[1]) {
						break;
					}
				}
			}
		}
		return m[t[0]][t[1]][K];
	}
	
	private static void updateCell(int[][][] m, char[][] A, 
			int i, int j, int k, int fromi, int fromj, char expected) {
		int v = m[fromi][fromj][k-1] + (A[fromi][fromj] == expected ? 0 : 1);
		if (m[i][j][k] != -1) {
			m[i][j][k] = Math.min(m[i][j][k], v);
		} else {
			m[i][j][k] = v;
		}
	}
	
	private static int[] findDestination(char[][] A, char expected) {
		int[] t = new int[2];
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[0].length; j++) {
				if (A[i][j] == expected) {
					t[0] = i;
					t[1] = j;
					break;
				}
			}
		}
		return t;
	}
}
