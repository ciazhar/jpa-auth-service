package com.ciazhar.authserver.model.dto.request

import javax.validation.constraints.NotNull

/**
 * Created by ciazhar on 9/2/17.
 */

class RegisterForm(

        @field:NotNull
        var username : String?=null,

        @field:NotNull
        var email : String?=null,

        @field:NotNull
        var password : String?=null
)