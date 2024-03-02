/**
 * RoomRate class to manage a room's rate
 */
public class RoomRate {

	String roomClass;
	Integer rate;
	
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
