package com.ciazhar.authserver.service.impl

import com.ciazhar.authserver.config.string.ErrorMessage
import com.ciazhar.authserver.model.dto.response.ResponseData
import com.ciazhar.authserver.model.jpa.Permission
import com.ciazhar.authserver.repository.PermissionRepository
import com.ciazhar.authserver.service.PermissionService
import org.springframework.stereotype.Service
import java.util.*

@Service
class PermissionServiceImpl (private val permissionRepository: PermissionRepository): PermissionService {
    override fun save(permission: Permission): ResponseData<*> {
        if(permission.id!=null&&permissionRepository.findOne(permission.id)==null){
            return ResponseData<Objects>(status = "Update Failed",message = "ID Not Found")
        }
        return ResponseData(permissionRepository.save(permission))
    }

    override fun findAll(): ResponseData<*> {
        return ResponseData(permissionRepository.findAll())
    }

    override fun findOne(id: String): ResponseData<*> {
        val permission = permissionRepository.findOne(id) ?: return ResponseData<Objects>(status = "Update Failed",message = "ID Not Found")

        return ResponseData(permission)
    }

    override fun delete(id: String): ResponseData<*> {
        val role = permissionRepository.findOne(id) ?: return ResponseData<Objects>(status = "Update Failed",message = "ID Not Found")

        permissionRepository.delete(id)

        return ResponseData(role)
    }
}