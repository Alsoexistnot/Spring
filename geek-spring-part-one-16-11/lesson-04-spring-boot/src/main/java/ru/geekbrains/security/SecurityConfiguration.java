package ru.geekbrains.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {

    @Autowired
    public void authConfig(AuthenticationManagerBuilder auth,
                           UserDetailsService userDetailsService,
                           PasswordEncoder encoder) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(encoder.encode("123"))
                .roles("ADMIN")
                .and()
                .withUser("guest")
                .password(encoder.encode("123"))
                .roles("GUEST")
                .and()
                .withUser("manager")
                .password(encoder.encode("123"))
                .roles("MANAGER")
                .and()
                .withUser("super-admin")
                .password(encoder.encode("123"))
                .roles("ADMIN", "SUPER_ADMIN");

        auth.userDetailsService(userDetailsService);
    }


    @Configuration
    @Order(1)
    public static class ApiUiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/api/**")
                    .authorizeRequests()
                    .anyRequest().permitAll()
                    .and()
                    .httpBasic()
                    .and()
                    .csrf().disable()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        }
    }

    @Configuration
    @Order(2)
    public static class UiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/**/*.css", "/**/*.js").permitAll()
                    .antMatchers("/product/**").permitAll()
                    .antMatchers("/user/**").hasAnyRole("ADMIN")
                    .and()
                    .formLogin()
                    .permitAll()
                    .loginPage("/login_form")
                    .loginProcessingUrl("/login")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/product")
                    .and()
                    .exceptionHandling()
                    .accessDeniedPage("/access_denied");
        }
    }
}