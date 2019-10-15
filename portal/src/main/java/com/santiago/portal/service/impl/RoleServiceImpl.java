package com.santiago.portal.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.santiago.portal.entity.domain.PmsOperatorRole;
import com.santiago.portal.entity.domain.PmsRole;
import com.santiago.portal.entity.dto.query.RoleQuery;
import com.santiago.portal.entity.dto.vo.RoleVO;
import com.santiago.portal.mapper.PmsOperatorRoleMapper;
import com.santiago.portal.mapper.PmsRoleMapper;
import com.santiago.portal.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: dependency
 * @description:
 * @author: zhuyue
 * @create: 2019-10-15 13:02
 **/
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    PmsOperatorRoleMapper operatorRoleMapper;
    @Autowired
    PmsRoleMapper roleMapper;

    @Override
    public List<PmsRole> listByOperatorId(Long id) {
        PmsOperatorRole operatorRoleTemp = new PmsOperatorRole();
        operatorRoleTemp.setOperatorId(id);
        List<PmsOperatorRole> operatorRoles = operatorRoleMapper.select(operatorRoleTemp);
        List<PmsRole> roleList = new ArrayList<>();
        operatorRoles.forEach(pmsOperatorRole -> {
            roleList.add(roleMapper.selectByPrimaryKey(pmsOperatorRole.getRoleId()));
        });
        return roleList;
    }

    @Override
    public void insert(PmsRole role) {
        roleMapper.insert(role);
    }

    @Override
    public void deleteByPrimaryKey(Long id) {
        roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PmsRole getByRoleCode(String roleCode) {
        PmsRole roleTemp = new PmsRole();
        roleTemp.setRoleCode(roleCode);
        PmsRole role = roleMapper.selectOne(roleTemp);
        return role;
    }

    @Override
    public List<PmsRole> list() {
        List<PmsRole> roleList = roleMapper.selectAll();
        return roleList;
    }

    @Override
    public PageInfo<RoleVO> page(RoleQuery queryDTO) {
//        Integer pageNum = null == queryDTO.getPageNum() ? 1 : queryDTO.getPageNum();
//        Integer pageSize = null == queryDTO.getPageSize() ? 10 : queryDTO.getPageSize();
        PageHelper.startPage(queryDTO.getPageNum(), queryDTO.getPageSize());
        List<RoleVO> roleVOList = listVO(queryDTO);
        PageInfo<RoleVO> pageInfo = new PageInfo<>(roleVOList);
        return pageInfo;
    }

    private List<RoleVO> listVO(RoleQuery queryDTO) {
        return null;
    }
}
