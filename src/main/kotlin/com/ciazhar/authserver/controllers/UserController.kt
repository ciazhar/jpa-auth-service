package com.ciazhar.authserver.controllers

import com.ciazhar.authserver.model.dto.request.*
import com.ciazhar.authserver.model.dto.response.DefaultResponse
import com.ciazhar.authserver.model.dto.response.ResponseData
import com.ciazhar.authserver.model.dto.response.UploadPhotoData
import com.ciazhar.authserver.model.dto.response.UserData
import com.ciazhar.authserver.model.jpa.User
import com.ciazhar.authserver.repository.UserRepository
import com.ciazhar.authserver.service.EmailService
import com.ciazhar.authserver.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

/**
 * Created by ciazhar on 5/27/17.
 */

/**
 * Service yang menyediakan API untuk user
 */
@RestController
@RequestMapping("api/user", "/mobile/user")
class UserController {

    @Autowired
    private val userRepository: UserRepository? = null
    @Autowired
    private val userService: UserService? = null
    @Autowired
    private val emailService: EmailService? = null

    /**
     * API untuk session user yang sedang login
     * @param authentication
     * @return
     */
    // @PreAuthorize("hasAuthority('SUPER_USER')")
    @PreAuthorize("permitAll()")
    @RequestMapping(method = arrayOf(RequestMethod.GET))
    @Throws(Exception::class)
    fun current(authentication: Authentication): ResponseData<Authentication> {
        val responseData = ResponseData<Authentication>()
        responseData.data = authentication
        return responseData
    }

    /**
     * API untuk register without CSRF protection
     * @param form
     * @return
     */
    @PreAuthorize("permitAll()")
    @RequestMapping(method = arrayOf(RequestMethod.POST), value = "/register")
    @Throws(Exception::class)
    fun register(@RequestBody @Valid form: RegisterForm): ResponseData<Any> {
        val responseData = ResponseData<Any>()
        userService!!.register(form)
        responseData.data = emailService!!.sendEmail(form)
        return responseData
    }

    /**
     * API untuk mencari user berdasarkan id user
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('SUPER_USER')")
    @RequestMapping(method = arrayOf(RequestMethod.GET), value = "/single")
    fun findSingle(@RequestParam id: String): ResponseData<User> {
        val responseData = ResponseData<User>()
        responseData.data = userRepository!!.findOne(id)
        return responseData
    }

    /**
     * API untuk melihat semua user
     * @return
     */
    @PreAuthorize("hasAuthority('SUPER_USER')")
    @RequestMapping(method = arrayOf(RequestMethod.GET), value = "/all")
    @Throws(Exception::class)
    fun findAll(): ResponseData<Iterable<User>> {
        val responseData = ResponseData<Iterable<User>>()
        responseData.data = userRepository!!.findAll()
        return responseData
    }

    /**
     * API untuk mengupdate profil
     * @param form
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAuthority('BASIC_USER')")
    @RequestMapping(method = arrayOf(RequestMethod.POST), value = "/update")
    @Throws(Exception::class)
    fun updateProfile(@Valid @RequestBody form: ProfileForm): ResponseData<UserData> {
        val responseData = ResponseData<UserData>()
        responseData.data = userService!!.updateProfile(form)
        return responseData
    }

    /**
     * API untuk mengubah username
     * @param request
     * @param form
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAuthority('BASIC_USER')")
    @RequestMapping(method = arrayOf(RequestMethod.POST), value = "/change-username")
    @Throws(Exception::class)
    fun changeUsername(request: HttpServletRequest, @Valid @RequestBody form: ChangeUsernameForm): ResponseData<Any> {
        userService!!.changeUsername(form)
        return DefaultResponse.success()
    }

    /**
     * API untuk mengubah nomor telephon
     * @param request
     * @param form
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAuthority('BASIC_USER')")
    @RequestMapping(method = arrayOf(RequestMethod.POST), value = "/change-phone")
    @Throws(Exception::class)
    fun changePhone(request: HttpServletRequest, @Valid @RequestBody form: ChangePhoneForm): ResponseData<Any> {
        userService!!.changePhone(form)
        return DefaultResponse.success()
    }

    /**
     * API untuk mengubah tanggal lahir
     * @param request
     * @param form
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAuthority('BASIC_USER')")
    @RequestMapping(method = arrayOf(RequestMethod.POST), value = "/change-birthdate")
    @Throws(Exception::class)
    fun changePBirthDate(request: HttpServletRequest, @Valid @RequestBody form: ChangeBirthDateForm): ResponseData<Any> {
        userService!!.changeBirthdate(form)
        return DefaultResponse.success()
    }

    /**
     * API untuk mengubah password
     * @param form
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAuthority('BASIC_USER')")
    @RequestMapping(method = arrayOf(RequestMethod.POST), value = "/change-password")
    @Throws(Exception::class)
    fun changePassword(
            @Valid @RequestBody form: ChangePasswordForm): ResponseData<Any> {
        userService!!.changePassword(form)
        return DefaultResponse.success()
    }

    /**
     * API untuk menguba email
     * @param request
     * @param form
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAuthority('BASIC_USER')")
    @RequestMapping(method = arrayOf(RequestMethod.POST), value = "/change-email")
    @Throws(Exception::class)
    fun changeEmail(request: HttpServletRequest, @Valid @RequestBody form: ChangeEmailForm): ResponseData<Any> {
        userService!!.changeEmail(form)
        return DefaultResponse.success()
    }

    /**
     * API untuk mengubah role
     * @param form
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAuthority('SUPER_USER')")
    @RequestMapping(method = arrayOf(RequestMethod.POST), value = "change/role")
    @Throws(Exception::class)
    fun changeRole(@Valid @RequestBody form: ChangeRoleForm): ResponseData<Any> {
        userService!!.changeRole(form)
        return DefaultResponse.success()
    }

    /**
     * API untuk upload avatar
     * @param request
     * @param id
     * @param photo
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAuthority('BASIC_USER')")
    @RequestMapping(method = arrayOf(RequestMethod.POST), value = "/upload/avatar")
    @Throws(Exception::class)
    fun uploadAvatar(request: HttpServletRequest,
                     @RequestParam("id") id: String,
                     @RequestParam("photo") photo: MultipartFile): ResponseData<UploadPhotoData> {
        val responseData = ResponseData<UploadPhotoData>()
        responseData.data = userService!!.uploadAvatar(request, id, photo)
        return responseData
    }

    /**
     * API untuk mengubah Android Hardware ID
     * @param request
     * @param form
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAuthority('BASIC_USER')")
    @RequestMapping(method = arrayOf(RequestMethod.POST), value = "/device/android")
    @Throws(Exception::class)
    fun changeAndroidHardwareId(request: HttpServletRequest, @Valid @RequestBody form: ChangeAndroidDeviceForm): ResponseData<Any> {
        userService!!.changeAndroidDevice(form)
        return DefaultResponse.success()
    }

    /**
     * Delete User By ID
     * @param id
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAuthority('SUPER_USER')")
    @RequestMapping(method = arrayOf(RequestMethod.POST), value = "/delete")
    @Throws(Exception::class)
    fun deleteAccount(@RequestBody id: String): ResponseData<Any> {
        userRepository!!.delete(id)
        return DefaultResponse.success()
    }
}
