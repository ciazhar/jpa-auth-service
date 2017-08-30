package com.ciazhar.authserver.model.dto.response;

import com.ciazhar.authserver.config.string.SuccessMessage;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by ciazhar on 6/20/17.
 */


/**
 * Untuk Generalisasi JSON format (status, message, data)
 * @param <T>
 */
public class ResponseData<T> {
    private String status;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public ResponseData() {
        this.status = SuccessMessage.SUCCESS;
        this.message = SuccessMessage.DEFAULT_SAVE_SUCCESS;
    }

    public ResponseData(T data) {
        this.status = SuccessMessage.SUCCESS;
        this.message = SuccessMessage.DEFAULT_SAVE_SUCCESS;
        this.data = data;
    }

    public ResponseData(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
