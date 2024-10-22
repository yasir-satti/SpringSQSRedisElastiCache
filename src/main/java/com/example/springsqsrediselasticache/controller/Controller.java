package com.example.springsqsrediselasticache.controller;

import com.amazonaws.services.sqs.model.ListQueuesResult;
import com.example.springsqsrediselasticache.model.Message;
import com.example.springsqsrediselasticache.service.QueueService;
import com.example.springsqsrediselasticache.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/")
public class Controller {

    @Autowired
    RedisService redisService;

    QueueService queueService = new QueueService();

    @GetMapping("/queue/create")
    public String createQueue() {
        return queueService.createSQS();
    }

    @GetMapping("/queue/list")
    public List<String> listQueue() {
        ListQueuesResult queueList = queueService.listQueues();
        return queueList.getQueueUrls();
    }

    @GetMapping("/queue/sendMessage/{queueUrl}")
    public void sendQueueMessage(@PathVariable String queueUrl,
                                 @RequestParam(value = "id", defaultValue = "0") String id,
                                 @RequestParam(value = "content", defaultValue = "") String content) {
        Message message = new Message(content, Integer.parseInt(id));
        queueService.sendQueueMSG(message, queueUrl);
    }

    @GetMapping("/queue/getMessages/{queueUrl}")
    public void getQueueMessages(@PathVariable String queueUrl) {
        queueService.getQueueMSGs(queueUrl);
    }

    @GetMapping("/queue/getAllMessages")
    public void getAllMessages() {
        queueService.getAllMSGs();
    }

    @GetMapping("/elasticache/message/{id}")
    public Message getById(@PathVariable Integer id) {
        return redisService.findById(id);
    }

    @GetMapping("/elasticache/messages")
    public Map<Integer, Message> getAll() {
        return redisService.findAll();
    }
}
