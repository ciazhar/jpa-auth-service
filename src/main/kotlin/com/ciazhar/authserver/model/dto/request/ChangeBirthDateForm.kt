package com.ciazhar.authserver.model.dto.request

import java.util.Date
import javax.validation.constraints.NotNull

class ChangeBirthDateForm (

        @field:NotNull
        var id: String?=null,

        @field:NotNull
        var birthDate: Date?=null
)
