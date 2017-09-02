package com.ciazhar.authserver.util.email

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Component

import javax.mail.internet.MimeMessage


@Component("mail-component")
class EmailSender {
    @Autowired
    internal var javaMailSender: JavaMailSender? = null

    internal var logger = LoggerFactory.getLogger(this.javaClass)

    fun sendPlainText(to: String, subject: String, text: String): EmailStatus {
        return sendM(to, subject, text, false)
    }

    fun sendHtml(to: String, subject: String, htmlBody: String): EmailStatus {
        return sendM(to, subject, htmlBody, true)
    }

    private fun sendM(to: String, subject: String, text: String, isHtml: Boolean?): EmailStatus {
        try {
            val mail = javaMailSender!!.createMimeMessage()
            val helper = MimeMessageHelper(mail, true)
            helper.setTo(to)
            helper.setSubject(subject)
            helper.setText(text, isHtml!!)
            javaMailSender!!.send(mail)
            logger.info("Send email '{}' to: {}", subject, to)
            return EmailStatus(to, subject, text).success()
        } catch (e: Exception) {
            logger.error(String.format("Problem with sending email to: {}, error message: {}", to, e.message))
            return EmailStatus(to, subject, text).error(e.message)
        }

    }
}