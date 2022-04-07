package com.example.Config;

import com.example.Service.UserDetailsServiceImpl;

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

        @Autowired
        UserDetailsServiceImpl detailsService;

        @Bean
        public BCryptPasswordEncoder bCryptPasswordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
                auth.userDetailsService(detailsService).passwordEncoder(bCryptPasswordEncoder());
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

                http.authorizeRequests()
                                .antMatchers("/admin", "/admin/**")
                                .hasAuthority("ADMIN")
                                .antMatchers("/seller", "/seller/**")
                                .hasAnyAuthority("ADMIN", "SELLER")
                                .antMatchers("/customer", "/customer/**")
                                .hasAuthority("CUSTOMER")
                                .anyRequest().permitAll();

                http.formLogin()
                                .loginPage("/member/login")
                                .loginProcessingUrl("/member/login")
                                .usernameParameter("uemail")
                                .passwordParameter("upw")
                                .defaultSuccessUrl("/home")
                                .permitAll();

                http.logout()
                                .logoutUrl("/member/logout")
                                .logoutSuccessUrl("/home")
                                .invalidateHttpSession(true)
                                .clearAuthentication(true)
                                .permitAll();

                // h2-console을 이용하기위해서
                http.csrf().ignoringAntMatchers("/h2-console/**");
                http.headers().frameOptions().sameOrigin();

                // 접근권한 불가
                http.exceptionHandling().accessDeniedPage("/page403");

                // rest controller사용
                http.csrf().ignoringAntMatchers("/api/**");

        }

}
