import java.util.Scanner;

public class MainApp {
	
	public static void main(String[] args) {
		MenuOperations operations = null;
		try {
			operations = new MenuOperations();
		} catch (ApplicationError e) {
			System.out.println(e.getMessage());
		}
				
		Scanner sc = new Scanner(System.in);
		int choice = -1;
		while (choice != 99) {
			System.out.println("Menu:");
	        System.out.println("1. Reserve a guest room");
	        System.out.println("2. Reserve a group");
	        System.out.println("3. Cancel a guest room");
	        System.out.println("4. Search reservations by guest's last name");
	        System.out.println("5. Sort all guests' reservations alphabetically by their last name");
	        System.out.println("6. Report total income for each room class");
	        System.out.println("7. Report all rooms availability");
	        System.out.println("11. Set test data");
	        System.out.println("99. Exit");
	        System.out.print("Please enter a number: ");
	        
	        choice = sc.nextInt();
	        
	        try {
		        switch (choice) {
			        case 1:
			        	operations.reserveGuest(sc);
		                break;
			        case 2:
			        	operations.reserveGroup(sc);
		                break;
			        case 3:
			        	operations.cancelReservation(sc);
		                break;
			        case 4:
			        	operations.searchReservations(sc);
		                break;
			        case 5:
			        	operations.sortReservations();
		                break;
		            case 6:
		            	operations.reportTotalIncome();
		                break;
			        case 7:
			        	operations.reportRoomAvailability(sc);
			        	break;
			        case 11:
			        	operations.setTestData();
			        	break;
			        case 99:
	                    System.out.println("Exit the app. Good bye.");
	                    break;
			        default:
	                    System.out.println("Invalid menu number. Please enter it again.");
		        }
	        } catch (ApplicationError e) {
	        	// if any application error occurs, show error message and go back to menu.
				System.out.println(e.getMessage());
			}
		}
		sc.close();
	}
	
	
}
