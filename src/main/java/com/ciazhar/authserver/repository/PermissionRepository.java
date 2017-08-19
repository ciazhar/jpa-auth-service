package com.ciazhar.authserver.repository;

import com.ciazhar.authserver.model.Permission;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by ciazhar on 6/23/17.
 */

public interface PermissionRepository extends PagingAndSortingRepository<Permission,String> {

}
