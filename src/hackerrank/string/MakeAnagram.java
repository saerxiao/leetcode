package hackerrank.string;

import java.util.Scanner;

public class MakeAnagram {
	
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			String a = in.nextLine();
			String b = in.nextLine();
			System.out.println(minToDelete(a, b));
		}
	}
	
	public static int minToDelete(String a, String b) {
		int[] hist1 = new int[26];
		for (int i = 0; i < a.length(); i++) {
			hist1[a.charAt(i) - 'a']++;
		}
		
		int[] hist2 = new int[26];
		for (int i = 0; i < b.length(); i++) {
			hist2[b.charAt(i) - 'a']++;
		}
		
		int sum = 0;
		for (int i = 0; i < 26; i++) {
			sum += Math.abs(hist1[i] - hist2[i]);
		}
		return sum;
	}

}
