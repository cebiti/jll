public class InvalidKeyException extends RuntimeException {

	public InvalidKeyException() {
		super("\nInvalid Key");
	}

	public InvalidKeyException(String message) {
		super(message);
	}
}