package strings.arrays;

import org.junit.Test;

public class IsNumber {
	
	public static boolean isNumber(String str) {
		if (str == null || str.isEmpty()) return false;
		if (str.charAt(0) == '-' || str.charAt(0) == '+') {
			return isPositiveNumber(str.substring(1));
		} else {
			return isPositiveNumber(str);
		}
	}

	private static boolean isPositiveNumber(String str) {
		boolean decimal = false;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '.') {
				if (decimal) {
					return false;
				} else {
					decimal = true;
				}
			} else if (str.charAt(i) < '0' || str.charAt(i) > '9') {
				return false;
			}
		}
		return true;
	}
	
	@Test
	public void test() {
		System.out.println(+.01 + -002.0);
		String str = "-.32";
		System.out.println(isNumber(str));
	}
}
