package com.trilogyed.retailedgeservice.controller;

import com.trilogyed.retailedgeservice.dto.LevelUp;
import com.trilogyed.retailedgeservice.service.RetailServiceLayer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RefreshScope
//@CacheConfig(cacheNames = {"levelUps"})
public class LevelUpController {
    @Autowired
    private RetailServiceLayer retailServiceLayer;
//    public static final String EXCHANGE = "points-exchange";
//    public static final String ROUTING_KEY = "levelup․#";
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    public LevelUpController(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }

    //    @Cacheable
    @RequestMapping(value = "/levelUp/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public LevelUp getLevelUpById(@PathVariable int id) {
        return retailServiceLayer.getLevelUpById(id);
    }

    //    @CacheEvict
    @RequestMapping(value = "/levelUp/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLevelUpById(@PathVariable int id) {
        retailServiceLayer.deleteLevelUpById(id);
    }

    //    @CachePut(key = "#result.getLevelUpId()")
    @RequestMapping(value = "/levelUp", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String createLevelUp(@RequestBody @Valid LevelUp levelUp) {
        retailServiceLayer.createLevelUp(levelUp);
        return "levelUp created: " + levelUp;
    }

    @RequestMapping(value = "/levelUp", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<LevelUp> getAllLevelUps() {
        return retailServiceLayer.getAllLevelUps();
    }

    //    @CacheEvict
    @RequestMapping(value = "/levelUp", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateLevelUp(@RequestBody @Valid LevelUp levelUp) {
        retailServiceLayer.updateLevelUp(levelUp);
    }

    //    @Cacheable
    @RequestMapping(value = "/levelup/points/customer/{id}", method = RequestMethod.GET)
    public Integer getLevelUpPointsByCustomerId(int id) {
        return retailServiceLayer.getLevelUpPointsByCustomerId(id);
    }
}
