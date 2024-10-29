package com.example.springsqsrediselasticache.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.AmazonSQSException;
import com.example.springsqsrediselasticache.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.mockito.Mockito.doThrow;

@SpringBootTest
public class QueueServiceTests {

    @Mock
    UserRepository userRepositoryMock;

    @Autowired
    AmazonSQS sqs;

    @Test
    public void CreateQueue_ReturnQueueString() {

        String expectedQUEUE_NAME = "test_queue" + new Date().getTime();
        RedisService redisService = new RedisService(userRepositoryMock);
        QueueService queueService = new QueueService(sqs, redisService);

        String actualQUEUE_NAME = queueService.createSQS();

        Assertions.assertEquals(expectedQUEUE_NAME, actualQUEUE_NAME);
    }

    @Test
    public void CreateQueue_ThrowAmazonSQSException() {

        String QUEUE_NAME = "test_queue" + new Date().getTime();
        RedisService redisService = new RedisService(userRepositoryMock);
        QueueService queueService = new QueueService(sqs, redisService);

        doThrow(AmazonSQSException.class).when(sqs.createQueue(QUEUE_NAME));

        AmazonSQSException amazonSQSException = Assertions.assertThrows(AmazonSQSException.class,
                () -> {
                    String actualQUEUE_NAME = queueService.createSQS();
        });

        String expectedMessage = "QueueAlreadyExists";
        String actualMessage = amazonSQSException.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }
}
