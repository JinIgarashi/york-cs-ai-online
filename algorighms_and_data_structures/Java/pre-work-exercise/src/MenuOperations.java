import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Scanner;

/**
 * MenuOperations class to manage each operation called from menu console.
 */
public class MenuOperations {

	/**
	 * Rooms class object
	 */
	private Rooms rooms = null;
	
	/**
	 * Hashtable to manage room rates per room class
	 * Key: room class name
	 * Value: RoomClass object
	 */
	private Hashtable<String, RoomRate> roomRates = null;
	
	/**
	 * Reservations class object
	 */
	private Reservations reservations = null;
	
	/**
	 * Constructor. 
	 * It inputs default room data and room rate data.
	 * Furthermore, it initialize an array of Reservation objects.
	 * @throws ApplicationError
	 */
	public MenuOperations() throws ApplicationError {
		// Initialize Room and Room class data
		rooms = this.initializeRoomData();
		roomRates = this.initializeRoomRateData();
		reservations = new Reservations(rooms, roomRates);
	}
	
	/**
	 * (Task 1) Store room details
	 * @throws ApplicationError
	 */
	private Rooms initializeRoomData() throws ApplicationError {
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
	private Hashtable<String, RoomRate> initializeRoomRateData() {
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
	 * @return Reservation object (in this stage, it is not booked yet. You have to call book method of Reservations class)
	 * @throws ApplicationError
	 */
	private Reservation askReservationQuestions(Scanner sc) throws ApplicationError {
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
	public void reserveGuest(Scanner sc) throws ApplicationError {
		
		Reservation reservation = this.askReservationQuestions(sc);
		reservations.book(reservation);
		
		System.out.println(String.format("The room of %s was reserved as follows:", reservation.getRoomNumber()));
		Reservation.printHeader();
		reservation.print();
		
		System.out.println("Do you wish to reserve another room? Select Yes (Y) or No (N): ");
		String answer = sc.next();
		if (answer.toLowerCase().equals("y")) {
			this.reserveGuest(sc);
		}
	}
	
	/**
	 * (Task 4) Cancel a room reservation
	 */
	public void cancelReservation(Scanner sc) throws ApplicationError {
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
	}
	
	/**
	 * (Task 5) search reservations by a guest's last name
	 */
	public void searchReservations(Scanner sc) throws ApplicationError {
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
	}
	
	/**
	 * (Task 6) Reserve a group
	 */
	public void reserveGroup(Scanner sc) throws ApplicationError {
		
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
			
			Reservation reservation = askReservationQuestions(sc);
			reservations.book(reservation);
			result.add(reservation);
		}
		
		System.out.println(String.format("%d rooms were reserved as follows:", result.size()));
		Reservation.printHeader();
		
		for (Reservation r : result) {
			r.print();
		}
	}
	
	public void reportTotalIncome() {
		reservations.reportTotalIncome();
	}
	
	/**
	 * (Task 8) Sort all guests' reservations alphabetically
	 */
	public void sortReservations() {
		Reservation[] sorted = reservations.sortByLastName(false);
		Reservation.printHeader();
		for (Reservation r: sorted) {
			r.print();
		}
	}
	
	/**
	 * (Task 9) Report room availability for selected room class
	 */
	public void reportRoomAvailability(Scanner sc) {
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
	public void setTestData() throws ApplicationError {
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
	}

}
