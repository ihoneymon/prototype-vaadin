package io.honeymon.study.vaadin.config;

import io.honeymon.study.vaadin.integ.security.PartnerUserDetailAuthenticationProvider;
import io.honeymon.study.vaadin.integ.security.SecurityAuthenticationFailureHandler;
import io.honeymon.study.vaadin.integ.security.SecurityAuthenticationSuccessHandler;
import io.honeymon.study.vaadin.service.PartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

/**
 * Created by jake on 26/04/2017.
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    PartnerService partnerService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //@formatter:off
        http.csrf().disable().exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"));

        /**
         * VAADIN 은 SPA 방식으로 동작한다.
         */
        http.authorizeRequests()
                .antMatchers("/VAADIN/**", "/vaadinServlet/**").permitAll()
                .antMatchers("/login", "/login/**", "/logout", "/reset-password", "/reset-password/**", "/sign-up", "/sign-up/**").permitAll()
                .anyRequest().fullyAuthenticated();

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logged-out")
                .deleteCookies("JSESSIONID").permitAll();

        //@formatter:on
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    AuthenticationFailureHandler authenticationFailureHandler() {
        return new SecurityAuthenticationFailureHandler();
    }

    @Bean
    AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new SecurityAuthenticationSuccessHandler();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        PartnerUserDetailAuthenticationProvider authenticationProvider = new PartnerUserDetailAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setPartnerService(partnerService);

        return authenticationProvider;
    }
}
