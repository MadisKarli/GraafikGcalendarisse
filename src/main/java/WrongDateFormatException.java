package main.java;

public class WrongDateFormatException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public WrongDateFormatException(String message){
		super(message);
	}

	public String getMessage()
    {
        return super.getMessage();
    }

}
