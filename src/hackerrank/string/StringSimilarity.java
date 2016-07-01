package hackerrank.string;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Test;

public class StringSimilarity {
	
	@Test
	public void test() {
//		String a = "aa";
		String a = "ababaa";
		System.out.println(stringSimilarity(a));
	}

	 public static long stringSimilarity(String a) {
		 Suffix[] suffixArray = new Suffix[a.length()];
		 createSortedSuffixArray(a, suffixArray);
		 
//		 print(suffixArray);
		 
		 int p = 0;
		 int q = a.length() - 1;
		 long sum = 0;
		 for (int i = 0; i < a.length(); i++) {
			 int newP = findFirst(suffixArray, a, p, q, i);
			 int newQ = findLast(suffixArray, a, p, q, i);
			 if (newP > -1) {
				 sum += Long.valueOf(newQ - newP + 1);
			 }
			 p = newP;
			 q = newQ;
		 }
		 return sum;
	 }
	 
	 private static void print(Suffix[] a) {
		 for (int i = 0; i < a.length; i++) {
			 System.out.println(a[i].originalIndex);
		 }
	 }
	 
	 private static void createSortedSuffixArray(String a, Suffix[] suffixArray) {
		 int[] rank = new int[a.length()];
		 for (int i = 0; i < a.length(); i++) {
			 suffixArray[i] = new Suffix(i);
			 rank[i] = a.charAt(i) - 'a';
		 }
		 
		 for (int cnt = 1; cnt < a.length(); cnt = cnt * 2) {
			 for (int i = 0; i < suffixArray.length; i++) {
				 suffixArray[i].firstHalfRank = rank[suffixArray[i].originalIndex];
				 if (suffixArray[i].originalIndex + cnt < a.length()) {
					 suffixArray[i].secondHalfRank = rank[suffixArray[i].originalIndex + cnt];
				 } else {
					 suffixArray[i].secondHalfRank = -1;
				 }
			 }
			 Arrays.sort(suffixArray, new SuffixComparator());
			 
			 // update rank
			 rank[suffixArray[0].originalIndex] = 0;
			 int currentRank = 0;
			 for (int i = 1; i < suffixArray.length; i++) {
				 if (suffixArray[i].firstHalfRank != suffixArray[i-1].firstHalfRank || 
						 suffixArray[i].secondHalfRank != suffixArray[i-1].secondHalfRank) {
					 currentRank++;
				 }
				 rank[suffixArray[i].originalIndex] = currentRank;
			 }
		 }
	 }
	 
	 
	 private static class SuffixComparator implements Comparator<Suffix> {

		@Override
		public int compare(Suffix o1, Suffix o2) {
			if (o1.firstHalfRank != o2.firstHalfRank) {
				return o1.firstHalfRank - o2.firstHalfRank;
			} else {
				return o1.secondHalfRank - o2.secondHalfRank;
			}			
		}
		 
	 }
	 
	 private static int findFirst(Suffix[] suffixArray, String a, int low, int hi, int i) {
		 while (low < hi) {
			 int mid = (low + hi) / 2;
			 if (suffixArray[mid].originalIndex + i > a.length() - 1 ||
					 a.charAt(suffixArray[mid].originalIndex + i) < a.charAt(i)) {
				 low = mid + 1;
			 } else {
				 hi = mid;
			 }
		 }
		 if (suffixArray[low].originalIndex + i < a.length() && 
				 a.charAt(suffixArray[low].originalIndex + i) == a.charAt(i)) {
			 return low;
		 } else {
			 return -1;
		 }
	 }
	 
	 private static int findLast(Suffix[] suffixArray, String a, int low, int hi, int i) {
		 while (low < hi) {
			 int mid = (low + hi + 1) / 2;
			 if (suffixArray[mid].originalIndex + i < a.length() && 
					 a.charAt(suffixArray[mid].originalIndex + i) > a.charAt(i)) {
				 hi = mid - 1;
			 } else {
				 low = mid;
			 }
		 }
		 
		 if (suffixArray[low].originalIndex + i < a.length() && 
				 a.charAt(suffixArray[low].originalIndex + i) == a.charAt(i)) {
			 return low;
		 } else {
			 return -1;
		 }
	 }
	 	 
	 private static class Suffix {
		 int originalIndex;
		 int firstHalfRank;
		 int secondHalfRank;
		 
		 Suffix(int i) {
			 originalIndex = i;
		 }
	 }
}
