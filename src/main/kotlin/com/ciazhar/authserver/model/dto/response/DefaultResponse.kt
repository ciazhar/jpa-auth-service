package com.ciazhar.authserver.model.dto.response

import com.ciazhar.authserver.config.string.ErrorMessage
import com.ciazhar.authserver.config.string.SuccessMessage

/**
 * Created by ciazhar on 6/21/17.
 */

object DefaultResponse {

    fun defaultValidationError(): ResponseData<Any> {
        return ResponseData(ErrorMessage.ERROR,
                ErrorMessage.DEFAULT_VALIDATION_ERROR)
    }

    fun forbiddenError(): ResponseData<Any> {
        return ResponseData(ErrorMessage.ERROR,
                ErrorMessage.FORBIDDEN_ERROR)
    }

    fun maxKeywordError(): ResponseData<Any> {
        return ResponseData(ErrorMessage.ERROR,
                ErrorMessage.MAX_KEYWORD_ERROR)
    }

    fun exception(exception: Exception): ResponseData<Any> {
        return ResponseData(ErrorMessage.ERROR,
                exception.message)
    }

    fun error(message: String): ResponseData<Any> {
        return ResponseData(ErrorMessage.ERROR, message)
    }

    fun noDataError(): ResponseData<Any> {
        return ResponseData(ErrorMessage.ERROR,
                ErrorMessage.NO_DATA_ERROR)
    }

    fun success(): ResponseData<Any> {
        return ResponseData(SuccessMessage.SUCCESS,
                SuccessMessage.DEFAULT_SAVE_SUCCESS)
    }

    fun ok(): ResponseData<Any> {
        return ResponseData(SuccessMessage.OK,
                SuccessMessage.HEALTH)
    }

    fun blockedKeywordError(): ResponseData<Any> {
        return ResponseData(ErrorMessage.BLOCKED_KEYWORD_ERROR,
                ErrorMessage.BLOCKED_KEYWORD_ERROR)
    }

    fun unknownError(): ResponseData<Any> {
        return ResponseData(ErrorMessage.ERROR,
                ErrorMessage.UNKNOWN_ERROR)
    }


}
