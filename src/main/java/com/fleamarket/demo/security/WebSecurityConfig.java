package com.fleamarket.demo.security;//

import com.fleamarket.demo.jwt.JwtAuthenticationFilter;
import com.fleamarket.demo.jwt.JwtTokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .exposedHeaders("X-AUTH-TOKEN")
//                .allowCredentials(true)
//                .allowedOrigins("http://localhost:3000");
//    }

    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/h2-console/**");
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .cors().configurationSource(corsConfigurationSource());

        http.csrf().disable();
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .addFilterBefore(new JwtAuthenticationFilter(this.jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        http.csrf().disable()
                .authorizeRequests()
                //요청을 오직 유저가 해야함
                .antMatchers("/main/nickname").authenticated()
                .antMatchers("/item/update").authenticated()
                .antMatchers("/item/show/image").authenticated()
                .antMatchers("/user/info").authenticated()
                // /about 요청에 대해서는 로그인을 요구함
//                .antMatchers("/api/comment/**").authenticated()
                // /admin 요청에 대해서는 ROLE_ADMIN 역할을 가지고 있어야 함
//                .antMatchers("/api/comment/**").hasRole("USER")
                // 나머지 요청에 대해서는 로그인을 요구하지 않음
                .anyRequest().permitAll()
                .and()
                // [로그아웃 기능]
                .logout()
                // 로그아웃 요청 처리 URL
                .logoutUrl("/user/logout")
                .permitAll();



    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        // config.addAllowedOrigin("*");
        config.addAllowedOriginPattern("*"); // addAllowedOriginPattern("*") 대신 사용
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    public WebSecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }
}
