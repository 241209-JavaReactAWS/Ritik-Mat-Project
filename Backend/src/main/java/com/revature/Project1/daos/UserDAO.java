package com.revature.Project1.daos;

import com.revature.Project1.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User,Integer> {
    public Optional<User> findUserByUsername(String username);
}
