package airebnb;

import org.junit.Test;

public class MinAbs {
	
	@Test
	public void test() {
		float[] A = {1.4f, 1.0f, 1.2f};
		System.out.println(minAbs(A));
	}
	
	public static float minAbs(float[] A) {
		// mapping -1 - > 0, 0 -> 1, 1 -> 2
		float[][] m = new float[A.length][3];
		int f = (int)A[0];
		int r = Math.round(A[0]);
		if (f < r) {
			m[0][-1 + 1] = A[0] - f;
			m[0][0 + 1] = r - A[0];
			m[0][1 + 1] = -1;
		} else {
			m[0][0 + 1] = A[0] - f;
			m[0][1 + 1] = f + 1 - A[0];
			m[0][-1 + 1] = -1;
		}
		
		float sum = A[0];
		int lastRound = Math.round(sum);
		for (int i = 1; i < A.length; i++) { 
			sum += A[i];
			int newRound = Math.round(sum);
			int floor = (int)A[i];
			int round = Math.round(A[i]);
			int ceiling = floor < round ? round : round + 1;
						
			if (lastRound + floor == newRound) {
				m[i][0+1] = m[i-1][0+1] + A[i] - floor;
				if (m[i-1][-1+1] != -1) {
					m[i][0+1] = Math.min(m[i][0+1], m[i-1][-1+1] + ceiling - A[i]);
					m[i][-1+1] = m[i-1][-1+1] + A[i] - floor;
				} else {
					m[i][-1+1] = -1;
				}
				m[i][1+1] = m[i-1][0+1] + ceiling - A[i];
				if (m[i-1][1+1] != -1) {
					m[i][1+1] = Math.min(m[i][1+1], m[i-1][1+1] + A[i] - floor);
				}
			} else {
				// must be lastRound + floor < newRound, or lastRound + ceiling == newRound
				m[i][0+1] = m[i-1][0+1] + ceiling - A[i];
				if (m[i-1][1+1] != -1) {
					m[i][0+1] = Math.min(m[i][0+1], m[i-1][1+1] + A[i] - floor);
					m[i][1+1] = m[i-1][1+1] + ceiling - A[i];
				} else {
					m[i][1+1] = -1;
				}
				m[i][-1+1] = m[i-1][0+1] + A[i] - floor;
				if (m[i-1][-1+1] != -1) {
					m[i][-1+1] = Math.min(m[i][-1+1], m[i-1][-1+1] + ceiling - A[i]);
				}
			}
			lastRound = newRound;
		}
		return m[A.length - 1][0 + 1];
	}

}
