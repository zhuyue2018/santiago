package com.santiago.redis.domain.entity;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.GeoIndexed;

import javax.persistence.Id;

@RedisHash("driver")
public class Driver {
	@Id
	    private long id;
	private String name;
	@GeoIndexed
	    private Point location;
	private DriverStatus status;
	// setters and getters ...
}