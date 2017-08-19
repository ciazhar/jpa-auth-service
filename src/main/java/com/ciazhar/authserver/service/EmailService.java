package com.ciazhar.authserver.service;

import com.ciazhar.authserver.dto.request.RegisterForm;
import com.ciazhar.authserver.util.email.EmailStatus;

public interface EmailService{

    EmailStatus sendEmail(RegisterForm form);
}