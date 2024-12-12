package com.revature.Project1.controllers;

import com.revature.Project1.Components.SupplementaryFunctions;
import com.revature.Project1.services.DuckService;
import com.revature.Project1.services.UserService;
import com.revature.Project1.services.WorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
