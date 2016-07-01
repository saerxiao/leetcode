package strings.arrays;

public class Candy {

	private int findMinCandyNeeded(int[] scores) {
		int currentCandy = 1;
		int sum = 1;
		boolean uptrend = true;
		int flatStart = 0;
		int downtrendCnt = 0;
		
		for (int i=0; i<scores.length-1; i++){
			if (scores[i+1] == scores[i]) {
				sum += currentCandy;
			}
			if (scores[i+1] > scores[i]) {
				uptrend = true;
				currentCandy++;
				sum += currentCandy;
				flatStart = i+1;
				downtrendCnt = 0;
			} else {
				// turning point of downtrend
				if (uptrend) {
					uptrend = false;
					downtrendCnt = i-flatStart+1;
				}
				downtrendCnt++;
				if (currentCandy == 1) {
					sum += downtrendCnt;
				} else {
					currentCandy--;
				}				
				sum += currentCandy;
				flatStart = i+1;
			}
		}
		return sum;
	}
}
