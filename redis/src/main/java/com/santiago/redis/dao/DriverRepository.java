package com.santiago.redis.dao;

import com.santiago.redis.domain.entity.Driver;
import org.springframework.data.repository.CrudRepository;

public interface DriverRepository extends CrudRepository<Driver, Long> {}