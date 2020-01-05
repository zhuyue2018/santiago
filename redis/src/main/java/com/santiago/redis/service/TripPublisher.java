package com.santiago.redis.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

public class TripPublisher {
	private static final Logger LOGGER = LoggerFactory.getLogger(TripPublisher.class);
	RedisTemplate<?, ?> redisTemplate;
	ChannelTopic topic;
	public TripPublisher(RedisTemplate<?, ?> redisTemplate, ChannelTopic topic) {
		this.redisTemplate = redisTemplate;
		this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer(Trip.class));
		this.topic = topic;
	}
	public void publish(Trip trip) throws JsonProcessingException {
		LOGGER.info("Sending: {}", trip);
		redisTemplate.convertAndSend(topic.getTopic(), trip);
	}
}