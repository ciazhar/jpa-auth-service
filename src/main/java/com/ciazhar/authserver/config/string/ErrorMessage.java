package com.ciazhar.authserver.config.string;

/**
 * Created by ciazhar on 6/21/17.
 */

public class ErrorMessage {
    public static final String ERROR = "Error";
    public static final String UNKNOWN_ERROR = "An error occured when post data to server, please try again!";
    public static final String NO_DATA_ERROR = "No data were available from your request!";
    public static final String DEFAULT_VALIDATION_ERROR = "Something wrong with your request!";
    public static final String FORBIDDEN_ERROR = "You dont have the rights to access this request!";
    public static final String INVALID_IMAGE_ERROR = "Please upload a valid image file!";
    public static final String FIELD_NULL_OR_EMPTY_ERROR = "Please fill up all required field!";
    public static final String MAX_DATA_LENGHT_ERROR = "You reach maximum allowed character to this field!";
    public static final String MIN_DATA_LENGHT_ERROR = "You doesn't reach minimum character to this field!";
    public static final String PASSWORD_NOT_SAME_ERROR = "Your new password didn't match!";
    public static final String PASSWORD_INCORRECT_ERROR = "Your old password is incorrect!";
    public static final String EMAIL_REGISTERED_ERROR = "The email you entered is already registered!";
    public static final String SLUG_USED_ERROR = "The slug you entered is used, please choose a different one!";
    public static final String MAX_KEYWORD_ERROR = "You have reachd maximum allowed keyword";
    public static final String BLOCKED_KEYWORD_ERROR = "Your keyword is forbidden, please try a different one!";
}
