package com.loja.usecs.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebConfigSecurity {


        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/register", "/login", "/css/**", "/js/**", "/images/**" , "/home").permitAll() // Permite acesso à página de login
                    .anyRequest().authenticated()
                )
                .formLogin(form -> form
                    .loginPage("/login") // Define a página de login
                    .usernameParameter("email") // Campo usado como username
                    .passwordParameter("password") // Campo usado como senha
                    .defaultSuccessUrl("/home", true) // Página após login bem-sucedido
                    .permitAll()
                )
                .logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout")
                    .permitAll()
                );
    
            return http.build();

    }


    
}
