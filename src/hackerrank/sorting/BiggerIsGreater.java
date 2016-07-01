package hackerrank.sorting;

import java.util.Arrays;
import java.util.Scanner;

public class BiggerIsGreater {
	
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int T = in.nextInt();
			in.nextLine();
			for (int t = 0; t < T; t++) {
				String solution = smallestBigger(in.nextLine());
				if (solution == null) {
					System.out.println("no answer");
				} else {
					System.out.println(solution);
				}
			}
		}
	}

	public static String smallestBigger(String A) {
		char[] array = A.toCharArray();
		boolean exist = false;
		int k = 0;
		outerloop:
		for (int i = array.length - 2; i >= 0; i--) {
			for (int j = array.length - 1; j > i; j--) {
				if (array[i] < array[j]) {					
					char tmp = array[i];
					array[i] = array[j];
					array[j] = tmp;
					k = i;
					exist = true;
					break outerloop;
				}
			}
		}
		
		if (exist) {
			char[] secondHalf = new char[array.length - k - 1];
			System.arraycopy(array, k+1, secondHalf, 0, secondHalf.length);
			Arrays.sort(secondHalf);
			System.arraycopy(secondHalf, 0, array, k+1, array.length - k - 1);
			return new String(array);
		} else {
			return null;
		}
	} 
}
