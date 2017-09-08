package com.ciazhar.authserver.service.impl

import com.ciazhar.authserver.model.dto.request.*
import com.ciazhar.authserver.model.dto.response.ResponseData
import com.ciazhar.authserver.model.jpa.User
import com.ciazhar.authserver.repository.PhotoRepository
import com.ciazhar.authserver.repository.RoleRepository
import com.ciazhar.authserver.repository.UserRepository
import com.ciazhar.authserver.service.EmailService
import com.ciazhar.authserver.service.UploadService
import com.ciazhar.authserver.service.UserService
import org.springframework.data.domain.Pageable
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.ui.Model
import java.util.*

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

        if(userRepository.findByUsername(form.username)!=null){
            return ResponseData<Objects>(status = "Update Failed",message = "Username already exist. Try another username")
        }

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

        user.role = roleRepository.findOne(form.role)?: return ResponseData<Objects>(status = "Update Failed",message = "Role Not Found")
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
