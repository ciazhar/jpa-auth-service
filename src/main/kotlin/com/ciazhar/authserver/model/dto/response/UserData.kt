package com.ciazhar.authserver.model.dto.response

import com.ciazhar.authserver.config.AppConfig
import com.ciazhar.authserver.model.jpa.User

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class UserData {

    var id: String? = null
    var email: String? = null
    var phoneNumber: String? = null
    var username: String? = null
    var avatar: String? = null
    var birthDate: Date? = null
    var joinedDate: Date? = null
    var firstname: String? = null
    var lastname: String? = null

    var dateFormat: DateFormat? = null

    constructor() {
        dateFormat = SimpleDateFormat(AppConfig.DATE_TIME_FORMAT, Locale.US)

    }

    constructor(user: User) {
        this.id = user.id
        this.email = user.email
        this.phoneNumber = user.phoneNumber
        this.username = user.username
        this.avatar = user.avatar
        this.birthDate = user.birthDate
        this.joinedDate = user.createdDate
        this.firstname = user.firstName
        this.lastname = user.lastName
    }
}