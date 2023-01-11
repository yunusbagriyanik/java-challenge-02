package com.java.challenge.hcase02.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.challenge.hcase02.dto.DataModel;
import com.java.challenge.hcase02.entity.DataModelMapper;
import com.java.challenge.hcase02.repository.DataModelRepository;
import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageProducer {

    protected final Logger logger = LogManager.getLogger(this.getClass());

    @Value("${spring-rabbitmq.rabbit.msg.routing.name}")
    private String routingName;

    @Value("${spring-rabbitmq.rabbit.exchange.name}")
    private String exchangeName;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private DataModelRepository dataModelRepository;

    @PostConstruct
    public void init() {

    }

    @Scheduled(fixedDelay = 1000, initialDelay = 1000)
    private void produce() throws IOException {

        List<DataModel> dataList = new ArrayList<>();
        dataList.add(DataModel.init().createdDate().randomInt().md5HashValue().md5Last2Char().build());
        dataList.add(DataModel.init().createdDate().randomInt().md5HashValue().md5Last2Char().build());
        dataList.add(DataModel.init().createdDate().randomInt().md5HashValue().md5Last2Char().build());
        dataList.add(DataModel.init().createdDate().randomInt().md5HashValue().md5Last2Char().build());
        dataList.add(DataModel.init().createdDate().randomInt().md5HashValue().md5Last2Char().build());

        List<DataModel> listOfDataTobeSent = dataList.stream()
                .filter(dataModel -> dataModel.getRandomInt() > 90)
                .toList();

        if (!listOfDataTobeSent.isEmpty())
            this.sendMessageToQueue(listOfDataTobeSent);

        List<DataModel> listOfDataTobeWriteFile = dataList.stream()
                .filter(dataModel -> dataModel.getRandomInt() <= 90)
                .toList();

        if (!listOfDataTobeWriteFile.isEmpty()) {
            Path path = Paths.get("h_case_02.txt");
            Files.write(path, listOfDataTobeWriteFile.toString().getBytes(), StandardOpenOption.APPEND);
        }
    }

    public void sendMessageToQueue(List<DataModel> data) {
        this.logger.info("*********************>>> Message sent id: " + data);
        this.dataModelRepository.saveAll(DataModelMapper.mapDataModelToEntity(data));

        this.rabbitTemplate.convertAndSend(exchangeName, routingName, data);
    }
}