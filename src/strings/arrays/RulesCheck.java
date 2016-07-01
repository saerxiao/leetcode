package strings.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class RulesCheck {

	public boolean check(String str, List<String> rules) {
		Map<String, Integer> ruleMap = createRuleMap(rules);
		int currentOrder = -1;
		for (int i = 0; i < str.length(); i++) {
			String s = String.valueOf(str.charAt(i));
			if (ruleMap.containsKey(s)) {
				int order = ruleMap.get(s);
				if (order < currentOrder) {
					return false;
				} else {
					currentOrder = order;
				}
			}
		}
		return true;
	}
	
	private Map<String, Integer> createRuleMap(List<String> rules) {
		int order = 0;
		Map<String, Integer> map = new HashMap<>();
		for (String r : rules) {
			map.put(r, order);
			order++;
		}
		return map;
	}
	
	@Test
	public void test() {
		List<String> rules = Arrays.asList("A","B","C");
//		String str = "A";
//		String str = "B";
//		String str = "C";
//		String str = "AB";
//		String str = "AC";
//		String str = "BC";
		String str = "ABCBBBBC";
		System.out.print(check(str, rules));
	}
}
