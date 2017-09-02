package com.ciazhar.authserver.model.dto.request

import java.util.Date

/**
 * Created by mirza on 11/12/16.
 */
class ProfileForm {

    var id: String? = null
    var username: String? = null

    var firstname: String? = null
    var lastname: String? = null
    var avatar: String? = null
    var birthDate: Date? = null
    var androidDeviceId: String? = null
    var email: String? = null
    var phone: String? = null
    var password: String? = null
}
