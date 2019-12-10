package com.trilogyed.levelupcrudservice;

import com.trilogyed.levelupcrudservice.dao.LevelUpDao;
import com.trilogyed.levelupcrudservice.dto.LevelUp;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {
    @Autowired
    private LevelUpDao client;

    @RabbitListener(queues = LevelUpCrudServiceApplication.QUEUE_NAME)
    public void receiveMessage(LevelUp levelUp) {
        System.out.println(levelUp.toString());
        client.save(levelUp);
    }
}