package hackerrank.womeno.codesprint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.junit.Test;

public class SecretMessageGroups {
	
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int n = in.nextInt();
			in.nextLine();
			List<String> list = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				list.add(in.nextLine());
			}
			SecretMessageGroups solution = new SecretMessageGroups();
			solution.process(list);
			System.out.println(solution.numberOfGroups());
			solution.printTheLargeGroup();
		}
    }

	@Test
	public void test() {
//		List<String> list = Arrays.asList("133","213","312","331","313","123","133","24","56","5","331");
		List<String> list = Arrays.asList("009","09","0990","3", "33");
		process(list);
		System.out.println(numberOfGroups());
		printTheLargeGroup();
	}
	
//	private void printGroups() {
//		for (Group g : groups.keySet()) {
//			Set<String> set = groups.get(g);
//			for (String s : set) {
//				System.out.print(s + " ");
//			}
//			System.out.println();
//		}
//	}
	
	Map<Group, Set<String>> groups = new HashMap<>();
	
	public void process(List<String> list) {
		for (String a : list) {
			Group g = new Group(a);
			Set<String> gList = groups.get(g);
			if (gList == null) {
				gList = new HashSet<String>();
				groups.put(g, gList);
			}
			gList.add(a);
		}
	}
	
	public int numberOfGroups() {
		return groups.size();
	}

	public void printTheLargeGroup() {
		Map<Integer, List<Group>> map = new HashMap<>();
		int max = 1;
		for (Group g : groups.keySet()) {
			List<Group> list = map.get(groups.get(g).size());
			if (list == null) {
				list = new ArrayList<Group>();
				map.put(groups.get(g).size(), list);
			}
			list.add(g);
			if (groups.get(g).size() > max) {
				max = groups.get(g).size();
			}
		}
		List<Group> maxGroups = map.get(max);
		Map<Group, List<String>> maxGroupMap = new HashMap<>();
		for (Group g : maxGroups) {
			List<String> list = new ArrayList<String>(groups.get(g));
			list.sort(new Comparator<String>(){

				@Override
				public int compare(String o1, String o2) {
					return o2.compareTo(o1);
				}
				
			});
			maxGroupMap.put(g, list);
		}
		maxGroups.sort(new Comparator<Group>() {

			@Override
			public int compare(Group o1, Group o2) {				
				return maxGroupMap.get(o1).get(0).compareTo(maxGroupMap.get(o2).get(0));
			}
			
		});
		for (Group g : maxGroups) {
			List<String> list = maxGroupMap.get(g);
			for (String s : list) {
				System.out.print(s + " ");
			}
			System.out.println();
		}
	}
	
	private static class Group {
		int[] frequency = new int[10];
		int bitsSet = 0;
		
		public Group(String a) {
			for (int i = 0; i < a.length(); i++) {
				frequency[a.charAt(i) - '0']++;
			}
			slim();
		}
		
		private void slim() {
			int gcd = 0;
			int i = 0;
			while ( i < frequency.length ) {
				if (frequency[i] > 0) {
					bitsSet++;
					if (gcd == 0) {
						gcd = frequency[i];
					} else {
						gcd = gcd(gcd, frequency[i]);
					}
				}
				i++;
			}
			if (bitsSet > 1) {
				for (int k = 0; k < frequency.length; k++) {
					frequency[k] = frequency[k] / gcd;
				}
			}			
		}
		
		private int gcd (int a, int b) {
			if (a < b) {
				int tmp = a;
				a = b;
				b = tmp;
			}
			if (b == 0) {
				return a;
			}
			return gcd(b, a%b);
		}
		
		@Override
		public int hashCode() {
			int h = 1;
			for (int i = 0; i < frequency.length; i++) {
				h = 37 * h + frequency[i];
			}
			return h;
		}
		
		@Override
		public boolean equals(Object obj) {
			Group other = (Group)obj;
			for (int i = 0; i < frequency.length; i++) {
				if (frequency[i] != other.frequency[i]) {
					return false;
				}
			}
			return true;
		}
	}
}
