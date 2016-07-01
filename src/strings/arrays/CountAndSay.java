package strings.arrays;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CountAndSay {
	
	public List<Integer> countNSay(int N) {
		List<Integer> current = new ArrayList<>();
		current.add(N);
		for (int i = 0; i < N; i++) {
			List<Integer> next = new ArrayList<>();
			int c = current.get(0);
			int cnt = 1;
			for (int p = 1; p < current.size(); p++) {
				if (current.get(p) == c) {
					cnt++;
				} else {
					next.add(cnt);
					next.add(c);
					c = current.get(p);
					cnt = 1;
				}
			}
			next.add(cnt);
			next.add(c);
			current = next;
		}
		return current;
	}
	
	@Test
	public void test() {
		int N = 3;
		List<Integer> seq = countNSay(N);
		for (Integer c : seq) {
			System.out.print(c);
		}
	}

}
