package com.revature.Project1.services;

import com.revature.Project1.daos.UserDAO;
import com.revature.Project1.exceptions.ClientSideException;
import com.revature.Project1.exceptions.ConflictException;
import com.revature.Project1.exceptions.PasswordException;
import com.revature.Project1.exceptions.UsernameException;
import com.revature.Project1.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Optional<User> getUserById(Integer id){
        return userDAO.findById(id);
    }

    public Optional<User> getUserByUsername(User user){
        return userDAO.findUserByUsername(user.getUsername());
    }

    public User createUser(User user) throws PasswordException, UsernameException, ConflictException {
        if(user.getPassword().length() < 4) throw new PasswordException();
        if(user.getUsername().trim().isEmpty()) throw new UsernameException();
        if(userDAO.findUserByUsername(user.getUsername()).isPresent()) throw new ConflictException();
        if(user.getUsername().equals("admin")) {
            user.setAdmin(1);
            user.setBackpack_space(999999);
        }
        user.setBackpack_space(6);
        return userDAO.save(user);
    }

    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    public User setUserBankAccount(User user, Double amount) throws ClientSideException{
        Optional<User> userObtained = userDAO.findById(user.getId());
        if(userObtained.isEmpty()) throw new ClientSideException();
        User returnUser = userObtained.get();
        returnUser.setBank_account((float)(returnUser.getBank_account() + amount));
        userDAO.save(returnUser);
        return returnUser;
    }

    public User setUserBackpackAmount(User user) throws ClientSideException{
        Optional<User> userObtained = userDAO.findById(user.getId());
        if(userObtained.isEmpty()) throw new ClientSideException();
        User returnUser = userObtained.get();
        returnUser.setBackpack_space((returnUser.getBackpack_space() + 1));
        userDAO.save(returnUser);
        return returnUser;
    }

}
