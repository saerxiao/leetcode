package hackerrank.string;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BearSteadyGene {
	
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			in.nextLine();
			String row = in.nextLine();
			char[] A = row.toCharArray();
			System.out.println(shortestSubstringToReplace(A));
		}
	}

	public static int shortestSubstringToReplace(char[] A) {
		Map<Character, Integer> geneIndexMap = new HashMap<>();
		geneIndexMap.put('A', 0);
		geneIndexMap.put('C', 1);
		geneIndexMap.put('T', 2);
		geneIndexMap.put('G', 3);
		
		int[] excess = new int[4];
		for (int i = 0; i < A.length; i++) {
			excess[geneIndexMap.get(A[i])]++;
		}
		int totalExcess = 0;
		int expected = A.length / 4;
		for (int i = 0; i < 4; i++) {
			excess[i] = excess[i] > expected ? excess[i] - expected : 0;
			totalExcess += excess[i];
		}
		
		if (totalExcess == 0) return 0;
		
		int minL = A.length;
		int[] minLs = new int[A.length];
		int[] hist = new int[4];
		for (int i = 0; i < A.length; i++) {
			hist[geneIndexMap.get(A[i])]++;
			if (hasEnoughToReplace(hist, excess)) {
				minLs[0] = i + 1;
				minL = Math.min(minL, minLs[0]);
				break;
			}
		}
		
		if (minL > totalExcess) {
			for (int i = 1; i < A.length - totalExcess; i++) {
				hist[geneIndexMap.get(A[i-1])]--;
				if (excess[geneIndexMap.get(A[i-1])] == 0 || 
						hist[geneIndexMap.get(A[i-1])] >= excess[geneIndexMap.get(A[i-1])]) {
					minLs[i] = minLs[i-1] - 1;
				} else {
					int j = i - 1 + minLs[i-1];
					while ( j < A.length && A[j] != A[i-1]) {
						hist[geneIndexMap.get(A[j])]++;
						j++;
					}
					if ( j == A.length) {
						return minL;
					}
					hist[geneIndexMap.get(A[j])]++;
					minLs[i] = j - i + 1;
				}
				minL = Math.min(minL, minLs[i]);
			}
		}
		return minL;
	}
	
	private static void print(int[] minLs) {
		for (int i = 0; i < minLs.length; i++) {
			System.out.print(minLs[i] + " ");
		}
		System.out.println();
	}
	
	private static boolean hasEnoughToReplace(int[] actual, int[] expected) {
		for (int i = 0; i < actual.length; i++) {
			if (actual[i] < expected[i]) {
				return false;
			}
		}
		return true;
	}
}
