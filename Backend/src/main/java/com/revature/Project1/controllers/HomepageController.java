package com.revature.Project1.controllers;

import com.revature.Project1.exceptions.ClientSideException;
import com.revature.Project1.exceptions.ConflictException;
import com.revature.Project1.exceptions.PasswordException;
import com.revature.Project1.exceptions.UsernameException;
import com.revature.Project1.models.User;
import com.revature.Project1.services.DuckService;
import com.revature.Project1.services.UserService;
import com.revature.Project1.services.WorldService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class HomepageController {

    private final UserService userService;

    @Autowired
    public HomepageController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "register")
    public ResponseEntity registerAccount(@RequestBody User user){
        try{
            User resultUser = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.OK).body(resultUser);
        }catch(ConflictException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username Taken");
        }catch(PasswordException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password Too Short");
        }catch(UsernameException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username Invalid");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.SEE_OTHER).body(null);
        }
    }

    @GetMapping(value = "login")
    public ResponseEntity loginAccount(@RequestBody User user,HttpServletResponse servlet){
            Optional<User> resultUser = userService.getUserByUsername(user);
            if(resultUser.isEmpty()){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Username or Password");
            }
            if(resultUser.get().getPassword().equals(user.getPassword())){
                Cookie cookie = new Cookie("project1LoginCookie", Integer.toString(user.getId()));
                cookie.setMaxAge(100000);
                servlet.addCookie(cookie);
                return ResponseEntity.status(HttpStatus.OK).body(resultUser);
            } else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Username or Password");
            }
    }

    @GetMapping(value = "logout")
    public ResponseEntity logout(HttpServletResponse servlet){
        Cookie cookie = new Cookie("project1LoginCookie", null);
        cookie.setMaxAge(100000);
        servlet.addCookie(cookie);
        return ResponseEntity.status(HttpStatus.OK).body("Logged Out");
    }


    @GetMapping(value = "")
    public String getLoginCookie(@CookieValue(value = "project1LoginCookie", defaultValue = "none") String cookie){
        return cookie;
        }
    }

