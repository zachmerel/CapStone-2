package com.trilogyed.levelupcrudservice.dao;

import com.trilogyed.levelupcrudservice.dto.LevelUp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LevelUpDaoTest {
    @Autowired
    LevelUpDao levelUpDao;

    @Before
    public void setUp(){
        levelUpDao.findAll().forEach(x->levelUpDao.deleteById(x.getLevelUpId()));
    }
    @Test
    public void findLevelUpsByCustomerId() {
        LevelUp levelUp = new LevelUp();
        levelUp.setCustomerId(1);
        levelUp.setPoints(1);
        levelUp.setMemberDate(LocalDate.now());
        levelUp = levelUpDao.save(levelUp);
        List<LevelUp> levelUpList = new ArrayList<>();
        levelUpList.add(levelUp);
        assertEquals(levelUpList,levelUpDao.findLevelUpsByCustomerId(1));
    }
}