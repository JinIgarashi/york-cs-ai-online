import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Scanner;

public class RoomBookingApp {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Rooms rooms = null;
		Hashtable<String, RoomRate> roomRates = null;
		Reservations reservations = null;
		
		try {
			// Initialize Room and Room class data
			rooms = initializeRoomData();
			roomRates = initializeRoomRateData();
			reservations = new Reservations(rooms, roomRates);
		} catch (ApplicationError e) {
			System.out.println(e.getMessage());
		}
		
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
			        	reservations = reserveGuest(sc, reservations, rooms);
		                break;
			        case 2:
			        	reservations = reserveGroup(sc, reservations, rooms);
		                break;
			        case 3:
			        	reservations = cancelReservation(sc, reservations);
		                break;
			        case 4:
			        	reservations = searchReservations(sc, reservations);
		                break;
			        case 5:
			        	sortReservations(reservations);
		                break;
		            case 6:
			        	reservations.reportTotalIncome();
		                break;
			        case 7:
			        	reportRoomAvailability(sc, reservations, rooms);
			        	break;
			        case 11:
			        	reservations = setTestData(reservations);
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
	
	/**
	 * (Task 1) Store room details
	 * @throws ApplicationError
	 */
	private static Rooms initializeRoomData() throws ApplicationError {
		Rooms rooms = new Rooms();
		
		Room standard = new Room(
				"Standard", 
				"Comfortable and budget-friendly accommodation", 
				new ArrayList<Integer>(Arrays.asList(1, 250)), 
				new ArrayList<String>(Arrays.asList("Twin", "Double")));
		rooms.add(standard);
		
		Room deluxe = new Room(
				"Deluxe", 
				"Enhanced comfort and additional space", 
				new ArrayList<Integer>(Arrays.asList(251, 500)), 
				new ArrayList<String>(Arrays.asList("Queen-size")));
		rooms.add(deluxe);
		
		Room superior = new Room(
				"Superior", 
				"Luxury and premium services", 
				new ArrayList<Integer>(Arrays.asList(501, 530)), 
				new ArrayList<String>(Arrays.asList("Queen-size", "King-size")));
		rooms.add(superior);
		
		System.out.println("The following room data was initialized.");
		rooms.print();
		
		return rooms;
	}
	
	/**
	 * (Task 2) Store room rates details
	 */
	private static Hashtable<String, RoomRate> initializeRoomRateData() {
		Hashtable<String, RoomRate> roomRates = new Hashtable<String, RoomRate>();
		
		RoomRate standard = new RoomRate("Standard", 1000);
		roomRates.put(standard.getRoomClass(), standard);
		
		RoomRate deluxe = new RoomRate("Deluxe", 1200);
		roomRates.put(deluxe.getRoomClass(), deluxe);
		
		RoomRate superior = new RoomRate("Superior", 1800);
		roomRates.put(superior.getRoomClass(), superior);
		
		System.out.println("The following room rates were initialized.");
		Collection<RoomRate> values = roomRates.values();
        Iterator<RoomRate> iterator = values.iterator();
        RoomRate.printHeader();
        while (iterator.hasNext()) {
        	RoomRate value = iterator.next();
        	value.print();
        }
        
        return roomRates;
	}
	
	/**
	 * (Task 4 and Task 6) Ask questions to collect information for reservation
	 * @param sc Scanner object
	 * @param reservations Reservations object
	 * @param rooms Rooms object
	 * @return Reservation object (in this stage, it is not booked yet. You have to call book method of Reservations class)
	 * @throws ApplicationError
	 */
	private static Reservation askReservationQuestions(Scanner sc, Reservations reservations, Rooms rooms) throws ApplicationError {
		System.out.print("Enter the choice of class (Standard, Deluxe, Superior): ");
		String roomChoice = sc.next();

		Room room = rooms.get(roomChoice);
		if (room == null) {
			throw new ApplicationError("Invalid room type. Please select it from (Standard, Deluxe, Superior)");
		}
		
		ArrayList<String> bedTypes = room.getBedTypes();
		System.out.print(String.format("Enter the choice of bed type (%s): ", String.join(", ", bedTypes)));
		String bedChoice = sc.next();
		if (bedTypes.indexOf(bedChoice) == -1) {
			throw new ApplicationError("Invalid bed type. please select from available bed types");
		}
		
		System.out.print("Enter the guest's first name: ");
		String firstName = sc.next();
		
		System.out.print("Enter the guest's last name: ");
		String lastName = sc.next();
		
		System.out.print("Enter the length of stay (in nights): ");
		Integer lengthOfStay = sc.nextInt();
		if (lengthOfStay < 1 ) {
			throw new ApplicationError("Please enter length of stay more than a night");
		}
		
		Reservation reservation = new Reservation(firstName, lastName, lengthOfStay, roomChoice, bedChoice);
		
		return reservation;
	}
	
