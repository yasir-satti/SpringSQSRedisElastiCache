package com.example.springsqsrediselasticache.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.AmazonSQSException;
import com.amazonaws.services.sqs.model.ListQueuesResult;
import com.example.springsqsrediselasticache.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
public class QueueServiceTests {

    @Mock
    private UserRepository userRepositoryMock;
    @Mock
    private AmazonSQS sqs;

    @Mock
    private RedisService redisService;

    @InjectMocks
    private QueueService queueService;

    @Test
    public void shouldReturnQueueStringWhenCreatingNewQueue() {

        /*
        Story:

        Given i have AWS account credentials
        When i try to create a SQS queue
        Then i get back name of the created SQS queue
        */

        String expectedQUEUE_NAME = "test_queue" + new Date().getTime();
        RedisService redisService = new RedisService(userRepositoryMock);
        QueueService queueService = new QueueService(sqs, redisService);

        String actualQUEUE_NAME = queueService.createSQS();

        Assertions.assertEquals(expectedQUEUE_NAME, actualQUEUE_NAME);
    }

    @Test
    public void shouldThrowExceptionWhenTryingToCreateNewQueueButAlreadyExists() {

        /*
        Story:

        Given a SQS queue exists with name x
        When i try to create a SQS queue with same name x
        Then exception is thrown with error message "QueueAlreadyExists"
        */

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

    @Test
    public void shouldReturnListOFQueuesWhenRequested() {

        /*
        Story:

        Given there are a list of SQS queues already exist
        When i try to get the list of these SQS queues
        Then i get back a list containing the names of these SQS queues
        */

        ListQueuesResult listQueuesResult = new ListQueuesResult();
        listQueuesResult.setQueueUrls(Arrays.asList("SQS1", "SQS2"));

         doReturn(listQueuesResult).when(sqs).listQueues();

        ListQueuesResult response = queueService.listQueues();

        assertThat(response).isEqualTo(listQueuesResult);
    }

    @Test
    void shouldSendMessageToQueueAndSaveToCacheWhenGivenValidQueueUrlAndMessage() {

        /*
        //TODO
        Story:

        Given a SQS queues and ElastiCache exist
        When i try to send message SQS queue and save to ElastiCache
        Then message is put in SQS queues, and saved to ElastiCache
        */
    }

    @Test
    void shouldReturnMessagesFromValidQueueUrl() {
        /*
        //TODO
        Story:

        Given a SQS queue exists and have messages in it
        When i try to get the messages from the SQS queue
        Then i get back the messages in SQS queue
        */
    }

    @Test
    void shouldReturnAllMessagesFromAllValidQueueUrls() {
        /*
        //TODO
        Story:

        Given a list of SQS queues exist and have messages in them
        When i try to get all the messages from all the SQS queues
        Then i get back all the messages from all the SQS queues
        */
    }
}
