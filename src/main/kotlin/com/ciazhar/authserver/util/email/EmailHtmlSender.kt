package com.ciazhar.authserver.util.email

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context


@Component
class EmailHtmlSender (private val mailSender:EmailSender, private val templateEngine: TemplateEngine){

    fun send(to: String?, subject: String, templateName: String, context: Context): EmailStatus {
        val body = templateEngine.process(templateName, context)
        return mailSender.sendHtml(to, subject, body)
    }
}