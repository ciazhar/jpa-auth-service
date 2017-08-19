package com.ciazhar.authserver.repository;

import com.ciazhar.authserver.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by ciazhar on 6/14/17.
 */

/**
 * Repository untuk user
 */
public interface UserRepository extends PagingAndSortingRepository<User, String>
{
    User findByEmail(String email);
}
