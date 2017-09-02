package com.ciazhar.authserver.service.impl

import com.ciazhar.authserver.model.dto.request.*
import com.ciazhar.authserver.model.dto.response.UploadPhotoData
import com.ciazhar.authserver.model.dto.response.UserData
import com.ciazhar.authserver.model.jpa.Photo
import com.ciazhar.authserver.model.jpa.User
import com.ciazhar.authserver.model.exception.AlreadyInUseException
import com.ciazhar.authserver.model.exception.AuthException
import com.ciazhar.authserver.repository.PhotoRepository
import com.ciazhar.authserver.repository.RoleRepository
import com.ciazhar.authserver.repository.UserRepository
import com.ciazhar.authserver.service.UploadService
import com.ciazhar.authserver.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

import javax.servlet.http.HttpServletRequest
import java.util.Date

/**
 * Created by ciazhar on 6/21/17.
 */

@Service
class UserServiceImpl : UserService {

    @Autowired
    private val userRepository: UserRepository? = null
    @Autowired
    private val photoRepository: PhotoRepository? = null
    @Autowired
    private val uploadService: UploadService? = null
    @Autowired
    private val roleRepository: RoleRepository? = null

    @Throws(Exception::class)
    override fun register(form: RegisterForm) {
        val user = User(form, roleRepository!!.findOne("r002"), Date())

        try {
            userRepository!!.save(user)
        } catch (exc: Exception) {
            throw AlreadyInUseException()
        }

    }

    @Throws(Exception::class)
    override fun updateProfile(form: ProfileForm): UserData {
        val user = userRepository!!.findOne(form.id) ?: throw AuthException()
        user.setProfileForm(form)
        userRepository.save(user)

        return UserData(user)
    }

    @Throws(AuthException::class)
    override fun changePassword(form: ChangePasswordForm) {
        val u = userRepository!!.findOne(form.id) ?: throw AuthException()

        if (u.password == form.oldPassword) {
            u.password = form.newPassword
        } else
            throw AuthException()

        userRepository.save(u)

    }

    @Throws(AuthException::class, AlreadyInUseException::class)
    override fun changeUsername(form: ChangeUsernameForm) {
        val user = userRepository!!.findOne(form.id) ?: throw AuthException()
        user.username = form.username
        userRepository.save(user)
    }

    @Throws(AuthException::class, AlreadyInUseException::class)
    override fun changeEmail(form: ChangeEmailForm) {
        val user = userRepository!!.findOne(form.id) ?: throw AuthException()
        user.email = form.email
        userRepository.save(user)
    }

    @Throws(AuthException::class)
    override fun changeBirthdate(form: ChangeBirthDateForm) {
        val user = userRepository!!.findOne(form.id) ?: throw AuthException()
        user.birthDate = form.birthDate
        userRepository.save(user)
    }

    @Throws(AuthException::class, AlreadyInUseException::class)
    override fun changePhone(form: ChangePhoneForm) {
        val user = userRepository!!.findOne(form.id) ?: throw AuthException()
        user.phoneNumber = form.phone
        userRepository.save(user)
    }

    @Throws(AuthException::class)
    override fun changeRole(form: ChangeRoleForm) {
        val user = userRepository!!.findOne(form.id) ?: throw AuthException()
        user.phoneNumber = form.role
        userRepository.save(user)
    }

    @Throws(Exception::class)
    override fun uploadAvatar(request: HttpServletRequest, id: String, photo: MultipartFile): UploadPhotoData {
        val user = userRepository!!.findOne(id) ?: throw Exception()

        val uploadPhoto = Photo()
        uploadPhoto.accountId = user.id

        uploadPhoto.url = uploadService!!.uploadAvatar(user.id, photo, request)
        photoRepository!!.save(uploadPhoto)

        val photoCheck = photoRepository.findByAccountIdAndCreatedDate(user.id!!, uploadPhoto.createdDate!!)

        val uploadPhotoData = UploadPhotoData()
        uploadPhotoData.url = uploadPhoto.url
        uploadPhotoData.id = photoCheck.id

        return uploadPhotoData
    }

    @Throws(AuthException::class)
    override fun changeAndroidDevice(form: ChangeAndroidDeviceForm) {
        val user = userRepository!!.findOne(form.id) ?: throw AuthException()
        user.androidDeviceId = form.androidDeviceId
        userRepository.save(user)
    }
}
