public class InvalidArgumentException extends RuntimeException {

	public InvalidArgumentException() {
		super("\nInvalid Argument");
	}

	public InvalidArgumentException(String newString) {
		super(newString);
	}

}
