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
        try {
            return ResponseData(permissionRepository.save(permission))
        } catch (e: Exception) {
            return ResponseData<Objects>(status = ErrorMessage.ERROR,message = e.message)
        }
    }

    override fun findAll(): ResponseData<*> {
        try {
            return ResponseData(permissionRepository.findAll())
        } catch (e: Exception) {
            return ResponseData<Objects>(status = ErrorMessage.ERROR,message = e.message)
        }
    }

    override fun findOne(id: String): ResponseData<*> {
        try {
            return ResponseData(permissionRepository.findOne(id))
        } catch (e: Exception) {
            return ResponseData<Objects>(status = ErrorMessage.ERROR,message = e.message)
        }
    }

    override fun delete(id: String): ResponseData<*> {
        try {
            return ResponseData(permissionRepository.delete(id))
        } catch (e: Exception) {
            return ResponseData<Objects>(status = ErrorMessage.ERROR,message = e.message)
        }
    }
}