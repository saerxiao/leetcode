package dp;

import org.junit.Test;

public class WalkingGrid {
	
	@Test
	public void test() {
		System.out.println(numberOfWays2D(2,3,1,1,3));
	}
	
	public static int numberOfWays2D(int d1, int d2, int x1, int x2, int M) {
		int[][][] m = new int[d1][d2][M + 1];
		for (int i = 0; i < d1; i++) {
			for (int j = 0; j < d2; j++) {
				m[i][j][0] = 1;
			}
		}
		
		for (int k = 1; k <= M; k++) {
			for (int i = 0; i < d1; i++) {
				for (int j = 0; j < d2; j++) {
					m[i][j][k] = (i > 0 ? m[i-1][j][k-1] : 0) 
								+ (i < d1 - 1 ? m[i+1][j][k-1] : 0)
								+ (j > 0 ? m[i][j-1][k-1] : 0)
								+ (j < d2 - 1 ? m[i][j+1][k-1] : 0);
					if (k == M && i == x1-1 && j == x2-1) {
						break;
					}
				}
			}
		}
		return m[x1-1][x2-1][M];
	}
	
	public class Tensor {
		int[] dims;
		int[] data;
		int[] stride;
		
		public Tensor(int[] dims) {
			this.dims = dims;
			stride = new int[dims.length];
			stride[0] = 1;
			for (int i = 0; i < dims.length - 1; ++i)
				stride[i+1] = stride[i] * dims[i];
			int totalSize = 1;
			for (int i = 0; i < dims.length; ++i)
				totalSize *= dims[i];
			data = new int[totalSize];
		}
		
		int getIndexFromCoordinate(int[] x) {
			int index = 0;
			for (int i = 0; i < x.length; ++i)
				index += x[i] * stride[i];
			return index;
		}
		
		int[] getCoordinateFromIndex(int index) {
			int[] x = new int[dims.length];
			int i = dims.length - 1;
			while (index > 0) {
				x[i] = index / stride[i];
				index = index % stride[i];
				i--;
			}
			return x;
		}
		
		int getElement(int[] x) {
			return data[getIndexFromCoordinate(x)];
		}

		void setElement(int[] x, int value) {
			data[getIndexFromCoordinate(x)] = value;
		}
	}
}
