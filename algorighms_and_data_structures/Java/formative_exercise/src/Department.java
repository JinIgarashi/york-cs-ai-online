import java.util.ArrayList;

/**
 * Department class with required attributes and methods
 */
public class Department {
	
	/**
	 * Department name
	 */
	private String name;
	
	/**
	 * Yearly sales data. It should consist of 12 elements starting with January up to December,
	 * each element represent a month's sales amount in thousand.
	 */
	private ArrayList<Integer> yearlyData;
	
	/**
	 * Tax rate, e.g., 0.17 (17%)
	 */
	private double taxRate;
	
	/**
	 * Department class constructor
	 * @param nameIn department name
	 * @param arrayList<Integer> it should have 12 elements, each element represent month's sales amount in GBP 1000.
	 */
	public Department(String nameIn, ArrayList<Integer> yearlyDataIn) {
		name = nameIn;
		yearlyData = yearlyDataIn;
		taxRate = 0.17;
	}
	
	public String getName() {
		return name;
	}
	
	/**
	 * get start month and end month index number for selected quarter
	 * @param quarter quarter number. shold be between 1 and 4
	 * @return int[]. first element contains start month index, second element is end month index. index starts with zero.
	 */
	private int[] getStartAndEndMonth(int quarter) {
		int start = 1;
		int end = 3;
	  if (quarter == 2) {
		  start = 4;
		  end = 6;
	  } else if (quarter == 3) {
		  start = 7;
		  end = 9;
	  } else if (quarter == 4) {
		  start = 10;
		end = 12;
	  }
	  
	  // minus 1 from start and end index.
	  start = start - 1;
	  end = end - 1;
	  
	  int[] startEnd = {start, end};
	  return startEnd;
	}
	
	/**
	 * Get sales data for targeted quarter
	 * @param quarter quarter number. should be between 1 and 4
	 * @return ArrayList<Integer>
	 */
	public ArrayList<Integer> getQuarterlyData(int quarter) {
		int[] startEndMonth = getStartAndEndMonth(quarter);
		int start = startEndMonth[0];
		int end = startEndMonth[1];
		
		ArrayList<Integer> output = new ArrayList<Integer>();
		for (int m=start; m <= end; m++) {
			output.add(yearlyData.get(m));
		}
		
		return output;
	}
	
	/**
	 * Algorithm 1: Calculate quarterly total sales
	 * @param quarter quarter number. should be between 1 and 4
	 * @return total sales
	 */
	public int calcTotalSales(int quarter) {
		int[] startEndMonth = getStartAndEndMonth(quarter);
		int start = startEndMonth[0];
		int end = startEndMonth[1];
		
		int sum = 0;
		for (int m=start; m <= end; m++) {
			int value = yearlyData.get(m);
			if (value == -1) {
				continue;
			}
			sum = sum + value;
		}
		
		return sum;
	}
	
	/**
	 * Algorithm 2: get the most effective month and sales amount for targeted quarter
	 * @param quarter quarter number. should be between 1 and 4
	 * @return array of int. First element is the most effective month, second element is the amount of sales.
	 */
	public int[] getMostEffectiveMonth(int quarter) {
		int[] startEndMonth = getStartAndEndMonth(quarter);
		int start = startEndMonth[0];
		int end = startEndMonth[1];
		
		int max = yearlyData.get(start);
		int effectiveMonth = start;
		for (int m=start + 1; m <= end; m++) {
			int value = yearlyData.get(m);
			if (value == -1) {
				continue;
			}
			if (max < value) {
				max = value;
				effectiveMonth = m;
			}
		}
		int[] result = {effectiveMonth, max};
		return result;
	}
	
	/**
	 * Algorithm 3: Estimate sales target for next quarter 
	 * by using the last reported quarter sales data with given percentage to increase.
	 * @param quarter quarter number. should be between 1 and 4
	 * @param increase % to increase sales e.g., 12(%)
	 * @return return sales target amount
	 */
	public int estimateSalesTarget(int quarter, int increase) {
		int[] startEndMonth = getStartAndEndMonth(quarter);
		int start = startEndMonth[0];
		int end = startEndMonth[1];
		
		int sum = 0;
		for (int m = start; m <= end; m++) {
			int value = yearlyData.get(m);
			if (value == -1) {
				continue;
			}
			sum += value * 1000;
		}
		int monthsInQuarter = 3;
		int avg = sum / monthsInQuarter;
		int newSales = avg * (increase / 100 + 1);
		int newSalesQuarterly = newSales * monthsInQuarter;
		
		return newSalesQuarterly;
		
	}
	
	/**
	 * Calculate tax
	 * @param amount amount in thousand
	 * @return tax amount calculated
	 */
	public int calcTax(int amount) {
		int tax = (int) (amount * 1000 * taxRate);
		return tax;
	}
	
	
}
