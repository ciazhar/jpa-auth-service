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
import org.springframework.data.domain.Pageable
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
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
        val encoder = BCryptPasswordEncoder()
        val encodedValue = encoder.encode(form.password)

        form.password = encodedValue
        userRepository.save(User(form))
        emailService.sendEmail(form)

        return ResponseData(form)
    }

    override fun current(authentication: Authentication): ResponseData<*> {
        return ResponseData(authentication)
    }

    override fun activate(email: String, model: Model): String {
        val user = userRepository.findByEmail(email)

        model.addAttribute("email", user.email)
        user.enabled = true
        userRepository.save(user)

        return "/activate"
    }

    override fun findAll(page : Pageable): ResponseData<*> {
        return ResponseData(userRepository.findAll(page))
    }

    override fun findOne(id: String): ResponseData<*> {
        val user = userRepository.findOne(id)?: return ResponseData<Objects>(status = "Update Failed",message = "ID Not Found")

        return ResponseData(user)
    }

    override fun updateProfile(form: ProfileForm): ResponseData<*>{
        val encoder = BCryptPasswordEncoder()
        val encodedValue = encoder.encode(form.password)

        form.password = encodedValue
        val user = User(form)
        userRepository.save(user)

        return ResponseData(user)
    }

    override fun changePassword(form: ChangePasswordForm) : ResponseData<*>{
        val encoder = BCryptPasswordEncoder()
        val user = userRepository.findOne(form.id) ?: return ResponseData<Objects>(status = "Update Failed",message = "ID Not Found")

        if (encoder.matches(form.oldPassword,user.password)) {
            val encodedValue = encoder.encode(form.newPassword)
            user.password = encodedValue
            userRepository.save(user)
            return ResponseData(user)
        }
        else{
            return ResponseData<Objects>(status = "Update Failed",message = "Old Password Not Same")
        }
    }

    override fun changeUsername(form: ChangeUsernameForm) : ResponseData<*>{
        val user = userRepository.findOne(form.id)?: return ResponseData<Objects>(status = "Update Failed",message = "ID Not Found")

        user.username = form.username
        userRepository.save(user)

        return ResponseData(user)
    }

    override fun changeEmail(form: ChangeEmailForm) : ResponseData<*>{
        val user = userRepository.findOne(form.id)?: return ResponseData<Objects>(status = "Update Failed",message = "ID Not Found")

        user.email = form.email
        userRepository.save(user)

        return ResponseData(user)
    }


    override fun changeBirthdate(form: ChangeBirthDateForm) : ResponseData<*>{
        val user = userRepository.findOne(form.id)?: return ResponseData<Objects>(status = "Update Failed",message = "ID Not Found")

        user.dateOfBirth = form.birthDate
        userRepository.save(user)

        return ResponseData(user)
    }

    override fun changePhone(form: ChangePhoneForm) : ResponseData<*>{
        val user = userRepository.findOne(form.id)?: return ResponseData<Objects>(status = "Update Failed",message = "ID Not Found")

        user.phoneNumber = form.phone
        userRepository.save(user)

        return ResponseData(user)
    }

    override fun changeRole(form: ChangeRoleForm) : ResponseData<*>{
        val user = userRepository.findOne(form.id)?: return ResponseData<Objects>(status = "Update Failed",message = "ID Not Found")

        user.role = roleRepository.findByNama(form.role)
        userRepository.save(user)

        return ResponseData(user)
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

    override fun delete(id: String): ResponseData<*> {
        val user = userRepository.findOne(id)?: return ResponseData<Objects>(status = "Update Failed",message = "ID Not Found")

        userRepository.delete(id)

        return ResponseData(user)
    }
}
