package hackerrank.womeno.codesprint;

import java.util.Scanner;

import org.junit.Test;

public class SmritiStrings {
	
	@Test
	public void test() {
		String A = "dcabe";
		StringBuilder stb = new StringBuilder();
		int n = 1000000;
		for (int i = 0; i < n; ++i)
			stb.append('z');
		
		A = stb.toString();
		int m = A.length() / 2;
		System.out.println(SmritiStrings.maxRemaining(A,0,A.length() - 1,m));
	}
	
	public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int T = in.nextInt();
            in.nextLine();
            for (int i = 0; i < T; i++) {
                String r = in.nextLine();
                String[] info = r.split(" ");
//                SmritiStrings solution = new SmritiStrings(info[0], Integer.parseInt(info[1]));
//                System.out.println(solution.maxRemaining());
                System.out.println(maxRemaining(info[0], 0, info[0].length() - 1, Integer.parseInt(info[1])));
            }
        }
    }
	
	public static String maxRemaining(String A, int start, int end, int n) {
		if (end - start + 1 == n) {
			return "";
		}
		int firstMaxIndex = findFirstMax(A, start, end);
		if (firstMaxIndex - start == n) {
			return A.substring(firstMaxIndex, end + 1);
		} else if (firstMaxIndex - start > n){
			return maxRemaining(A, start, firstMaxIndex - 1, n) + A.substring(firstMaxIndex, end + 1);
		} else {
			return A.charAt(firstMaxIndex) + maxRemaining(A, firstMaxIndex + 1, end, n-(firstMaxIndex - start));
		}
	}

	private static int findFirstMax(String A, int start, int end) {
		int firstMax = start;
		for (int i = start + 1; i <= end; i++) {
			if (A.charAt(i) > A.charAt(firstMax)) {
				firstMax = i;
			}
		}
		return firstMax;
	}
	
//	private final int[][] maxMatrix;
//	private final int N;
//	private final String A;
//	
//	public SmritiStrings(String A, int N) {
//		this.N = N;
//		this.A = A;
//		maxMatrix = new int[A.length()][A.length()];
//		for (int i = 0; i < A.length(); i++) {
//			maxMatrix[i][i] = A.charAt(i);
//			int maxSoFar = i;
//			for (int j = i + 1; j < A.length(); j++) {
//				if (A.charAt(j) > A.charAt(maxSoFar)) {
//					maxSoFar = j;
//				}
//				maxMatrix[i][j] = maxSoFar;
//			}
//		}
//	}
//	
//	public String maxRemaining() {
//		return maxRemaining(0, A.length() - 1, N);
//	}
//	
//	private String maxRemaining(int start, int end, int n) {
//		if ((end - start + 1) == n) {
//			return "";
//		}
//		int firstMaxIndex = maxMatrix[start][end];
//		if (firstMaxIndex - start == n) {
//			return A.substring(firstMaxIndex, end + 1);
//		} else if (firstMaxIndex - start > n){
//			return maxRemaining(start, firstMaxIndex - 1, n) + A.substring(firstMaxIndex, end + 1);
//		} else {
//			return A.charAt(firstMaxIndex) + maxRemaining(firstMaxIndex + 1, end, n-(firstMaxIndex - start));
//		}
//	}

//	public static String maxRemaining(String A, int n) {
//		if (A.length() == n) {
//			return "";
//		}
//		int firstMaxIndex = findFirstMax(A);
//		if (firstMaxIndex == n) {
//			return A.substring(firstMaxIndex);
//		} else if (firstMaxIndex > n){
//			return maxRemaining(A.substring(0, firstMaxIndex), n) + A.substring(firstMaxIndex);
//		} else {
//			return A.charAt(firstMaxIndex) + maxRemaining(A.substring(firstMaxIndex + 1), n-firstMaxIndex);
//		}
//	}
	
//	private static int findFirstMax(String A) {
//		int firstMax = 0;
//		char max = A.charAt(0);
//		for (int i = 1; i < A.length(); i++) {
//			if (A.charAt(i) > max) {
//				firstMax = i;
//				max = A.charAt(i);
//			}
//		}
//		return firstMax;
//	}
	

}
