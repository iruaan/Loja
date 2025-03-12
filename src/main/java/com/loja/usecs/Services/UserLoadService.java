package com.loja.usecs.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.loja.usecs.Model.User.UserInfo;
import com.loja.usecs.Repository.UserRepository;

@Service
public class UserLoadService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserInfo user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
    
        return User.builder()
            .username(user.getEmail())
            .password(user.getPassword()) 
            .roles(user.getRole().name()) 
            .build();
    }



    

    
}
