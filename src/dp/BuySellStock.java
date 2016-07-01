package dp;

public class BuySellStock {

	private int oneTransaction(int[] stock) {
		int minSoFar = stock[0];
		int maxProfit = 0;
		
		for (int i=0; i<stock.length; i++) {
			maxProfit = Math.max(maxProfit, Math.max(stock[i] - minSoFar, 0));
			minSoFar = Math.min(minSoFar, stock[i]);
		}
		
		return maxProfit;
	}
	
	private int multipleTransactions(int[] stock) {
		int startOfRisingTrend = 0;
		int profitSum = 0;
		int i = 1;
		while (i < stock.length) {
			if (stock[i] < stock[i-1]) {
				profitSum += stock[i-1] - stock[startOfRisingTrend];
				startOfRisingTrend = i;
			}
			i++;
		}
		return profitSum;
	}
	
	private int atMostTwoTransations(int[] stock) {		
		
		int[] maxFrom0Toi = oneTransactionFrom0Toi(stock);
		int[] maxFromiToN = oneTransactionFromiToN(stock);
		int maxProfit = maxFromiToN[0];
		
		for (int i=0; i<stock.length-1; i++) {
			maxProfit = Math.max(maxProfit, 
					maxFrom0Toi[i] + maxFromiToN[i+1]);
		}
		return maxProfit;
	}
	
	private int[] oneTransactionFrom0Toi(int[] stock) {
		int[] max = new int[stock.length];
		int maxProfitSoFar = 0;
		int minSoFar = stock[0];
		
		for (int i=1; i<stock.length; i++) {
			max[i] = Math.max(stock[i] - minSoFar, maxProfitSoFar);
			minSoFar = Math.min(minSoFar, stock[i]);
		}		
		return max;
	}
	
	private int[] oneTransactionFromiToN(int[] stock) {
		int[] max = new int[stock.length];
		int maxFromiToN = stock[stock.length];
		int maxProfitSoFar = 0;
		
		for (int i=stock.length-1; i>0; i--) {
			max[i] = Math.max(maxFromiToN - stock[i], maxProfitSoFar);
			maxFromiToN = Math.max(maxFromiToN, stock[i]);
		}
		return max;
	}
	
	private int atMostMTransfactions(int[] stock, int M) {
		int[][] oneTransactionMatrix = computeMaxProfitMatrix1Transaction(stock);
		return computeAtMostMTransactions(stock, 0, stock.length, M, oneTransactionMatrix);
	}
	
	private int computeAtMostMTransactions(int[] stock, int startInclusive, int endExclusive, int M, int[][] maxProfitMatrix) {
		if (startInclusive ==  endExclusive) return 0;
		
		if (M == 1) {
			return maxProfitMatrix[startInclusive][endExclusive-1];
		}
		
		int maxProfit = 0;
		
		for (int i=startInclusive; i<endExclusive; i++) {
			maxProfit = Math.max(maxProfit, 
					maxProfitMatrix[startInclusive][i] 
					+ computeAtMostMTransactions(stock, i+1, endExclusive, M-1, maxProfitMatrix));
		}
		return maxProfit;
	}
	
	private int[][] computeMaxProfitMatrix1Transaction(int[] stock) {
		int[][] matrix = new int[stock.length][stock.length];
		
		for (int i=0; i<stock.length; i++) {
			int minSoFar = stock[i];
			int max = 0;
			for (int j=i; j<stock.length; j++) {
				matrix[i][j] = Math.max(max, stock[i] - minSoFar);
				minSoFar = Math.min(minSoFar, stock[i]);
			}
		}
		return matrix;
	}
}
