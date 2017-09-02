package com.ciazhar.authserver.model.dto.request

import javax.validation.constraints.NotNull

class ChangeAndroidDeviceForm (

        @field:NotNull
        var id : String?=null,

        @field:NotNull
        var androidDeviceId : String?=null
)
