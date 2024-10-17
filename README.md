# Spring Cloud with AWS SQS and ElastiCache using Redis

Create SQS queue, and send messages to the queue. Then the m,essages can be saved to the ElastiCache.

1. Send data to SQS queue
2. Store data in ElastiCache
3. Retrieve data from ElastiCache

## Requirements
* [AWS account](https://aws.amazon.com/free/?gclid=CjwKCAjw68K4BhAuEiwAylp3ko0OccFRSrnu8XOqzlRX0DezWP2mRS2n_ZF-SbFA2XCDK66gfBXvKRoCiF0QAvD_BwE&trk=ce1f55b8-6da8-4aa2-af36-3f11e9a449ae&sc_channel=ps&ef_id=CjwKCAjw68K4BhAuEiwAylp3ko0OccFRSrnu8XOqzlRX0DezWP2mRS2n_ZF-SbFA2XCDK66gfBXvKRoCiF0QAvD_BwE:G:s&s_kwcid=AL!4422!3!433803620870!e!!g!!aws%20account!9762827897!98496538463&all-free-tier.sort-by=item.additionalFields.SortRank&all-free-tier.sort-order=asc&awsf.Free%20Tier%20Types=*all&awsf.Free%20Tier%20Categories=*all)
* Spring Boot
* Java 17

## Preperations

* Create ElastiCache db with Redis
* populate the ElastiCache db name and endpoint into the application.properties file. Here is a [sample file](application.properties-example).

## API endpoints

### SQS queue
* Create SQS queue --> ```/queue/create```
* List SQS queues --> ```/queue/list```
* Send message to SQS queue --> ```/queue/sendMessage/{queueUrl}```
- - Takes 2 parameters: id + content
- - example ```/queue/sendMessage/{queueUrl}?id=1+content=test```
* Retreive messages from a SQS queue --> ```/queue/getMessages/{queueUrl}```
* Retreive all messages in all SQS queues --> ```/queue/getAllMessages```

### ElastiCache

* Retreive a message from ElastiCache --> ```/elasticache/message/{id}```
* Retreive all messages from ElastiCache --> ```/elasticache/messages```

## Spring Boot
* Spring Cloud

## AWS Resources

* EC2
* SQS
* ElastiCache using Redis OSS

