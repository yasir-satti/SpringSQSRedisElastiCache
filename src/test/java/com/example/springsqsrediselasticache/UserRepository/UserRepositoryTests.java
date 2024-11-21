package com.example.springsqsrediselasticache.UserRepository;

import com.example.springsqsrediselasticache.model.Message;
import com.example.springsqsrediselasticache.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.redis.core.HashOperations;

public class UserRepositoryTests {

    @Mock
    HashOperations hashOperationsMock;

    private UserRepository userRepository;

    @Test
    public void UserRepository_saveMessage(){
        Message testMessage = Message
                .builder()
                .id(1)
                .content("test")
                .build();

        userRepository.save(testMessage);

    }

    @Test
    public void UserRepository_findAll_ReturnListOfMessages() {

    }
}
