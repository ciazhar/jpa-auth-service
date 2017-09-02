package com.ciazhar.authserver.service.impl

import com.ciazhar.authserver.model.dto.request.RegisterForm
import com.ciazhar.authserver.service.EmailService
import com.ciazhar.authserver.util.email.EmailHtmlSender
import com.ciazhar.authserver.util.email.EmailStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.thymeleaf.context.Context

@Service
class EmailServiceImpl (private val emailHtmlSender: EmailHtmlSender): EmailService {

    override fun sendEmail(form: RegisterForm): EmailStatus {
        val context = Context()
        context.setVariable("title", "Email Verification")
        context.setVariable("description", "To Verify your account please click link below ")
        context.setVariable("email", form.email)

        return emailHtmlSender.send(form.email, "Email Verification", "mail", context)
    }
}