package com.loja.usecs.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.loja.usecs.Model.User.UserInfo;
import com.loja.usecs.Services.UserLoadService;

@Configuration
public class WebConfigSecurity {

    @Autowired
    private UserLoadService userDetailsService;

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register", "/login", "/css/**", "/js/**", "/images/**", "/home", "/products/{id}", "/cart/add", "/cart/add/{productId}").permitAll()                                            
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login") // Define a página de login
                        .loginProcessingUrl("/login/auth")  // URL de autenticação
                        .usernameParameter("email") // Campo usado como username
                        .passwordParameter("password") // Campo usado como senha
                        .defaultSuccessUrl("/home", true) // Página após login bem-sucedido
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll());

        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = 
            http.getSharedObject(AuthenticationManagerBuilder.class);
        
        authenticationManagerBuilder
            .userDetailsService(userDetailsService)
            .passwordEncoder(new BCryptPasswordEncoder());

        return authenticationManagerBuilder.build();
    }





}
