package com.ciazhar.authserver.model.dto.request

import java.util.Date
import javax.validation.constraints.NotNull

/**
 * Created by mirza on 11/12/16.
 */
class ProfileForm (

        @field:NotNull
        var id: String,

        @field:NotNull
        var username: String,

        @field:NotNull
        var email: String,

        @field:NotNull
        var password: String,

        @field:NotNull
        var firstName: String,

        @field:NotNull
        var lastName: String,

        @field:NotNull
        var dateOfBirth: Date,

        @field:NotNull
        var phoneNumber: String,

        @field:NotNull
        var avatar: String

)