package com.ciazhar.authserver.model.dto.request

import javax.validation.constraints.NotNull

/**
 * Created by ciazhar on 6/23/17.
 */


class ChangeRoleForm (

        @field:NotNull
        var id: String,

        @field:NotNull
        var role: String
)
