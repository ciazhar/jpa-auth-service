package com.ciazhar.authserver.controllers;

import com.ciazhar.authserver.dto.response.DefaultResponse;
import com.ciazhar.authserver.dto.response.ResponseData;
import com.ciazhar.authserver.model.Role;
import com.ciazhar.authserver.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by ciazhar on 6/23/17.
 */

/**
 * Service yang menyediakan API role
 */
@RestController
@RequestMapping({"api/role","mobile/role"})
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    /**
     * API untuk membuat atau mengupdate role
     * @param role
     * @return
     */
    @PreAuthorize("hasAuthority('SUPER_USER')")
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResponseData<Role> createRole(@Valid @RequestBody Role role) throws Exception{
        ResponseData<Role> responseData= new ResponseData<>();
        roleRepository.save(role);
        responseData.setData(role);
        return responseData;
    }

    /**
     * API untuk mencari role berdasarkan id
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('SUPER_USER')")
    @RequestMapping(value = "/single", method = RequestMethod.GET)
    public ResponseData<Role> findSingle(@RequestParam String id) throws Exception{
        ResponseData<Role> responseData =  new ResponseData<>();
        responseData.setData(roleRepository.findOne(id));
        return responseData;
    }

    /**
     * API untuk mencari semua role
     * @return
     */
    @PreAuthorize("hasAuthority('SUPER_USER')")
    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public ResponseData<Iterable<Role>> findAll() throws Exception{
        ResponseData<Iterable<Role>> responseData = new ResponseData<>();
        responseData.setData(roleRepository.findAll());
        return responseData;
    }

    /**
     * API untuk menghapus role
     * @param id
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAuthority('SUPER_USER')")
    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    public ResponseData<Object> deleteRole(@RequestBody String id)
            throws Exception {
        roleRepository.delete(id);
        return DefaultResponse.success();
    }

}
