package hackerrank.dp;

import java.util.Scanner;

public class TheMaximumSubarray {
	
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int T = in.nextInt();
			for (int t = 0; t < T; t++) {
				int N = in.nextInt();
				int[] A = new int[N];
				for (int i = 0; i < N; i++) {
					A[i] = in.nextInt();
				}
				System.out.println(maxSubArray(A) + " " + maxSubSequence(A));
			}
		}
	}
	
	public static int maxSubArray(int[] A) {
		int max = A[0];
		int last = A[0];
		
		for (int i = 1; i < A.length; i++) {
			last = Math.max(A[i], last + A[i]);
			max = Math.max(max, last);
		}
		
		return max;
	}
	
	public static int maxSubSequence(int[] A) {
		int maxSum = 0;
		int max = A[0];
		
		for (int i = 0; i < A.length; i++) {
			maxSum = Math.max(maxSum, maxSum + A[i]);
			max = Math.max(max, A[i]);
		}
		
		if (max < 0) {
			return max;
		} else {
			return maxSum;
		}
	}

}

//2 
//4 
//1 2 3 4
//3
//-1 -4 -5
