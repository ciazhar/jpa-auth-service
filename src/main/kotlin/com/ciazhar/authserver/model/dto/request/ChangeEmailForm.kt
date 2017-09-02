package com.ciazhar.authserver.model.dto.request

import javax.validation.constraints.NotNull

class ChangeEmailForm (

        @field:NotNull
        var id: String? = null,

        @field:NotNull
        var email: String? = null

)
