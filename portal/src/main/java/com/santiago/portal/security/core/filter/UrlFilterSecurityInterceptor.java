package com.santiago.portal.security.core.filter;

import com.santiago.portal.security.core.permission.WebAccessDecisionManager;
import com.santiago.portal.security.core.permission.WebSecurityMetadataSource;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import java.io.IOException;

/**
 * @Author OZY
 * @Date 2019/08/14 10:36
 * @Description 自定义Url过滤器，AbstractSecurityInterceptor为对资源访问过滤的抽象类
 * @Version V1.0
 **/
@Component
public class UrlFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

    //权限资源管理器
    @Resource
    private WebSecurityMetadataSource webSecurityMetadataSource;

    //注入决策器
    @Resource
    public void setWebAccessDecisionManager(WebAccessDecisionManager webAccessDecisionManager) {
        super.setAccessDecisionManager(webAccessDecisionManager);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        FilterInvocation fi = new FilterInvocation(servletRequest, servletResponse, filterChain);
        //访问资源的权限校验方法
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.webSecurityMetadataSource;
    }


}