package com.ciazhar.authserver.service

import com.ciazhar.authserver.model.dto.request.RegisterForm
import com.ciazhar.authserver.util.email.EmailStatus

interface EmailService {

    fun sendEmail(form: RegisterForm): EmailStatus
}