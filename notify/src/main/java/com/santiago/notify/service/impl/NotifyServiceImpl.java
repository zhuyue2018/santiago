package com.santiago.notify.service.impl;

import com.santiago.commons.enums.ErrorCodeEnum;
import com.santiago.commons.enums.StatusEnum;
import com.santiago.notify.entity.domain.NotifyRecord;
import com.santiago.notify.mapper.NotifyRecordMapper;
import com.santiago.notify.service.NotifyService;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class NotifyServiceImpl implements NotifyService {
    private static final Logger logger = LoggerFactory.getLogger(NotifyServiceImpl.class);
    @Autowired
    NotifyRecordMapper notifyRecordMapper;

    @Override
    public void insertNotifyRecord(NotifyRecord record) {
        notifyRecordMapper.insert(record);
    }

    @Override
    public void doNotify(NotifyRecord record) {
        // 通知商户
        String respCode = notifyMerchant(record);
        // 成功则更新status为1 失败则增加通知次数
        if (ErrorCodeEnum.SUCCESS.getCode().equals(respCode)) {
            record.setStatus(StatusEnum.SUCCESS.getCode());
        }
        record.setNotifyTimes(record.getNotifyTimes() + 1);
        record.setGmtModified(new Date());
        record.setEditor("job");
        notifyRecordMapper.updateByPrimaryKey(record);
    }

    @Override
    public void batchNotify() {
        LocalDateTime now = LocalDateTime.now();
        Date time = now.minusHours(9).toDate();
        Example example = new Example(NotifyRecord.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andGreaterThan("gmtCreate", time)
                .andEqualTo("status", StatusEnum.INIT.getCode())
                .andLessThanOrEqualTo("notifyTimes", 10);
        List<NotifyRecord> recordList = notifyRecordMapper.selectByExample(example);
        if (recordList.size() > 0) {
            recordList.forEach(record -> doNotify(record));
            logger.info("time:{},通知完成", now.toString());
        } else {
            logger.info("time:{},没有须通知的记录", now.toString());
        }
    }

    private String notifyMerchant(NotifyRecord record) {
        String url = record.getUrl();
        String merchantNo = record.getMerchantNo();
        String merchantOrderNo = record.getMerchantOrderNo();
        String orderStatus = record.getOrderStatus();
//        OrderNotifyRequest orderNotifyRequest = new OrderNotifyRequest(merchantNo, merchantOrderNo, orderStatus);
//        String resp = HttpUtils.sendSimpleJsonPost(url, JsonUtil.create().objectToJson(orderNotifyRequest));
        return "2";
    }
}
