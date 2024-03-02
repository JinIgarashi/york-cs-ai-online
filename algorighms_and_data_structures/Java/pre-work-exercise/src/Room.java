import java.util.ArrayList;

/**
 * Room class to manage a room's specification
 */
public class Room {

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
