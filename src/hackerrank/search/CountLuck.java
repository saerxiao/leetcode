package hackerrank.search;

import java.util.BitSet;
import java.util.Scanner;

public class CountLuck {
	
//1
//2 3
//*.M
//.X.
//1
	
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)){
			int T = in.nextInt();
			for (int i = 0; i < T; i++) {
				int N = in.nextInt();
				int M = in.nextInt();
				in.nextLine();
				char[][] m = new char[N][M];
				for (int p = 0; p < N; p++) {
					String row = in.nextLine();
					for (int q = 0; q < M; q++) {
						m[p][q] = row.charAt(q);
					}
				}
				int K = in.nextInt();
				int[] start = find(m, 'M');
				int[] port = find(m, '*');
				Result rlt = new Result();
				count(m, port[0], port[1], start[0], start[1], new boolean[N][M], rlt);
				if (rlt.cnt == K) {
					System.out.println("Impressed");
				} else {
					System.out.println("Oops!");
				}				
			}
		}
	}
	
	public static int[] find(char[][] m, char expected) {
		int[] rlt = new int[2];
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++) {
				if (m[i][j] == expected) {
					rlt[0] = i;
					rlt[1] = j;
					return rlt;
				}
			}
		}
		return rlt;
	}
	
	private static final int[][] moves = new int[][] {{-1,0},{1,0},{0,-1}, {0,1}};

	public static void count(char[][] m, int pi, int pj, int ci, int cj, boolean[][] visited, Result rlt) {
		visited[ci][cj] = true;
		if (ci == pi && cj == pj) {
			rlt.correctPath = true;
			return;
		}
		
		BitSet validMoves = getValidMoves(m, ci, cj, visited);
		if (validMoves.cardinality() == 0) {
			rlt.correctPath = false;
			return;
		} else {
			if (validMoves.cardinality() > 1) {
				rlt.cnt++;
			}
			for (int i = validMoves.nextSetBit(0); i >= 0; i = validMoves.nextSetBit(i + 1)) {
				count(m, pi, pj, ci + moves[i][0], cj + moves[i][1], visited, rlt);
				if (rlt.correctPath) {
					return;
				}
			}
			if (validMoves.cardinality() > 1) {
				rlt.cnt--;
			}
		}
		
	}
	
	private static BitSet getValidMoves(char[][] m, int i, int j, boolean[][] visited) {
		BitSet bs = new BitSet(4);
		if (i > 0 && !visited[i-1][j] && m[i-1][j] != 'X') {
			bs.set(0);
		}
		if (i < m.length - 1 && !visited[i+1][j] && m[i+1][j] != 'X') {
			bs.set(1);
		}
		if (j > 0 && !visited[i][j-1] && m[i][j-1] != 'X') {
			bs.set(2);
		}
		if (j < m[0].length - 1 && !visited[i][j+1] && m[i][j+1] != 'X') {
			bs.set(3);
		}
		return bs;
	}

	private static class Result {
		boolean correctPath;
		int cnt;
	}
}
