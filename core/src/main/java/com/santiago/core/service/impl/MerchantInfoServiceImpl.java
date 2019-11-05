package com.santiago.core.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.santiago.commons.enums.PublicStatusEnum;
import com.santiago.core.entity.domain.MerchantInfo;
import com.santiago.core.entity.vo.MerchantInfoVO;
import com.santiago.core.mapper.MerchantInfoMapper;
import com.santiago.core.service.MerchantInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MerchantInfoServiceImpl implements MerchantInfoService {
    @Autowired
    MerchantInfoMapper merchantInfoMapper;
    @Override
    public void createDefault(MerchantInfo merchantInfo) {
        merchantInfo.setGmtCreate(new Date());
        merchantInfo.setGmtModified(new Date());
        merchantInfo.setStatus(PublicStatusEnum.ACTIVE.getCode());
        merchantInfoMapper.insert(merchantInfo);
    }


    @Override
    public List<MerchantInfo> list(MerchantInfo merchantInfo) {
        return merchantInfoMapper.select(merchantInfo);
    }

    @Override
    public PageInfo page() {
        PageHelper.startPage(1, 10);
        List<MerchantInfo> merchantInfoList = merchantInfoMapper.selectAll();
        PageInfo pageInfo = new PageInfo<>(merchantInfoList);
        List<MerchantInfoVO> VOList = transfer2VO(merchantInfoList);
        pageInfo.setList(VOList);
        return pageInfo;
    }

    private List<MerchantInfoVO> transfer2VO(List<MerchantInfo> merchantInfoList) {
        ArrayList<MerchantInfoVO> merchantInfoVOS = new ArrayList<>();
        merchantInfoList.forEach(merchantInfo -> {
            MerchantInfoVO merchantInfoVO = new MerchantInfoVO();
            merchantInfoVO.setMerchantNo(merchantInfo.getMerchantNo());
            merchantInfoVO.setMerchantName(merchantInfo.getMerchantName());
            merchantInfoVO.setAccountNo(merchantInfo.getAccountNo());
            merchantInfoVOS.add(merchantInfoVO);
        });
        return merchantInfoVOS;
    }
}
