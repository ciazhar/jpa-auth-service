package com.ciazhar.authserver.model.jpa

import org.hibernate.annotations.GenericGenerator

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.NotNull

/**
 * Created by ciazhar on 5/27/17.
 */

/**
 * Model untuk role
 */
@Entity
data class Role (

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @field:Column(name = "id_role")
    var id: String? = null,

    @field:Column(name = "nama_role", unique = true)
    @field:NotNull
    var nama: String? = null,

    @field:Column(name = "label_role")
    @field:NotNull
    var label: String? = null

)
