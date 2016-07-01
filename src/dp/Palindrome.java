package dp;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

public class Palindrome {
	
	public static Set<String> longestPalindromeSubseq(String a) {
		int[][] m = new int[a.length()][a.length()];
		
		for (int j = 0; j < a.length(); j++) {
			for (int i = j; i >= 0; i--) {
				if ( i == j) {
					m[i][j] = 1;
				} else {
					m[i][j] = Math.max(m[i+1][j], m[i][j-1]);
					if (a.charAt(i) == a.charAt(j)) {
						// when i=j-1, m[i+1][j-1] is in the half of the matrix
						// which is not populated, so use the default value 0, which is correct
						// for the case j=0, the program won't come to here
						m[i][j] = Math.max(m[i][j], m[i+1][j-1] + 2);
					}
				}
			}
		}
//		print(m);
		System.out.println(m[0][a.length()-1]);
		return findAllPaths(a, m);
	}

	private static Set<String> findAllPaths(String a, int[][] m) {
		Set<String> path = new HashSet<>();
		findAll(a, m, 0, a.length()-1, path, new StringBuilder());
		return path;
	}
	
	private static void findAll(String a, int[][] m, int i, int j, 
			Set<String> path, StringBuilder str) {
		if (i == j) {
			int pos = str.length() / 2;
			str.insert(pos, a.charAt(i));
			path.add(str.toString());
			str.deleteCharAt(pos);
		} else {
			if (a.charAt(i) == a.charAt(j) && m[i+1][j-1] + 2 == m[i][j]) {
				int pos = str.length() / 2;
				str.insert(pos, a.charAt(i));
				str.insert(pos, a.charAt(i));
				if ( i+1 == j) {
					path.add(str.toString());
				} else {
					findAll(a, m, i+1, j-1, path, str);
				}						
				str.deleteCharAt(0);
				str.deleteCharAt(str.length()-1);
			}
			if (m[i+1][j] == m[i][j]) {
				findAll(a, m, i+1, j, path, str);
			}
			if (m[i][j-1] == m[i][j]) {
				findAll(a, m, i, j-1, path, str);
			}
		}
			
	}
	
	private static void print(int[][] m) {
		for(int i=0; i<m.length; i++) {
			for(int j=0; j<m[i].length; j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	@Test
	public void test() {
//		String a ="abb";
//		String a = "abca";
		String a = "alfalfa";
//		System.out.println(longestPalindromeSubseq(a));
		Set<String> set = longestPalindromeSubseq(a);
		Iterator<String> it = set.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
}
