package hackerrank.hackway2016;

import java.util.Arrays;
import java.util.Scanner;

public class MaximumPerimeterTriangle {
	
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int N = in.nextInt();
			int[] A = new int[N];
			for (int i = 0; i < N; i++) {
				A[i] = in.nextInt();
			}
			int[] rlt = maxPerimeterTriangle(A);
			if (rlt == null) {
				System.out.println("-1");
			} else {
				for (int i = 0; i < 3; i++) {
					System.out.print(rlt[i] + " ");
				}
				System.out.println();
			}
		}
	}
	
	public static int[] maxPerimeterTriangle(int[] A) {
		Arrays.sort(A);
		
		int longest = -1;
		for (int i = A.length - 1; i >= 2; i--) {
			if (A[i-1] + A[i-2] > A[i]) {
				longest = i;
				break;
			}
		}
		if (longest > -1) {
			int[] rlt = new int[3];
			rlt[2] = A[longest];
			rlt[1] = A[longest-1];
			rlt[0] = A[longest-2];
			return rlt;
		} else {
			return null;
		}
	}

}
