package com.revature.Project1.services;

import com.revature.Project1.daos.WorldDAO;
import com.revature.Project1.exceptions.ClientSideException;
import com.revature.Project1.models.World;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class WorldService {

    private final WorldDAO worldDAO;

    @Autowired
    public WorldService(WorldDAO worldDAO) {
        this.worldDAO = worldDAO;
    }

    public World getWorldValuesById(int id) throws  ClientSideException{
        Optional<World> resultWorld = worldDAO.findById(id);
        if(resultWorld.isEmpty()) throw new ClientSideException();
        return resultWorld.get();
    }

    public World setWorldValues(World world) throws ClientSideException {
        if(world.getF_rank() < 0) throw new ClientSideException();
        if(world.getE_rank() < 0) throw new ClientSideException();
        if(world.getD_rank() < 0) throw new ClientSideException();
        if(world.getC_rank() < 0) throw new ClientSideException();
        if(world.getB_rank() < 0) throw new ClientSideException();
        if(world.getA_rank() < 0) throw new ClientSideException();
        if(world.getS_rank() < 0) throw new ClientSideException();
        if(world.getSs_rank() < 0) throw new ClientSideException();
        if(worldDAO.findById(world.getId()).isEmpty()) throw new ClientSideException();
        World innerWorld = worldDAO.findById(world.getId()).get();

        innerWorld.setF_rank(world.getF_rank());
        innerWorld.setE_rank(world.getE_rank());
        innerWorld.setD_rank(world.getD_rank());
        innerWorld.setC_rank(world.getC_rank());
        innerWorld.setB_rank(world.getB_rank());
        innerWorld.setA_rank(world.getA_rank());
        innerWorld.setS_rank(world.getS_rank());
        innerWorld.setSs_rank(world.getSs_rank());

        worldDAO.save(innerWorld);
        return innerWorld;
    }
}
