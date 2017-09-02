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
class Role {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id_role")
    var id: String? = null

    @Column(name = "nama_role", unique = true)
    @NotNull
    var nama: String? = null

    @Column(name = "label_role")
    @NotNull
    var label: String? = null

}
