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
		System.out.println("-----" + formatQuarterName(quarter) + "Quarter report-----");
		
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
