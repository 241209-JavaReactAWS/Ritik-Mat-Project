package com.revature.Project1.daos;

import com.revature.Project1.models.World;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorldDAO extends JpaRepository<World,Integer> {
}
