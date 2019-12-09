package com.trilogyed.retailedgeservice.feign;

import com.trilogyed.retailedgeservice.dto.Invoice;
import com.trilogyed.retailedgeservice.dto.LevelUp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "levelup-crud-service")
@RequestMapping(value = "/levelUp")
public interface LevelUpClient {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LevelUp createLevelUp(@RequestBody @Valid LevelUp levelUp);

    @GetMapping
    public List<LevelUp> getAllLevelUps();

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateLevelUp(@RequestBody @Valid LevelUp levelUp);

    @GetMapping(value = "/{id}")
    public LevelUp getLevelUpById(@PathVariable int id);

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLevelUpById(@PathVariable int id);

    @GetMapping(value = "/customer/{id}")
    public List<LevelUp> findLevelUpByCustomerId(@PathVariable int id);
}
