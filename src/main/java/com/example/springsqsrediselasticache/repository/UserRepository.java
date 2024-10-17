package com.example.springsqsrediselasticache.repository;

import com.example.springsqsrediselasticache.model.Message;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserRepository {
    void save(Message message);
    Map<Integer, Message> findAll();
    Message findById(Integer id);
    void update(Message message);
    void delete(Integer id);
}
