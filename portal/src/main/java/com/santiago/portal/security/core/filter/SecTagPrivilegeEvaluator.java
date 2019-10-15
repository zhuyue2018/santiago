package com.santiago.portal.security.core.filter;

import com.santiago.portal.entity.domain.PmsMenu;
import com.santiago.portal.entity.domain.PmsRole;
import com.santiago.portal.service.MenuService;
import com.santiago.portal.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.access.WebInvocationPrivilegeEvaluator;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author OZY
 * @Date 2019/07/19 20:38
 * @Description 标签权限认证
 * @Version V1.0
 **/
@Component
public class SecTagPrivilegeEvaluator implements WebInvocationPrivilegeEvaluator {

    @Autowired
    MenuService menuService;
    @Autowired
    RoleService roleService;

    @Override
    public boolean isAllowed(String uri, Authentication authentication) {
        return true;
    }

    /**
     * 判断是否有该资源权限
     * @param contextPath
     * @param uri
     * @param method
     * @param authentication
     * @return
     */
    @Override
    public boolean isAllowed(String contextPath, String uri, String method, Authentication authentication) {

        //判断该角色是否存在访问该资源的权限
        //默认每个用户对应一个角色，所以这里就直接获取
        GrantedAuthority grantedAuthority = authentication.getAuthorities().iterator().next();
        //循环角色获取当前角色
        PmsRole role = roleService.getByRoleCode(grantedAuthority.getAuthority());
        List<PmsMenu> menuList = menuService.listByRoleId(role.getId());
        for (PmsMenu menu : menuList) {
            if (uri.equals(menu.getUrl())) {
                //如果存在则证明有权限访问
                return true;
            }
        }
        //无权限则返回false
        return false;
    }

}
