package com.example.springsqsrediselasticache;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.ListQueuesResult;
import com.example.springsqsrediselasticache.service.QueueService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableSqs
@SpringBootApplication
public class SpringSqsRedisElastiCacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSqsRedisElastiCacheApplication.class, args);

/*        QueueService queueService = new QueueService();
        queueService.createSQS();
        ListQueuesResult queueList = queueService.listQueues();
        queueService.sendQueueMSG("Hello", queueList.getQueueUrls().get(0));*/
    }
}
