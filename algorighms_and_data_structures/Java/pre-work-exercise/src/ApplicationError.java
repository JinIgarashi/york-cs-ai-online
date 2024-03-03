
/**
 * Application error class used in this app
 */
public class ApplicationError extends Exception {

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
