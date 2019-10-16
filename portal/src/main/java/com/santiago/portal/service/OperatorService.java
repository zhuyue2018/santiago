package com.santiago.portal.service;


import com.github.pagehelper.PageInfo;
import com.santiago.portal.entity.domain.PmsOperator;
import com.santiago.portal.entity.dto.query.OperatorQuery;
import com.santiago.portal.entity.dto.vo.OperatorVO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface OperatorService extends UserDetailsService {
    PageInfo<OperatorVO> page(OperatorQuery queryDTO);

    void insert(PmsOperator operator);

    void deleteByPrimaryKey(Long id);

    List<String> listMerchantNoByOperatorId(Long id);
}
