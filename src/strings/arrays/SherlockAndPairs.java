package strings.arrays;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SherlockAndPairs {
	
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int T = in.nextInt();
			for (int i = 0; i < T; i++) {
				int N = in.nextInt();
				long[] A = new long[N];
				for (int j = 0; j < N; j++) {
					A[j] = in.nextInt();
				}
				System.out.println(pairs(A));
			}
		}
	}
	
	public static long pairs(long[] A) {
		Map<Long, Long> dupes = new HashMap<>();
		for (int i = 0; i < A.length; i++) {
			if (dupes.containsKey(A[i])) {
				dupes.put(A[i], dupes.get(A[i]) + 1);
			} else {
				dupes.put(A[i], Long.valueOf(1));
			}
		}
		long pairs = 0;
		for (long d : dupes.keySet()) {
			if (dupes.get(d) > 1) {
				pairs += ( dupes.get(d) * (dupes.get(d) - 1));
			}
		}
		return pairs;
	}

}
