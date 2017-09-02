package com.ciazhar.authserver.model.exception

/**
 * Created by ciazhar on 6/21/17.
 */

class AuthException : Exception(MESSAGE) {
    companion object {
        private val serialVersionUID = 6034102462788278056L

        val MESSAGE = "authentication error"
    }
}
