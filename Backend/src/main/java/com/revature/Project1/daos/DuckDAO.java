package com.revature.Project1.daos;

import com.revature.Project1.models.Duck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DuckDAO extends JpaRepository<Duck,Integer> {
}
