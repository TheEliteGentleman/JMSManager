/**
 * 
 */
package za.co.sindi.jms.exception;

/**
 * @author Buhake Sindi
 * @since 03 July 2012
 *
 */
public class ServiceLocatorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1093794547131004627L;

	/**
	 * @param message
	 */
	public ServiceLocatorException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public ServiceLocatorException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ServiceLocatorException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
}
