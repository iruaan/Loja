package com.loja.usecs.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.loja.usecs.Model.Role;
import com.loja.usecs.Model.UserInfo;
import com.loja.usecs.Repository.UserRepository;

@Controller
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@RequestParam("email") String email, 
                           @RequestParam("password") String password) {
        if (userRepository.existsByEmail(email)) {
            return "redirect:/register?error=email_existente";
        }

        // Criptografa a senha
        String encodedPassword = passwordEncoder.encode(password);

        // Cria o novo usuário
        UserInfo newUser = new UserInfo();
        newUser.setEmail(email);
        newUser.setPassword(encodedPassword);
        newUser.setRole(Role.USER);  


        userRepository.save(newUser);
        System.out.println("Usuário cadastrado com sucesso: " + newUser.getEmail());

        return "redirect:/login";  
    }
}
