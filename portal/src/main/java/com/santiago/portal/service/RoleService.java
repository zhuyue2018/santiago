package com.santiago.portal.service;

import com.github.pagehelper.PageInfo;
import com.santiago.portal.entity.domain.PmsRole;
import com.santiago.portal.entity.dto.query.RoleQuery;
import com.santiago.portal.entity.dto.vo.RoleVO;

import java.util.List;

public interface RoleService {
    void insert(PmsRole role);
    void deleteByPrimaryKey(Long id);
    PmsRole getByRoleCode(String roleCode);
    List<PmsRole> list();
    List<PmsRole> listByOperatorId(Long id);
    PageInfo<RoleVO> page(RoleQuery queryDTO);
}
