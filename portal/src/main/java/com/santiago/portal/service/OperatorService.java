package com.santiago.portal.service;


import com.github.pagehelper.PageInfo;
import com.santiago.portal.entity.domain.PmsOperator;
import com.santiago.portal.entity.dto.query.OperatorQuery;
import com.santiago.portal.entity.dto.vo.OperatorVO;

public interface OperatorService {
    PageInfo<OperatorVO> page(OperatorQuery queryDTO);

    void insert(PmsOperator operator);

    void deleteByPrimaryKey(Long id);
}
