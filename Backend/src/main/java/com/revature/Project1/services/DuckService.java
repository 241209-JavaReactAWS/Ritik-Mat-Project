package com.revature.Project1.services;

import com.revature.Project1.daos.DuckDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DuckService {

    private final DuckDAO duckDAO;

    @Autowired
    public DuckService(DuckDAO duckDAO) {
        this.duckDAO = duckDAO;
    }
}
