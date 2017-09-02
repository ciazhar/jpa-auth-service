package com.ciazhar.authserver.service.impl

import com.ciazhar.authserver.config.string.ErrorMessage
import com.ciazhar.authserver.model.dto.request.*
import com.ciazhar.authserver.model.dto.response.ResponseData
import com.ciazhar.authserver.model.dto.response.UploadPhotoData
import com.ciazhar.authserver.model.dto.response.UserData
import com.ciazhar.authserver.model.jpa.Photo
import com.ciazhar.authserver.model.jpa.User
import com.ciazhar.authserver.model.exception.AlreadyInUseException
import com.ciazhar.authserver.model.exception.AuthException
import com.ciazhar.authserver.repository.PhotoRepository
import com.ciazhar.authserver.repository.RoleRepository
import com.ciazhar.authserver.repository.UserRepository
import com.ciazhar.authserver.service.EmailService
import com.ciazhar.authserver.service.UploadService
import com.ciazhar.authserver.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import org.springframework.ui.Model
import org.springframework.web.multipart.MultipartFile
import java.util.*

import javax.servlet.http.HttpServletRequest

/**
 * Created by ciazhar on 6/21/17.
 */

@Service
class UserServiceImpl (private val userRepository: UserRepository,
                       private val photoRepository: PhotoRepository,
                       private val uploadService: UploadService,
                       private val roleRepository: RoleRepository,
                       private val emailService: EmailService
                       ): UserService{

    override fun register(form: RegisterForm) :ResponseData<*> {
        try {
            userRepository.save(User(form))
            emailService.sendEmail(form)
        } catch (e: Exception) {
            return ResponseData<Objects>(status = "Register Failed",message = e.message)
        }
        return ResponseData(form)
    }

    override fun current(authentication: Authentication): ResponseData<*> {
        try {
            return ResponseData(authentication)
        } catch (e: Exception) {
            return ResponseData<Objects>(status = ErrorMessage.REQUEST,message = e.message)
        }
    }

    override fun activate(email: String, model: Model): String {
        try{
            val user = userRepository.findByEmail(email)
            model.addAttribute("email", user.email)
            user.enabled = true
            userRepository.save(user)
        }
        catch(e: Exception){
            "/error"
        }
        return "/activate"
    }

    override fun findAll(): ResponseData<*> {
        try {
            return ResponseData(userRepository.findAll())
        } catch (e: Exception) {
            return ResponseData<Objects>(status = ErrorMessage.REQUEST,message = e.message)
        }
    }

    override fun findOne(id: String): ResponseData<*> {
        try {
            return ResponseData(userRepository.findOne(id))
        } catch (e: Exception) {
            return ResponseData<Objects>(status = ErrorMessage.REQUEST,message = e.message)
        }
    }

    override fun updateProfile(form: ProfileForm): ResponseData<*>{
        try {
            userRepository.save(User(form))
        } catch (e: Exception) {
            return ResponseData<Objects>(status = "Update Failed",message = e.message)
        }
        return ResponseData(form)
    }

    override fun changePassword(form: ChangePasswordForm) : ResponseData<*>{
        try {
            val u = userRepository.findOne(form.id)
            if (u.password == form.oldPassword) {
                u.password = form.newPassword
            }
            else{
                return ResponseData<Objects>(status = "Update Failed",message = "Old Password Not Same")
            }
            userRepository.save(u)
        } catch (e: Exception) {
            return ResponseData<Objects>(status = "Update Failed",message = e.message)
        }
        return ResponseData(form)
    }

    override fun changeUsername(form: ChangeUsernameForm) : ResponseData<*>{
        try {
            val user = userRepository.findOne(form.id)
            user.username = form.username
            userRepository.save(user)
        } catch (e: Exception) {
            return ResponseData<Objects>(status = "Update Failed",message = e.message)
        }
        return ResponseData(form)

    }

    override fun changeEmail(form: ChangeEmailForm) : ResponseData<*>{
        try {
            val user = userRepository.findOne(form.id)
            user.email = form.email
            userRepository.save(user)
        } catch (e: Exception) {
            return ResponseData<Objects>(status = "Update Failed",message = e.message)
        }
        return ResponseData(form)
    }


    override fun changeBirthdate(form: ChangeBirthDateForm) : ResponseData<*>{
        try {
            val user = userRepository.findOne(form.id)
            user.dateOfBirth = form.birthDate
            userRepository.save(user)
        } catch (e: Exception) {
            return ResponseData<Objects>(status = "Update Failed",message = e.message)
        }
        return ResponseData(form)
    }

    override fun changePhone(form: ChangePhoneForm) : ResponseData<*>{
        try {
            val user = userRepository.findOne(form.id)
            user.phoneNumber = form.phone
            userRepository.save(user)
        } catch (e: Exception) {
            return ResponseData<Objects>(status = "Update Failed",message = e.message)
        }
        return ResponseData(form)
    }

    override fun changeRole(form: ChangeRoleForm) : ResponseData<*>{
        try {
            val user = userRepository.findOne(form.id)
            user.role = roleRepository.findByNama(form.role)
            userRepository.save(user)
        } catch (e: Exception) {
            return ResponseData<Objects>(status = "Update Failed",message = e.message)
        }
        return ResponseData(form)
    }

    //    override fun uploadAvatar(request: HttpServletRequest, id: String?=null, photo: MultipartFile): UploadPhotoData {
//        val user = userRepository.findOne(id) ?: throw Exception()
//
//        val uploadPhoto = Photo()
//        uploadPhoto.accountId = user.id
//
//        uploadPhoto.url = uploadService.uploadAvatar(user.id, photo, request)

//        photoRepository.save(uploadPhoto)
//
//        val photoCheck = photoRepository.findByAccountIdAndCreatedDate(user.id, uploadPhoto.createdDate)
//
//        val uploadPhotoData = UploadPhotoData()
//        uploadPhotoData.url = uploadPhoto.url
//        uploadPhotoData.id = photoCheck.id
//
//        return uploadPhotoData
//    }

    override fun changeAndroidDevice(form: ChangeAndroidDeviceForm) : ResponseData<*>{
        try {
            val user = userRepository.findOne(form.id)
            user.androidDeviceId = form.androidDeviceId
            userRepository.save(user)
        } catch (e: Exception) {
            return ResponseData<Objects>(status = "Update Failed",message = e.message)
        }
        return ResponseData(form)
    }

    override fun delete(id: String): ResponseData<*> {
        try {
            return ResponseData(userRepository.delete(id))
        } catch (e: Exception) {
            return ResponseData<Objects>(status = "Update Failed",message = e.message)
        }
    }
}
