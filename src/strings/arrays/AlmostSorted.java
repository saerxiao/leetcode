package strings.arrays;

import org.junit.Test;

public class AlmostSorted {
	
	@Test
	public void test() {
//		int[] A = new int[]{4,2};
//		int[] A = new int[]{3,1,2};
//		int[] A = new int[]{1,5,4,3,2};
//		int[] A = new int[]{1,5,4,3,2,6};
//		int[] A = new int[]{5,4,3,2};
//		int[] A = new int[]{1,2};
//		int[] A = new int[]{2,1,3};
//		int[] A = new int[]{2,1,4,3};
//		int[] A = new int[]{3,2,1,4};
//		int[] A = new int[]{0,3,2,1,4,5};
//		int[] A = new int[]{8,7,6,5,14,15};
		int[] A = new int[]{1,2,8,5,4,9};
		Result rlt = isAlmostSorted(A);
		if (rlt.isAlmostSorted) {
			System.out.println("yes");
			if (rlt.type == Result.Type.swap) {
				System.out.println("swap " + rlt.range[0] + " " + rlt.range[1]);
			} else if (rlt.type == Result.Type.reverse) {
				System.out.println("reverse " + rlt.range[0] + " " + rlt.range[1]);
			}
		} else {
			System.out.println("no");
		}
	}
	
	public static Result isAlmostSorted(int[] A) {
		int desc = 0;
		int peaks = 0;
		int firstPeak = -1, secondPeak = -1;
		boolean setRange = false;
		Result rlt = new Result();
		for (int i = 1; i <= A.length - 1; i++) {
			if (A[i] < A[i-1]) {
				if (peaks == 0) {					
					if (desc < 1) {						
						rlt.type = Result.Type.swap;
						firstPeak = i-1;
					} else {
						rlt.type = Result.Type.reverse;
					}					
				} else if (peaks == 1) {
					if (rlt.type == Result.Type.swap) {
						if (desc == 0) {
							secondPeak = i-1;
						} else {
							return rlt;
						}
					} else {
						return rlt;
					}					
				} else {
					return rlt;
				}
				desc++;
			} else {
				if (desc > 0) {
					if (firstPeak >= 0) {
						if (rlt.type == Result.Type.reverse) {
							if (canReverse(A, firstPeak, i)) {
								rlt.range[0] = firstPeak + 1;
								rlt.range[1] = i;
								setRange = true;
							} else {
								return rlt;
							}							
						}
						if (secondPeak > 0) {
							if (canSwap(A, firstPeak, secondPeak, i)) {
								rlt.range[0] = firstPeak + 1;
								rlt.range[1] = i;
								setRange = true;
							} else {
								return rlt;
							}	
						}
					}
					peaks++;
					desc = 0;
				}				
			}
		}
		if (rlt.type != Result.Type.none && !setRange) {
			if (rlt.type == Result.Type.swap) {
				if (canSwap(A, firstPeak, secondPeak, A.length - 1)) {
					rlt.range[0] = firstPeak + 1;
					if (secondPeak == -1) {
						rlt.range[1] = firstPeak + 2;
					} else {
						rlt.range[1] = secondPeak + 2;
					}				
				} else {
					return rlt;
				}
			} else {
				if (canReverse(A, firstPeak, A.length)) {
					rlt.range[0] = firstPeak + 1;
					rlt.range[1] = A.length;
				} else {
					return rlt;
				}
			}
		}
		
		if (rlt.type == Result.Type.reverse) {
			if (rlt.range[1] - rlt.range[0] == 2) {
				rlt.type = Result.Type.swap;
			}
		}
		rlt.isAlmostSorted = true;
		return rlt;
	}
	
	private static boolean canReverse(int[] A, int firstPeak, int cont) {
		boolean canReverse = true;
		if (cont < A.length) {
			canReverse &= A[firstPeak] < A[cont];
		}
		
		if (firstPeak > 0) {
			canReverse &= A[firstPeak - 1] < A[cont - 1];
		}
		return canReverse;
	}
	
	private static boolean canSwap(int[] A, int firstPeak, int secondPeak, int i) {
		if (secondPeak == -1) {
			if ( A[i] < A[i-1]) {
				if (firstPeak == 0 || (A[firstPeak + 1] > A[firstPeak - 1])) {
					return true;
				} else {
					return false;
				}
			} else {
				boolean swap = A[firstPeak] < A[i];
				if (firstPeak > 0) {
					swap &= A[firstPeak + 1] > A[firstPeak - 1];
				}
				return swap;
			}
			
		} else {
			boolean canSwap = true;
			canSwap = A[secondPeak] < A[firstPeak];
			if (secondPeak + 2 < A.length) {
				canSwap &= A[firstPeak] < A[secondPeak + 2];
			}
			canSwap &= A[secondPeak + 1] < A[firstPeak + 1];
			if (firstPeak > 0) {
				canSwap &= A[firstPeak - 1] < A[secondPeak + 1];
			}
			return canSwap;
		}		
	}

	private static class Result {
		public enum Type {
			none,
			swap,
			reverse;
		}
		boolean isAlmostSorted;
		Type type = Type.none;
		int[] range = new int[2];
	}
}
