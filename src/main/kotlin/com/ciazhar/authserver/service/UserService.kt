package com.ciazhar.authserver.service

import com.ciazhar.authserver.model.dto.request.*
import com.ciazhar.authserver.model.dto.response.ResponseData
import com.ciazhar.authserver.model.dto.response.UploadPhotoData
import com.ciazhar.authserver.model.dto.response.UserData
import com.ciazhar.authserver.model.exception.AlreadyInUseException
import com.ciazhar.authserver.model.exception.AuthException
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.ui.Model
import org.springframework.web.multipart.MultipartFile

import javax.servlet.http.HttpServletRequest

/**
 * Created by ciazhar on 6/21/17.
 */


interface UserService {

    fun register(form: RegisterForm) : ResponseData<*>

    fun activate(email : String, model : Model):String

    fun current(authentication : Authentication) : ResponseData<*>

    fun findAll() : ResponseData<*>

    fun findOne(id : String) : ResponseData<*>

    fun updateProfile(form: ProfileForm): ResponseData<*>

    fun changePassword(form: ChangePasswordForm) : ResponseData<*>

    fun changeUsername(form: ChangeUsernameForm) : ResponseData<*>

    fun changeEmail(form: ChangeEmailForm) : ResponseData<*>

    fun changeBirthdate(form: ChangeBirthDateForm) : ResponseData<*>

    fun changePhone(form: ChangePhoneForm) : ResponseData<*>

    fun changeRole(form: ChangeRoleForm) : ResponseData<*>

//    fun uploadAvatar(request: HttpServletRequest, id: String, photo: MultipartFile): UploadPhotoData

    fun delete(id : String) : ResponseData<*>
}
