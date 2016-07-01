package hackerrank.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class IntervalSelection {
	
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int T = in.nextInt();
			for (int t = 0; t < T; t++) {
				int N = in.nextInt();
				int[][] A = new int[N][2];
				for (int i = 0 ; i < N; i++) {
					A[i][0] = in.nextInt();
					A[i][1] = in.nextInt();
				}
				System.out.println(maxSubset(A));
			}
		}
	}
	
	public static int maxSubset(int[][] A) {
		Map<Integer, List<Integer>> map = new HashMap<>();
		int[] array = new int[A.length * 2];
		for (int i = 0; i < A.length; i++) {
			array[i*2] = A[i][0];
			array[i*2+1] = A[i][1];
			
			List<Integer> list = map.get(A[i][1]);
			if (list == null) {
				list = new ArrayList<Integer>();
				map.put(A[i][1], list);
			}
			list.add(A[i][0]);
		}
		Arrays.sort(array);
		
		int[] B = new int[A.length*2];
		int p = 0;
		B[p] = array[0];
		for (int i = 1; i < array.length; i++) {
			if (array[i] != B[p]) {
				p++;
				B[p] = array[i];
			}
		}
		
		int[] m = new int[p+1];
		int[] used = new int[p+1];
		for (int i = 0; i <= p; i++) {
			if (i > 0) {
				m[i] = m[i-1];
			}
			if (map.containsKey(B[i])) {
				List<Integer> list = map.get(B[i]);
				Collections.sort(list, Collections.reverseOrder());
				for (int j = 0; j < Math.min(2, list.size()); j++) {
					int k = findInB(B, list.get(j), i);
					if (isValid(used, k, i)) {
						addInterval(m, used, k, i);
					} else {
						break;
					}
				}
				if (m[i] == 0) {
					m[i] = m[i-1];
				}
			}
		}
		return m[p];
	}
	
	private static int findInB(int[] B, int t, int high) {
		int low = 0;
		int hi = high;
		while (low < high) {
			int mid = (low + hi) / 2;
			if (B[mid] == t) {
				return mid;
			} else if (B[mid] < t) {
				low = mid + 1;
			} else {
				hi = mid - 1;
			}
		}
		return low;
	}
	
	private static boolean isValid(int[] used, int low, int hi) {
		for (int i = low; i <= hi; i++) {
			if (used[i] > 1) {
				return false;
			}
		}
		return true;
	}
	
	private static void addInterval(int[] m, int[] used, int low, int hi) {
		for (int i = low; i <= hi; i++) {
			m[i]++;
			used[i]++;
		}
	}
	
//	public static int maxSubset(Point[] points) {
//		Arrays.sort(points, new PointComparator());
//		
//		int[] m = new int[points.length];
//		m[0] = 1;
//		m[1] = 2;
//		for (int i = 2; i < points.length; i++) {
//			for (int j = i-1; j >= 0; j--) {
//				if (points[j].end < points[i].start) {
//					if (j + 1 < i) {
//						m[i] = m[j+1] + 1;
//					} else {
//						m[i] = m[j] + 1;
//					}
//					break;
//				}
//			}
//			if (m[i] == 0) {
//				m[i] = 2;
//			}
//		}
//		return m[points.length - 1];
//	}
	
	private static class PointComparator implements Comparator<Point> {

		@Override
		public int compare(Point o1, Point o2) {
			if (o1.start != o2.start) {
				return o1.start - o2.start;
			} else {
				return o1.end - o2.end;
			}
		}
		
	}
	
	private static class Point {
		int start;
		int end;
		
		Point(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

}

//1
//11
//2 5
//1 1
//1 2
//1 2
//2 3
//5 5
//3 5
//1 2
//1 4
//2 4
//3 4

//1
//3
//1 2
//2 3
//2 4