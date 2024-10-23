package com.example.springsqsrediselasticache.config;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonSQSConfig {

  @Bean
  public AmazonSQS sqs() {
    return AmazonSQSClientBuilder.defaultClient();
  }
}
