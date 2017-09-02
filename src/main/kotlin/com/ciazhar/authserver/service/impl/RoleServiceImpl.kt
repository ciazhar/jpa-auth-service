package com.ciazhar.authserver.service.impl

import com.ciazhar.authserver.config.string.ErrorMessage
import com.ciazhar.authserver.model.dto.response.ResponseData
import com.ciazhar.authserver.model.jpa.Role
import com.ciazhar.authserver.repository.RoleRepository
import com.ciazhar.authserver.service.RoleService
import org.springframework.stereotype.Service
import java.util.*

@Service
class RoleServiceImpl (private val roleRepository: RoleRepository): RoleService {
    override fun save(role: Role): ResponseData<*> {
        try {
            return ResponseData(roleRepository.save(role))
        } catch (e: Exception) {
            return ResponseData<Objects>(status = ErrorMessage.ERROR,message = e.message)
        }
    }

    override fun findAll(): ResponseData<*> {
        try {
            return ResponseData(roleRepository.findAll())
        } catch (e: Exception) {
            return ResponseData<Objects>(status = ErrorMessage.ERROR,message = e.message)
        }
    }

    override fun findOne(id: String): ResponseData<*> {
        try {
            return ResponseData(roleRepository.findOne(id))
        } catch (e: Exception) {
            return ResponseData<Objects>(status = ErrorMessage.ERROR,message = e.message)
        }
    }

    override fun delete(id: String): ResponseData<*> {
        try {
            return ResponseData(roleRepository.delete(id))
        } catch (e: Exception) {
            return ResponseData<Objects>(status = ErrorMessage.ERROR,message = e.message)
        }
    }
}