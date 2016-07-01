package hackerrank.greedy;

import java.util.Arrays;
import java.util.Scanner;

public class SherlockAndMiniMax {
	
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int N = in.nextInt();
			int[] A = new int[N];
			for (int i = 0; i < N; i++) {
				A[i] = in.nextInt();
 			}
			System.out.println(miniMax(A, in.nextInt(), in.nextInt()));
		}
	}

	public static int miniMax(int[] A, int P, int Q) {
		Arrays.sort(A);
//		print(A);
		if (Q <= A[0]) {
			return P;
		} else if (P >= A[A.length-1]) {
			return Q;
		} else {
			
			int m = 0;
			int maxGap = 0;
			if (A.length >= 2) {
				for (int i = 1; i < A.length; i++) {
					if (A[i-1] >= Q) {
						break;
					} else {
						int mid = (A[i] + A[i-1]) / 2;
						if (mid >= P && mid <= Q) {
							if ((A[i] - A[i-1]) / 2 > maxGap) {
								maxGap = (A[i] - A[i-1]) / 2;
								m = (A[i] + A[i-1]) / 2;
							}
						} else if (mid > Q) {
							if (Q - A[i-1] > maxGap) {
								m = Q;
								maxGap = Q- A[i-1];
							}
						} else if (mid < P && A[i] > P)	{
							if (A[i] - P >= maxGap) {
								m = P;
								maxGap = A[i] - P;
							}
						}
					}
				}
			}
			if (P <= A[0] && A[0] - P >= maxGap) {
				m = P;
				maxGap = A[0] - P;
			}
			if (Q > A[A.length-1] && Q - A[A.length-1] > maxGap) {
				m = Q;
				maxGap = Q - A[A.length-1];
			}
			return m;
		}		
	}
	
	private static void print(int[] A) {
		for (int i = 0; i < A.length; i++) {
			System.out.print(A[i] + " ");
		}
		System.out.println();
	}
}

//5
//15 10 50 24 40
//13 16
