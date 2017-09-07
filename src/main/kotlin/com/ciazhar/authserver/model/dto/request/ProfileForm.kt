package com.ciazhar.authserver.model.dto.request

import java.util.Date
import javax.validation.constraints.NotNull

/**
 * Created by mirza on 11/12/16.
 */
class ProfileForm (

        @field:NotNull
        var id: String?=null,

        @field:NotNull
        var username: String?=null,

        @field:NotNull
        var email: String?=null,

        @field:NotNull
        var password: String?=null,

        @field:NotNull
        var firstName: String?=null,

        @field:NotNull
        var lastName: String?=null,

        @field:NotNull
        var dateOfBirth: Date?=null,

        @field:NotNull
        var phoneNumber: String?=null,

        @field:NotNull
        var avatar: String?=null

)