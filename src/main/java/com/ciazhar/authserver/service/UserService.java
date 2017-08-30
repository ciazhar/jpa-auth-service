package com.ciazhar.authserver.service;

import com.ciazhar.authserver.model.dto.request.*;
import com.ciazhar.authserver.model.dto.response.UploadPhotoData;
import com.ciazhar.authserver.model.dto.response.UserData;
import com.ciazhar.authserver.model.exception.AlreadyInUseException;
import com.ciazhar.authserver.model.exception.AuthException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ciazhar on 6/21/17.
 */


public interface UserService {

    void register(RegisterForm form) throws Exception;

    UserData updateProfile(ProfileForm form) throws Exception;

    void changePassword(ChangePasswordForm form) throws AuthException;

    void changeUsername(ChangeUsernameForm form) throws AuthException, AlreadyInUseException;

    void changeEmail(ChangeEmailForm form) throws AuthException, AlreadyInUseException;

    void changeBirthdate(ChangeBirthDateForm form) throws AuthException;

    void changePhone(ChangePhoneForm form) throws AuthException, AlreadyInUseException;

    void changeRole(ChangeRoleForm form) throws AuthException;

    UploadPhotoData uploadAvatar(HttpServletRequest request, String id, MultipartFile photo) throws Exception;

    void changeAndroidDevice(ChangeAndroidDeviceForm form) throws AuthException;
}
