package validator.exceptions;

@SuppressWarnings("serial")
public class NoDocumentsReturnedException extends RuntimeException {
	private String _message;
	
	public NoDocumentsReturnedException(String message) {
		_message = message;
	}
}
