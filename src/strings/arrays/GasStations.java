package strings.arrays;

import org.junit.Test;

public class GasStations {
	
	public int doCircle(int[] gas, int[] cost) {
		int N = gas.length;
		int start = 0;
		while (start < N) {
			int gasAcc = 0;
			boolean success = true;
			for (int i = 0; i < N; i++) {
				int from = (start + i) % N;
				gasAcc += gas[from];
				if (gasAcc >= cost[from]) {
					gasAcc -= cost[from];
				} else {
					success = false;
					if (((from + 1) % N) <= start) {
						return -1;
					} else {
						start = (from + 1) % N;
						break;
					}
				}
			}
			if (success) {
				return start;
			}
		}
		return -1;
	}
	
	public int canComplete(int[] gas, int[] cost) {
		int total = 0;
		int gasAcc = 0;
		int start = 0;
		for (int i = 0; i < gas.length; i++) {
			total = total + gas[i] - cost[i];
			gasAcc += gas[i];
			if (gasAcc >= cost[i]) {
				gasAcc -= cost[i];
			} else {
				start = (i + 1) % gas.length;
			}
		}
		if (total >= 0) {
			return start;
		} else {
			return -1;
		}
	}
	
	public int canCompleteCircuit(int[] gas, int[] cost) {
		int sumRemaining = 0; // track current remaining
		int total = 0; // track total remaining
		int start = 0; 
	 
		for (int i = 0; i < gas.length; i++) {
			int remaining = gas[i] - cost[i];
	 
			//if sum remaining of (i-1) >= 0, continue 
			if (sumRemaining >= 0) { 
				sumRemaining += remaining;
			//otherwise, reset start index to be current
			} else {
				sumRemaining = remaining;
				start = i;
			}
			total += remaining;
		}
	 
		if (total >= 0){
			return start;
		}else{
			return -1;
		}
	}

	@Test
	public void test() {
//		int[] gas =  {1,2,3,4,5};
//		int[] cost = {1,3,2,4,5};
		int[] gas =  {4,5,1,2,3};
		int[] cost = {4,5,1,3,2};
		System.out.println(doCircle(gas, cost));
		System.out.println(canCompleteCircuit(gas, cost));
		System.out.println(canComplete(gas, cost));
	}
}
