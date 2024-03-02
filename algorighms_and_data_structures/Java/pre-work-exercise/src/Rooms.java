import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;

/**
 * Rooms class manages Room object in Hashtable by adding, getting and validate room numbers for input.
 */
public class Rooms {

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
