package com.santiago.portal.service.impl;

import com.github.pagehelper.PageInfo;
import com.santiago.commons.enums.StatusEnum;
import com.santiago.portal.entity.domain.PmsOperator;
import com.santiago.portal.entity.dto.query.OperatorQuery;
import com.santiago.portal.entity.dto.request.MerchantInsertReq;
import com.santiago.portal.entity.dto.vo.OperatorVO;
import com.santiago.portal.mapper.PmsOperatorMapper;
import com.santiago.portal.service.OperatorService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author OZY
 * @Date 2019/08/09 20:42
 * @Description
 * @Version V1.0
 **/
@Service
public class OperatorServiceImpl implements OperatorService {

    @Resource
    private PmsOperatorMapper operatorMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PmsOperator pmsOperatorTemp = new PmsOperator();
        pmsOperatorTemp.setLoginName(username);
        PmsOperator operator = operatorMapper.selectOne(pmsOperatorTemp);
        if (operator == null) {
            throw new UsernameNotFoundException("不存在该用户!");
        }
        return operator;
    }


    @Override
    public PageInfo<OperatorVO> page(OperatorQuery queryDTO) {
        return null;
    }

    @Override
    public void insert(PmsOperator operator) {
        operatorMapper.insert(operator);
    }

    @Override
    public void deleteByPrimaryKey(Long id) {
        operatorMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<String> listMerchantNoByOperatorId(Long id) {
        return null;
    }

    @Override
    public PmsOperator create(long version, String status, String creater, MerchantInsertReq req) {
        PmsOperator operator = new PmsOperator();
        operator.setGmtCreate(new Date());
        operator.setGmtModified(new Date());
        operator.setVersion(1L);
        operator.setStatus(StatusEnum.SUCCESS.getCode());
        operator.setCreater("portal");
        operator.setRealName(req.getInsertRealName());
        operator.setMobileNo(req.getInsertMobile());
        operator.setLoginName(req.getInsertMerchantName());
        operator.setLoginPwd(req.getInsertPassword());
        operator.setType("todo");
        operator.setSalt("todo");
        operatorMapper.insert(operator);
        return operator;
    }
}
