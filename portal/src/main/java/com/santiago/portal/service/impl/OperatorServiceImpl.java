package com.santiago.portal.service.impl;

import com.github.pagehelper.PageInfo;
import com.santiago.portal.entity.domain.PmsOperator;
import com.santiago.portal.entity.dto.query.OperatorQuery;
import com.santiago.portal.entity.dto.vo.OperatorVO;
import com.santiago.portal.mapper.PmsOperatorMapper;
import com.santiago.portal.service.OperatorService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author OZY
 * @Date 2019/08/09 20:42
 * @Description
 * @Version V1.0
 **/
@Service
public class OperatorServiceImpl implements OperatorService, UserDetailsService {

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
}
