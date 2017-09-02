package com.ciazhar.authserver.util.email

class EmailStatus(val to: String, val subject: String, val body: String) {

    var status: String? = null
        private set
    var errorMessage: String? = null
        private set

    fun success(): EmailStatus {
        this.status = SUCCESS
        return this
    }

    fun error(errorMessage: String): EmailStatus {
        this.status = ERROR
        this.errorMessage = errorMessage
        return this
    }

    val isSuccess: Boolean
        get() = SUCCESS == this.status

    val isError: Boolean
        get() = ERROR == this.status

    companion object {
        val SUCCESS = "SUCCESS"
        val ERROR = "ERROR"
    }
}