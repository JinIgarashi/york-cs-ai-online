/**
 * Util class to manage utilized methods
 */
public class Utils {
	
	/**
	 * Transform the first letter of string to capitalize
	 * @param value string value
	 * @return capitalized string value
	 */
	public static String toCapitalize(String value) {
		if (value.isEmpty()) {
			return "";
		}
		value = value.toLowerCase();
		
		if (value.length() == 1) {
			return value.substring(0, 1).toUpperCase();
		} else {
			return value.substring(0, 1).toUpperCase() + value.substring(1);
		}
	}
}
