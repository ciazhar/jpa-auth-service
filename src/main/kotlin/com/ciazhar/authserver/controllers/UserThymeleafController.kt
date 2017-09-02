package com.ciazhar.authserver.controllers

import com.ciazhar.authserver.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class UserThymeleafController @Autowired constructor(private val service: UserService){

    @PreAuthorize("permitAll()")
    @RequestMapping("/activate")
    fun activate(@RequestParam(value = "email") email: String, model: Model): String {
        return service.activate(email,model)
    }

}