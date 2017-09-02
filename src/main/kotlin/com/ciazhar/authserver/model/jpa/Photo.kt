package com.ciazhar.authserver.model.jpa


import org.hibernate.annotations.GenericGenerator
import javax.persistence.Entity
import javax.persistence.Id
import java.util.Date
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.validation.constraints.NotNull

/**
 * Created by ciazhar on 6/21/17.
 */

@Entity
data class Photo (

        @Id
        @GeneratedValue(generator = "uuid")
        @GenericGenerator(name = "uuid", strategy = "uuid2")
        @field:Column(name = "id_permission")
        var id: String? = null,

        @field:NotNull
        var accountId: String? = null,

        @field:NotNull
        var createdDate: Date? = null,

        @field:NotNull
        var url: String? = null
){
    init {
        this.createdDate = Date()
    }
}
