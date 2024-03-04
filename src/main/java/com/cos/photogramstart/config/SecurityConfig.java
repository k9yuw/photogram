package com.cos.photogramstart.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration  // IoC에등록
public class SecurityConfig {

    @Bean
    BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/", "/user/**", "/image/**", "/subscribe/**", "/comment/**", "/api/**").authenticated() // 해당 URL로 매핑되는건 인증이 필요하고
                .anyRequest().permitAll() // 그외의 모든 요청은 허용하겠다.
                .and()
                .formLogin()
                .loginPage("/auth/signin") // 사용자가 인증이 필요한 URL로 요청하면(GET 방식) /auth/signin으로 리다이렉트.
                .loginProcessingUrl("/auth/signin") // POST(로그인)로 요청시 리다이렉트
                .defaultSuccessUrl("/"); // 1-9. 1-8이 정상적으로 처리가 되었으면 /로 이동.

        return http.build();
    }
}
