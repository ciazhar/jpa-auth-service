package com.ciazhar.authserver.controller

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Created by ciazhar on 9/9/17.
 */

@Controller
class UserController{

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/api/user/me")
    @ResponseBody
    fun currentUser(auth: Authentication): Authentication {
        return auth
    }

    @RequestMapping("/login")
    fun loginPage() {
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/home")
    fun homepage(currentUser: Authentication): ModelMap {
        return ModelMap("user", currentUser)
    }
}