package com.ciazhar.authserver.repository

import com.ciazhar.authserver.model.jpa.Role
import org.springframework.data.repository.PagingAndSortingRepository

/**
 * Created by ciazhar on 6/23/17.
 */
interface RoleRepository : PagingAndSortingRepository<Role, String>
