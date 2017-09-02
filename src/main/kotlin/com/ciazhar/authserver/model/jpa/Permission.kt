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
class Permission {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id_permission")
    var id: String? = null

    @ManyToOne
    @JoinColumn(name = "id_role")
    var id_role: Role? = null

    @NotNull
    @Column(name = "nama_permission")
    var nama: String? = null

    @NotNull
    @Column(name = "label_permission")
    var label: String? = null
}
