package com.revature.Project1.controllers;

import com.revature.Project1.Components.SupplementaryFunctions;
import com.revature.Project1.exceptions.ClientSideException;
import com.revature.Project1.models.Duck;
import com.revature.Project1.models.User;
import com.revature.Project1.models.World;
import com.revature.Project1.services.DuckService;
import com.revature.Project1.services.UserService;
import com.revature.Project1.services.WorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("backpack")
public class MainpageController {
    private final UserService userService;
    private final DuckService duckService;
    private final WorldService worldService;
    private final SupplementaryFunctions supFunctions;

    @Autowired
    public MainpageController(UserService userService, DuckService duckService, WorldService worldService, SupplementaryFunctions supFunctions) {
        this.userService = userService;
        this.duckService = duckService;
        this.worldService = worldService;
        this.supFunctions = supFunctions;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getUserInfoById(@PathVariable Integer id){
        Optional<User> resultUser = userService.getUserById(id);
        if(resultUser.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Id");
        return ResponseEntity.status(HttpStatus.OK).body(resultUser);
    }

    @GetMapping(value = "/{id}/ducks")
    public ResponseEntity getDucksOwnedById (@PathVariable Integer id){
        List<Duck> resultDucks = duckService.getDucksByForeignId(id);
        return ResponseEntity.status(HttpStatus.OK).body(resultDucks);
    }

    @GetMapping(value = "/world")
    public ResponseEntity getWorldInfo(){
        try {
            World world = worldService.getWorldValuesById(1);
            return ResponseEntity.status(HttpStatus.OK).body(world);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Id");
        }
    }


    @PatchMapping(value = "/{id}")
    public ResponseEntity patchDuckNicknameById (@PathVariable Integer id, @RequestBody Duck duck) {
        try {
            Optional<Duck> resultDucks = duckService.setDuckNicknameById(duck);
            return ResponseEntity.status(HttpStatus.OK).body(resultDucks);
        } catch (ClientSideException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Improper Info");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something Went wrong");
        }
    }
}
