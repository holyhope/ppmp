package fr.onlineCV.dao;

public class DAOException extends RuntimeException {
	
	private static final long serialVersionUID = -6662042658969121535L;

	public DAOException(String message) {
		super(message);
	}

	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	public DAOException(Throwable cause) {
		super(cause);
	}

}