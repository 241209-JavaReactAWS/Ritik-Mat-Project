package com.revature.Project1.services;

import com.revature.Project1.daos.DuckDAO;
import com.revature.Project1.exceptions.ClientSideException;
import com.revature.Project1.models.Duck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DuckService {

    private final DuckDAO duckDAO;

    @Autowired
    public DuckService(DuckDAO duckDAO) {
        this.duckDAO = duckDAO;
    }

    public Optional<Duck> getDuckById(Duck duck){
        return duckDAO.findById(duck.getId());
    }

    public List<Duck> getDucksByForeignId(int foreignId){
        return duckDAO.findAllDuckByForeign_id(foreignId);
    }

    public Optional<Duck> setDuckNicknameById(Duck duck) throws ClientSideException {
        Optional<Duck> resultDuck = duckDAO.findById(duck.getId());
        if(resultDuck.isEmpty()) throw new ClientSideException();
        if(duck.getReference_id() != resultDuck.get().getReference_id()) throw new ClientSideException();
        if(duck.getNickname().trim().isEmpty()) throw new ClientSideException();
        Duck innerDuck = resultDuck.get();
        innerDuck.setNickname(duck.getNickname());
        duckDAO.save(innerDuck);
        return resultDuck;
    }

    public Optional<Duck> deleteDuckById(Duck duck) throws ClientSideException {
        Optional<Duck> resultDuck = duckDAO.findById(duck.getId());
        if(resultDuck.isEmpty()) throw new ClientSideException();
        duckDAO.deleteById(duck.getId());
        return  resultDuck;
    }

    public Duck createDuck(Duck duck) throws ClientSideException{
        if(duck.getNickname().trim().isEmpty()) throw new ClientSideException();
        return duckDAO.save(duck);
    }

}
