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
import org.springframework.scheduling.annotation.Scheduled;
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

    @GetMapping(value = "")
    public ResponseEntity getUserInfoById(@CookieValue(value = "project1LoginCookie", defaultValue = "none") String cookie){
        if(cookie.equals("none")) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        Optional<User> resultUser = userService.getUserById(Integer.getInteger(cookie));
        if(resultUser.isEmpty()) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        return ResponseEntity.status(HttpStatus.OK).body(resultUser);
    }

//    @Scheduled(fixedRate = 10000)
    @PostMapping(value = "")
    public ResponseEntity postNewDuck(@CookieValue(value = "project1LoginCookie", defaultValue = "none") String cookie){
        if(cookie.equals("none")) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        Optional<User> resultUser = userService.getUserById(Integer.getInteger(cookie));
        if(resultUser.isEmpty()) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");

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
        returnDuck.setReference_id(Integer.getInteger(cookie));
        returnDuck.setNickname("DefaultName");

        try{
            World updatedWorld = worldService.getWorldValuesById(1);
            switch(duckRank){
                case "B": updatedWorld.setB_rank(updatedWorld.getB_rank() - 1);
                case "A": updatedWorld.setA_rank(updatedWorld.getA_rank() - 1);
                case "S": updatedWorld.setS_rank(updatedWorld.getS_rank() - 1);
                case "SS": updatedWorld.setSs_rank(updatedWorld.getSs_rank() - 1);
            }
            worldService.setWorldValues(updatedWorld);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Something Went Wrong");
        }

        try{
            returnDuck = duckService.createDuck(returnDuck);

            return ResponseEntity.status(HttpStatus.OK).body(returnDuck);
        }
        catch (Exception e){
            return ResponseEntity.status(500).body("Something Went Wrong");
        }
    }

    @PatchMapping(value = "")
    public ResponseEntity patchDuckNicknameById (@CookieValue(value = "project1LoginCookie", defaultValue = "none") String cookie, @RequestBody Duck duck) {
        if(cookie.equals("none")) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        if(!cookie.equals(Integer.toString(duck.getReference_id()))) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        try {
            Optional<Duck> resultDucks = duckService.setDuckNicknameById(duck);
            return ResponseEntity.status(HttpStatus.OK).body(resultDucks);
        } catch (ClientSideException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Improper Info");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something Went wrong");
        }
    }

    @GetMapping(value = "ducks")
    public ResponseEntity getDucksOwnedById (@CookieValue(value = "project1LoginCookie", defaultValue = "none") String cookie){
        if(cookie.equals("none")) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        List<Duck> resultDucks = duckService.getDucksByForeignId(Integer.getInteger(cookie));
        return ResponseEntity.status(HttpStatus.OK).body(resultDucks);
    }

    @PatchMapping(value = "ducks")
    public ResponseEntity patchSetAvailableDucks(@CookieValue(value = "project1LoginCookie", defaultValue = "none") String cookie,@RequestBody World world){
        if(cookie.equals("none")) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        try {
            Optional<User> resultUser = userService.getUserById(Integer.getInteger(cookie));
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

    @DeleteMapping(value = "")
    public ResponseEntity deleteDuckById (@CookieValue(value = "project1LoginCookie", defaultValue = "none") String cookie, @RequestBody Duck duck){
        if(cookie.equals("none")) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        try {
            Optional<Duck> resultDuck = duckService.deleteDuckById(duck);
            if (resultDuck.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Improper Info");
            if(resultDuck.get().getReference_id() != Integer.getInteger(cookie)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Improper Info");
            return ResponseEntity.status(HttpStatus.OK).body(resultDuck);
        }
        catch (ClientSideException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Improper Info");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something Went wrong");
        }
    }

    @GetMapping(value = "world")
    public ResponseEntity getWorldInfo(){
        try {
            World world = worldService.getWorldValuesById(1);
            return ResponseEntity.status(HttpStatus.OK).body(world);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Id");
        }
    }

    @GetMapping(value = "price")
    public ResponseEntity getBackpackPrice(@CookieValue(value = "project1LoginCookie", defaultValue = "none") String cookie){
        if(cookie.equals("none")) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        try {
            Optional<User> resultUser = userService.getUserById(Integer.getInteger(cookie));
            if (resultUser.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Improper Info");
            double backpackPrice = Math.pow(1.5,resultUser.get().getBackpack_space() - 5.0);
            backpackPrice = Math.round(backpackPrice * 100.0) / 100.0;
            return ResponseEntity.status(HttpStatus.OK).body(backpackPrice);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }

    @PatchMapping(value = "price")
    public ResponseEntity buyBackpack(@CookieValue(value = "project1LoginCookie", defaultValue = "none") String cookie){
        if(cookie.equals("none")) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        try {
            Optional<User> resultUser = userService.getUserById(Integer.getInteger(cookie));
            if (resultUser.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Improper Info");
            double backpackPrice = Math.pow(1.5,resultUser.get().getBackpack_space() - 5.0);
            backpackPrice = Math.round(backpackPrice * -100.0) / 100.0;
            User user = userService.setUserBankAccount(resultUser.get(),backpackPrice);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }


}
