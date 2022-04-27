package com.example.config;

import com.example.service.UserDetailsServiceImpl;

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

        // 1. 직접만든 detailService가져오기
        @Autowired
        UserDetailsServiceImpl detailsService;

        // 회원가입시 암호화 했던 방법의객체 생성
        @Bean
        public BCryptPasswordEncoder bCryptPasswordEncoder() {
                return new BCryptPasswordEncoder();
        }

        // 직접만든 detailService에 암호화 방법 사용
        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
                auth.userDetailsService(detailsService).passwordEncoder(bCryptPasswordEncoder());
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
                // super.configure(http);
                http.authorizeRequests()
                                .antMatchers("/admin", "/admin/**")
                                .hasAuthority("ADMIN")
                                .antMatchers("/customer", "/customer/**")
                                .hasAuthority("CUSTOMER")
                                .anyRequest().permitAll();

                // 로그인 페이지 설정 단 POST는 직접 만들지 않음
                http.formLogin()
                                .loginPage("/member/login")
                                .loginProcessingUrl("/member/loginaction")
                                .usernameParameter("mId")
                                .passwordParameter("mPw")
                                .defaultSuccessUrl("/member/home")
                                .permitAll();

                // 로그아웃
                http.logout()
                                .logoutUrl("/member/logout")
                                .logoutSuccessUrl("/member/home")
                                .invalidateHttpSession(true)
                                .clearAuthentication(true)
                                .permitAll();

                // 접근권한 불가
                http.exceptionHandling().accessDeniedPage("/403");

                // h2 console사용을 위해
                http.csrf().ignoringAntMatchers("/h2-console/**");
                http.headers().frameOptions().sameOrigin();

                http.csrf().ignoringAntMatchers("/api/**");
        }

}
