package com.santiago.core.wss;

import com.santiago.commons.dto.resp.SimpleResponse;
import com.santiago.commons.util.JsonUtil;
import com.santiago.core.entity.domain.MerchantPayProduct;
import com.santiago.core.entity.domain.TradeOrder;
import com.santiago.core.entity.domain.TradeRecord;
import com.santiago.core.entity.dto.WeixinNotifyRequest;
import com.santiago.core.entity.dto.response.PreOrderResponse;
import com.santiago.core.mapper.MerchantPayProductMapper;
import com.santiago.core.mapper.TradeOrderMapper;
import com.santiago.core.mapper.TradeRecordMapper;
import com.santiago.core.service.ChannelInteractService;
import com.santiago.core.service.SpringContextUtil;
import com.santiago.notify.entity.domain.NotifyRecord;
import com.santiago.notify.wss.MerchantNotifyWss;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController()
public class ChannelSendWss {
    private static final Logger logger = LoggerFactory.getLogger(ChannelSendWss.class);
    @Autowired
    TradeOrderMapper orderMapper;
    @Autowired
    TradeRecordMapper recordMapper;
    @Autowired
    MerchantNotifyWss notifyWss;
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
