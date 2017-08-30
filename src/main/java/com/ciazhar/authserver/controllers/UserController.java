package com.ciazhar.authserver.controllers;

import com.ciazhar.authserver.model.dto.request.*;
import com.ciazhar.authserver.model.dto.response.DefaultResponse;
import com.ciazhar.authserver.model.dto.response.ResponseData;
import com.ciazhar.authserver.model.dto.response.UploadPhotoData;
import com.ciazhar.authserver.model.dto.response.UserData;
import com.ciazhar.authserver.model.jpa.User;
import com.ciazhar.authserver.repository.UserRepository;
import com.ciazhar.authserver.service.EmailService;
import com.ciazhar.authserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by ciazhar on 5/27/17.
 */

/**
 * Service yang menyediakan API untuk user
 */
@RestController
@RequestMapping({"api/user","/mobile/user"})
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;

    /**
     * API untuk session user yang sedang login
     * @param authentication
     * @return
     */
    // @PreAuthorize("hasAuthority('SUPER_USER')")
    @PreAuthorize("permitAll()")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseData<Authentication> current(Authentication authentication) throws Exception{
        ResponseData<Authentication> responseData = new ResponseData<>();
        responseData.setData(authentication);
        return responseData;
    }

    /**
     * API untuk register without CSRF protection
     * @param form
     * @return
     */
    @PreAuthorize("permitAll()")
    @RequestMapping(method = RequestMethod.POST,value = "/register")
    public ResponseData<Object> register(@RequestBody @Valid RegisterForm form)throws Exception{
        ResponseData<Object> responseData = new ResponseData<>();
        userService.register(form);
        responseData.setData(emailService.sendEmail(form));
        return responseData;
    }

    /**
     * API untuk mencari user berdasarkan id user
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('SUPER_USER')")
    @RequestMapping(method = RequestMethod.GET, value = "/single")
    public ResponseData<User> findSingle(@RequestParam String id){
        ResponseData<User> responseData = new ResponseData<>();
        responseData.setData(userRepository.findOne(id));
        return responseData;
    }

    /**
     * API untuk melihat semua user
     * @return
     */
    @PreAuthorize("hasAuthority('SUPER_USER')")
    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public ResponseData<Iterable<User>> findAll() throws Exception{
        ResponseData<Iterable<User>> responseData = new ResponseData<>();
        responseData.setData(userRepository.findAll());
        return responseData;
    }

    /**
     * API untuk mengupdate profil
     * @param form
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAuthority('BASIC_USER')")
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public ResponseData<UserData> updateProfile(@Valid @RequestBody ProfileForm form) throws Exception {
        ResponseData<UserData> responseData = new ResponseData<>();
        responseData.setData(userService.updateProfile(form));
        return responseData;
    }

    /**
     * API untuk mengubah username
     * @param request
     * @param form
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAuthority('BASIC_USER')")
    @RequestMapping(method = RequestMethod.POST, value = "/change-username")
    public ResponseData<Object> changeUsername(HttpServletRequest request, @Valid @RequestBody ChangeUsernameForm form)
            throws Exception {
        userService.changeUsername(form);
        return DefaultResponse.success();
    }

    /**
     * API untuk mengubah nomor telephon
     * @param request
     * @param form
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAuthority('BASIC_USER')")
    @RequestMapping(method = RequestMethod.POST, value = "/change-phone")
    public ResponseData<Object> changePhone(HttpServletRequest request, @Valid @RequestBody ChangePhoneForm form)
            throws Exception {
        userService.changePhone(form);
        return DefaultResponse.success();
    }

    /**
     * API untuk mengubah tanggal lahir
     * @param request
     * @param form
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAuthority('BASIC_USER')")
    @RequestMapping(method = RequestMethod.POST, value = "/change-birthdate")
    public ResponseData<Object> changePBirthDate(HttpServletRequest request, @Valid @RequestBody ChangeBirthDateForm form)
            throws Exception {
        userService.changeBirthdate(form);
        return DefaultResponse.success();
    }

    /**
     * API untuk mengubah password
     * @param form
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAuthority('BASIC_USER')")
    @RequestMapping(method = RequestMethod.POST, value = "/change-password")
    public ResponseData<Object> changePassword(
            @Valid @RequestBody ChangePasswordForm form)
            throws Exception {
        userService.changePassword(form);
        return DefaultResponse.success();
    }

    /**
     * API untuk menguba email
     * @param request
     * @param form
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAuthority('BASIC_USER')")
    @RequestMapping(method = RequestMethod.POST, value = "/change-email")
    public ResponseData<Object> changeEmail(HttpServletRequest request, @Valid @RequestBody ChangeEmailForm form)
            throws Exception {
        userService.changeEmail(form);
        return DefaultResponse.success();
    }

    /**
     * API untuk mengubah role
     * @param form
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAuthority('SUPER_USER')")
    @RequestMapping(method = RequestMethod.POST, value = "change/role")
    public ResponseData<Object> changeRole(@Valid @RequestBody ChangeRoleForm form) throws Exception{
        userService.changeRole(form);
        return DefaultResponse.success();
    }

    /**
     * API untuk upload avatar
     * @param request
     * @param id
     * @param photo
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAuthority('BASIC_USER')")
    @RequestMapping(method = RequestMethod.POST, value = "/upload/avatar")
    public ResponseData<UploadPhotoData> uploadAvatar(HttpServletRequest request,
                                                      @RequestParam("id") String id,
                                                      @RequestParam("photo") MultipartFile photo) throws Exception {
        ResponseData<UploadPhotoData> responseData = new ResponseData<>();
        responseData.setData(userService.uploadAvatar(request,id,photo));
        return responseData;
    }

    /**
     * API untuk mengubah Android Hardware ID
     * @param request
     * @param form
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAuthority('BASIC_USER')")
    @RequestMapping(method = RequestMethod.POST, value = "/device/android")
    public ResponseData<Object> changeAndroidHardwareId(HttpServletRequest request, @Valid @RequestBody ChangeAndroidDeviceForm form)
            throws Exception {
        userService.changeAndroidDevice(form);
        return DefaultResponse.success();
    }

    /**
     * Delete User By ID
     * @param id
     * @return
     * @throws Exception
     */
    @PreAuthorize("hasAuthority('SUPER_USER')")
    @RequestMapping(method = RequestMethod.POST, value = "/delete")
    public ResponseData<Object> deleteAccount(@RequestBody String id)
            throws Exception {
        userRepository.delete(id);
        return DefaultResponse.success();
    }
}
