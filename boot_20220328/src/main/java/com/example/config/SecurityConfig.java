package com.example.config;

import com.example.handler.MyLoginSuccessHandler;
import com.example.handler.MyLogoutSuccessHandler;
import com.example.service.MemberDetailServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 1. 직접만든 detailService 객체 가져오기
    @Autowired
    MemberDetailServiceImpl mService;

    // 2. 암호화방법 객체생성, Bean은 서버 구동시 자동으로 객체생성
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(mService).passwordEncoder(bCryptPasswordEncoder());

    }

    // 직접만든 detailsService에 암호화 방법 적용
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 페이지별 접근 권한 설정
        http.authorizeRequests()
                .antMatchers("/security_admin", "/security_admin/**")
                .hasAuthority("ADMIN")
                .antMatchers("/security_seller", "/security_seller/**")
                .hasAnyAuthority("ADMIN", "SELLER")
                .antMatchers("/security_customer", "/security_customer/**")
                .hasAuthority("CUSTOMER")
                .anyRequest().permitAll();

        // 로그인 페이지 설정, 단 POST는 직접 만들지 않음
        http.formLogin()
                .loginPage("/member/security_login")
                .loginProcessingUrl("/member/security_login")
                .usernameParameter("uemail")
                .passwordParameter("upw")
                .successHandler(new MyLoginSuccessHandler())
                // .defaultSuccessUrl("/security_home")
                .permitAll();

        // 로그아웃 페이지 설정, url에 맞게 POST로 호출하면 됨.
        http.logout()
                .logoutUrl("/member/security_logout")
                .logoutSuccessUrl("/security_home")
                .logoutSuccessHandler(new MyLogoutSuccessHandler())
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .permitAll();

        // 접근권한불가 403
        http.exceptionHandling().accessDeniedPage("/security_403");

        // h2 console 사용을 위해서 임시로
        // http.csrf().disable(); // 보안에 취약
        http.csrf().ignoringAntMatchers("/h2-console/**");
        http.headers().frameOptions().sameOrigin();
    }

}