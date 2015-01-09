package com.jmp.services.bank.service.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class DataManagerException extends Exception {

    private static final long serialVersionUID = 1L;

    public DataManagerException() {
        super();
    }

    public DataManagerException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataManagerException(String message) {
        super(message);
    }

    public DataManagerException(Throwable cause) {
        super(cause);
    }

}
