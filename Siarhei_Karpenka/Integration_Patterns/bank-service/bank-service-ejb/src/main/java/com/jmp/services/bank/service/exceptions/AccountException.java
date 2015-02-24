package com.jmp.services.bank.service.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class AccountException extends Exception {

	private static final long serialVersionUID = 1L;

	public AccountException() {
		super();
	}

	public AccountException(String message, Throwable cause) {
		super(message, cause);
	}

	public AccountException(String message) {
		super(message);
	}

	public AccountException(Throwable cause) {
		super(cause);
	}

	
}
