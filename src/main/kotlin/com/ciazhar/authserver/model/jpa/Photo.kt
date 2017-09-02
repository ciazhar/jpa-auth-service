package com.ciazhar.authserver.model.jpa


import javax.persistence.Entity
import javax.persistence.Id
import java.util.Date

/**
 * Created by ciazhar on 6/21/17.
 */

@Entity
class Photo {

    @Id
    var id: String? = null
    var accountId: String? = null
    var createdDate: Date? = null
    var url: String? = null

    init {
        this.createdDate = Date()
    }
}
