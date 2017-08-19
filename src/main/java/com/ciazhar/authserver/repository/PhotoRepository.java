package com.ciazhar.authserver.repository;

import com.ciazhar.authserver.model.Photo;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;

/**
 * Created by ciazhar on 6/21/17.
 */

public interface PhotoRepository extends PagingAndSortingRepository<Photo,String> {
    Photo findByAccountIdAndCreatedDate(String accountId, Date createdDate);
}
