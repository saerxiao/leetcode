package strings.arrays;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class AnagramPairs {
	
	@Test
	public void test() {
//		String s = "abba";
//		String s = "abcd";
//		String s = "ifailuhkqq";
//		String s = "hucpoltgty";
//		String s = "ovarjsnrbf";
//		String s = "pvmupwjjjf";
//		String s = "iwwhrlkpek";
//		String s = "ifailuhkqqhucpoltgtyovarjsnrbfpvmupwjjjfiwwhrlkpekxxnebfrwibylcvkfealgonjkzwlyfhhkefuvgndgdnbelgruel"; //399
//		String s = "gffryqktmwocejbxfidpjfgrrkpowoxwggxaknmltjcpazgtnakcfcogzatyskqjyorcftwxjrtgayvllutrjxpbzggjxbmxpnde"; //471
//		String s = "mqmtjwxaaaxklheghvqcyhaaegtlyntxmoluqlzvuzgkwhkkfpwarkckansgabfclzgnumdrojexnrdunivxqjzfbzsodycnsnmw"; //370
//		String s = "ofeqjnqnxwidhbuxxhfwargwkikjqwyghpsygjxyrarcoacwnhxyqlrviikfuiuotifznqmzpjrxycnqktkryutpqvbgbgthfges"; //403
//		String s = "zjekimenscyiamnwlpxytkndjsygifmqlqibxxqlauxamfviftquntvkwppxrzuncyenacfivtigvfsadtlytzymuwvpntngkyhw"; //428
//		String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"; //166650
//		String s = "bbcaadacaacbdddcdbddaddabcccdaaadcadcbddadababdaaabcccdcdaacadcababbabbdbacabbdcbbbbbddacdbbcdddbaaa"; //4832
//		String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"; //166650
//		String s = "cacccbbcaaccbaacbbbcaaaababcacbbababbaacabccccaaaacbcababcbaaaaaacbacbccabcabbaaacabccbabccabbabcbba"; //13022
//		String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"; //166650
		String s = "bbcbacaabacacaaacbbcaabccacbaaaabbcaaaaaaaccaccabcacabbbbabbbbacaaccbabbccccaacccccabcabaacaabbcbaca"; //9644
//		String s = "cbaacdbaadbabbdbbaabddbdabbbccbdaccdbbdacdcabdbacbcadbbbbacbdabddcaccbbacbcadcdcabaabdbaacdccbbabbbc"; //6346
//		String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"; //166650
//		babacaccaaabaaaaaaaccaaaccaaccabcbbbabccbbabababccaabcccacccaaabaccbccccbaacbcaacbcaaaaaaabacbcbbbcc //8640
//		bcbabbaccacbacaacbbaccbcbccbaaaabbbcaccaacaccbabcbabccacbaabbaaaabbbcbbbbbaababacacbcaabbcbcbcabbaba //11577
//		dbcfibibcheigfccacfegicigcefieeeeegcghggdheichgafhdigffgifidfbeaccadabecbdcgieaffbigffcecahafcafhcdg //1464
//		dfcaabeaeeabfffcdbbfaffadcacdeeabcadabfdefcfcbbacadaeafcfceeedacbafdebbffcecdbfebdbfdbdecbfbadddbcec //2452
//		gjjkaaakklheghidclhaaeggllagkmblhdlmihmgkjhkkfcjaekckaafgabfclmgahmdebjekaedhaiikdjmfbmfbdlcafamjbfe //873
//		fdbdidhaiqbggqkhdmqhmemgljaphocpaacdohnokfqmlpmiioedpnjhphmjjnjlpihmpodgkmookedkehfaceklbljcjglncfal //585
//		bcgdehhbcefeeadchgaheddegbiihehcbbdffiiiifgibhfbchffcaiabbbcceabehhiffggghbafabbaaebgediafabafdicdhg //1305
//		aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa //166650
//		mhmgmbbccbbaffhbncgndbffkjbhmkfncmihhdhcebmchnfacdigflhhbekhfejblegakjjiejeenibemfmkfjbkkmlichlkbnhc //840
//		fdacbaeacbdbaaacafdfbbdcefadgfcagdfcgbgeafbfbggdedfbdefdbgbefcgdababafgffedbefdecbaabdaafggceffbacgb //2134
//		bahdcafcdadbdgagdddcidaaicggcfdbfeeeghiibbdhabdhffddhffcdccfdddhgiceciffhgdibfdacbidgagdadhdceibbbcc //1571
//		dichcagakdajjhhdhegiifiiggjebejejciaabbifkcbdeigajhgfcfdgekfajbcdifikafkgjjjfefkdbeicgiccgkjheeiefje //1042
		
		System.out.println(allAnagramPairs(s));
	}

	public static int allAnagramPairs(String A) {
		Set<Pair> totalSet = new HashSet<>();
		int cnt = 0;
		Set<Pair> set = new HashSet<>();
		for (int i = 0; i < A.length() - 1; i++) {
			for (int j = i + 1; j < A.length(); j++) {
				if (A.charAt(i) == A.charAt(j)) {
					set.add(new Pair(i, i, j, j));
				}
			}
		}
		totalSet.addAll(set);
		print(set);
		cnt += set.size();
		while (!set.isEmpty()) {
			Set<Pair> nextSet = new HashSet<>();
			for (Pair pair : set) {				
				if (pair.p > pair.j + 1) {
					Pair p1 = new Pair(pair.i, pair.p-1, pair.j+1, pair.q);
					if (!totalSet.contains(p1)) {
						nextSet.add(p1);
					}
				}			
				if (pair.i > 0) {
					if (pair.p > 0 && A.charAt(pair.i-1) == A.charAt(pair.p-1)) {
						Pair p1 = new Pair(pair.i-1, pair.j, pair.p-1, pair.q);
						if (!totalSet.contains(p1)) {
							nextSet.add(p1);
						}						
					}
					if (pair.q < A.length() - 1 && A.charAt(pair.i-1) == A.charAt(pair.q + 1)) {
						Pair p1 = new Pair(pair.i-1, pair.j, pair.p, pair.q+1);
						if (!totalSet.contains(p1)) {
							nextSet.add(p1);
						}						
					}
				}
				if (pair.j+1 != pair.q) {					
					if (/*pair.p - 1 < pair.j + 1 ||*/ (A.charAt(pair.j+1) == A.charAt(pair.p-1))) {
						Pair p1 = new Pair(pair.i, pair.j+1, pair.p-1, pair.q);
						if (!totalSet.contains(p1)) {
							nextSet.add(p1);
						}
					}
				}
				
				if (pair.q < A.length() - 1 && A.charAt(pair.j+1) == A.charAt(pair.q+1)) {
					Pair p1 = new Pair(pair.i, pair.j+1, pair.p, pair.q+1);
					if (!totalSet.contains(p1)) {
						nextSet.add(p1);
					}
				}
			}			
			cnt += nextSet.size();
			set = nextSet;
			totalSet.addAll(nextSet);
			print(set);
		}
		return cnt;
	}
	
	private static void print(Set<Pair> set) {
		for (Pair pair : set) {
			System.out.println(pair);
		}
	}
	
	private static class Pair {
		int i;
		int j;
		int p;
		int q;

		public Pair(int i, int j, int p, int q) {
			this.i = i;
			this.j = j;
			this.p = p;
			this.q = q;
		}

		@Override
		public boolean equals(Object obj) {
			Pair other = (Pair) obj;
			return this.i == other.i && this.j == other.j && this.p == other.p && this.q == other.q;
		}
		
		@Override
		public int hashCode() {
			int rlt = 1;
			rlt = 37 * rlt + i;
			rlt = 37 * rlt + j;
			rlt = 37 * rlt + p;
			rlt = 37 * rlt + q;
			return rlt;
		}
		
		public String toString() {
			return "[" + i + "," + j + "] and [" + p + "," + q + "]";
		}
	}
}
