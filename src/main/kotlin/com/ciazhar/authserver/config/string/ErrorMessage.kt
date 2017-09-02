package com.ciazhar.authserver.config.string

/**
 * Created by ciazhar on 6/21/17.
 */

object ErrorMessage {
    val REQUEST = "Request Error"
    val ERROR = "Error"
    val UNKNOWN_ERROR = "An error occured when post data to server, please try again!"
    val NO_DATA_ERROR = "No data were available from your request!"
    val DEFAULT_VALIDATION_ERROR = "Something wrong with your request!"
    val FORBIDDEN_ERROR = "You dont have the rights to access this request!"
    val INVALID_IMAGE_ERROR = "Please upload a valid image file!"
    val FIELD_NULL_OR_EMPTY_ERROR = "Please fill up all required field!"
    val MAX_DATA_LENGHT_ERROR = "You reach maximum allowed character to this field!"
    val MIN_DATA_LENGHT_ERROR = "You doesn't reach minimum character to this field!"
    val PASSWORD_NOT_SAME_ERROR = "Your new password didn't match!"
    val PASSWORD_INCORRECT_ERROR = "Your old password is incorrect!"
    val EMAIL_REGISTERED_ERROR = "The email you entered is already registered!"
    val SLUG_USED_ERROR = "The slug you entered is used, please choose a different one!"
    val MAX_KEYWORD_ERROR = "You have reachd maximum allowed keyword"
    val BLOCKED_KEYWORD_ERROR = "Your keyword is forbidden, please try a different one!"
}
