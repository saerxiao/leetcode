package hackerrank.dp;

import java.util.Scanner;

public class TravelAroundTheWorld {

	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
            int N = in.nextInt();
            int C = in.nextInt();
            int[] a = new int[N];
            for (int i = 0; i < N; i++) {
                a[i] = in.nextInt();
            }
            int[] b = new int[N];
            for (int i = 0; i < N; i++) {
                b[i] = in.nextInt();
            }
            System.out.println(numberOfCitiesToStart(N, C, a, b));
        }
    }
    
    public static int numberOfCitiesToStart(int N, long C, int[] a, int[] b) {
    	long sumA = 0;
    	long sumB = 0;
    	for (int i = 0; i < N ; i++) {
    		if (b[i] > C) {
    			return 0;
    		}
    		sumA += Long.valueOf(a[i] < C ? a[i] : C);
    		sumB += Long.valueOf(b[i]);
    	}
    	if (sumA < sumB) return 0;
    	
        int cnt = 0;
        int start = 0;
        int firstSuccessIndex = -1;
        while (start < N) {
            long left = 0;
            boolean succeed = true;
            for (int i = start; i <= N + firstSuccessIndex; i++) {
                left = (left + Long.valueOf(a[i%N]) < C ? left + Long.valueOf(a[i%N]) : C) - Long.valueOf(b[i%N]);
                if (left < 0) {
                	if (i > N) {
                		return cnt;
                	} else {
                		start = i + 1;
                        succeed = false;
                        break;
                	}
                    
                }
            }
            if (succeed) {
            	start++;
                cnt++;
                if (firstSuccessIndex == -1) {
                	firstSuccessIndex = start;
                }
            }
        }
        return cnt;
    }
}
