package com.santiago.message.service;

import java.util.List;

import com.santiago.message.domain.MsgRetryDTO;


/**
 * @author: net.easipay.ec.code.generator.CodeGeneratorBuilder
 * @create: 2019-07-24 13:22:54
 **/
public interface MsgRetryManager {


    MsgRetryDTO getByOrderNo(String orderNo);



    int updateSuccessStatus(String orderNo);

    int updateRetryCount(MsgRetryDTO psMsgRetryDTO);
    int update(MsgRetryDTO dto);

    void insert(MsgRetryDTO psMsgRetryDTO);
}