package strings.arrays;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class Sort {

	private static int[] merge(int[] A, int[] B) 
	{
		int[] result = new int[A.length + B.length];
		int i = 0, j = 0, k = 0;
		while (i < A.length && j < B.length)
		{
			if (A[i] < B[j])
			{
				result[k] = A[i];
				++i;
			}
			else
			{
				result[k] = B[j];
				++j;
			}
			++k;
		}
		if (i < A.length)
			System.arraycopy(A, i, result, k, result.length - k);
		else
			System.arraycopy(B, j, result, k, result.length - k);
		return result;
	}
	
	public static int[] mergeSort(int[] A, int low, int hi) {
		if (low == hi)
			return new int[] {};
		else if (low == hi - 1)
			return new int[] {A[low]};
		else if (low < hi) {
			int mid = (low + hi) / 2;
			int[] left = mergeSort(A, low, mid);
			int[] right = mergeSort(A, mid, hi);
			return merge(left, right);
		}
		else
		{
			throw new RuntimeException("Shouldn't get here");
		}
		
	}
	
	@Test
	public void test() {
		int[][] testCases =
			{{}, {1}, {2,1}, {2,03,4912,48,203,4,3,198,3,41}};
		for (int[] t : testCases)
		{
			int[] result = mergeSort(t, 0, t.length);
			ArrayList<Integer> list = new ArrayList<Integer>(result.length);
			for (int i = 0; i < result.length; i++)
			  list.add(Integer.valueOf(result[i]));
			System.out.println(list);
		}
		
	}
}
