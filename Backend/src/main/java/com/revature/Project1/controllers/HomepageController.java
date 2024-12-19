package com.revature.Project1.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.Project1.exceptions.ConflictException;
import com.revature.Project1.exceptions.PasswordException;
import com.revature.Project1.exceptions.UsernameException;
import com.revature.Project1.models.User;
import com.revature.Project1.services.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping()
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

    @PostMapping(value = "login")
    public ResponseEntity loginAccount(@RequestBody User user,HttpServletResponse servlet){
            Optional<User> resultUser = userService.getUserByUsername(user);
            if(resultUser.isEmpty()){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Username or Password");
            }

            if(resultUser.get().getPassword().equals(user.getPassword())){
                Cookie cookie = new Cookie("project1LoginCookie", Integer.toString(resultUser.get().getId()));
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
        cookie.setMaxAge(-100000);
        servlet.addCookie(cookie);
        return ResponseEntity.status(HttpStatus.OK).body("Logged Out");
    }


    @GetMapping(value = "")
    public ResponseEntity getLoginCookie(@CookieValue(value = "project1LoginCookie", defaultValue = "none") String cookie){
        if(cookie.equals("none")) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Cookie Found");
        return ResponseEntity.status(HttpStatus.OK).body(cookie);
        }
    }

