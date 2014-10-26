package com.jmp.concurrency.services.bank.service.exception;

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
