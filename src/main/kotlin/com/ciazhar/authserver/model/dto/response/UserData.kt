package com.ciazhar.authserver.model.dto.response

import java.util.Date
import javax.validation.constraints.NotNull

class UserData (

        @field:NotNull
        var id: String? = null,

        @field:NotNull
        var email: String? = null,

        @field:NotNull
        var phoneNumber: String? = null,

        @field:NotNull
        var username: String? = null,

        @field:NotNull
        var avatar: String? = null,

        @field:NotNull
        var birthDate: Date? = null,

        @field:NotNull
        var joinedDate: Date? = null,

        @field:NotNull
        var firstname: String? = null,

        @field:NotNull
        var lastname: String? = null

)