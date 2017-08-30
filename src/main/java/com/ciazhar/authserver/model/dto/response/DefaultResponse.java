package com.ciazhar.authserver.model.dto.response;

import com.ciazhar.authserver.config.string.ErrorMessage;
import com.ciazhar.authserver.config.string.SuccessMessage;

/**
 * Created by ciazhar on 6/21/17.
 */

public class DefaultResponse {

    public static ResponseData<Object> defaultValidationError() {
        return new ResponseData<Object>(ErrorMessage.ERROR,
                ErrorMessage.DEFAULT_VALIDATION_ERROR);
    }

    public static ResponseData<Object> forbiddenError() {
        return new ResponseData<Object>(ErrorMessage.ERROR,
                ErrorMessage.FORBIDDEN_ERROR);
    }

    public static ResponseData<Object> maxKeywordError() {
        return new ResponseData<Object>(ErrorMessage.ERROR,
                ErrorMessage.MAX_KEYWORD_ERROR);
    }

    public static ResponseData<Object> exception(Exception exception) {
        return new ResponseData<Object>(ErrorMessage.ERROR,
                exception.getMessage());
    }

    public static ResponseData<Object> error(String message) {
        return new ResponseData<Object>(ErrorMessage.ERROR, message);
    }

    public static ResponseData<Object> noDataError() {
        return new ResponseData<Object>(ErrorMessage.ERROR,
                ErrorMessage.NO_DATA_ERROR);
    }

    public static ResponseData<Object> success() {
        return new ResponseData<Object>(SuccessMessage.SUCCESS,
                SuccessMessage.DEFAULT_SAVE_SUCCESS);
    }

    public static ResponseData<Object> ok() {
        return new ResponseData<Object>(SuccessMessage.OK,
                SuccessMessage.HEALTH);
    }

    public static ResponseData<Object> blockedKeywordError() {
        return new ResponseData<Object>(ErrorMessage.BLOCKED_KEYWORD_ERROR,
                ErrorMessage.BLOCKED_KEYWORD_ERROR);
    }

    public static ResponseData<Object> unknownError() {
        return new ResponseData<Object>(ErrorMessage.ERROR,
                ErrorMessage.UNKNOWN_ERROR);
    }


}
