package validator.exceptions;

@SuppressWarnings("serial")
public class AuthenticationException extends RuntimeException {
	private String _message;
	
	public AuthenticationException(String message) {
		_message = message;
	}
	
	public String getMessage() {
		return _message;
	}	
}
