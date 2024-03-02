
/**
 * Application error class used in this app
 */
public class ApplicationError extends Exception {

	public ApplicationError() {
		// TODO Auto-generated constructor stub
	}

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
