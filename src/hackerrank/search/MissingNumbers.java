package hackerrank.search;

import java.util.Scanner;

public class MissingNumbers {
	
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int n = in.nextInt();
			int[] A = new int[n];
			for (int i = 0; i < n; i++) {
				A[i] = in.nextInt();
			}
			int m = in.nextInt();
			int[] B = new int[m];
			for (int i = 0; i < m; i++) {
				B[i] = in.nextInt();
			}
			int[] minMax = findMinMax(B);
			boolean[] missing = findMissingNumbers(minMax[0], minMax[1], A, B);
			for (int i = 0; i < missing.length; i++) {
				if (missing[i]) {
					System.out.print(minMax[0] + i + " ");
				}
			}
		}
	}
	
	public static int[] findMinMax(int[] B) {
		int[] minMax = new int[2];
		minMax[0] = B[0];
		minMax[1] = B[0];
		for (int i = 0; i < B.length; i++) {
			minMax[0] = Math.min(minMax[0], B[i]);
			minMax[1] = Math.max(minMax[1], B[i]);
		}
		return minMax;
	}
	
	public static boolean[] findMissingNumbers(int min, int max, int[] A, int[] B) {
		int[] bucketA = new int[max - min + 1];
		for (int i = 0; i < A.length; i++) {
			bucketA[A[i] - min]++;
		}
		int[] bucketB = new int[max - min + 1];
		for (int i = 0; i < B.length; i++) {
			bucketB[B[i] - min]++;
		}
		
		boolean[] missing = new boolean[max - min + 1];
		for (int i = 0; i < max - min + 1; i++) {
			if (bucketA[i] < bucketB[i]) {
				missing[i] = true;
			}
		}
		return missing;
	}

}
