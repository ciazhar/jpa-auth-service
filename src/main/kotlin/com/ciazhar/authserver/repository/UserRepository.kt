package com.ciazhar.authserver.repository

import com.ciazhar.authserver.model.jpa.User
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Created by ciazhar on 6/14/17.
 */

/**
 * Repository untuk user
 */
interface UserRepository : PagingAndSortingRepository<User, String> {
    fun findByEmail(email: String): User
}
