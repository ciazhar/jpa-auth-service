package com.ciazhar.authserver.service

import com.ciazhar.authserver.model.dto.request.PermissionForm
import com.ciazhar.authserver.model.dto.response.ResponseData
import com.ciazhar.authserver.model.jpa.Permission

/**
 * Created by ciazhar on 9/2/17.
 */
interface PermissionService {
    fun save(role: PermissionForm) : ResponseData<*>
    fun findAll() : ResponseData<*>
    fun findOne(id : String) : ResponseData<*>
    fun delete(id: String): ResponseData<*>
}