package com.santiago.core.service.impl;

import com.santiago.core.entity.domain.MerchantPayProduct;
import com.santiago.core.entity.domain.TradeRecord;
import com.santiago.core.entity.dto.response.PreOrderResponse;
import com.santiago.core.mapper.MerchantPayProductMapper;
import com.santiago.core.mapper.TradeOrderMapper;
import com.santiago.core.mapper.TradeRecordMapper;
import com.santiago.core.service.ChannelInteractService;
import com.santiago.core.service.ChannelSendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class ChannelSendServiceImpl implements ChannelSendService{
    private static final Logger logger = LoggerFactory.getLogger(ChannelSendServiceImpl.class);
    @Autowired
    TradeOrderMapper orderMapper;
    @Autowired
    TradeRecordMapper recordMapper;
    @Autowired
    MerchantPayProductMapper merchantPayProductMapper;


    @PostMapping("/channel/preOrder")
    public PreOrderResponse preOrder(TradeRecord tradeRecord, String payProductCode) {
        MerchantPayProduct merchantPayProduct = getByMerchantNoAndPayProductCode(tradeRecord.getMerchantNo(), payProductCode);
        if (null == merchantPayProduct) {
            logger.warn("merchantOrderNo:{},trxNo:{},merchantNo:{},productCode:{},payProduct not available");
        }
        ChannelInteractService channel = (ChannelInteractService) SpringContextUtil.getBean(merchantPayProduct.getPayProductCode());
        return channel.interact(tradeRecord);
    }

    private MerchantPayProduct getByMerchantNoAndPayProductCode(String merchantNo, String payProductCode) {
        MerchantPayProduct merchantPayProductTemp = new MerchantPayProduct();
        merchantPayProductTemp.setMerchantNo(merchantNo);
        merchantPayProductTemp.setPayProductCode(payProductCode);
        MerchantPayProduct merchantPayProduct = merchantPayProductMapper.selectOne(merchantPayProductTemp);
        return merchantPayProduct;
    }





}
