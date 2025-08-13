package com.org.SpringSecurity.Repository;

import com.org.SpringSecurity.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Users,Integer> {

    Users findByUsername(String username);
}
