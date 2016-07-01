package hackerrank.dp;

import java.util.Scanner;

public class HexagonalGrid {
	
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int T = in.nextInt();
			for (int t = 0; t < T; t++) {
				int N = in.nextInt();
				in.nextLine();				
				int[][] A = new int[N][2];				
				for (int j = 0; j < 2; j++) {
					String row = in.nextLine();
					for (int i = 0; i < N; i++) {
						A[i][j] = Integer.parseInt(String.valueOf(row.charAt(i)));
					}
				}
				if (canFill(A)) {
					System.out.println("YES");
				} else {
					System.out.println("NO");
				}
			}
		}
	}
	
	public static boolean canFill(int[][] A) {
		int[][] m = new int[A.length][2];
		if (A[0][0] == 0) {
			if (A[0][1] == 0) {
				m[0][0] = 0;
				m[0][1] = 0;
			} else {
				m[0][0] = 1;
				m[0][1] = 0;
			}
		} else {
			if (A[0][1] == 0) {
				m[0][0] = 0;
				m[0][1] = 1;
			} else {
				m[0][0] = 0;
				m[0][1] = 0;
			}
		}
		
		for (int i = 1; i < A.length; i++) {
			if (A[i][0] == 0) {
				if (A[i][1] == 0) {
					if (m[i-1][0] == 0) {
						if (m[i-1][1] == 0) {
							m[i][0] = 0;
							m[i][1] = 0;
						} else {
							m[i][0] = 1;
							m[i][1] = 1;
						}
					} else {
						if (m[i-1][1] == 0) {
							m[i][0] = 0;
							m[i][1] = 1;
						} else {
							m[i][0] = 1;
							m[i][1] = 1;
						}
					}
				} else {
					if (m[i-1][0] == 0) {
						if (m[i-1][1] == 0) {
							m[i][0] = 1;
							m[i][1] = 0;
						} else {
							m[i][0] = 0;
							m[i][1] = 0;
						}
					} else {
						if (m[i-1][1] == 0) {
							m[i][0] = 0;
							m[i][1] = 0;
						} else {
							m[i][0] = 0;
							m[i][1] = 0;
						}
					}
				}
			} else {
				if (A[i][1] == 0) {
					if (m[i-1][0] == 0) {
						if (m[i-1][1] == 0) {
							m[i][0] = 0;
							m[i][1] = 1;
						} else {
							m[i][0] = 0;
							m[i][1] = 0;
						}
					} else {
						if (m[i-1][1] == 0) {
							return false;
						} else {
							m[i][0] = 0;
							m[i][1] = 0;
						}
					}
				} else {
					if (m[i-1][0] == 0 && m[i-1][1] == 0) {
						m[i][0] = 0;
						m[i][1] = 0;
					} else {
						return false;
					}
				}
			}
		}
		
//		print(m);
		
		if (m[A.length-1][0] == 0 && m[A.length-1][1] == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	private static void print(int[][] m) {
		for (int j = 0; j < m[0].length; j++) {
			for (int i = 0; i < m.length; i++) {
				System.out.print(m[i][j]);
			}
			System.out.println();
		}
	}

}

//1
//6
//010000
//000010
