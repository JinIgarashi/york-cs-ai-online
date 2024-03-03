import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
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

/**
 * Application error class used in this app
 */
class ApplicationError extends Exception {

	private static final long serialVersionUID = 1L;

	public ApplicationError() {}

	public ApplicationError(String message) {
		super(message);
	}

	public ApplicationError(Throwable cause) {
		super(cause);
	}

	public ApplicationError(String message, Throwable cause) {
		super(message, cause);
	}

	public ApplicationError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}


/**
 * MenuOperations class to manage each operation called from menu console.
 * This class's methods can interact with users by using Scanner object.
 */
class MenuOperations {

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
	private Reservation askReservationQuestions(Scanner sc){
		String roomChoice = "";
		while(true) {
			System.out.print("Enter the choice of class (Standard, Deluxe, Superior): ");
			roomChoice = sc.next();

			if (rooms.get(roomChoice) == null) {
				System.out.println("Invalid room type. Please select it from (Standard, Deluxe, Superior)");
			} else {
				break;
			}
		}
		Room room = rooms.get(roomChoice);
		
		ArrayList<String> bedTypes = room.getBedTypes();
		String bedChoice = "";
		while(true) {
			System.out.print(String.format("Enter the choice of bed type (%s): ", String.join(", ", bedTypes)));
			bedChoice = sc.next();
			if (bedTypes.indexOf(bedChoice) == -1) {
				System.out.println("Invalid bed type. please select from available bed types");
			} else {
				break;
			}
		}
		
		
		System.out.print("Enter the guest's first name: ");
		String firstName = sc.next();
		
		System.out.print("Enter the guest's last name: ");
		String lastName = sc.next();
		
		Integer lengthOfStay = 0;
		while (true) {
			System.out.print("Enter the length of stay (in nights): ");
			lengthOfStay = sc.nextInt();
			if (lengthOfStay < 1 ) {
				System.out.println("Please enter length of stay more than a night");
			} else {
				break;
			}
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
		
		System.out.println(String.format("The room number %s was reserved as follows:", reservation.getRoomNumber()));
		Reservation.printHeader();
		reservation.print();
		
		System.out.print("Do you wish to reserve another room? Select Yes (Y) or No (N): ");
		while (true) {
			String answer = sc.next();
			if (answer.toLowerCase().equals("y") || answer.toLowerCase().equals("yes")) {
				this.reserveGuest(sc);
			} else if (answer.toLowerCase().equals("n") || answer.toLowerCase().equals("no")) {
				System.out.println("Single reservation menu ended.");
				break;
			} else {
				System.out.println("Please enter your answer either Yes (Y) or No (N): ");
			}
		}
		
	}
	
	/**
	 * (Task 4) Cancel a room reservation
	 */
	public void cancelReservation(Scanner sc) throws ApplicationError {
		String roomChoice = "";
		while(true) {
			System.out.print("Enter the choice of class (Standard, Deluxe, Superior): ");
			roomChoice = sc.next();

			if (rooms.get(roomChoice) == null) {
				System.out.println("Invalid room type. Please select it from (Standard, Deluxe, Superior)");
			} else {
				break;
			}
		}
		
		System.out.print("Enter the room number: ");
		Integer roomNumber = sc.nextInt();
		
		System.out.print("Enter the guest’s last name: ");
		String lastName = sc.next();
		
		Integer lengthOfStay = 0;
		while (true) {
			System.out.print("Enter the length of stay (in nights): ");
			lengthOfStay = sc.nextInt();
			if (lengthOfStay < 1 ) {
				System.out.println("Please enter length of stay more than a night");
			} else {
				break;
			}
		}
		
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
	
	/**
	 * (Task 7) report total income calculated from reservations
	 */
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

/**
 * RoomRate class to manage a room's rate
 */
class RoomRate {

	/**
	 * Room class name
	 */
	private String roomClass;
	
	/**
	 * Room rate
	 */
	private Integer rate;
	
	/**
	 * Constructor
	 * @param roomClass room class name
	 * @param rate room rate
	 */
	public RoomRate(String roomClass, Integer rate) {
		this.roomClass = roomClass;
		this.rate = rate;
	}
	
	/**
	 * Get room class
	 * @return String
	 */
	public String getRoomClass() {
		return this.roomClass;
	}
	
	/**
	 * get room rate
	 * @return Integer
	 */
	public Integer getRate() {
		return this.rate;
	}
	
	/**
	 * print room rate information to the console
	 */
	public void print() {
		System.out.printf(
			"| %10s | %10s |", 
			this.roomClass, 
			this.rate
		);
		System.out.println();
	}

	/**
	 * print header to the console
	 */
	public static void printHeader() {
		System.out.printf(
			"| %10s | %10s |", 
			"Class", 
			"Rate"
		);
		System.out.println();
	}
	
}

/**
 * Room class to manage a room's specification
 */
class Room {

	/**
	 * Room class name
	 */
	private String roomClass;
	
	/**
	 * Room class description
	 */
	private String description;
	
	/**
	 * The range of room numbers available for the class
	 */
	private ArrayList<Integer> roomNumbers;
	
	/**
	 * The options for bed types available for the class
	 */
	private ArrayList<String> bedTypes;
	
	/**
	 * Constructor
	 * @param roomClass RoomClass name
	 * @param description description
	 * @param roomNumbers room numbers from and to in an array
	 * @param bedTypes Available bed types in an array
	 */
	public Room(String roomClass, String description, ArrayList<Integer> roomNumbers, ArrayList<String> bedTypes) {
		this.roomClass = roomClass;
		this.description = description;
		this.roomNumbers = roomNumbers;
		this.bedTypes = bedTypes;
	}
	
	/**
	 * Get room class name
	 * @return string
	 */
	public String getRoomClass() {
		return this.roomClass;
	}
	
	/**
	 * Get description
	 * @return string
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Get room numbers range
	 * @return ArrayList<Integer>
	 */
	public ArrayList<Integer> getRoomNumbers() {
		return this.roomNumbers;
	}
	
	/**
	 * Get available bed types
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getBedTypes() {
		return this.bedTypes;
	}
	
	/**
	 * Print room details to the console
	 */
	public void print() {
		StringBuilder sbRoomNumbers = new StringBuilder();
		for(int i = 0; i < this.roomNumbers.size(); i++) {
			sbRoomNumbers.append(this.roomNumbers.get(i));
			if (i < this.roomNumbers.size() - 1) {
				sbRoomNumbers.append(" to ");
			}
		}
		
		System.out.printf(
			"| %10s | %50s | %15s | %25s |", 
			this.roomClass, 
			this.description, 
			sbRoomNumbers.toString(), 
			String.join(", ", this.bedTypes)
		);
		System.out.println();
	}
	
	/**
	 * print header to the console
	 */
	public static void printHeader() {
		System.out.printf(
			"| %10s | %50s | %15s | %25s |", 
			"Class", 
			"Description", 
			"Room numbers", 
			"Bed types"
		);
		System.out.println();
	}

}

/**
 * Rooms class manages Room object in Hashtable by adding, getting and validate room numbers for input.
 */
class Rooms {

	/**
	 * Hashtable to store room objects. Key is room class
	 */
	private Hashtable<String, Room> rooms;
	
	/**
	 * Constructor. Initialize rooms hashtable
	 */
	public Rooms() {
		rooms = new Hashtable<String, Room>();
	}
	
	/**
	 * Add a room object into Hashtable
	 * @param room Room object
	 * @throws ApplicationError throw error if room numbers has conflict with existing rooms or room class was already registered.
	 */
	public void add(Room room) throws ApplicationError {
		ArrayList<Integer> numbers = room.getRoomNumbers();

		// check room numbers
		if (numbers.size() != 2) {
			throw new ApplicationError("Room numbers have to be 2 elements like 1 to 100");
		}
		
		if (numbers.get(0) == numbers.get(1) ) {
			throw new ApplicationError("room numbers from and to cannot be the same");
		}
		
		if (!this.validateRoomNumbers(room)) {
			throw new ApplicationError("Room number " + numbers.get(0) + " to " + numbers.get(1) + " are already occupied by other room classes.");
		}
		
		// check room class's conflict
		if (this.rooms.get(room.getRoomClass()) != null) {
			throw new ApplicationError("Room class " + room.getRoomClass() + " was already registered");
		}
		
		// check bed type
		ArrayList<String> bedTypes = room.getBedTypes();
		if (bedTypes.size() ==0) {
			throw new ApplicationError("Please add at least a bed type");
		} else {
			String[] acceptable = {"Twin", "Double", "Queen-size", "King-size"};
			for (int i = 0; i < bedTypes.size(); i ++) {
				String bedType = bedTypes.get(i);
				if (!Arrays.asList(acceptable).contains(bedType)) {
					throw new ApplicationError("Bed type have to be one of " + String.join(", ", acceptable));
				}
			}
		}
		
		this.rooms.put(room.getRoomClass(), room);
	}
	
	/**
	 * Get a room object by class name
	 * @param roomClass Room class name
	 * @return A room object
	 */
	public Room get(String roomClass) {
		return this.rooms.get(roomClass);
	}
	
	/**
	 * Remove a room object from hash table
	 * @param roomClass Room class name
	 */
	public void remove(String roomClass) {
		if (this.rooms.containsKey(roomClass)) {
			this.rooms.remove(roomClass);
		}
	}
	
	/**
	 * Print rooms information to the console
	 */
	public void print() {
		Collection<Room> values = this.rooms.values();
        Iterator<Room> iterator = values.iterator();
        Room.printHeader();
        while (iterator.hasNext()) {
            Room value = iterator.next();
            value.print();
        }
	}
	
	/**
	 * Check room numbers validity against existing rooms 
	 * @param room Room Object
	 * @return true: valid room numbers, false: invalid
	 */
	private Boolean validateRoomNumbers (Room room) {
		ArrayList<Integer> roomNumbers = room.getRoomNumbers();
		Integer first = roomNumbers.get(0);
		Integer last = roomNumbers.get(1);
		
		Boolean isValid = true;
		
		Collection<Room> values = this.rooms.values();
        Iterator<Room> iterator = values.iterator();
        while (isValid == true && iterator.hasNext()) {
        	// check whether the room number ranges have conflict with existing rooms;
            Room value = iterator.next();
            ArrayList<Integer>numbers = value.getRoomNumbers();
            if ((first >= numbers.get(0) && first <= numbers.get(1)) || (last >= numbers.get(0) && last <= numbers.get(1))) {
            	isValid = false;
            }
            
        }
        
        return isValid;
		
	}

}

/**
 * Reservation class to manage a room's reservation detail
 */
class Reservation {

	/**
	 * guest's first name
	 */
	private String firstName;
	
	/**
	 * guest's last name
	 */
	private String lastName;
	
	/**
	 * length of stay (nights)
	 */
	private Integer lengthOfStay;
	
	/**
	 * Room number reserved
	 */
	private Integer roomNumber;
	
	/**
	 * guest's room class chosen
	 */
	private String roomChoice;
	
	/**
	 * guest's bed type choice
	 */
	private String bedChoice;
	
	/**
	 * Constructor
	 * @param firstName guest's first name
	 * @param lastName guest's last name
	 * @param lengthOfStay length of stay (night)
	 * @param roomChoice Room class chosen
	 * @param bedChoice bed type chosen
	 */
	public Reservation(
			String firstName, 
			String lastName, 
			Integer lengthOfStay, 
			String roomChoice, 
			String bedChoice) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.lengthOfStay = lengthOfStay;
		this.roomChoice = roomChoice;
		this.bedChoice = bedChoice;
	}
	
	/**
	 * get first name
	 * @return
	 */
	public String getFirstName() {
		return this.firstName;
	}
	
	/**
	 * get last name
	 * @return
	 */
	public String getLastName() {
		return this.lastName;
	}
	
	/**
	 * get length of stay (night)
	 * @return
	 */
	public Integer getLengthOfStay() {
		return this.lengthOfStay;
	}
	
	/**
	 * get room number
	 * @return
	 */
	public Integer getRoomNumber() {
		return this.roomNumber;
	}
	
	/**
	 * set room number
	 * @param roomNumber room number
	 */
	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	/**
	 * get room class choice
	 * @return
	 */
	public String getRoomChoice() {
		return this.roomChoice;
	}
	
	/**
	 * get bed type choice
	 */
	public String getBedChooice() {
		return this.bedChoice;
	}
	
	/**
	 * print room reservation detail to the console
	 */
	public void print() {
		System.out.printf(
			"| %15s | %15s | %15d | %15d | %10s | %10s |", 
			this.firstName, 
			this.lastName, 
			this.lengthOfStay, 
			this.roomNumber, 
			this.roomChoice, 
			this.bedChoice
		);
		System.out.println();
	}
	
	/**
	 * print header to the console
	 */
	public static void printHeader() {
		System.out.printf(
			"| %15s | %15s | %15s | %15s | %10s | %10s |", 
			"First name", 
			"Last name", 
			"Length of stay", 
			"Room reserved", 
			"Class", 
			"Type"
		);
		System.out.println();
	}

}

/**
 * Reservations class to manage room booking details
 */
class Reservations {

	private ArrayList<Reservation> reservations;
	private Rooms rooms;
	private Hashtable<String, RoomRate> roomRates;
	
	/**
	 * Constructor
	 * @param rooms Rooms object
	 * @param roomRates RoomRate hashtable object
	 */
	public Reservations(Rooms rooms, Hashtable<String, RoomRate> roomRates) {
		this.reservations = new ArrayList<Reservation>();
		this.rooms = rooms;
		this.roomRates = roomRates;
	}
	
	/**
	 * (Task 3 & task 6)Book a room reservation
	 * @param reservation Reservation class object
	 * @throws ApplicationError
	 */
	public void book(Reservation reservation) throws ApplicationError {
		Room room = rooms.get(reservation.getRoomChoice());
		if (room == null) {
			throw new ApplicationError("Invalid room class type.");
		}
		
		ArrayList<Integer> roomNumbers = room.getRoomNumbers();
		
		Integer newRoomNumber = roomNumbers.get(0);
		while (newRoomNumber <= roomNumbers.get(1)) {
			
			ArrayList<Integer> bookedRooms = new ArrayList<Integer>();
			for(Reservation r: reservations) {
				if (!r.getRoomChoice().equals(reservation.getRoomChoice())) continue;
				bookedRooms.add(r.getRoomNumber());
			}
			if (bookedRooms.indexOf(newRoomNumber) == -1) {
				break;
			} else if (newRoomNumber < roomNumbers.get(1)) {
				newRoomNumber++;
			} else {
				newRoomNumber = null;
				break;
			}
		}
		
		if (newRoomNumber == null) {
			throw new ApplicationError("All rooms of this class are fully booked");
		}
		
		reservation.setRoomNumber(newRoomNumber);
		reservations.add(reservation);
		
	}
	
	/**
	 * (Task4) Cancel a reservation by given information
	 * @param roomChoice room class choice
	 * @param roomNumber room number
	 * @param lastName last name
	 * @param lengthOfStay length of stay (nights)
	 * @return deleted reservation object. return null if no records matched.
	 */
	public Reservation cancel(String roomChoice, Integer roomNumber, String lastName, Integer lengthOfStay) {
		int targetIndex = -1;
		Reservation target = null;
		for (int i = 0; i < reservations.size(); i++) {
			Reservation r = reservations.get(i);
			if (r.getRoomChoice().equals(roomChoice)
					&& r.getRoomNumber() == roomNumber
					&& r.getLastName().equals(lastName)
					&& r.getLengthOfStay() == lengthOfStay) {
				// if found matched reservation, keep index and break from loop
				targetIndex = i;
				target = r;
				break;
			}
		}
		if (targetIndex != -1) {
			reservations.remove(targetIndex);
		}
		return target;
	}
	
	/**
	 * (Task 5) Search reservations by last name given
	 * @param lastName a guest's last name
	 * @return searched results of an array of Reservation objects
	 */
	public ArrayList<Reservation> search(String lastName) {
		ArrayList<Reservation> result = new ArrayList<Reservation>();
		
		for (Reservation r : reservations) {
			if (r.getLastName().toLowerCase().equals(lastName.toLowerCase())) {
				// search last name by using lower case to improve matches
				// if last name is matched, add it to result
				result.add(r);
			}
		}
		return result;
	}
	
	/**
	 * (Task 7) generate a report to calculate the total income from all reservations for each room class
	 */
	public void reportTotalIncome() {
		// create a hashtable with key of room type
		Hashtable<String, ArrayList<Reservation>> summary = new Hashtable<String, ArrayList<Reservation>>();
		
		// categorize reservations by room type
		for (Reservation r: this.reservations) {
			ArrayList<Reservation> bookings = summary.get(r.getRoomChoice());
			if (!summary.containsKey(r.getRoomChoice())) {
				bookings = new ArrayList<Reservation>();
			}
			bookings.add(r);
			summary.put(r.getRoomChoice(), bookings);
		}
		
		// print header
		System.out.printf(
			"| %8s | %15s | %15s | %22s |", 
			"Class", 
			"Number of rooms",
			"Length of stays",
			"Total income generated"
		);
		System.out.println();
		
		Enumeration<String> keys = summary.keys();
        Iterator<String> iterator = keys.asIterator();
        while (iterator.hasNext()) {
            String roomClass = iterator.next();
            RoomRate roomRate = this.roomRates.get(roomClass);
            Integer rate = roomRate.getRate();
            
            ArrayList<Reservation> bookings = summary.get(roomClass);
            
            int numberOfRooms = 0;
            int lengthOfStays = 0;
            
            // count total length of stay
            if (bookings != null && bookings.size() > 0) {
            	numberOfRooms = bookings.size();
            	
            	for (Reservation b: bookings) {
            		lengthOfStays = lengthOfStays + b.getLengthOfStay();
            	}
            }
            
            int totalIncome = lengthOfStays * rate;
            
            System.out.printf(
    			"| %8s | %15d | %15d | %22d |", 
    			roomClass, 
    			numberOfRooms,
    			lengthOfStays, 
    			totalIncome
    		);
    		System.out.println();
        }
		
		
	}
	
	/**
	 * (Task 8) Sort all guests' data alphabetically by their last name
	 * @param isReversed if true, reverse sorted order
	 * @return A sorted array of Reservation objects
	 */
	public Reservation[] sortByLastName(Boolean isReversed) {
		Reservation[] sorted = this.reservations.toArray(new Reservation[this.reservations.size()]);
		if (sorted.length < 2) {
			return sorted;
		}
		
		Comparator<Reservation> comparator = Comparator.comparing(Reservation::getLastName);
		if (isReversed) {
			comparator = comparator.reversed();
		}
		
		Arrays.sort(sorted, comparator);
		
		return sorted;
	}
	
	/**
	 * (Task 9) Report room availability for a selected room class
	 * @param roomChoice selected room class name
	 */
	public void reportRoomAvailability(String roomChoice) {
		Hashtable<String, int[]> stats = this.computeRoomAvailability();
        
        int[] roomStats = stats.get(roomChoice);
        // print results
        System.out.printf(
    			"| %8s | %24s | %25s | %5s |", 
    			"Class", 
    			"Number of rooms reserved",
    			"Number of rooms available", 
    			"Total"
    		);
        System.out.println();
        
        System.out.printf(
			"| %8s | %24d | %25d | %5d |", 
			roomChoice, 
			roomStats[0],
			roomStats[1], 
			roomStats[2]
		);
		System.out.println();
        
	}
	
	/**
	 * (Task 9) Compute room availability for all room classes
	 * @return Hashtable<String, int[]> object
	 * key is room type name
	 * value is int[] which can have the following three elements
	 * - first element is Number of rooms reserved
	 * - second element is number of rooms available
	 * - third element is total number of rooms
	 */
	private Hashtable<String, int[]> computeRoomAvailability() {
		Hashtable<String, int[]> outputs = new Hashtable<String, int[]>();
		
		// initialize stats
		Enumeration<String> keys = this.roomRates.keys();
        Iterator<String> iterator = keys.asIterator();
        while (iterator.hasNext()) {
        	String roomClass = iterator.next();
            Room room = rooms.get(roomClass);
            ArrayList<Integer> numbers = room.getRoomNumbers();
            int total = (numbers.get(1) - numbers.get(0)) + 1;
            
            // set zero for the number of rooms reservations
    		// set total room numbers for rooms available
            int[] stats = new int[] {0, total, total};
            outputs.put(roomClass, stats);
        }
        
        // compute stats
        for (Reservation r: this.reservations) {
        	String roomClass = r.getRoomChoice();
        	int[] stats = outputs.get(roomClass);
        	stats[0] = stats[0] + 1;
        	stats[1] = stats[2] - stats[0];
        	outputs.put(roomClass, stats);
        }
        return outputs;
	}
	
	/**
	 * Print reservations details to the console
	 */
	public void print() {
		Reservation.printHeader();
		for (int i = 0; i < reservations.size(); i++) {
			reservations.get(i).print();
		}
	}

}

