package com.ciazhar.authserver.controllers

import com.ciazhar.authserver.model.dto.request.*
import com.ciazhar.authserver.model.dto.response.ResponseData
import com.ciazhar.authserver.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.annotation.Secured
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

/**
 * Created by ciazhar on 5/27/17.
 */

/**
 * Service yang menyediakan API untuk user
 */
@RestController
@RequestMapping("api/user", "/mobile/user")
class UserController @Autowired constructor(private val service: UserService){

    @PostMapping("/register")
    fun register(@RequestBody @Valid form: RegisterForm): ResponseData<*> {
        return service.register(form)
    }

    @PreAuthorize("permitAll()")
    @GetMapping
    fun current(authentication: Authentication): ResponseData<*> {
        return service.current(authentication)
    }

    @PreAuthorize("hasAuthority('SUPER_USER')")
    @RequestMapping("/all")
    fun findAll(): ResponseData<*> {
        return service.findAll()
    }

    @PreAuthorize("hasAuthority('SUPER_USER')")
    @GetMapping("/one")
    fun findSingle(@RequestParam id: String): ResponseData<*> {
        return service.findOne(id)
    }

    @PostMapping("/update")
    fun updateProfile(@Valid @RequestBody form: ProfileForm): ResponseData<*> {
        return service.updateProfile(form)
    }

    @PostMapping("/change-username")
    fun changeUsername(@Valid @RequestBody form: ChangeUsernameForm): ResponseData<*> {
        return service.changeUsername(form)
    }

    @PostMapping("/change-phone")
    fun changePhone(@Valid @RequestBody form: ChangePhoneForm): ResponseData<*> {
        return service.changePhone(form)
    }

    @PostMapping("/change-birthdate")
    fun changeBirthDate(@Valid @RequestBody form: ChangeBirthDateForm): ResponseData<*> {
        return service.changeBirthdate(form)
    }

    @PostMapping("/change-password")
    fun changePassword(@Valid @RequestBody form: ChangePasswordForm): ResponseData<*> {
        return service.changePassword(form)
    }

    @PostMapping("/change-email")
    fun changeEmail(@Valid @RequestBody form: ChangeEmailForm): ResponseData<*> {
        return service.changeEmail(form)
    }

    @PreAuthorize("hasAuthority('SUPER_USER')")
    @PostMapping("change/role")
    fun changeRole(@Valid @RequestBody form: ChangeRoleForm): ResponseData<*> {
        return service.changeRole(form)
    }


//    @PreAuthorize("hasAuthority('BASIC_USER')")
//    @RequestMapping(method = arrayOf(RequestMethod.POST), value = "/upload/avatar")
//    @Throws(Exception::class)
//    fun uploadAvatar(request: HttpServletRequest,
//                     @RequestParam("id") id: String,
//                     @RequestParam("photo") photo: MultipartFile): ResponseData<UploadPhotoData> {
//        val responseData = ResponseData<UploadPhotoData>()
//        responseData.data = userService!!.uploadAvatar(request, id, photo)
//        return responseData
//    }

    @PreAuthorize("hasAuthority('SUPER_USER')")
    @PostMapping("/delete")
    fun deleteAccount(@RequestParam id: String): ResponseData<*> {
        return service.delete(id)
    }
}
