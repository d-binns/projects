package listi;

public class EmptyCollectionException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EmptyCollectionException() {
		super();
	}
	
	EmptyCollectionException(String msg){
		super(msg);
	}

}
