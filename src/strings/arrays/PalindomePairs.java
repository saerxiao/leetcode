package strings.arrays;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.junit.Test;

public class PalindomePairs {

	public static void main(String[] args) {
		/*
		 * Enter your code here. Read input from STDIN. Print output to STDOUT.
		 * Your class should be named Solution.
		 */
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.nextLine();
		for (int i = 0; i < n; i++) {
			String s = sc.nextLine();
			System.out.println(allPalindromePairs(s));
		}
	}
	
	@Test
	public void test() {
//		String s = "abba";
//		String s = "ifailuhkqq";
//		String s = "hucpoltgty";
		String s = "ovarjsnrbf";
		System.out.println(allPalindromePairs(s));
	}

	public static int allPalindromePairs(String A) {
		int cnt = 0;
		Set<Pair> set = new HashSet<>();
		for (int i = 0; i < A.length() - 1; i++) {
			for (int j = i + 1; j < A.length(); j++) {
				if (A.charAt(i) == A.charAt(j)) {
					set.add(new Pair(i, i, j, j));
				}
			}
		}
		print(set);
		cnt += set.size();
		while (!set.isEmpty()) {
			Set<Pair> nextSet = new HashSet<>();
			for (Pair pair : set) {
				if (pair.i > 0 && pair.q < A.length() - 1) {
					if (A.charAt(pair.i - 1) == A.charAt(pair.q + 1)) {
						nextSet.add(new Pair(pair.i - 1, pair.j, pair.p, pair.q + 1));
					}					
				}
				if (pair.j + 1 != pair.q) {
					if (A.charAt(pair.j + 1) == A.charAt(pair.p - 1)) {
						nextSet.add(new Pair(pair.i, pair.j + 1, pair.p - 1, pair.q));
					}
				}
			}			
			cnt += nextSet.size();
			set = nextSet;
			print(set);
		}
		return cnt;
	}
	
	private static void print(Set<Pair> set) {
		for (Pair pair : set) {
			System.out.println(pair);
		}
	}

	private static class Pair {
		int i;
		int j;
		int p;
		int q;

		public Pair(int i, int j, int p, int q) {
			this.i = i;
			this.j = j;
			this.p = p;
			this.q = q;
		}

		@Override
		public boolean equals(Object obj) {
			Pair other = (Pair) obj;
			return this.i == other.i && this.j == other.j && this.p == other.p && this.q == other.q;
		}
		
		@Override
		public int hashCode() {
			int rlt = 1;
			rlt = 37 * rlt + i;
			rlt = 37 * rlt + j;
			rlt = 37 * rlt + p;
			rlt = 37 * rlt + q;
			return rlt;
		}
		
		public String toString() {
			return "[" + i + "," + j + "] and [" + p + "," + q + "]";
		}
	}
}
