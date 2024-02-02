import java.util.ArrayList;
import java.util.Arrays;

public class Quarterly_Report {

	/**
	 *  preparing financing data
	 */
	public static ArrayList<Department> initData() {
		ArrayList<Department> departments = new ArrayList<Department>();
		departments.add(new Department(
				"Electrical", 
				new ArrayList<Integer>(Arrays.asList(-1, -1, -1, 67, 63, 78, 78, 104, 103, -1, -1, -1))
		));
		
		departments.add(new Department(
				"Kitchen", 
				new ArrayList<Integer>(Arrays.asList(-1, -1, -1, 65, 67, 56, 45, 56, 72, -1, -1, -1))
		));
		
		departments.add(new Department(
				"Bathroom", 
				new ArrayList<Integer>(Arrays.asList(-1, -1, -1, 63, 63, 65, 71, 73, 69, -1, -1, -1))
		));
		departments.add(new Department(
				"Soft Furnishing", 
				new ArrayList<Integer>(Arrays.asList(-1, -1, -1, 18, 24, 22, 19, 17, 16, -1, -1, -1))
		));

		departments.add(new Department(
				"Accessories", 
				new ArrayList<Integer>(Arrays.asList(-1, -1, -1, 16, 23, 21, 19, 20, 19, -1, -1, -1))
		));
		return departments;
	}
	
	/**
	 * Format quarter name
	 * @param quarter quarter number. should be between 1 and 4
	 * @return returning like "1st", "2nd"...
	 */
	public static String formatQuarterName (int quarter) {
		if (quarter == 1) {
			return "1st";
		} else if (quarter == 2) {
			return "2nd";
		} else if (quarter == 3) {
			return "3rd";
		} else {
			return "4th";
		}
	}
	
	/**
	 * Format amount with currency unit and comma separated
	 * @param amount amount. If the amount is rounded in thousand, isRounded argument should be set to true.
	 * @param isRounded True: in thousand, False: not rounded in thousand
	 * @return returning formatted amount like "£100,000"
	 */
	public static String formatAmount(int amount, boolean isRounded) {
		if (isRounded == true) {
			amount = amount * 1000;
		}
		return "£" + String.format("%,d", amount);
	}
	
	/**
	 * Format month index to month name
	 * @param month month index. It should be between 0 and 11.
	 * @return month name
	 */
	public static String formatMonth(int month) {
		String[] months = {
			"January",
			"February", 
			"March", 
			"April", 
			"May", 
			"June", 
			"July", 
			"August",
			"September",
			"October",
			"November",
			"December"
		};
		
		return months[month];
	}
	
	/**
	 * Generate quarterly report for each department
	 * @param quarter quarter quarter number. should be between 1 and 4
	 * @param departments Array of Department object
	 */
	public static void generateQuarterlyReport(int quarter, ArrayList<Department> departments) {
		System.out.println("-----" + formatQuarterName(quarter) + " Quarter report-----");
		
		// report quarterly total sales
		for (Department dept: departments) {
			int totalSales = dept.calcTotalSales(quarter);
			int tax = dept.calcTax(totalSales);
			System.out.print("Total sales: " + dept.getName() + ", " + formatAmount(totalSales, true));
			System.out.println(", Tax: " + formatAmount(tax, false));
		}
		
		// best department
		Department mostEffectiveDept = null;
		int[] bestMonthSales = null;
		for (Department dept: departments) {
			int[] effectiveMonthData = dept.getMostEffectiveMonth(quarter);
			if (mostEffectiveDept == null) {
				mostEffectiveDept = dept;
				bestMonthSales = effectiveMonthData;
			} else if (bestMonthSales.length > 0) {
				if (bestMonthSales[1] < effectiveMonthData[1]) {
					bestMonthSales = effectiveMonthData;
				}
			}
		}
		System.out.print("The best department: " + mostEffectiveDept.getName());			
		ArrayList<Integer> bestQuarterData = mostEffectiveDept.getQuarterlyData(quarter);
		for (int i = 0; i< bestQuarterData.size(); i++) {
			Integer data = bestQuarterData.get(i);
			if (i == bestQuarterData.size() - 1) {
				System.out.println(", " + formatAmount(data, true));
			} else {
				System.out.print(", " + formatAmount(data, true));
			}
			
		}
		
		// worst department
		Department worstEffectiveDept = null;
		int[] worstMonthSales = null;
		for (Department dept: departments) {
			int[] worstMonthData = dept.getMostEffectiveMonth(quarter);
			if (worstEffectiveDept == null) {
				worstEffectiveDept = dept;
				worstMonthSales = worstMonthData;
			} else if (worstMonthSales.length > 0) {
				if (worstMonthSales[1] > worstMonthData[1]) {
					worstMonthSales = worstMonthData;
					worstEffectiveDept = dept;
				}
			}
		}
		System.out.print("The worst department: " + worstEffectiveDept.getName());			
		ArrayList<Integer> worstQuarterData = worstEffectiveDept.getQuarterlyData(quarter);
		for (int i = 0; i< worstQuarterData.size(); i++) {
			Integer data = worstQuarterData.get(i);
			if (i == worstQuarterData.size() - 1) {
				System.out.println(", " + formatAmount(data, true));
			} else {
				System.out.print(", " + formatAmount(data, true));
			}
		}
		
		// most effective month for each department, each quarter
		System.out.println("The most effective month and sales for each department: ");
		for (Department dept: departments) {
			int[] effectiveMonthData = dept.getMostEffectiveMonth(quarter);
			int month = effectiveMonthData[0];
			int amount = effectiveMonthData[1];
			String message = " - " + dept.getName() + ", " + formatMonth(month) + ", " + formatAmount(amount, true) + "; ";
			System.out.println(message);
		}
	}
	
	/**
	 * Generate sales target report of next quarter for each department based on the last reported quarter data.
	 * @param quarter quarter quarter number. should be between 1 and 4
	 * @param departments Array of Department object
	 */
	public static void generateSalesTarget(int quarter, ArrayList<Department> departments) {
		int nextQuarter = quarter + 1;
		if (quarter == 4) {
			nextQuarter = 1;
		}
		System.out.println("The sales target for next " + formatQuarterName(nextQuarter) + " Quarter: ");
		for (Department dept: departments) {
			int targetSales = dept.estimateSalesTarget(quarter, 12);
			String message = " - " + dept.getName() + ", " + formatAmount(targetSales, false) + "; ";
			System.out.println(message);
		}
	}
	
	
	public static void main(String[] args) {
		ArrayList<Department> departments = initData();
		
		generateQuarterlyReport(2, departments);
		generateQuarterlyReport(3, departments);
		
		generateSalesTarget(3, departments);
		
	}

}

/**
 * Department class with required attributes and methods
 */
class Department {
	
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

