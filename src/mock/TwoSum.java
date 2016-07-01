package mock;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class TwoSum {
	public static void main(String[] args) {
	    ArrayList<String> strings = new ArrayList<String>();
	    strings.add("Hello, World!");
	    strings.add("Welcome to CoderPad.");
	    strings.add("This pad is running Java 8.");

	    for (String string : strings) {
	      System.out.println(string);
	    }
	    
	    int[] A = {2,5,5, -5,8,20};
	    int t = 0;
	    int[] rlt = twoSum(A, t);
	    if (rlt == null) {
	      System.out.println("doesn't exist");
	    } else {
	      System.out.println(rlt[0] + " " + rlt[1]);
	    }
	  }
	  
	  public static int[] twoSum(int[] A, int t) {
	    Set<Integer> set = new HashSet<>();
	    for (int i = 0; i < A.length; i++) {
	      set.add(t - A[i]);
	    }
	    
	    printset(set);
	    
	    boolean special = false;
	    boolean seen = false;
	    if (t % 2 == 0) {
	      special = true;
	    }
	    
	    int[] rlt = new int[2];
	    for (int i = 0; i < A.length; i++) {
	      System.out.println(A[i]);
	      if (set.contains(A[i])) {
	        if (special) {
	          if (A[i] == t / 2)  {
	            if (seen) {
	              rlt[0] = t/2;
	              rlt[1] = t/2;
	              return rlt;
	            } else {
	              seen = true;
	            }
	          }
	        } else {
	          rlt[0] = A[i];
	          rlt[1] = t - A[i];
	          return rlt;
	        }
	      }
	    }
	    
	    return null;
	     
	  }  
	  
	  private static void printset(Set<Integer> set) {
	    for (int i : set) {
	      System.out.print(i + " ");
	    }
	    System.out.println();
	  }
}


