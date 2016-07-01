package hackerrank.search;

import java.util.Scanner;

public class TheGridSearch {
	
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int T = in.nextInt();
			for (int t = 0; t < T; t++) {
				int R = in.nextInt();
				int C = in.nextInt();
				in.nextLine();
				char[][] G = new char[R][C];
				for (int i = 0; i < R; i++) {
					String row = in.nextLine();
					for (int j = 0; j < C; j++) {
						G[i][j] = row.charAt(j);
					}
				}
				int r = in.nextInt();
				int c = in.nextInt();
				in.nextLine();
				char[][] P = new char[r][c];
				for (int i = 0; i < r; i++) {
					String row = in.nextLine();
					for (int j = 0; j < c; j++) {
						P[i][j] = row.charAt(j);
					}
				}
				if (findSubGrid(G,P)) {
					System.out.println("YES");
				} else {
					System.out.println("NO");
				}
				
			}
		}

	}
	public static boolean findSubGrid(char[][] G, char[][] P) {
		for (int i = 0; i <= G.length - P.length; i++) {
			for (int j = 0; j <= G[0].length - P[0].length; j++) {
				if (isMatch(G, i, j, P)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private static boolean isMatch(char[][] G, int si, int sj, char[][] P) {
		for (int i = si; i < si + P.length; i++) {
			for (int j = sj; j < sj + P[0].length; j++) {
				if (G[i][j] != P[i - si][j - sj]) {
					return false;
				}
			}
		}
		return true;
	}
}
//1
//4 6
//123412
//561212
//123634
//781288
//2 2
//12
//34
