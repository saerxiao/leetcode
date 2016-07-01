package strings.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class PseudoIsomorphicSubstrings {
	
	public static int[] longestNonIsomorphicSubstrings(String A) {
		int[] rlt = new int[A.length()];
		rlt[0] = 1;
		List<Set<Map<Integer,BitSet>>> largestSet = new ArrayList<>();
		
		Set<Map<Integer, BitSet>> set = new HashSet<>();
		set.add(new HashMap<Integer, BitSet>());
		largestSet.add(set);
		
		for (int l = 2; l <= A.length(); l++) {
			rlt[l-1] = rlt[l-2];
			for (int i = l -1; i >=0; i--) {
				Map<Integer, BitSet> newMap = new HashMap<>();
				for (int p = i; p < l; p++) {
					BitSet newbs = new BitSet();
					for (int q = p + 1; q < l; q++) {
						if (A.charAt(p) == A.charAt(q)) {
							newbs.set(q - p);
						}
					}
					if (newbs.cardinality() > 0) {
						newMap.put(p - i, newbs);
					}
				}
				if (largestSet.size() >= l - i) {
					Set<Map<Integer, BitSet>> currentSet = largestSet.get(l - i - 1);
					if (!currentSet.contains(newMap)) {
						rlt[l-1]++;
						currentSet.add(newMap);
					}
				} else {
					Set<Map<Integer, BitSet>> newset = new HashSet<>();
					newset.add(newMap);
					largestSet.add(newset);
					rlt[l-1]++;
				}
			}
		}
		return rlt;
	}
	
	@Test
	public void test() {
//		1
//		2
//		3
//		5
//		7
//		10
//		13
//		17
//		22
//		27
//		33
//		42
//		51
//		60
//		69
//		81
//		93
//		106
//		119
//		133
		String A = "bdedbfbcbdebcedabacf";
//		String A = "bdedbf";
//		String A = "abb";
		int[] rlt = longestNonIsomorphicSubstrings(A);
		for (int i = 0; i < A.length(); i++) {
			System.out.println(rlt[i]);
		}
	}

}
