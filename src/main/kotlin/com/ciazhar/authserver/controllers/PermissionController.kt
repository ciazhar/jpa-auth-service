package com.ciazhar.authserver.controllers

import com.ciazhar.authserver.model.dto.request.PermissionForm
import com.ciazhar.authserver.model.dto.response.DefaultResponse
import com.ciazhar.authserver.model.dto.response.ResponseData
import com.ciazhar.authserver.model.jpa.Permission
import com.ciazhar.authserver.repository.PermissionRepository
import com.ciazhar.authserver.service.PermissionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.*

import javax.validation.Valid

/**
 * Created by ciazhar on 6/23/17.
 */

/**
 * Service yang menyediakan API permission
 */
@RestController
@RequestMapping("api/permission","mobile/permission")
class PermissionController (val service : PermissionService){

    @PreAuthorize("hasAuthority('SUPER_USER')")
    @PostMapping("/save")
    fun save(@Valid @RequestBody permission: PermissionForm): ResponseData<*> {
        return service.save(permission)
    }

    @PreAuthorize("hasAuthority('SUPER_USER')")
    @GetMapping("/one")
    fun findOne(@RequestParam id: String): ResponseData<*> {
        return service.findOne(id)
    }

    @PreAuthorize("hasAuthority('SUPER_USER')")
    @GetMapping("/all")
    fun findAll(): ResponseData<*> {
        return service.findAll()
    }

    @PreAuthorize("hasAuthority('SUPER_USER')")
    @PostMapping("/delete")
    fun deletePermission(@RequestParam id: String): ResponseData<*> {
        return service.delete(id)
    }

}
