package com.revature.Project1.services;

import com.revature.Project1.daos.UserDAO;
import com.revature.Project1.exceptions.ClientSideException;
import com.revature.Project1.exceptions.ConflictException;
import com.revature.Project1.exceptions.PasswordException;
import com.revature.Project1.exceptions.UsernameException;
import com.revature.Project1.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Optional<User> getUserById(User user){
        return userDAO.findById(user.getId());
    }

    public Optional<User> getUserByUsername(User user){
        return getUserByUsername(user);
    }

    public User createUser(User user) throws PasswordException, UsernameException, ConflictException {
        if(user.getPassword().length() < 4) throw new PasswordException();
        if(user.getUsername().trim().isEmpty()) throw new UsernameException();
        if(userDAO.findUserByUsername(user.getUsername()).isPresent()) throw new ConflictException();
        return userDAO.save(user);
    }
}
