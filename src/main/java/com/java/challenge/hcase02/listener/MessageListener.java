package com.java.challenge.hcase02.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.challenge.hcase02.entity.DataModelEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageListener {

    protected final Logger logger = LogManager.getLogger(this.getClass());

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "${spring-rabbitmq.rabbit.msg.queue.name}")
    public void handleMessage(List<DataModelEntity> data) {
        this.logger.info("|||||||||||| Data received: " + data.toString());
    }
}