package hackerrank.string;

import java.util.Scanner;

public class Anagram {
	
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int T = in.nextInt();
			in.nextLine();
			for (int t = 0; t < T; t++) {
				System.out.println(minToChange(in.nextLine()));
			}
		}
	}
	
	public static int minToChange(String s) {
		if (s.length() % 2 > 0) return -1;
		
		int[] hist1 = new int[26];
		int[] hist2 = new int[26];
		int i = 0, j = s.length() - 1;
		while (i < j) {
			hist1[s.charAt(i) - 'a']++;
			hist2[s.charAt(j) - 'a']++;
			i++;
			j--;
		}
		
		int sum = 0;
		for (int p = 0; p < 26; p++) {
			if (hist1[p] > hist2[p]) {
				sum += hist1[p] - hist2[p];
			}
		}
		return sum;
	}

}
