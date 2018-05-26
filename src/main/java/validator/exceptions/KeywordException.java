package validator.exceptions;

@SuppressWarnings("serial")
public class KeywordException extends RuntimeException {
	private String _message;
	
	public KeywordException(String message) {
		_message = message;
	}
	
	public String getMessage() {
		return _message;
	}	
}
