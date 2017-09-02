package com.ciazhar.authserver.service

import com.ciazhar.authserver.model.dto.request.*
import com.ciazhar.authserver.model.dto.response.UploadPhotoData
import com.ciazhar.authserver.model.dto.response.UserData
import com.ciazhar.authserver.model.exception.AlreadyInUseException
import com.ciazhar.authserver.model.exception.AuthException
import org.springframework.web.multipart.MultipartFile

import javax.servlet.http.HttpServletRequest

/**
 * Created by ciazhar on 6/21/17.
 */


interface UserService {

    @Throws(Exception::class)
    fun register(form: RegisterForm)

    @Throws(Exception::class)
    fun updateProfile(form: ProfileForm): UserData

    @Throws(AuthException::class)
    fun changePassword(form: ChangePasswordForm)

    @Throws(AuthException::class, AlreadyInUseException::class)
    fun changeUsername(form: ChangeUsernameForm)

    @Throws(AuthException::class, AlreadyInUseException::class)
    fun changeEmail(form: ChangeEmailForm)

    @Throws(AuthException::class)
    fun changeBirthdate(form: ChangeBirthDateForm)

    @Throws(AuthException::class, AlreadyInUseException::class)
    fun changePhone(form: ChangePhoneForm)

    @Throws(AuthException::class)
    fun changeRole(form: ChangeRoleForm)

    @Throws(Exception::class)
    fun uploadAvatar(request: HttpServletRequest, id: String, photo: MultipartFile): UploadPhotoData

    @Throws(AuthException::class)
    fun changeAndroidDevice(form: ChangeAndroidDeviceForm)
}
