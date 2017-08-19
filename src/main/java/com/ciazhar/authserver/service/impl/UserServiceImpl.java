package com.ciazhar.authserver.service.impl;

import com.ciazhar.authserver.dto.request.*;
import com.ciazhar.authserver.dto.response.UploadPhotoData;
import com.ciazhar.authserver.dto.response.UserData;
import com.ciazhar.authserver.model.Photo;
import com.ciazhar.authserver.model.User;
import com.ciazhar.authserver.model.exception.AlreadyInUseException;
import com.ciazhar.authserver.model.exception.AuthException;
import com.ciazhar.authserver.repository.PhotoRepository;
import com.ciazhar.authserver.repository.RoleRepository;
import com.ciazhar.authserver.repository.UserRepository;
import com.ciazhar.authserver.service.UploadService;
import com.ciazhar.authserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by ciazhar on 6/21/17.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PhotoRepository photoRepository;
    @Autowired
    private UploadService uploadService;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void register(RegisterForm form) throws Exception{
        User user = new User(form, roleRepository.findOne("r002"), new Date());

        try {
            userRepository.save(user);
        }
        catch (Exception exc){
            throw new AlreadyInUseException();
        }
    }

    @Override
    public UserData updateProfile(ProfileForm form) throws Exception {
        User user = userRepository.findOne(form.getId());
        if (user == null) throw new AuthException();
        user.setProfileForm(form);
        userRepository.save(user);

        return new UserData(user);
    }

    @Override
    public void changePassword(ChangePasswordForm form) throws AuthException{
        User u = userRepository.findOne(form.getId());
        if (u == null) throw new AuthException();

        if (u.getPassword().equals(form.getOldPassword())){
            u.setPassword(form.getNewPassword());
        } else throw new AuthException();

        userRepository.save(u);

    }

    @Override
    public void changeUsername(ChangeUsernameForm form) throws AuthException, AlreadyInUseException {
        User user = userRepository.findOne(form.getId());
        if (user == null) throw new AuthException();
        user.setUsername(form.getUsername());
        userRepository.save(user);
    }

    @Override
    public void changeEmail(ChangeEmailForm form) throws AuthException, AlreadyInUseException {
        User user = userRepository.findOne(form.getId());
        if (user == null) throw new AuthException();
        user.setEmail(form.getEmail());
        userRepository.save(user);
    }

    @Override
    public void changeBirthdate(ChangeBirthDateForm form) throws AuthException {
        User user = userRepository.findOne(form.getId());
        if (user == null) throw new AuthException();
        user.setBirthDate(form.getBirthDate());
        userRepository.save(user);
    }

    @Override
    public void changePhone(ChangePhoneForm form) throws AuthException, AlreadyInUseException {
        User user = userRepository.findOne(form.getId());
        if (user == null) throw new AuthException();
        user.setPhoneNumber(form.getPhone());
        userRepository.save(user);
    }

    @Override
    public void changeRole(ChangeRoleForm form) throws AuthException {
        User user = userRepository.findOne(form.getId());
        if (user == null) throw new AuthException();
        user.setPhoneNumber(form.getRole());
        userRepository.save(user);
    }

    @Override
    public UploadPhotoData uploadAvatar(HttpServletRequest request, String id, MultipartFile photo) throws Exception {
        User user = userRepository.findOne(id);
        if (user == null) throw new Exception();

        Photo uploadPhoto = new Photo();
        uploadPhoto.setAccountId(user.getId());

        uploadPhoto.setUrl(uploadService.uploadAvatar(user.getId(), photo, request));
        photoRepository.save(uploadPhoto);

        Photo photoCheck = photoRepository.findByAccountIdAndCreatedDate(user.getId(), uploadPhoto.getCreatedDate());

        UploadPhotoData uploadPhotoData = new UploadPhotoData();
        uploadPhotoData.setUrl(uploadPhoto.getUrl());
        uploadPhotoData.setId(photoCheck.getId());

        return uploadPhotoData;
    }

    @Override
    public void changeAndroidDevice(ChangeAndroidDeviceForm form) throws AuthException {
        User user = userRepository.findOne(form.getId());
        if (user == null) throw new AuthException();
        user.setAndroidDeviceId(form.getAndroidDeviceId());
        userRepository.save(user);
    }
}
