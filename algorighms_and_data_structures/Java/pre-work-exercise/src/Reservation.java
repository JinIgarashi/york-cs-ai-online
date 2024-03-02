/**
 * Reservation class to manage a room's reservation detail
 */
public class Reservation {

	private String firstName;
	private String lastName;
	private Integer lengthOfStay;
	private Integer roomNumber;
	private String roomChoice;
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
