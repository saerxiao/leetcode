package dp;

public class ZigzagSeq {

	public int longestZigzag(int[] A) {
		if (A.length <= 2) {
			return A.length;
		}
		
		int[][] max = new int[A.length][2];
		max[0][0] = 1;
		max[0][1] = 0;
//		max[1][0] = 2;
//		max[1][1] = A[1] > A[0] ? 1 : 0;
		for (int i = 1; i < A.length; i++) {
			max[i][0] = 2;
			max[i][1] = A[i] > A[0] ? 1 : 0;
			for (int j = 1; j < i; j++) {
				if ((A[i] > A[j] && max[j][1] == 0 ) || (A[i] < A[j] && max[j][1] == 1)) {
					int m = max[j][0] + 1;
					if (m > max[i][0]) {
						max[i][0] = m;
						max[i][1] = max[j][0] == 0 ? 1 : 0;
					}
				}
			}
		}
		return max[A.length][0];
	}
}
