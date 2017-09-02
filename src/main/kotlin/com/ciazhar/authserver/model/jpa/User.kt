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
class User {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id_user")
    var id: String? = null

    @Column(unique = true)
    var username: String? = null

    @NotNull
    var password: String? = null

    @NotNull
    @Column(columnDefinition = "boolean DEFAULT 1")
    var isEnabled: Boolean = false

    @Email
    @NotNull
    @Column(unique = true)
    var email: String? = null

    var phoneNumber: String? = null

    var avatar: String? = null

    var firstName: String? = null

    var lastName: String? = null

    @Temporal(TemporalType.DATE)
    var birthDate: Date? = null

    @NotNull
    @Temporal(TemporalType.DATE)
    var createdDate: Date? = null

    //    @NotNull
    var androidDeviceId: String? = null

    @ManyToOne
    @JoinColumn(name = "id_role", nullable = false)
    @NotNull
    var role: Role? = null

    constructor() {}

    constructor(username: String, password: String, enabled: Boolean, email: String, phoneNumber: String, avatar: String, firstName: String, lastName: String, birthDate: Date, createdDate: Date, androidDeviceId: String, role: Role) {
        this.username = username
        this.password = password
        this.isEnabled = enabled
        this.email = email
        this.phoneNumber = phoneNumber
        this.avatar = avatar
        this.firstName = firstName
        this.lastName = lastName
        this.birthDate = birthDate
        this.createdDate = createdDate
        this.androidDeviceId = androidDeviceId
        this.role = role
    }

    constructor(form: RegisterForm, role: Role, date: Date) {
        this.password = form.password
        this.email = form.email
        this.createdDate = date
        this.role = role
    }

    fun setProfileForm(profileForm: ProfileForm) {
        this.id = profileForm.id
        this.username = profileForm.username
        this.firstName = profileForm.firstname
        this.lastName = profileForm.lastname
        this.avatar = profileForm.avatar
        this.birthDate = profileForm.birthDate
        this.androidDeviceId = profileForm.androidDeviceId
        this.email = profileForm.email
        this.phoneNumber = profileForm.phone
        this.password = profileForm.password
    }

}
