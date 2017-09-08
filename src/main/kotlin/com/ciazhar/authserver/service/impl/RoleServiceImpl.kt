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
        if(role.id!=null&&roleRepository.findOne(role.id)==null){
            return ResponseData<Objects>(status = "Update Failed",message = "ID Not Found")
        }
        return ResponseData(roleRepository.save(role))
    }

    override fun findAll(): ResponseData<*> {
        return ResponseData(roleRepository.findAll())
    }

    override fun findOne(id: String): ResponseData<*> {
        val role = roleRepository.findOne(id)?: return ResponseData<Objects>(status = "Update Failed",message = "ID Not Found")

        return ResponseData(role)
    }

    override fun delete(id: String): ResponseData<*> {
        val role = roleRepository.findOne(id)?: return ResponseData<Objects>(status = "Update Failed",message = "ID Not Found")

        roleRepository.delete(id)

        return ResponseData(role)
    }
}