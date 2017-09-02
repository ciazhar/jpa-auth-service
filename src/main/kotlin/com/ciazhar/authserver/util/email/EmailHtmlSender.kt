package com.ciazhar.authserver.util.email

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context


@Component
class EmailHtmlSender {
    @Autowired
    private val mailSender: EmailSender? = null

    @Autowired
    private val templateEngine: TemplateEngine? = null

    fun send(to: String, subject: String, templateName: String, context: Context): EmailStatus {
        val body = templateEngine!!.process(templateName, context)
        return mailSender!!.sendHtml(to, subject, body)
    }
}