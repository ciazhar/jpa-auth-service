package com.ciazhar.authserver.controllers;

import com.ciazhar.authserver.model.User;
import com.ciazhar.authserver.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserThymeleafController{

    @Autowired
    private UserRepository userRepository;


    @PreAuthorize("permitAll()")
    @RequestMapping("/activate")
    public String activate (@RequestParam(value="email")String email, Model model){
        User user = userRepository.findByEmail(email);
        model.addAttribute("email",user.getEmail());

        Logger logger = LoggerFactory.getLogger(this.getClass());
        logger.info("\n\n Email yang dikirim yaitu\n\n\n", user.getEmail());

        user.setEnabled(true);
        userRepository.save(user);
        
        return "/activate";
    }

}