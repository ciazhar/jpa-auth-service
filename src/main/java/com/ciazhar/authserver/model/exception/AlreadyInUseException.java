package com.ciazhar.authserver.model.exception;

/**
 * Created by ciazhar on 6/21/17.
 */
public class AlreadyInUseException extends Exception{
    private static final long serialVersionUID = 3975795550933136358L;

    public static final String MESSAGE = "some value is unique and already in use";

    public AlreadyInUseException() {
        super(MESSAGE);
    }

}
