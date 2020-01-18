package ua.vlad.hg.web.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import ua.vlad.hg.core.service.UserService;
import ua.vlad.hg.core.util.Role;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider());
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        return new CustomAuthenticationProvider(userService, passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/administrator/login",
                        "/administrator/signup",
                        "/administrator/logout",
                        "/administrator/international",
                        "/administrator/theme").permitAll()
                .antMatchers("/administrator/**").hasAnyAuthority(Role.ADMIN.name())

                .and()
                .formLogin()
                .loginPage("/administrator/login")
                .defaultSuccessUrl("/administrator/")
                .failureUrl("/administrator/login?error")

                .and()
                .logout()
                .logoutUrl("/administrator/logout")
                .logoutSuccessUrl("/administrator/login?logout");
    }

}
