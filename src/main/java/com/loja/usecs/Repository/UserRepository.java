package com.loja.usecs.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loja.usecs.Model.User.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo,Long> {

    UserInfo findByEmail(String email);

    boolean existsByEmail(String email);
    

}
