public class NonComparableException extends RuntimeException {

    public NonComparableException() {
    	super("\nNon Comparable");
    }
	
	public NonComparableException(String message) {
        super(message);
    }
}
