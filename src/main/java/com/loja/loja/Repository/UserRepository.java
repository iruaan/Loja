package com.loja.loja.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loja.loja.Model.User.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo,Long> {

    UserInfo findByEmail(String email);

    boolean existsByEmail(String email);
    

}
