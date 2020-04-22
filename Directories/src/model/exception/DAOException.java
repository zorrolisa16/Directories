package model.exception;

/**
 * @author Polina Astashko
 */
public class DAOException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor with specified string
	 *
	 * @param message string
	 */
	public DAOException(String message) {
		super(message);
	}

	/**
	 * Constructor with specified string and exception
	 *
	 * @param message string
	 * @param e       error covered
	 */
	public DAOException(String message, Throwable e) {
		super(message, e);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}

	@Override
	public void printStackTrace() {
		super.printStackTrace();
	}

}
