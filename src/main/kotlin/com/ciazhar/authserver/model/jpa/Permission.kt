package com.ciazhar.authserver.model.jpa

import org.hibernate.annotations.GenericGenerator

import javax.persistence.*
import javax.validation.constraints.NotNull

/**
 * Created by ciazhar on 5/27/17.
 */


/**
 * Model untuk permmision
 */
@Entity
data class Permission (
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @field:Column(name = "id_permission")
    var id: String? = null,

    @field:ManyToOne
    @field:JoinColumn(name = "id_role")
    var id_role: Role? = null,

    @field:NotNull
    @field:Column(name = "nama_permission")
    var nama: String? = null,

    @field:NotNull
    @field:Column(name = "label_permission")
    var label: String? = null
)
