public class InvalidStateException extends RuntimeException {

	public InvalidStateException() {
		super("\nInvalid State");
	}

	public InvalidStateException(String message) {
		super(message);
	}
}