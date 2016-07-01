package hackerrank.dp;

import java.util.Arrays;
import java.util.Scanner;

public class Equals {
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
            int T = in.nextInt();
            for (int t = 0; t < T; t++) {
                int N = in.nextInt();
                int[] A = new int[N];
                for (int i = 0; i < N; i++) {
                    A[i] = in.nextInt();
                }
                System.out.println(minSteps(A));
            }
        }
    }
    
    public static long minSteps(int[] A) {
        Arrays.sort(A);
        int[] m = new int[1000];
        m[0] = 0;
        for (int k = 1; k < 1000; k++) {
            m[k] = m[k-1] + 1;
            if (k >= 2) {
                m[k] = Math.min(m[k], m[k-2] + 1);
            }
            if (k >= 5) {
                m[k] = Math.min(m[k], m[k-5] + 1);
            }
        }
        int i = 1;
        long min = 0;
        int diff = 0;
        while (i < A.length) {
            if (A[i] + diff > A[i-1]) {
                min += Long.valueOf(m[A[i]-A[i-1] + diff]);
                diff += A[i] - A[i-1];
            }
            i++;
        }
        return min;
    }
}
