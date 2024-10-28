package com.example.springsqsrediselasticache.service;

import com.example.springsqsrediselasticache.model.Message;
import com.example.springsqsrediselasticache.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class RedisService {

    private final UserRepository userRepository;

    public void save(Message message){
        userRepository.save(message);
    }

    public Message findById(Integer id) {
        return userRepository.findById(id);
    }

    public Map<Integer, Message> findAll() {
        return userRepository.findAll();
    }
}
