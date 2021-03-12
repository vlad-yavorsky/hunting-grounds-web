package ua.vlad.hg.web.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/administrator/login",
                        "/administrator/signup",
                        "/administrator/logout",
                        "/administrator/international",
                        "/administrator/theme").permitAll()
                .antMatchers("/administrator/**").authenticated()

                .and()
                .formLogin()
                .loginPage("/administrator/login")
                .defaultSuccessUrl("/administrator")
                .failureUrl("/administrator/login?error")

                .and()
                .logout()
                .logoutUrl("/administrator/logout")
                .logoutSuccessUrl("/administrator/login?logout");
    }

}
