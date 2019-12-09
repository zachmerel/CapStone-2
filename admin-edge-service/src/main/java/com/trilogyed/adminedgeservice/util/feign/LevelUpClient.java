package com.trilogyed.adminedgeservice.util.feign;

import com.trilogyed.adminedgeservice.dto.LevelUp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "levelup-crud-service")
public interface LevelUpClient {

    @PostMapping(value = "/levelUp")
    @ResponseStatus(HttpStatus.CREATED)
    public LevelUp createLevelUp(@RequestBody @Valid LevelUp levelUp);

    @GetMapping(value = "/levelUp")
    public List<LevelUp> getAllLevelUps();

    @PutMapping(value = "/levelUp")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateLevelUp(@RequestBody @Valid LevelUp levelUp);

    @GetMapping(value = "levelUp/{id}")
    public LevelUp getLevelUpById(@PathVariable int id);

    @DeleteMapping(value = "levelUp/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLevelUpById(@PathVariable int id);
}
