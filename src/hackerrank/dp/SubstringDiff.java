package hackerrank.dp;

import java.util.Scanner;

public class SubstringDiff {
	
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int T = in.nextInt();
			in.nextLine();
			for (int t = 0; t < T; t++) {
				String row = in.nextLine();
				String[] parts = row.split(" ");
				int S = Integer.parseInt(parts[0]);
				System.out.println(longestSubstringWithDiffS(S, parts[1], parts[2]));
			}
		}
	}
	
	public static int longestSubstringWithDiffS(int S, String A, String B) {
		char[] a = A.toCharArray();
		char[] b = B.toCharArray();
		int[][] m = new int[a.length][b.length];
		
		int max = 0;
		for (int i = 0; i < b.length; i++) {
			m[0][i] = substringWithDiffS(a, b, 0, i, S);
			max = Math.max(max, m[0][i]);
		}
		
		for (int i = 1; i < a.length; i++) {
			m[i][0] = substringWithDiffS(a, b, i, 0, S);
			max = Math.max(max, m[i][0]);
		}
				
		for (int i = 1; i < a.length; i++) {
			for (int j = 1; j < b.length; j++) {
				m[i][j] = m[i-1][j-1] - 1;
				if (a[i-1] != b[j-1]) {
					int p = i-1+m[i-1][j-1];
					int q = j-1+m[i-1][j-1];
					int k = 0;
					while (p < a.length && q < b.length && k < 2) {						
						if (a[p] != b[q]) {
							k++;
						}
						if (k < 2) {
							m[i][j]++;
							p++;
							q++;							
						}
					}
				}
				max = Math.max(max, m[i][j]);
			}
		}
		
//		print(m);
		return max;
	}
	
	private static void print(int[][] m) {
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[0].length; j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static int substringWithDiffS(char[] a, char[] b, int i, int j, int S) {
		int starti = i;
		int k = 0;
		while (i < a.length && j < b.length && k <= S) {
			if (a[i] != b[j]) {
				k++;
			}
			if ( k <= S) {
				i++;
				j++;
			}
		}
		return i - starti;
	}
}

//1
//2 tabrizo torinoo
//0 abacba abcaba
//3 helloworld yellomarin

