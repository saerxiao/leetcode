package strings.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Pairs {
	
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int N = in.nextInt();
			int K = in.nextInt();
			int[] A = new int[N];
			for (int i = 0; i < N; i++) {
				A[i] = in.nextInt();
			}
			System.out.println(pairs(A, K));
		}
	}

	public static int pairs(int[] A, int K) {
		Arrays.sort(A);
		Set<Integer> set = new HashSet<>();
		int pairs = 0;
		for (int i = 0; i < A.length; i++) {
			if (set.contains(A[i] - K)) {
				pairs++;
			}
			set.add(A[i]);
		}
		return pairs;
	}
}
