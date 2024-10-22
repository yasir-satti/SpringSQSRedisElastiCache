package com.example.springsqsrediselasticache.repository;

import com.example.springsqsrediselasticache.model.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;

import java.util.Map;

public class UserRepository{

    private HashOperations hashOperations;

    public UserRepository(RedisTemplate<String, Message> redisTemplate){
        this.hashOperations = redisTemplate.opsForHash();
    }

    public void save(Message message) {
        hashOperations.put("${redisdb}", message.getId(), message);
    }

    public Map<Integer, Message> findAll() {
        return hashOperations.entries("${redisdb}");
    }

    public Message findById(Integer id) {
        return (Message) hashOperations.get("${redisdb}", id);
    }

    public void update(Message message) {
        save(message);
    }

    public void delete(Integer id) {
        hashOperations.delete("${redisdb}", id);
    }
}
