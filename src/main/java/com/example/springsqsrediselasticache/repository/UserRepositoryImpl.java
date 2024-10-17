package com.example.springsqsrediselasticache.repository;

import com.example.springsqsrediselasticache.model.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.data.redis.core.HashOperations;

import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository{

    private RedisTemplate<String, Message> redisTemplate;
    private HashOperations hashOperations;

    public UserRepositoryImpl(RedisTemplate<String, Message> redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(Message message) {
        hashOperations.put("${redisdb}", message.getId(), message);
    }

    @Override
    public Map<Integer, Message> findAll() {
        return hashOperations.entries("${redisdb}");
    }

    @Override
    public Message findById(Integer id) {
        return (Message) hashOperations.get("${redisdb}", id);
    }

    @Override
    public void update(Message message) {
        save(message);
    }

    @Override
    public void delete(Integer id) {
        hashOperations.delete("${redisdb}", id);
    }
}
