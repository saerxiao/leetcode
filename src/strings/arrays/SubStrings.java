package strings.arrays;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.Test;

public class SubStrings {

	public String longestSubstringWith2UniqueChar(String s) {
		Map<Character, Integer> charMap = new HashMap<>();
		String longest = "";
		int start = 0;
		int length = 0;
		for (int i = 0;  i < s.length(); i++) {
			if (charMap.size() < 2 || charMap.containsKey(s.charAt(i))) {
				charMap.put(s.charAt(i), i);
				length++;
			} else {				
				char keyEndFirst = getKeyEndsFirst(charMap);
				start = charMap.get(keyEndFirst) + 1;
				length = i - start + 1;
				charMap.remove(keyEndFirst);
				charMap.put(s.charAt(i), i);				
			}
			if (length > longest.length()) {
				longest = s.substring(start, start + length);
			}
		}
		return longest;
	}
	
	private Character getKeyEndsFirst(Map<Character, Integer> charMap) {
		Iterator<Character> it = charMap.keySet().iterator();
		Character firstKey = it.next();
		int firstPos = charMap.get(firstKey);
		while (it.hasNext()) {
			Character c = it.next();
			if (charMap.get(c) < firstPos) {
				firstKey = c;
				firstPos = charMap.get(c);
			}
		}
		return firstKey;
	}
	
	@Test
	public void test() {
		String s = "abcbbbbcccbdddadacb";
		System.out.println(longestSubstringWith2UniqueChar(s));
	}
}
