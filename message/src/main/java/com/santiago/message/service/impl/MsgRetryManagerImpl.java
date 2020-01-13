package com.santiago.message.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.santiago.message.domain.MsgRetryDO;
import com.santiago.message.domain.MsgRetryDTO;
import com.santiago.message.domain.MsgRetryStatusEnum;
import com.santiago.message.service.MsgRetryManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;


/**
 * @author: net.easipay.ec.code.generator.CodeGeneratorBuilder
 * @create: 2019-07-24 13:22:54
 **/
@Service
public class MsgRetryManagerImpl implements MsgRetryManager {
    private static final Logger logger = LoggerFactory.getLogger(MsgRetryManagerImpl.class);


    @Override
    public MsgRetryDTO getByOrderNo(String orderNo) {
        if (StringUtils.isEmpty(orderNo)) {
            return null;
        }
        MsgRetryDTO dto = new MsgRetryDTO();
        dto.setOrderNo(orderNo);
        List<MsgRetryDTO> lists = listPsMsgRetry(dto);
        if (CollectionUtils.isEmpty(lists)) {
            logger.warn("orderNo {} not exist", orderNo);
            return null;
        }
        if (lists.size() > 1) {
            logger.warn("orderNo {} get multiOrder", orderNo);
        }
        return lists.get(0);
    }

    private List<MsgRetryDTO> listPsMsgRetry(MsgRetryDTO dto) {
        return null;
    }


    @Override
    public int updateSuccessStatus(String orderNo) {
        return updateStatus(orderNo, MsgRetryStatusEnum.S.name());
    }

    private int updateStatus(String orderNo, String name) {
        return 0;
    }

    @Override
    public int updateRetryCount(MsgRetryDTO psMsgRetryDTO) {
        psMsgRetryDTO.setLastSendTime(new Date());
        psMsgRetryDTO.setUpdateTime(new Date());
        psMsgRetryDTO.setRetryCount(psMsgRetryDTO.getRetryCount().add(new BigDecimal("1")));
        return update(psMsgRetryDTO);
    }


    @Override
    public int update(MsgRetryDTO dto) {
//        return psMsgRetryDao.updateByPrimaryKeySelective(getConverter().fromDto(dto));
        return 0;
    }

    @Override
    public void insert(MsgRetryDTO psMsgRetryDTO) {

    }

}