	/**
	 * (Task 4) reserve a guest' room
	 */
	private static Reservations reserveGuest(Scanner sc, Reservations reservations, Rooms rooms) throws ApplicationError {
		
		Reservation reservation = askReservationQuestions(sc, reservations, rooms);
		reservations.book(reservation);
		
		System.out.println(String.format("The room of %s was reserved as follows:", reservation.getRoomNumber()));
		Reservation.printHeader();
		reservation.print();
		
		System.out.println("Do you wish to reserve another room? Select Yes (Y) or No (N): ");
		String answer = sc.next();
		if (answer.toLowerCase().equals("y")) {
			return reserveGuest(sc, reservations, rooms);
		}
		return reservations;
	}
	
	/**
	 * (Task 4) Cancel a room reservation
	 */
	private static Reservations cancelReservation(Scanner sc, Reservations reservations) throws ApplicationError {
		System.out.println("Enter the choice of class (Standard, Deluxe, Superior):");
		String roomChoice = sc.next();
		
		System.out.print("Enter the room number: ");
		Integer roomNumber = sc.nextInt();
		
		System.out.print("Enter the guest’s last name: ");
		String lastName = sc.next();
		
		System.out.print("Enter the length of stay (nights): ");
		Integer lengthOfStay = sc.nextInt();
		
		Reservation deleted = reservations.cancel(roomChoice, roomNumber, lastName, lengthOfStay);
		if (deleted == null) {
			throw new ApplicationError("There is no reservation matched to the inputs");
		}
		
		System.out.println("The following reservation was cancelled successfully.");
		Reservation.printHeader();
		deleted.print();
		return reservations;
	}
	
	/**
	 * (Task 5) search reservations by a guest's last name
	 */
	private static Reservations searchReservations(Scanner sc, Reservations reservations) throws ApplicationError {
		System.out.print("Enter the guest’s last name: ");
		String lastName = sc.next();
	
		ArrayList<Reservation> result = reservations.search(lastName);
		if (result.size() == 0) {
			throw new ApplicationError("There is no reservation found for the guest of " + lastName);
		}
		
		System.out.println("The following reservations were found.");
		Reservation.printHeader();
		for (Reservation r : result) {
			r.print();
		}
		return reservations;
	}
	
	/**
	 * (Task 6) Reserve a group
	 */
	private static Reservations reserveGroup(Scanner sc, Reservations reservations, Rooms rooms) throws ApplicationError {
		
		int numberOfRooms = 0;
		while (numberOfRooms == 0) {
			System.out.print("Enter the number of rooms required together: ");
			numberOfRooms = sc.nextInt();
			
			if (numberOfRooms == 0) {
				System.out.println("Please enter a number more than 0.");
			}
		}
		
		ArrayList<Reservation> result = new ArrayList<Reservation>();
		
		for (int i = 0; i < numberOfRooms; i++) {
			System.out.println(String.format("#%d reservation details: ", i + 1));
			
			Reservation reservation = askReservationQuestions(sc, reservations, rooms);
			reservations.book(reservation);
			result.add(reservation);
		}
		
		System.out.println(String.format("%d rooms were reserved as follows:", result.size()));
		Reservation.printHeader();
		
		for (Reservation r : result) {
			r.print();
		}
		
		return reservations;
	}
	
	/**
	 * (Task 8) Sort all guests' reservations alphabetically
	 */
	private static void sortReservations(Reservations reservations) {
		Reservation[] sorted = reservations.sortByLastName(false);
		Reservation.printHeader();
		for (Reservation r: sorted) {
			r.print();
		}
	}
	
	/**
	 * (Task 9) Report room availability for selected room class
	 */
	private static void reportRoomAvailability(Scanner sc, Reservations reservations, Rooms rooms) {
		String roomChoice = "";
		
		while(true) {
			System.out.print("Enter the choice of class (Standard, Deluxe, Superior):");
			roomChoice = sc.next();
			
			if (rooms.get(roomChoice) == null) {
				System.out.println("Invalid room type. Please select it from (Standard, Deluxe, Superior)");
			} else {
				break;
			}
		}
		reservations.reportRoomAvailability(roomChoice);
	}
	
	/**
	 * prepare initial reservations data for testing
	 */
	private static Reservations setTestData(Reservations reservations) throws ApplicationError {
		Reservation r1 = new Reservation("Qazi", "Zubair", 1, "Deluxe", "Queen-size");
		Reservation r2 = new Reservation("Oliver", "Barker", 3, "Superior", "King-size");
		Reservation r3 = new Reservation("Akram", "Khan", 20, "Standard", "Double");
		Reservation r4 = new Reservation("Jordan", "Robinson", 14, "Standard", "Double");
		Reservation r5 = new Reservation("Daniel", "Scott", 10, "Standard", "Twin");
		Reservation r6 = new Reservation("Alisha", "Aslam", 2, "Standard", "Double");

		ArrayList<Reservation> items = new ArrayList<Reservation>(Arrays.asList(r1, r2, r3, r4, r5, r6));
		for (int i = 0; i < items.size(); i++) {
			reservations.book(items.get(i));
		}
		reservations.print();
		return reservations;
	}
}
