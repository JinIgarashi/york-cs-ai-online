
public class EmployeeTester
{
	public static void main(String[] args)
	{
		FullTimeEmployee fte = new FullTimeEmployee("A123", "Ms Full-Time", 25000);
		PartTimeEmployee pte = new PartTimeEmployee("B456", "Mr Part-Time",30);
		testMethod(fte); // call testMethod with a full-time employee object
		testMethod(pte); // call testMethod with a part-time employee object
		
		Employee fe = new FullTimeEmployee("C789", "Mr Jin", 30000);
		Employee pe = new PartTimeEmployee("C789", "Mr Igarashi", 50);
		testMethod(fe);
		testMethod(pe);
	}
	
	static void testMethod(Employee employeeIn) // the method expects to receive an Employee object
	{
	       System.out.println(employeeIn.getName());
	}
} 
