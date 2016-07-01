package hackerrank.search;

import java.util.Scanner;

public class ConnectedCell {
	
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int m = in.nextInt();
			int n = in.nextInt();
			boolean[][] A = new boolean[m][n];
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					int a = in.nextInt();
					if (a == 1) {
						A[i][j] = true;
					}
				}
			}
			System.out.println(maxRegion(A));
		}
	}
	
	public static int maxRegion(boolean[][] A) {
		int max = 0;
		boolean[][] visited = new boolean[A.length][A[0].length];
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[0].length; j++) {
				if (!visited[i][j] && A[i][j]) {
					max = Math.max(max,  countCells(i, j, A, visited));
				}				
			}			
		}
		return max;
	}
	
	private static int countCells(int i, int j, boolean[][] A, boolean[][] visited) {
		visited[i][j] = true;
		int cnt = 1;
		if (i > 0 && A[i-1][j] && !visited[i-1][j]) {
			cnt += countCells(i-1, j, A, visited);
		}
		if (i < A.length - 1 && A[i+1][j] && !visited[i+1][j]) {
			cnt += countCells(i+1, j, A, visited);
		}
		if (j > 0 && A[i][j-1] && !visited[i][j-1]) {
			cnt += countCells(i, j-1, A, visited);
		}
		if (j < A[0].length - 1 && A[i][j+1] && !visited[i][j+1]) {
			cnt += countCells(i, j+1, A, visited);
		}
		if (i > 0) {
			if (j > 0 && A[i-1][j-1] && !visited[i-1][j-1]) {
				cnt += countCells(i-1, j-1, A, visited);
			}
			if (j < A[0].length - 1 && A[i-1][j+1] && !visited[i-1][j+1]) {
				cnt += countCells(i-1, j+1, A, visited);
			}
		}
		if ( i < A.length -1) {
			if (j > 0 && A[i+1][j-1] && !visited[i+1][j-1]) {
				cnt += countCells(i+1, j-1, A, visited);
			}
			if (j < A[0].length - 1 && A[i+1][j+1] && !visited[i+1][j+1]) {
				cnt += countCells(i+1, j+1, A, visited);
			}
		}
		return cnt;
	}
}
