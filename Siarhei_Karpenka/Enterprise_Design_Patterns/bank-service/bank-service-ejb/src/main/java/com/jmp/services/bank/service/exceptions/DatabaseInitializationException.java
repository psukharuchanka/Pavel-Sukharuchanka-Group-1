package com.jmp.services.bank.service.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class DatabaseInitializationException extends Exception {

	private static final long serialVersionUID = 1L;

	public DatabaseInitializationException() {
		super();
	}

	public DatabaseInitializationException(String message, Throwable cause) {
		super(message, cause);
	}

	public DatabaseInitializationException(String message) {
		super(message);
	}

	public DatabaseInitializationException(Throwable cause) {
		super(cause);
	}

}
