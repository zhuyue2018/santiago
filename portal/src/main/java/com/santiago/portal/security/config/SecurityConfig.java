package com.santiago.portal.security.config;

import com.santiago.portal.security.core.LoginValidateAuthenticationProvider;
import com.santiago.portal.security.core.filter.SecTagPrivilegeEvaluator;
import com.santiago.portal.security.core.filter.UrlFilterSecurityInterceptor;
import com.santiago.portal.security.core.handler.LoginFailureHandler;
import com.santiago.portal.security.core.handler.LoginSuccessHandler;
import com.santiago.portal.security.core.handler.PerAccessDeniedHandler;
import com.santiago.portal.service.impl.OperatorServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @Author OZY
 * @Date 2019/08/08 13:59
 * @Description
 * @Version V1.0
 **/

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private DataSource dataSource;
    @Resource
    private OperatorServiceImpl operatorService;
    @Resource
    private LoginValidateAuthenticationProvider loginValidateAuthenticationProvider;
    @Resource
    private UrlFilterSecurityInterceptor urlFilterSecurityInterceptor;
    @Resource
    private LoginSuccessHandler loginSuccessHandler;
    @Resource
    private LoginFailureHandler loginFailureHandler;
    @Resource
    private PerAccessDeniedHandler perAccessDeniedHandler;
    @Resource
    private SecTagPrivilegeEvaluator secTagPrivilegeEvaluator;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //基础设置
        http.httpBasic()//配置HTTP基本身份验证
            .and()
                .authorizeRequests()
                .anyRequest().authenticated()//所有请求都需要认证
//                .antMatchers("static/**").permitAll()
            .and()
                .formLogin() //登录表单
                .loginPage("/login")//登录页面url
                .loginProcessingUrl("/login")//登录验证url
                .defaultSuccessUrl("/index")//成功登录跳转
                .successHandler(loginSuccessHandler)//成功登录处理器
                .failureHandler(loginFailureHandler)//失败登录处理器
                .permitAll()//登录成功后有权限访问所有页面
            .and()
                .rememberMe()//记住我功能
                .userDetailsService(operatorService)//设置用户业务层
                .tokenRepository(persistentTokenRepository())//设置持久化token
                .tokenValiditySeconds(24 * 60 * 60); //记住登录1天(24小时 * 60分钟 * 60秒)
        //关闭csrf跨域攻击防御
        http.csrf().disable();
        //设置权限不足handler
        http.exceptionHandling().accessDeniedHandler(perAccessDeniedHandler);
        //添加url拦截
        http.addFilterBefore(urlFilterSecurityInterceptor, FilterSecurityInterceptor.class);
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        //自定义标签权限配置
        web.privilegeEvaluator(secTagPrivilegeEvaluator);
//        web.ignoring().antMatchers("/static/**");
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //这里要设置自定义认证
        auth.authenticationProvider(loginValidateAuthenticationProvider);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        //启动的时候创建表，这里只执行一次，第二次就注释掉，否则每次启动都重新创建表
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

}
