package com.revature.Project1.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.Project1.daos.WorldDAO;
import com.revature.Project1.exceptions.ClientSideException;
import com.revature.Project1.models.World;

@Service
public class WorldService {

    private final WorldDAO worldDAO;

    @Autowired
    public WorldService(WorldDAO worldDAO) {
        this.worldDAO = worldDAO;
    }

    public World createWorld(World world){
        return worldDAO.save(world);
    }

    public World getWorldValuesById(int id) throws  ClientSideException{
        Optional<World> resultWorld = worldDAO.findById(id);
        if(resultWorld.isEmpty()) throw new ClientSideException();
        return resultWorld.get();
    }

    public World setWorldValues(World world) throws ClientSideException {
        World innerWorld = worldDAO.findById(1).get();

        innerWorld.setF_rank(Math.max(innerWorld.getF_rank() + world.getF_rank(),0));
        innerWorld.setE_rank(Math.max(innerWorld.getE_rank() + world.getE_rank(),0));
        innerWorld.setD_rank(Math.max(innerWorld.getD_rank() + world.getD_rank(),0));
        innerWorld.setC_rank(Math.max(innerWorld.getC_rank() + world.getC_rank(),0));
        innerWorld.setB_rank(Math.max(innerWorld.getB_rank() + world.getB_rank(),0));
        innerWorld.setA_rank(Math.max(innerWorld.getA_rank() + world.getA_rank(),0));
        innerWorld.setS_rank(Math.max(innerWorld.getS_rank() + world.getS_rank(),0));
        innerWorld.setSs_rank(Math.max(innerWorld.getSs_rank() + world.getSs_rank(),0));

        worldDAO.save(innerWorld);
        return innerWorld;
    }
}
