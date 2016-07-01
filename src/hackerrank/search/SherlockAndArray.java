package hackerrank.search;

import java.util.Scanner;

public class SherlockAndArray {

	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int T = in.nextInt();
			for (int t = 0; t < T; t++) {
				int N = in.nextInt();
				int[] A = new int[N];
				for (int i = 0; i < N; i++) {
					A[i] = in.nextInt();
				}
				if (existEvenDivider(A)) {
					System.out.println("YES");
				} else {
					System.out.println("NO");
				}
			}
		}
	}
	
	public static boolean existEvenDivider(int[] A) {
		if (A.length == 1) {
			return true;
		}
		else if (A.length == 2) {
			return false;
		} else {
			int sum = 0;
			for (int i = 0; i < A.length; i++) {
				sum += A[i];
			}
			
			int left = A[0];
			for (int i = 1; i < A.length - 1; i++) {
				int right = sum - left - A[i];
				if (left == right) {
					return true;
				} else {
					left += A[i];
				}
			}
			return false;
		}		
	}
}
