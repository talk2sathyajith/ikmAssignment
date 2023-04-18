package exception;

import org.springframework.http.HttpStatus;

public class CustomAPIException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2142188388451172627L;
	private HttpStatus status;
	private String message;

	public CustomAPIException(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}

	public CustomAPIException(String message, HttpStatus status, String message1) {
		super(message);
		this.status = status;
		this.message = message1;
	}

	public HttpStatus getStatus() {
		return status;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
