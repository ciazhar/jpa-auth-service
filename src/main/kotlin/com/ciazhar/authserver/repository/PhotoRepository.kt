package com.ciazhar.authserver.repository

import com.ciazhar.authserver.model.jpa.Photo
import org.springframework.data.repository.PagingAndSortingRepository

import java.util.Date

/**
 * Created by ciazhar on 6/21/17.
 */

interface PhotoRepository : PagingAndSortingRepository<Photo, String> {
    fun findByAccountIdAndCreatedDate(accountId: String, createdDate: Date): Photo
}
