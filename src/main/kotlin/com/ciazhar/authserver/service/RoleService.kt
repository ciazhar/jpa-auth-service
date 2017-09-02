package com.ciazhar.authserver.service

import com.ciazhar.authserver.model.dto.response.ResponseData
import com.ciazhar.authserver.model.jpa.Role


/**
 * Created by ciazhar on 9/2/17.
 */

interface RoleService{
    fun save(role: Role) : ResponseData<*>
    fun findAll() : ResponseData<*>
    fun findOne(id : String) : ResponseData<*>
    fun delete(id: String): ResponseData<*>
}