package com.example.springsqsrediselasticache.controller;

import com.amazonaws.services.sqs.model.ListQueuesResult;
import com.example.springsqsrediselasticache.service.QueueService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class ControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    private QueueService queueService;

    @Test
    void shouldCreateNewQueue() {

        /*
        //TODO
        Story:

        Given i have AWS account credentials
        When i try to create new SQS queue
        Then a new queue is created, and i get back the name of the queue
        */
    }

    @Test
    void shouldThrowExceptionWhenCreatingQueueWithInvalidCredentials() {

        /*
        //TODO
        Story:

        Given i have invalid AWS account credentials
        When i try to create new SQS queue
        Then an exception is thrown
        */
    }

    @Test
    public void shouldReturnListOfExistingQueues() throws Exception {

        ListQueuesResult queueList = new ListQueuesResult();
        List<String> queueUrls = Arrays.asList("");

        doReturn(queueList).when(queueService).listQueues();
        doReturn(queueUrls).when(queueList).getQueueUrls();

        mockMvc.perform(get("/queue/list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnEmptyListWhenNoQueuesFound() {

        /*
        //TODO
        Story:

        Given there is no existing queues
        When i try to get list of all existing queues
        Then it returns a message saying "No existing queues"
        */
    }

    @Test
    void shouldSendMessageToQueueAndSaveToElastiCache() {

        /*
        //TODO
        Story:

        Given a valid existing queue url and message
        When i try to send the message to the queue
        Then the message is added to the queue
        */
    }

    @Test
    void shouldThrowExceptionWhenSendingMessageToInvalidQueue() {

        /*
        //TODO
        Story:

        Given an invalid queue url and valid message
        When i try to send the message to the queue
        Then exception is thrown that the queue url is invalid
        */
    }

    @Test
    void shouldThrowExceptionWhenSendingInvalidMessageToValidQueue() {

        /*
        //TODO
        Story:

        Given a valid queue url and invalid message
        When i try to send the invalid message to the queue
        Then exception is thrown that the message is invalid
        */
    }

    @Test
    void shouldReturnAllMessagesFromValidQueue() {

        /*
        //TODO
        Story:

        Given a valid queue url that has a valid list of messages
        When i try to get the valid messages from the queue
        Then it returns list of all messages in the queue
        */
    }

    @Test
    void shouldReturnEmptyListFromValidQueue() {

        /*
        //TODO
        Story:

        Given a valid queue url that has no messages
        When i try to get the messages from the queue
        Then it returns message saying "No messages in the queue"
        */
    }

    @Test
    void shouldThrowExceptionWhenGettingMessagesFromInvalidQueue() {

        /*
        //TODO
        Story:

        Given an invalid queue url
        When i try to get the messages from the queue
        Then it returns message saying "Invalid queue url"
        */
    }

    @Test
    void shouldReturnAllMessagesFromAllValidQueues() {

        /*
        //TODO
        Story:

        Given a valid list of queue urls that have messages in them
        When i try to get all the messages from the queues
        Then it returns list of all messages from the valid queues
        */
    }

    @Test
    void shouldReturnWarningThatAllValidQueuesHaveNoMessages() {

        /*
        //TODO
        Story:

        Given a valid list of queue urls that have no messages in them
        When i try to get all the messages from the queues
        Then it returns a message saying "There are no messages in these queues"
        */
    }

    @Test
    void shouldThrowExceptionWhenNoQueuesExist() {

        /*
        //TODO
        Story:

        Given no queues exist
        When i try to get all the messages from the queues
        Then it returns a message saying "There are no existing queues"
        */
    }

    @Test
    void shouldReturnMessageFromElasticAsheById() {

        /*
        //TODO
        Story:

        Given a message existing in ElastiCache
        When i try to get the message by its Id from ElastiCahce
        Then it returns the message
        */
    }

    @Test
    void shouldThrowExceptionWhenNoMessageByIdWasFoundInElastiCache() {

        /*
        //TODO
        Story:

        Given no message with the requested Id exists in ElastiCache
        When i try to get the message by using this invalid Id
        Then it returns the message saying "Provided Id is invalid"
        */
    }

    @Test
    void shouldReturnAllMessagesStoredInElastiCache() {

        /*
        //TODO
        Story:

        Given valid messages exist in ElastiCache
        When i try to get all the messages
        Then it returns list of messages stored in ElastiCache
        */
    }

    @Test
    void shouldReturnEmptyListWhenNoMessagesFoundInElastiCahce() {

        /*
        //TODO
        Story:

        Given no messages exist in ElastiCache
        When i try to get all the messages
        Then it returns a message saying "No messages found in ElastiCache"
        */
    }

    @Test
    void shouldThrowExceptionWhenElastiCacheIsNotAvailable() {

        /*
        //TODO
        Story:

        Given ElastiCache does not exist or unavailable
        When i try to get all the messages
        Then it returns a message saying "ElastiCache not found"
        */
    }
}
