package com.santiago.portal.service.impl;

import com.santiago.commons.enums.AccountStatusEnum;
import com.santiago.portal.entity.domain.PmsOperatorRole;
import com.santiago.portal.mapper.PmsOperatorRoleMapper;
import com.santiago.portal.service.OperatorRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @program: dependency
 * @description:
 * @author: zhuyue
 * @create: 2019-10-24 13:25
 **/
@Service
public class OperatorRoleServiceImpl implements OperatorRoleService {
    @Autowired
    PmsOperatorRoleMapper operatorRoleMapper;
    @Override
    public PmsOperatorRole create(Long operatorId, Long roleId) {
        PmsOperatorRole operatorRole = new PmsOperatorRole();
        operatorRole.setVersion(1L);
        operatorRole.setCreater("portal");
        operatorRole.setGmtCreate(new Date());
        operatorRole.setGmtModified(new Date());
        operatorRole.setStatus(AccountStatusEnum.ACTIVE.getCode());
        operatorRole.setOperatorId(operatorId);
        operatorRole.setRoleId(roleId);
        operatorRoleMapper.insert(operatorRole);
        return operatorRole;
    }
}
