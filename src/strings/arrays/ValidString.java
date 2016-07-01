package strings.arrays;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class ValidString {

	public static boolean isValid(String A) {
		int[] cnt = new int[26];
		for (int i = 0; i < A.length(); i++) {
			cnt[A.charAt(i) - 'a']++;
		}
		
		Map<Integer, Integer> stats = new HashMap<>();
		for (int i = 0; i < cnt.length; i++) {
			if (cnt[i] > 0) {
				Integer c = stats.get(cnt[i]);
				if (c == null) {
					stats.put(cnt[i], 1);
				} else {
					stats.put(cnt[i], c + 1);
				}
			}
		}
		
		boolean isValid = false;
		if (stats.size() == 2) {
			if (stats.containsKey(1)) {
				if (stats.get(1) == 1 || (stats.containsKey(2) && stats.get(2) == 1)) {
					isValid =  true;
				}
			} else {
				int cnt1 = -1;
				for (int c : stats.keySet()) {
					if (cnt1 == -1) {
						cnt1 = c;
					} else {
						if ((c == cnt1 + 1 && stats.get(c) == 1) || (cnt1 == c + 1 && stats.get(cnt1) == 1)) {
							isValid = true;
						}
					}
				}
			}			
		} else if (stats.size() == 1) {
			isValid = true;
		}
		
//		if (stats.size() > 2) {
//			isValid = false;
//		} else if (stats.size() == 2) {
//			if (stats.containsKey(1)) {
//				if (stats.get(1) == 1 || (stats.containsKey(2) && stats.get(2) == 1)) {
//					isValid =  true;
//				} else {
//					isValid = false;
//				}
//			} else {
//				int cnt1 = -1;
//				for (int c : stats.keySet()) {
//					if (cnt1 == -1) {
//						cnt1 = c;
//					} else {
//						if ((c == cnt1 + 1 && stats.get(c) == 1) || (cnt1 == c + 1 && stats.get(cnt1) == 1)) {
//							isValid = true;
//						} else {
//							isValid = false;
//						}
//					}
//				}
//			}
//		} else {
//			isValid = true;
//		}
		return isValid;
	}
	
	@Test
	public void test() {
		String A = "aabbcd";
		System.out.println(isValid(A));
	}
}
