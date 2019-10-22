package com.santiago.portal.security.core;

import com.santiago.portal.entity.domain.PmsOperator;
import com.santiago.portal.entity.domain.PmsRole;
import com.santiago.portal.service.OperatorService;
import com.santiago.portal.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class LoginValidateAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private OperatorService operatorService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String rawPassword = (String) authentication.getCredentials();
        PmsOperator user = (PmsOperator) operatorService.loadUserByUsername(username);
        if (user.isEnabled()) {
            throw new DisabledException("该账户已被禁用，请联系管理员");
        } else if (user.isAccountNonLocked()) {
            throw new LockedException("该账号已被锁定");
        } else if (user.isAccountNonExpired()) {
            throw new AccountExpiredException("该账号已过期，请联系管理员");
        } else if (user.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException("该账户的登录凭证已过期，请重新登录");
        }
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new BadCredentialsException("输入密码错误!");
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        List<PmsRole> roleList = roleService.listByOperatorId(user.getId());
        roleList.forEach(sysRole -> grantedAuthorities.add(new SimpleGrantedAuthority(sysRole.getRoleCode())));
        user.setAuthorities(grantedAuthorities);
        return new UsernamePasswordAuthenticationToken(user, rawPassword, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
