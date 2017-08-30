package com.ciazhar.authserver.controllers;

import com.ciazhar.authserver.model.dto.response.DefaultResponse;
import com.ciazhar.authserver.model.dto.response.ResponseData;
import com.ciazhar.authserver.model.jpa.Permission;
import com.ciazhar.authserver.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by ciazhar on 6/23/17.
 */

/**
 * Service yang menyediakan API permission
 */
@RestController
@RequestMapping("{api/permission,mobile/permission}")
public class PermissionController {
    @Autowired
    private PermissionRepository permissionRepository;

    /**
     * API untuk membuat atau mengupdate permission
     * @param permission
     * @return
     */
    @PreAuthorize("hasAuthority('SUPER_USER')")
    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseData<Permission> createOrUpdatePermission(
            @Valid @RequestBody Permission permission){
        ResponseData<Permission> responseData= new ResponseData<>();
        permissionRepository.save(permission);
        responseData.setData(permission);
        return responseData;
    }

    /**
     * API untuk mencari permission berdasar id
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('SUPER_USER')")
    @RequestMapping(method = RequestMethod.GET, value = "/single")
    public ResponseData<Permission> findSingle(@RequestParam String id){
        ResponseData<Permission> responseData =  new ResponseData<>();
        responseData.setData(permissionRepository.findOne(id));
        return responseData;
    }

    /**
     * API untuk mencari semua permission
     * @return
     */
    @PreAuthorize("hasAuthority('SUPER_USER')")
    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public ResponseData<Iterable<Permission>> findAll(){
        ResponseData<Iterable<Permission>> responseData = new ResponseData<>();
        responseData.setData(permissionRepository.findAll());
        return responseData;
    }

    /**
     * API untuk delete permission
     * @param id
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAuthority('SUPER_USER')")
    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    public ResponseData<Object> deletePermission(@RequestBody String id)
            throws Exception {
        permissionRepository.delete(id);
        return DefaultResponse.success();
    }


}
