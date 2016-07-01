package dp;

import org.junit.Test;

public class DungeonGame {
	
	public int minBloodNeed(int[][] d) {
		int m = d.length;
		int n = d[0].length;
		int[][] b = new int[m][n];
		b[m-1][n-1] = Math.max(1, 1 - d[m-1][n-1]);
		for (int i = m-2; i >= 0; i--) {
			b[i][n-1] = Math.max(1, b[i+1][n-1] - d[i][n-1]);  
		}
		for (int j = n-2; j >= 0; j--) {
			b[m-1][j] = Math.max(1, b[m-1][j+1] - d[m-1][j]);
		}
		for (int i = m-2; i >= 0; i--) {
			for (int j = n-2; j >= 0; j--) {
				b[i][j] = Math.max(1, Math.min(b[i+1][j], b[i][j+1]) - d[i][j]);
			}
		}
		return b[0][0];
	}
	
	private int minBloodNeeded(int[][] d) {
		int m = d.length;
		int n = d[0].length;
		
		int[][] b = new int[m][n];
		b[m-1][n-1] = d[m-1][n-1] + 1; // wrong
		
		for (int i = m-2; i >= 0; i--) {
			b[i][n-1] = b[i+1][n-1] + d[i][n-1] + 1; //wrong
		}
		
		for (int j = n-2; j >= 0; j--) {
			b[m-1][j] = b[m-1][j+1] + d[m-1][j] + 1; //wrong
		}
		
		for (int i = m-2; i >= 0; i--) {
			for(int j = n-2; j >= 0; j--) {
				int up = Math.max(b[i+1][j]-d[i][j], 1);
				int left = Math.max(b[i][j+1]-d[i][j], 1);
				b[i][j] = Math.min(up, left);
			}
		}
		
		return b[0][0];
	}
	
	// This solution wrong!
	// the -(max blood left on each node) != min blood needed
	private int minBlood(int[][] d, int m, int n) {
		// 1 means from i-1, 2 means from j-1
		int[][] pointer = new int[m][n];
		int[][] max = new int[m][n];
		
		max[0][0] = d[0][0];
		for(int i = 1; i < m; i++) {
			max[i][0] = max[i-1][0] + d[i][0];
			pointer[i][0] = 1;
		}
		
		for(int j = 1; j < n; j++) {
			max[0][j] = max[0][j-1] + d[0][j];
			pointer[0][j] = 2;
			System.out.println(" 0 " + j + ": " + max[0][j]);
		}
		
		for (int i=1; i<m; i++) {
			for (int j=1; j<n; j++) {
				max[i][j] = Math.max(max[i-1][j], max[i][j-1]) + d[i][j];
				pointer[i][j] = max[i-1][j] > max[i][j-1] ? 1 : 2;
				System.out.println(i + " " + j + ": "+ max[i][j]);
			}
		}
				
		int i=m-1, j=n-1;
		int minBloodLevel = max[m-1][n-1];
		while (i > 0 || j > 0) {
			if (pointer[i][j] == 1) i--;
			else j--;
			System.out.println(" i= " + i + " j=" + j);
			minBloodLevel = Math.min(minBloodLevel, max[i][j]);
		}
		System.out.println(minBloodLevel);
		return minBloodLevel >=0 ? 0 : -minBloodLevel;
	}
	
	public int calculateMinimumHP(int[][] dungeon) {
		int m = dungeon.length;
		int n = dungeon[0].length;
	 
		//init dp table
		int[][] h = new int[m][n];
	 
		h[m - 1][n - 1] = Math.max(1 - dungeon[m - 1][n - 1], 1);
	 
		//init last row
		for (int i = m - 2; i >= 0; i--) {
			h[i][n - 1] = Math.max(h[i + 1][n - 1] - dungeon[i][n - 1], 1);
		}
	 
		//init last column
		for (int j = n - 2; j >= 0; j--) {
			h[m - 1][j] = Math.max(h[m - 1][j + 1] - dungeon[m - 1][j], 1);
		}
	 
		//calculate dp table
		for (int i = m - 2; i >= 0; i--) {
			for (int j = n - 2; j >= 0; j--) {
				int down = Math.max(h[i + 1][j] - dungeon[i][j], 1);
				int right = Math.max(h[i][j + 1] - dungeon[i][j], 1);
				h[i][j] = Math.min(right, down);
			}
		}
	 
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				System.out.println(i + " " + j + ": " + h[i][j]);
			}
		}
		return h[0][0];
	}
	
	@Test
	public void test() {
		int m = 3, n = 3;
		int[][] d = new int[3][3];
		d[0][0] = -2;
		d[0][1] = -3;
		d[0][2] = 3;
		d[1][0] = -5;
		d[1][1] = -10;
		d[1][2] = 1;
		d[2][0] = 10;
		d[2][1] = 30;
		d[2][2] = -5;
		
		System.out.println(minBloodNeeded(d));
		System.out.println(calculateMinimumHP(d));
		System.out.println(minBloodNeed(d));
	}

}
