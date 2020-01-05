package com.santiago.redis.config;

import com.santiago.redis.service.TripPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;

@Configuration
public class TripConfiguration {
	@Autowired
	RedisTemplate<?, ?> redisTemplate;
	@Bean
	TripPublisher redisPublisher() {
		return new TripPublisher(redisTemplate, topic());
	}
	@Bean
	ChannelTopic topic() {
		return new ChannelTopic("trips");
	}
}