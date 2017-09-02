package com.ciazhar.authserver.controllers

import com.ciazhar.authserver.model.dto.response.DefaultResponse
import com.ciazhar.authserver.model.dto.response.ResponseData
import com.ciazhar.authserver.model.jpa.Role
import com.ciazhar.authserver.repository.RoleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

import javax.validation.Valid

/**
 * Created by ciazhar on 6/23/17.
 */

/**
 * Service yang menyediakan API role
 */
@RestController
@RequestMapping("api/role", "mobile/role")
class RoleController {

    @Autowired
    private val roleRepository: RoleRepository? = null

    /**
     * API untuk membuat atau mengupdate role
     * @param role
     * @return
     */
    @PreAuthorize("hasAuthority('SUPER_USER')")
    @RequestMapping(value = "/create", method = arrayOf(RequestMethod.POST))
    @Throws(Exception::class)
    fun createRole(@Valid @RequestBody role: Role): ResponseData<Role> {
        val responseData = ResponseData<Role>()
        roleRepository!!.save(role)
        responseData.data = role
        return responseData
    }

    /**
     * API untuk mencari role berdasarkan id
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('SUPER_USER')")
    @RequestMapping(value = "/single", method = arrayOf(RequestMethod.GET))
    @Throws(Exception::class)
    fun findSingle(@RequestParam id: String): ResponseData<Role> {
        val responseData = ResponseData<Role>()
        responseData.data = roleRepository!!.findOne(id)
        return responseData
    }

    /**
     * API untuk mencari semua role
     * @return
     */
    @PreAuthorize("hasAuthority('SUPER_USER')")
    @RequestMapping(method = arrayOf(RequestMethod.GET), value = "/all")
    @Throws(Exception::class)
    fun findAll(): ResponseData<Iterable<Role>> {
        val responseData = ResponseData<Iterable<Role>>()
        responseData.data = roleRepository!!.findAll()
        return responseData
    }

    /**
     * API untuk menghapus role
     * @param id
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAuthority('SUPER_USER')")
    @RequestMapping(method = arrayOf(RequestMethod.POST), value = "/delete")
    @Throws(Exception::class)
    fun deleteRole(@RequestBody id: String): ResponseData<Any> {
        roleRepository!!.delete(id)
        return DefaultResponse.success()
    }

}
