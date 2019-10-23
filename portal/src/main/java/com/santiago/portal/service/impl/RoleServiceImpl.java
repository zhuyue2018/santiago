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
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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
        PageHelper.startPage(queryDTO.getPageNum(), queryDTO.getPageSize());
        Example example = new Example(PmsRole.class);
        Example.Criteria criteria = example.createCriteria();
        List<PmsRole> roleList = roleMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(roleList);
        List<RoleVO> VOList = transfer2VO(pageInfo.getList());
        pageInfo.setList(VOList);
        return pageInfo;
    }

    private List<RoleVO> transfer2VO(List<PmsRole> roleList) {
        if (CollectionUtils.isEmpty(roleList)) {
            return null;
        }
        List<RoleVO> VOList = new ArrayList<>();
        roleList.forEach(pmsRole -> {
            RoleVO roleVO = new RoleVO();
            roleVO.setId(pmsRole.getId());
            roleVO.setRoleCode(pmsRole.getRoleCode());
            roleVO.setRoleName(pmsRole.getRoleName());
            VOList.add(roleVO);
        });
        return VOList;
    }

}
