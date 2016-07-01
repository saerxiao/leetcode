package dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class CommonStringSeq {

	public static Set<String> longestCommonSubSequence(String a, String b) {
		if (a == null || a.length() == 0 || b == null || b.length() == 0) 
			return Collections.EMPTY_SET;
		
		int[][] m = new int[a.length()][b.length()];
		for (int i = 0; i < a.length(); i++) {
			for (int j = 0; j < b.length(); j++) {
				m[i][j] = Math.max(i > 0 ? m[i-1][j] : 0, j > 0 ? m[i][j-1] : 0);
				if (a.charAt(i) == b.charAt(j)) {
					m[i][j] = Math.max(m[i][j], i > 0 && j > 0 ? m[i-1][j-1] + 1 : 1);
				}
			}
		}
		
		System.out.println("longest common subsequence's length is: " 
				+ m[a.length() - 1][b.length() - 1]);
//		print(m);
		return allLongest(a, b, m);
	}
	
	private static void print(int[][] m) {
		for(int i=0; i<m.length; i++) {
			for(int j=0; j<m[i].length; j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	private static Set<String> allLongest(String a, String b, int[][] m) {
		Set<String> list = new HashSet<>();
		int i = m.length - 1;
		int j = m[0].length - 1;
		findPath(a, b, m, i, j, list, new StringBuilder());
		return list;
	}
	
	private static void findPath(String a, String b, int[][] m, int i, int j, 
			Set<String> list, StringBuilder str) {
		if (i < 0 || j < 0) {
			StringBuilder found = new StringBuilder(str);
			list.add(found.reverse().toString());
		}
		else if (i > 0 && j > 0) {
			if (a.charAt(i) == b.charAt(j)) {
				str.append(a.charAt(i));
				findPath(a, b, m, i-1, j-1, list, str);
				str.deleteCharAt(str.length()-1);
			}
			if (m[i-1][j] == m[i][j]) {
				findPath(a, b, m, i-1, j, list, str);
			}
			if (m[i][j-1] == m[i][j]) {
				findPath(a, b, m, i, j-1, list, str);
			}
		} else if (i == 0 && j == 0) {
			if (m[i][j] == 1) {
				str.append(a.charAt(i));
				findPath(a, b, m, i-1, j-1, list, str);
				str.deleteCharAt(str.length()-1);
			} else {
				findPath(a, b, m, i-1, j, list, str);
			}
		} else if (i == 0) /* j > 0 */ {
			if (m[i][j-1] < m[i][j]) {
				str.append(a.charAt(i));
				findPath(a, b, m, i-1, j-1, list, str);
				str.deleteCharAt(str.length()-1);
			} else {
				findPath(a, b, m, i, j-1, list, str);
			}
		} else /* j==0 && i>0 */{
			if (m[i-1][j] < m[i][j]) {
				str.append(a.charAt(i));
				findPath(a, b, m, i-1, j-1, list, str);
				str.deleteCharAt(str.length()-1);
			} else {
				findPath(a, b, m, i-1, j, list, str);
			}
		}
	}
	
	public static String findOneLongestCommonSubSeq(String a, String b) {
		if (a == null || a.length() == 0 || b == null || b.length() == 0) return "";
		int[][] m = new int[a.length()][b.length()];
		// 0 means from left, 1 means from up, 2 means from diagonal
		int[][] path = new int[a.length()][b.length()];
		if (a.charAt(0) == b.charAt(0)) {
			m[0][0] = 1;
			path[0][0] = 2;
		} else {
			m[0][0] = 0;
			path[0][0] = 0;
		}
		for (int i = 1; i < a.length(); i++) {
			if (a.charAt(i) == b.charAt(0)) {
				m[i][0] = 1;
				path[i][0] = 2;
			} else {
				m[i][0] = m[i-1][0];
				path[i][0] = 1;
			}			
		}
		for (int j = 1; j < b.length(); j++) {
			if (b.charAt(j) == a.charAt(0)) {
				m[0][j] = 1;
				path[0][j] = 2;
			} else {
				m[0][j] = m[0][j-1];
				path[0][j] = 0;
			}			
		}
		for (int i = 1; i < a.length(); i++) {
			for (int j = 1; j < b.length(); j++) {
				if (m[i-1][j] > m[i][j-1]) {
					m[i][j] = m[i-1][j];
					path[i][j] = 1;
				} else {
					m[i][j] = m[i][j-1];
					path[i][j] = 0;
				}
				if (a.charAt(i) == b.charAt(j)) {
					if (m[i-1][j-1] + 1 > m[i][j]) {
						m[i][j] = m[i-1][j-1] + 1;
						path[i][j] = 2;
					}
				}
			}
		}
		
		StringBuilder str = new StringBuilder();
		int i = a.length() - 1;
		int j = b.length() - 1;
		while (i > -1 && j > -1) {
			if (path[i][j] == 2) {
				str.append(a.charAt(i));
				i--;
				j--;
			} else if (path[i][j] == 0) {
				j--;
			} else {
				i--;
			}
		}
		return str.reverse().toString();
 	}
	
//	public static List<String> findAllLongestCommonSubSeq(String a, String b) {
//		// path[i][j][0] = true means from left, path[i][j][1] = true means from up
//		// path[i][j][2] = true means from diagonal
//		boolean[][][] path = new boolean[a.length()][b.length()][3];
//		int[][] m = new int[a.length()][b.length()];
// 	}
	
	@Test
	public void testLongestCommonSubSeq() {
//		String a = "badbacb";
//		String b = "abec";
//		String a = "bac";
//		String b = "bec";
//		String a = "ba";
//		String b = "ab";
//		String a = "db";
//		String b = "b";
//		String a = "a";
//		String b = "aa";
		String a = "alfalfa";
		String b = "aflafla";
		System.out.println(longestCommonSubSequence(a, b));
//		System.out.println(findOneLongestCommonSubSeq(a, b));
	}
}
