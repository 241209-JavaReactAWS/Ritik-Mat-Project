package com.revature.Project1.services;

import com.revature.Project1.daos.WorldDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorldService {

    private final WorldDAO worldDAO;

    @Autowired
    public WorldService(WorldDAO worldDAO) {
        this.worldDAO = worldDAO;
    }
}
