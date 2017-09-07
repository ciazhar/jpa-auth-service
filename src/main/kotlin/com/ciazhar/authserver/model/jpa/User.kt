package com.ciazhar.authserver.model.jpa

import com.ciazhar.authserver.model.dto.request.ProfileForm
import com.ciazhar.authserver.model.dto.request.RegisterForm
import org.hibernate.annotations.GenericGenerator
import org.hibernate.validator.constraints.Email

import javax.persistence.*
import javax.validation.constraints.NotNull
import java.util.Date

/**
 * Created by ciazhar on 5/27/17.
 */

/**
 * Model untuk user
 */
@Entity
data class User (
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @field:Column(name = "id_user", length = 50)
    var id: String? = null,

    @field:NotNull
    @field:Column(columnDefinition = "boolean DEFAULT 0")
    var enabled: Boolean = false,

    @field:NotNull
    @field:Temporal(TemporalType.DATE)
    var dateCreated: Date? = null,

    @field:ManyToOne
    @field:JoinColumn(name = "id_role", nullable = false)
    @field:NotNull
    var role: Role? = null,


    @field:Column(unique = true, length = 30)
    @field:NotNull
    var username: String? = null,

    @field:Email
    @field:NotNull
    @field:Column(unique = true, length = 30)
    var email: String? = null,

    @field:NotNull
    @field:Column(length = 100)
    var password: String? = null,



    @field:Column(length = 20)
    var firstName: String? = null,

    @field:Column(length = 20)
    var lastName: String? = null,

    @field:Temporal(TemporalType.DATE)
    var dateOfBirth: Date? = null,

    @field:Column(length = 12)
    var phoneNumber: String? = null,

    var avatar: String? = null


){
    init {
        enabled = false
        dateCreated = Date()
        role = Role("2", "BASIC_USER", "Basic User")
    }

    constructor(registerForm: RegisterForm) : this(null,false,null,null,null,null,null,null,null,null,null,null){
        this.username = registerForm.username
        this.email = registerForm.email
        this.password = registerForm.password
    }

    constructor(profileForm: ProfileForm) : this(null,false,null,null,null,null,null,null,null,null,null,null){
        this.id = profileForm.id
        this.username = profileForm.username
        this.email = profileForm.email
        this.password = profileForm.password
        this.firstName = profileForm.firstName
        this.lastName = profileForm.lastName
        this.dateOfBirth = profileForm.dateOfBirth
        this.avatar = profileForm.avatar
    }

}