package hackerrank.dp;

import java.util.Scanner;

public class MrKMarsh {

	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int m = in.nextInt();
			int n = in.nextInt();
			char[][] A = new char[m][n];
			in.nextLine();
			for (int i = 0; i < m; i++) {
				String row = in.nextLine();
				A[i] = row.toCharArray();
			}
			int largestRect = largestRect(A);
			if (largestRect == 0) {
				System.out.println("impossible");
			} else {
				System.out.println(largestRect);
			}
		}
	}
	
	public static int largestRect(char[][] A) {
		int[][][] m = new int[A.length+1][A[0].length+1][2];
		for (int i = 0; i <= A.length; i++) {
			m[i][0][0] = 0;
			m[i][0][1] = 0;
		}
		for (int j = 0; j <= A[0].length; j++) {
			m[0][j][0] = 0;
			m[0][j][1] = 0;
		}
		
		for (int i = 1; i <= A.length; i++) {
			for (int j = 1; j <= A[0].length; j++) {
				if (A[i-1][j-1] == '.') {
					m[i][j][0] = m[i-1][j][0] + 1;
					m[i][j][1] = m[i][j-1][1] + 1;
 				} else {
 					m[i][j][0] = m[i-1][j][0];
					m[i][j][1] = m[i][j-1][1];
 				}
			}
		}
		
//		print(m);
		
		int max = 0;
		for (int i = 2; i <= A.length; i++) {
			for (int j = 2; j <= A[0].length; j++) {
				if (A[i-1][j-1] == '.') {
//					for (int p = 1; p < i; p++) {
//						for (int q = 1; q < j; q++) {
//							boolean isRect = true;
//							if (m[i][j][0] - m[p][j][0] < i - p) {
//								isRect = false;	
//							}
//							if (m[i][q][0] - m[p][q][0] < i - p) {
//								isRect = false;
//							}
//							if (m[i][j][1] - m[i][q][1] < j - q) {
//								isRect = false;
//							}
//							if (m[p][j][1] - m[p][q][1] < j - q) {
//								isRect = false;
//							}
//							if (isRect) {
//								max = Math.max(max, (i-p+j-q)*2);
//							}
//						}
//					}
//					System.out.println("i=" + i + " j=" + j);
					outerloop:
					for (int k = 0; k <= i+j-4; k++) {
						int pStart = Math.max(1, k+3-j);
						int pEnd = Math.min(i-1, k + 1);
						for (int p = pStart; p <= pEnd; p++) {
							// p - 1 + q - 1 = k
							int q = k + 2 - p;
							if (isRect(A,m,i,j,p,q)) {
								max = Math.max(max, (i-p+j-q)*2);
								break outerloop;
							}
						}
					}			
				}				
			}
		}
		return max;
	}
	
	private static boolean isRect(char[][] A, int[][][] m, int i, int j, int p, int q) {
		boolean isRect = true;
		if (A[p-1][q-1] == 'x') {
			isRect = false;
		}
		if (m[i][j][0] - m[p][j][0] < i - p) {
			isRect = false;	
		}
		if (m[i][q][0] - m[p][q][0] < i - p) {
			isRect = false;
		}
		if (m[i][j][1] - m[i][q][1] < j - q) {
			isRect = false;
		}
		if (m[p][j][1] - m[p][q][1] < j - q) {
			isRect = false;
		}
		return isRect;
	}
	
	private static void print(int[][][] m) {
		for (int i = 1; i < m.length; i++) {
			for (int j = 1; j < m[0].length; j++) {
				System.out.print("(" + m[i][j][0] + "," + m[i][j][1] + ") ");
			}
			System.out.println();
		}
	}
	
	//Wrong solution!
	public static int largestRectWrong(char[][] A) {
		int[][][] m = new int[A.length+1][A[0].length+1][2];
		for (int i = 0; i <= A.length; i++) {
			m[i][0][0] = 0;
			m[i][0][1] = 0;
		}
		for (int j = 0; j <= A[0].length; j++) {
			m[0][j][0] = 0;
			m[0][j][1] = 0;
		}
		
		int max = 0;
		for (int i = 1; i <= A.length; i++) {
			for (int j = 1; j <= A[0].length; j++) {
				if (A[i-1][j-1] == '.') {
					m[i][j][0] = m[i-1][j][0] + 1;
					m[i][j][1] = m[i][j-1][1] + 1;
				} else {
					m[i][j][0] = 0;
					m[i][j][1] = 0;
				}
				if (m[i][j][0] > 1 && m[i][j][1] > 1) {
					max = Math.max((m[i][j][0] - 1 + m[i][j][1] - 1)*2, max);
				}
			}
		}
		
		return max;		
	}
}

//4 5
//.....
//.x.x.
//..x.x
//.....
