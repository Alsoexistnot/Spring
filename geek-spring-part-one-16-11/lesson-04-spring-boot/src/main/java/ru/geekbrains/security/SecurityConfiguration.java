package ru.geekbrains.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;

@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    public void authConfig(AuthenticationManagerBuilder auth,
                           UserDetailsService userDetailsService,
                           PasswordEncoder encoder) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("mem_user")
                .password(encoder.encode("password"))
                .roles("SUPER_ADMIN")
                .and()
                .withUser("guest")
                .password(encoder.encode("password"))
                .roles("GUEST");

        auth.userDetailsService(userDetailsService);
    }

    @Configuration
    public static class UiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/**/*.css", "/**/*.js").permitAll()
                    .antMatchers("/product/**").permitAll()
                    .antMatchers("/user/**").hasAnyRole("SUPER_ADMIN")
                    .and()
                    .formLogin()
                    //.loginPage("/login")
                    .defaultSuccessUrl("/product")
                    .and()
                    .exceptionHandling()
                    .accessDeniedPage("/access_denied");
        }
    }
}
