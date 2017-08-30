package com.ciazhar.authserver.service.impl;

import com.ciazhar.authserver.model.dto.request.RegisterForm;
import com.ciazhar.authserver.service.EmailService;
import com.ciazhar.authserver.util.email.EmailHtmlSender;
import com.ciazhar.authserver.util.email.EmailStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
	public EmailHtmlSender emailHtmlSender;

    @Override
    public EmailStatus sendEmail(RegisterForm form){
        Context context = new Context();
        context.setVariable("title", "Clorus Email Verification");
        context.setVariable("description", "To Verify your clorus account please click link below ");
        context.setVariable("email",form.getEmail());
        
        EmailStatus emailStatus = emailHtmlSender.send(form.getEmail(), "Clorus Email Verification", "mail", context);

        return emailStatus;
    }
}