package hackerrank.womeno.codesprint;

import java.util.BitSet;
import java.util.Scanner;

public class EvenTraining {

	public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
        	int n = in.nextInt();
            BitSet r = new BitSet();
            for(int i = 0; i < n; i++){
                int a = in.nextInt();
                if (a == 1) {
                	r.set(i);
                }
            }
            int days = r.cardinality();
            if (days % 2 == 0) {
            	System.out.println("YES");
            } else {
            	System.out.println("NO");
            }
            System.out.println(days);
        }        
    }
}
