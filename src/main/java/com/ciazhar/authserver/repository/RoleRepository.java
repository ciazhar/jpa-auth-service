package com.ciazhar.authserver.repository;

import com.ciazhar.authserver.model.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by ciazhar on 6/23/17.
 */
public interface RoleRepository extends PagingAndSortingRepository<Role,String> {
}
