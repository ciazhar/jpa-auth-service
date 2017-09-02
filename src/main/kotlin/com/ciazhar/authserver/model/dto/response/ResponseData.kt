package com.ciazhar.authserver.model.dto.response

import com.ciazhar.authserver.config.string.SuccessMessage
import com.fasterxml.jackson.annotation.JsonInclude

/**
 * Created by ciazhar on 6/20/17.
 */


/**
 * Untuk Generalisasi JSON format (status, message, data)
 * @param <T>
</T> */
class ResponseData<T> {
    var status: String? = null
    var message: String? = null
    @JsonInclude(JsonInclude.Include.NON_NULL)
    var data: T? = null

    constructor() {
        this.status = SuccessMessage.SUCCESS
        this.message = SuccessMessage.DEFAULT_SAVE_SUCCESS
    }

    constructor(data: T) {
        this.status = SuccessMessage.SUCCESS
        this.message = SuccessMessage.DEFAULT_SAVE_SUCCESS
        this.data = data
    }

    constructor(status: String, message: String?) {
        this.status = status
        this.message = message
    }


}
