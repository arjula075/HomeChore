package fi.rofl.HomeChore.util;

public class WrongPasswordException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
    public WrongPasswordException(String message) {
        super(message);
    }

}
