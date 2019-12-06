package com.trilogyed.levelupcrudservice.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.trilogyed.levelupcrudservice.dao.LevelUpDao;
import com.trilogyed.levelupcrudservice.dto.LevelUp;
import com.trilogyed.levelupcrudservice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/levelUp")
@RefreshScope
public class LevelUpController {
    @Autowired
    private LevelUpDao levelUpRepo;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LevelUp createLevelUp(@RequestBody @Valid LevelUp levelUp) {
        return levelUpRepo.save(levelUp);
    }

    @GetMapping
    public List<LevelUp> getAllLevelUps() {
        return levelUpRepo.findAll();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateLevelUp(@RequestBody @Valid LevelUp levelUp) {
        levelUpRepo.save(levelUp);
    }

    @GetMapping(value = "/{id}")
    public LevelUp getLevelUpById(@PathVariable int id) throws JsonMappingException {
        Optional<LevelUp> levelUp = levelUpRepo.findById(id);
        if (levelUp.isPresent()) {
            return levelUp.get();
        } else {
            throw new NotFoundException("Did not find an levelUp with id " + id);
        }
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLevelUpById(@PathVariable int id) throws JsonMappingException {
        Optional<LevelUp> levelUp = levelUpRepo.findById(id);
        if (levelUp.isPresent()) {
            levelUpRepo.deleteById(id);
        } else {
            throw new NotFoundException("Did not find an levelUp with id " + id);
        }
    }
}