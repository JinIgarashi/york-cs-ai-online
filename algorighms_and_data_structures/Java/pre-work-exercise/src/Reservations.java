import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

/**
 * Reservations class to manage room booking details
 */
public class Reservations {

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
	
	public void print() {
		Reservation.printHeader();
		for (int i = 0; i < reservations.size(); i++) {
			reservations.get(i).print();
		}
	}

}
