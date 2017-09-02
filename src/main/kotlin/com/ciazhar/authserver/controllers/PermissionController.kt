package com.ciazhar.authserver.controllers

import com.ciazhar.authserver.model.dto.response.DefaultResponse
import com.ciazhar.authserver.model.dto.response.ResponseData
import com.ciazhar.authserver.model.jpa.Permission
import com.ciazhar.authserver.repository.PermissionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

import javax.validation.Valid

/**
 * Created by ciazhar on 6/23/17.
 */

/**
 * Service yang menyediakan API permission
 */
@RestController
@RequestMapping("{api/permission,mobile/permission}")
class PermissionController {
    @Autowired
    private val permissionRepository: PermissionRepository? = null

    /**
     * API untuk membuat atau mengupdate permission
     * @param permission
     * @return
     */
    @PreAuthorize("hasAuthority('SUPER_USER')")
    @RequestMapping(method = arrayOf(RequestMethod.POST), value = "/create")
    fun createOrUpdatePermission(
            @Valid @RequestBody permission: Permission): ResponseData<Permission> {
        val responseData = ResponseData<Permission>()
        permissionRepository!!.save(permission)
        responseData.data = permission
        return responseData
    }

    /**
     * API untuk mencari permission berdasar id
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('SUPER_USER')")
    @RequestMapping(method = arrayOf(RequestMethod.GET), value = "/single")
    fun findSingle(@RequestParam id: String): ResponseData<Permission> {
        val responseData = ResponseData<Permission>()
        responseData.data = permissionRepository!!.findOne(id)
        return responseData
    }

    /**
     * API untuk mencari semua permission
     * @return
     */
    @PreAuthorize("hasAuthority('SUPER_USER')")
    @RequestMapping(method = arrayOf(RequestMethod.GET), value = "/all")
    fun findAll(): ResponseData<Iterable<Permission>> {
        val responseData = ResponseData<Iterable<Permission>>()
        responseData.data = permissionRepository!!.findAll()
        return responseData
    }

    /**
     * API untuk delete permission
     * @param id
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAuthority('SUPER_USER')")
    @RequestMapping(method = arrayOf(RequestMethod.POST), value = "/delete")
    @Throws(Exception::class)
    fun deletePermission(@RequestBody id: String): ResponseData<Any> {
        permissionRepository!!.delete(id)
        return DefaultResponse.success()
    }


}
