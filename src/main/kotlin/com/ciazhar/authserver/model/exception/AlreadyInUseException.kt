package com.ciazhar.authserver.model.exception

/**
 * Created by ciazhar on 6/21/17.
 */
class AlreadyInUseException : Exception(MESSAGE) {
    companion object {
        private val serialVersionUID = 3975795550933136358L

        val MESSAGE = "some value is unique and already in use"
    }

}
