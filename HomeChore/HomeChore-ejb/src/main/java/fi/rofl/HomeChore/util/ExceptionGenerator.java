package fi.rofl.HomeChore.util;

public class ExceptionGenerator {
	
	public static UserNotFoundException createUserNotFoundException(String message) {
		UserNotFoundException e = new UserNotFoundException(message);
		return e;
	}

	public static WrongPasswordException createWrongPasswordException(String string) {
		WrongPasswordException e = new WrongPasswordException(string);
		return e;
	}
	
	public static DuplicateUidException createDuplicateUidException(String string) {
		DuplicateUidException e = new DuplicateUidException(string);
		return e;
	}
	
	public static NoSessionException createNoSessionException(String string) {
		NoSessionException e = new NoSessionException(string);
		return e;
	}

}
