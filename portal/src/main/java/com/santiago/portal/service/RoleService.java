package com.santiago.portal.service;

import com.github.pagehelper.PageInfo;
import com.santiago.portal.entity.domain.PmsRole;
import com.santiago.portal.entity.dto.query.RoleQuery;
import com.santiago.portal.entity.dto.vo.RoleVO;

import java.util.List;

public interface RoleService {
    List<PmsRole> listByOperatorId(Long id);
    PmsRole getByRoleCode(String roleCode);
    List<PmsRole> list();
    PageInfo<RoleVO> page(RoleQuery queryDTO);
}
