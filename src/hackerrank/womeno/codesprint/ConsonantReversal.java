package hackerrank.womeno.codesprint;

import java.util.BitSet;

import org.junit.Test;

public class ConsonantReversal {
	
	public static String reverseConsonant(String A) {
		BitSet cSet = new BitSet();
		char[] vowls = new char[] {'a','e','i','o','u'};
		for (int i = 0; i < vowls.length; i++) {
			cSet.set(vowls[i] - 'a');
		}
		
		char[] array = A.toCharArray();
		int i = 0, j = A.length() - 1;
		while ( i < j) {
			if (cSet.get(array[i] - 'a')) {
				i++;
			} else if (cSet.get(array[j] - 'a')) {
				j--;
			} else {
				char tmp = array[i];
				array[i] = array[j];
				array[j] = tmp;
				i++;
				j--;
			}
		}
		return String.valueOf(array);
	}

	@Test
	public void test() {
		String A = "eabafgs";
		System.out.println(reverseConsonant(A));
	}
}
