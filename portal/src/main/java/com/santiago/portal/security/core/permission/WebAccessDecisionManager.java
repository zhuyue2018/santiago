package com.santiago.portal.security.core.permission;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class WebAccessDecisionManager implements AccessDecisionManager {



    /**
     * 判断是否拥有权限的决策方法
     * @param authentication
     * @param object
     * @param configAttributes
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object object,
                       Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        //System.out.println(configAttributes.toString());
        if (configAttributes != null && configAttributes.size() > 0) {
            for (ConfigAttribute configAttribute : configAttributes) {
                //用户所拥有的角色权限
                Collection<? extends GrantedAuthority> grantedAuthorities = authentication.getAuthorities();
                for (GrantedAuthority grantedAuthority : grantedAuthorities) {
                    if (configAttribute.getAttribute().equals(grantedAuthority.getAuthority())) {
                        return;
                    }
                }

            }
        }
        //没有权限
        throw new AccessDeniedException("对不起，您当前无该权限访问!");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

}
