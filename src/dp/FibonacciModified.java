package dp;

import java.math.BigInteger;

import org.junit.Test;

public class FibonacciModified {

	@Test
	public void test() {
		int A = 0;
		int B = 1;
		int N = 10;
		System.out.println(compute(A,B,N));
	}
	
	public static BigInteger compute(int A, int B, int N) {
		BigInteger pp = BigInteger.valueOf(A);
		BigInteger p = BigInteger.valueOf(B);
		for (int i = 2; i < N; i++) {
			BigInteger c = p.multiply(p).add(pp);
			pp = p;
			p = c;
		}
		return p;
	}
}
