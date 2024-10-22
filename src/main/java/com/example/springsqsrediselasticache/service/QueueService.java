package com.example.springsqsrediselasticache.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.*;

import java.util.Date;
import java.util.List;

import com.example.springsqsrediselasticache.model.Message;
import org.springframework.stereotype.Service;

@Service
public class QueueService {

    RedisService redisService = new RedisService();

    AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();

    public String createSQS (){
        String QUEUE_NAME = "test_queue" + new Date().getTime();
        try {
            sqs.createQueue(QUEUE_NAME);
        } catch(AmazonSQSException e) {
            if (!e.getErrorCode().equals("QueueAlreadyExists")) {
                throw e;
            }
        }
        return  QUEUE_NAME;
    }

    public ListQueuesResult listQueues(){
        ListQueuesResult queueList = sqs.listQueues();
        System.out.println("Your SQS Queue URLs:");
        for (String url: queueList.getQueueUrls()) {
            System.out.println(url);
        }
        return queueList;
    }

    public void sendQueueMSG(Message message, String queueUrl){
        SendMessageRequest request = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                        .withMessageBody(message.getContent())
                                .withDelaySeconds(5);
        sqs.sendMessage(request);
        redisService.save(message);
        System.out.println("Saved message to cache...");
    }

    public void getQueueMSGs(String queueUrl){
        printQueueMessages(queueUrl);
    }

    public void getAllMSGs() {
        ListQueuesResult queueList = sqs.listQueues();
        for (String url: queueList.getQueueUrls()) {
            System.out.println(url);
            printQueueMessages(url);
        }
    }

    private void printQueueMessages(String url) {
        List<com.amazonaws.services.sqs.model.Message> messages = sqs.receiveMessage(url).getMessages();
        for (com.amazonaws.services.sqs.model.Message message : messages) {
            System.out.println(message.getMessageId());
            System.out.println(message.getBody());
        }
    }
}
