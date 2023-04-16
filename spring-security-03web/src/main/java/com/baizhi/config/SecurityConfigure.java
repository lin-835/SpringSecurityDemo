package com.baizhi.config;

import com.baizhi.security.filter.KaptchaFilter;
import com.baizhi.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 自定义 security 相关配置
 */
@Configuration
public class SecurityConfigure extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailService myUserDetailService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService);
    }

    @Override
    @Bean//暴露处理
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public KaptchaFilter kaptchaFilter() throws Exception {
        KaptchaFilter kaptchaFilter = new KaptchaFilter();
        kaptchaFilter.setFilterProcessesUrl("/doLogin");
        kaptchaFilter.setUsernameParameter("uname");
        kaptchaFilter.setPasswordParameter("passwd");
        kaptchaFilter.setKaptchaParameter("kaptcha");
        //指定认证管理器
        kaptchaFilter.setAuthenticationManager(authenticationManagerBean());
        //指定认证成功处理
        kaptchaFilter.setAuthenticationSuccessHandler((req, resp, auth) -> {
            resp.sendRedirect("/index.html");
        });
        //指定认证失败处理
        kaptchaFilter.setAuthenticationFailureHandler((req, resp, ex) -> {
            resp.sendRedirect("/login.html");
        });
        return kaptchaFilter;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/login.html").permitAll()
                .mvcMatchers("/vc.jpg").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .and()
                .logout()
                .logoutUrl("/logout")
                .and()
                .csrf().disable();

        http.addFilterAt(kaptchaFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
