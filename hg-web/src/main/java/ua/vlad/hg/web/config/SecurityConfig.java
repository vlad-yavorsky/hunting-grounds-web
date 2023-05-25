package ua.vlad.hg.web.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/",
                                "/api/**",
                                "/error**",
                                "/css/**",
                                "/img/**",
                                "/js/**",
                                "/favicon.ico",
                                "/webjars/**",
                                "/administrator/login",
                                "/administrator/signup",
                                "/administrator/logout",
                                "/administrator/international",
                                "/administrator/theme").permitAll()
                        .requestMatchers("/administrator/**").authenticated()
                )
                .formLogin()
                .loginPage("/administrator/login")
                .defaultSuccessUrl("/administrator")
                .failureUrl("/administrator/login?error")
                .and()
                .logout()
                .logoutUrl("/administrator/logout")
                .logoutSuccessUrl("/administrator/login?logout");
        return http.build();
    }

}