//1
//5 jbzchixyxcxfqjnjmiasntcprcskiivbfzxjaohtaejmusmefigtzettecygkzzlmlbflkiemsovrqkarzsgrgilxteokjemfhpqabufpliyhizsenlynufjsqnosvdkkciijvxykgjevlxcggfsnbipvephmheeqajkrbemzxlfmetnyrsmtdisrccglfvegxvepghmczavkrqxfoijhqnsihckuhvhdyzjiopzcikryykimvfzesgahkxjgxdfcyjpmvmouegrvjgjoxudpntcpyzsabsaetiaeyvdyttnfunkkpkcnxnpmhtxvnozxplhsqixkrgnpaxtvaojuitdiiixtfschpjfrgoaffolkpfbpsbdacufyqloidkgvuntnuiumvcckrvdepcscenvlaffmldeohhxibznfshehgqevbdljvurefbvvlzlpkvusdibytomjrkcitubfetvqcnlkoghxqpzcfizgzccjponmepcvtvjccfzxnxnhipsnqkqgemmqdeacnphoivlbdqzyafjckepdqokrmkhjzzjbijugnqkaaezbguyhjgtqqpgfbizhjaejllodtusjgbolkrmrynmkdvtzkfaovieccrhlzlusyxchyryzirvnrknrzvfmnilvcxzjauyajhaavprjybsgeijspvjaxugeralbjnccyhimafuiexgftzxjzzizprdmopvximdvjmoshirkvcubjyhdvutzzcmardemkzlpfayclmpatmreoycgeccgcdnbvovvvvhiffodetqthtbenpnhimbyevlqjhqtgjsioazjtelvbpyihclhqayxtgrkkbpmdqggepczzryyrmruulsjgzjzacrcdkmxmobhrcdiatullfczxlvavyrqzgndbozblozqoohtbqlqelbaipcqyykgpdpxkgdyqvhyhuogbtjqkilrmdfhljrugvbljdsqufaclhajkmhydykgxvfvayjlcdeqfhohuuqzofvuamxjgkhjupqkyzxnmxzatpumutgrhippzsjlgjbpfyofrjunpxkfvpjxkqgjsdxaotdqqtjhbmmhcqzskahjcxblcyxdhpfqbbmxuxgydnvvvhmytoshbjghpaqmexflmincpnhutgulmbckqzhilydqnietdmketagxygynpbktvetkaqoihhmlqdiytgdligdoycnnpvpiehngoxqdxqzcjobgtzbmlomptsipinsbapeeqipjievsjazayebtnbuvizgxhimngceavogbzmrmqdkqxatxnbnxnciouljshvgdapdmvvvhejvzphafkvospycdqebvrogfsgdczfrhyqvnkdvxxtsejlvcbpeeajvucllhhpfmqfctdfanbayhglfzrdptgxbnvpqoqmqlidifgmdeklvayxitjiyndhmjujsoqzmxvyjgztrfelxjqlchmgkzmkafttvxgnudkkcqnot dxehkupglkaajohzbekahlbgcoxkopopcdevojqsaabbozmeykpgcqjsfzhxgdlscldvvsxcrgaxhjpidpcngrarjgbcgdipjpvjnhepaifsvboljiprmapgrffuasaanhmqdficmdtollxdtntzkfqetzrnjvdmayellsvaytstgmzbednrfpkbstaudtboxsphxorsfblihdgyejbomsgjuhlsvfafsdcqzyfqfaafiujdgqlpqgojubrqfkyribrvysvxbcnvxfoysrbveeeohsyzluyjumxrmikeezlxchentzkfvqhsytshyqdzczcphohymlyxlphlzrvocrqigexlbnndtshqmnbekorqobuhcgvziolqzqqkkbialvhgsgyiclmjkxunpjnovkvhzembctticfuhuavsjzfnubdyrfvfbzcxedayytivhuyfhvpcrzsxaxsosmqzhuapfgmdtlaigesrbsxicjtdguchdeucnhtuotpazemxszosafsuymxxzlzohrksdzudtjkbmekakygvmeneqfxquiolsocnhgalcexzjelmisniocdxbsbkejmvbbzayxdrszeruyzfsgoagddalceivzuqhiyfqirqtvkjkkhozlpbthvcqkzglqcgkylxsvoigvuocpkzcydirevjmzgrlixbeyqcvkuxovayjzslhxfolykklurmccrcyacxlzplxinbcotfbronigtfjthoqiuasiylpjmrkklmfeoeiqkguhenzcanqdjffztqigzpbhgisunqdsmbokpnzgextfkbtuzpxjdqtvecsglnprmbzhapkixpgkmpcjybucvgvdaxknhkoldrsrmvtxyamarhgurjridzxhkcxskloleikgcvizaavnccxujcbqmivpzypktxtkropbkfbvqnxdeygjoxhvshvgojpfozmfufzbebsfrqpjbcnostyauchzcryqyofiupgesbnnytnfxgodikltnqicxzutkflxrmloqktglsixsyrrduarsaqjmjlvlgskysycdpibmrrdchtuagvpdhqqasslmunyaizszauduvbhhvgqogxfeedfyycjpabgpxxqgplxyatcghpridotcsdfpuvmqbrqxiadusvskmoflmgyzqivkmizsrjtbkbrstcixtrvlzasnzrjvakuugzcanyffrnvbqmijvsrszmeujouvblcikrknpnmooplxmynulrsyfhbeepgjedexkqusafsmnllirimmtyknkdvacnkjgbetvlomykzeatfrmregvfpicskkaxdbcmcjkadkqqshppmtmaujsthiceyyoicbfrhodvlolelgelsrmsykqlglotlyfxbjderyzsibxlsvpbzfhxbugzvhgavbseevohbmracxoaukpjopeqvjmmcjebcmdqsnctcdonoshumbjmbosadtqpxjaedjhkxulzfyzftxu
