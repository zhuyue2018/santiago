package com.santiago.portal.security.core.permission;

import com.santiago.portal.entity.domain.PmsMenu;
import com.santiago.portal.entity.domain.PmsRole;
import com.santiago.portal.service.MenuService;
import com.santiago.portal.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author OZY
 * @Date 2019/07/16 09:08
 * @Description 权限资源管理器
 * @Version V1.0
 **/
@Component
public class WebSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;

    /**
     * 判断用户请求的url是否在权限表中，如果在权限表中，则返回给decide决策
     * 简单的说，就是每当需要验证权限的时候，都需要从改方法中加载权限，如存在该权限则返回给decide去判断，不存在权限则直接无法访问
     * 该方法不做权限判断
     * @param object 用户请求基本信息
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    @SuppressWarnings("unchecked")
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        List<PmsRole> roleList = roleService.list();
        //转换FilterInvocation类,里面存储了用户请求的基本信息,如request,response等
        FilterInvocation fi = (FilterInvocation) object;
        List<ConfigAttribute> configAttributeList = new ArrayList<>();
        //循环判断角色集合
        for (PmsRole role : roleList) {
            //循环判断角色下的资源，如果有与该url配对的，就证明该角色有权限访问
            for (PmsMenu menu : menuService.listByRoleId(role.getId())) {
                if (new AntPathRequestMatcher(menu.getUrl()).matches(fi.getRequest())) {
                    //AntPathRequestMatcher为Url匹配器，方法matches如果两Url匹配(相同)则会返回true
                    configAttributeList.add(new SecurityConfig(role.getRoleCode()));
                    break;
                }
            }
        }
        return configAttributeList;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

}
