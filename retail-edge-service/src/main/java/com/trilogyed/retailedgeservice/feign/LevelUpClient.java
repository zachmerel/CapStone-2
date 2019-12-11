package com.trilogyed.retailedgeservice.feign;

import com.trilogyed.retailedgeservice.dto.Invoice;
import com.trilogyed.retailedgeservice.dto.LevelUp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "levelup-crud-service")
//@RequestMapping(value = "/levelUp")
public interface LevelUpClient {

    @PostMapping(value = "/levelUp")
    @ResponseStatus(HttpStatus.CREATED)
    public LevelUp createLevelUp(@RequestBody @Valid LevelUp levelUp);

    @GetMapping(value = "/levelUp")
    public List<LevelUp> getAllLevelUps();

    @PutMapping(value = "/levelUp")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateLevelUp(@RequestBody @Valid LevelUp levelUp);

    @GetMapping(value = "/levelUp/{id}")
    public LevelUp getLevelUpById(@PathVariable int id);

    @DeleteMapping(value = "/levelUp/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLevelUpById(@PathVariable int id);

    @GetMapping(value = "/levelUp/customerId/{id}")
    public List<LevelUp> findLevelUpByCustomerId(@PathVariable int id);
}
