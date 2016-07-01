package airebnb;

public class StringSimilarity {
	
	public static long[] StringSimilarity(String[] inputs) {
		long[] rlt = new long[inputs.length];
		for (int i = 0; i < inputs.length; i++) {
			rlt[i] = compute(inputs[i].toCharArray());
		}
		return rlt;
    }

	private static long compute(char[] A) {
		int sum = A.length;
		for (int i = 1; i < A.length; i++) {
			sum += Long.valueOf(longestCommonPrefix(A, i));
		}
		return sum;
	}
	
	private static int longestCommonPrefix(char[] A, int s) {
		int l = 0;
		for (int i = 0; s + i < A.length; i++) {
			if (A[i] == A[s+i]) {
				l++;
			} else {
				return l;
			}
		}
		return l;
	}
}
