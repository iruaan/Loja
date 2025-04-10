package com.loja.loja.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.loja.loja.Repository.UserRepository;
import com.loja.loja.Services.RegisterService;

@Controller
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RegisterService registerService;

    @PostMapping("/register")
    public String register(@RequestParam("email") String email, 
                           @RequestParam("password") String password) {


        return  registerService.register(email, password);
    }
}
