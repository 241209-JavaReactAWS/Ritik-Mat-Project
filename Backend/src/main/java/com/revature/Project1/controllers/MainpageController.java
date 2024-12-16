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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

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

    @PostMapping(value = "/{id}")
    public ResponseEntity postNewDuck(@PathVariable Integer id){
        Optional<User> resultUser = userService.getUserById(id);
        if(resultUser.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Id");

        World world = null;
        try {
            world = worldService.getWorldValuesById(1);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Id");
        }

        String duckRank = supFunctions.obtainDuckRank(supFunctions.rollForDuck());
        Map<String,Integer> duckAmounts = world.convertToMap();
        Boolean duckAvailable = supFunctions.duckAvailable(duckRank,duckAmounts);
        duckRank = duckAvailable ? duckRank : "C";

        Duck returnDuck = new Duck(duckRank);
        returnDuck.setReference_id(id);
        returnDuck.setNickname("DefaultName");

        try{
            returnDuck = duckService.createDuck(returnDuck);
            return ResponseEntity.status(HttpStatus.OK).body(returnDuck);
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Something Went Wrong");
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

    @PatchMapping(value = "/{id}/ducks")
    public ResponseEntity patchSetAvailableDucks(@PathVariable Integer id,@RequestBody World world){
        try {
           Optional<User> resultUser = userService.getUserById(id);
           if(resultUser.isEmpty())  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Improper Info");
           if(resultUser.get().getAdmin() == 0) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
           World resultWorld = worldService.setWorldValues(world);
           return ResponseEntity.status(HttpStatus.OK).body(resultWorld);
        } catch (ClientSideException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Improper Info");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something Went wrong");
        }
    }

    @DeleteMapping(value = "/{id}/ducks")
    public ResponseEntity deleteDuckById (@PathVariable Integer id, @RequestBody Duck duck){
        try {
            Optional<Duck> resultDuck = duckService.deleteDuckById(duck);
            if(resultDuck.get().getReference_id() != id) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Improper Info");
            return ResponseEntity.status(HttpStatus.OK).body(resultDuck);
        }
        catch (ClientSideException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Improper Info");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something Went wrong");
        }
    }
}
