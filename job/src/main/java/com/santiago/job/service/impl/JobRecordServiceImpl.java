package com.santiago.job.service.impl;

import com.santiago.job.entity.domain.JobRecord;
import com.santiago.job.mapper.JobRecordMapper;
import com.santiago.job.service.JobRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobRecordServiceImpl implements JobRecordService {
    @Autowired
    JobRecordMapper jobRecordMapper;
    @Override
    public void insert(JobRecord jobRecord) {
        jobRecordMapper.insert(jobRecord);
    }
}
