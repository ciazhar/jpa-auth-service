package com.ciazhar.authserver.model.exception;

/**
 * Created by ciazhar on 6/21/17.
 */

public class AuthException extends Exception {
    private static final long serialVersionUID = 6034102462788278056L;

    public static final String MESSAGE = "authentication error";

    public AuthException() {
        super(MESSAGE);
    }
}
