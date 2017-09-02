package com.ciazhar.authserver.controllers

import com.ciazhar.authserver.model.jpa.User
import com.ciazhar.authserver.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class UserThymeleafController {

    @Autowired
    private val userRepository: UserRepository? = null


    @PreAuthorize("permitAll()")
    @RequestMapping("/activate")
    fun activate(@RequestParam(value = "email") email: String, model: Model): String {
        val user = userRepository!!.findByEmail(email)
        model.addAttribute("email", user.email)

        val logger = LoggerFactory.getLogger(this.javaClass)
        logger.info("\n\n Email yang dikirim yaitu\n\n\n", user.email)

        user.enabled = true
        userRepository.save(user)

        return "/activate"
    }

}