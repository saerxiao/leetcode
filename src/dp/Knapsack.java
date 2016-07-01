package dp;

import org.junit.Test;

public class Knapsack {
	
	public int knapsack01(int[][] items, int bagSize) {
		int[][] max = new int[items.length + 1][bagSize + 1];
		for (int i = 0; i < items.length + 1; i++) {
			max[i][0] = 0;
		}
		for (int j = 0; j < bagSize + 1; j++) {
			max[0][j] = 0;
		}
		for (int i = 1; i < items.length + 1; i++) {
			for (int j = 1; j < bagSize + 1; j++) {
				max[i][j] = Math.max(max[i-1][j],
						j - items[i-1][0] >= 0 ? max[i-1][j-items[i-1][0]] + items[i-1][1] : 0);
			}
		}
		return max[items.length][bagSize];
	}
	
	public int knapsackNolimit(int[][] items, int bagSize) {
		int[] max = new int[bagSize + 1];
		max[0] = 0;
		for (int s = 1; s < bagSize + 1; s++) {
			max[s] = max[s-1];
			for (int i = 0; i < items.length; i++) {
				max[s] = Math.max(max[s], 
						s - items[i][0] >= 0 ? max[s-items[i][0]] + items[i][1] : 0);
			}
		}
		return max[bagSize];
	}
	
	public int knapsackNolimitOld(int[][] items, int bagSize) {

		int[] max = new int[bagSize + 1];
		max[0] = 0;
		for (int i = 0; i < items.length; i++) {
			for (int j = 1; j < bagSize + 1; j++) {
				if ( j - items[i][0] >= 0) {
					max[j] = Math.max(max[j], max[j - items[i][0]] + items[i][1]);
				} else {
					max[j] = Math.max(max[j-1], max[j]);
				}
			}
		}
		return max[bagSize];
	}

	private void print(int[] A) {
		for (int i = 0; i < A.length; i++) {
			System.out.print(A[i] + " ");
		}
		System.out.println();
	}
	
	@Test
	public void test() {
		int[][] items = new int[][] {{1,1},{2,5},{2,2},{3,3}};
		int bagSize = 4;
		System.out.println(knapsackNolimit(items, bagSize));
		System.out.println(knapsack01(items, bagSize));
	}
}
