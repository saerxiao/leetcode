package airebnb;

public class JumpingJack {
	
	public static int maxStep(int N, int K) {
		int sum = 0;
		for (int i = 1; i <= N; i++) {
			sum += i;
			if (sum == K) {
				return (1 + N) * N / 2 - 1;
			}
		}
		return sum;
	}

}
