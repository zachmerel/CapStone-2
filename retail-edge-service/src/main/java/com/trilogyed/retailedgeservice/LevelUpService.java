package com.trilogyed.retailedgeservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.trilogyed.retailedgeservice.dto.LevelUp;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
@Component
public class LevelUpService {
    private final RestTemplate restTemplate;

    public LevelUpService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "reliable")
    public List<LevelUp> getAllLevelUps() {
        URI uri = URI.create("http://localhost:7001/levelUp");
        return this.restTemplate.getForObject(uri, List.class);
    }

    public List<LevelUp> reliableLevelUpGetter() {
        return new ArrayList<>();
    }
}
