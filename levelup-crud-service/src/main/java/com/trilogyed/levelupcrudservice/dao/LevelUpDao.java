package com.trilogyed.levelupcrudservice.dao;

import com.trilogyed.levelupcrudservice.dto.LevelUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LevelUpDao extends JpaRepository<LevelUp, Integer> {
    List<LevelUp> findLevelUpsByCustomerId(int id);
}
