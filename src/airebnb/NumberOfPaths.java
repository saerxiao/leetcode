package airebnb;

import java.util.Scanner;

public class NumberOfPaths {
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
				
		int _a_cnt = 0,_b_cnt = 0;
		int [][] _a = new int[1001][1001];
		try {
			_a_cnt = sc.nextInt();
			_b_cnt = sc.nextInt();
		}catch (Exception e) {
			 System.out.println("Here: " + e.getMessage()); 
		} 

		for(int i=0; i < _a_cnt; i++) {
			for( int j = 0;j < _b_cnt;j++ ){
				int _a_tmp = 0;
				try {
					_a_tmp = sc.nextInt();
				}catch (Exception e) { }
				_a[i][j] = _a_tmp;
			}			
		}
		System.out.println(numberOfPaths (_a ,_a_cnt,_b_cnt));

	}

	public static int numberOfPaths(int[][] a, int M, int N) {
		long[][] m = new long[M][N];
		if (a[0][0] == 1) {
			m[0][0] = 1;
		} else {
			return 0;
		}
		
		long B = Long.valueOf(1000000007);
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (a[i][j] == 0) {
					m[i][j] = 0;
				} else {
					if (i - 1 >= 0) {
						m[i][j] = m[i-1][j];
					}
					if (j - 1 >= 0) {
						m[i][j] = (m[i][j] + Long.valueOf(m[i][j-1])) % B;
					}
				}
			}
		}
		return (int) m[M-1][N-1];
	}
}

//3 4
//1 1 1 1
//1 1 1 1
//1 1 1 1
