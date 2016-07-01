package tree;

import java.util.HashMap;
import java.util.Map;

public class BSTFrom1ToN {
	
	public int numberOfBST(int N) {
		BSTSet set = createBST(N);
		return set.number;
	}

	private BSTSet createBST(int n) {
		BSTSet set = new BSTSet();
		if (n == 1) {			
			set.number = 1;
			set.rightChildDepth.put(1,1);
		} else if (n > 1) {
			BSTSet previousSet = createBST(n-1);
			int cnt = previousSet.number;
			set.rightChildDepth.put(1, previousSet.number);
			for (int r : previousSet.rightChildDepth.keySet()) {
				cnt = cnt + previousSet.rightChildDepth.get(r);
				for (int i = 1; i <= r+1; i++) {
					int v = previousSet.rightChildDepth.get(r);
					if (set.rightChildDepth.containsKey(i)) {
						v = v + set.rightChildDepth.get(i);
					}
					set.rightChildDepth.put(i, v);
				}
			}
		}
		return set;
	}
	
	private static class BSTSet {
		int number;
		Map<Integer, Integer> rightChildDepth = new HashMap<>();
	}
}
