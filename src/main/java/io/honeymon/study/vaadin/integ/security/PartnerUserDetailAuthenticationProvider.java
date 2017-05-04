package io.honeymon.study.vaadin.integ.security;

import io.honeymon.study.vaadin.service.PartnerService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by jake on 04/05/2017.
 */
@Slf4j
@Component
public class PartnerUserDetailAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    @Setter
    @Autowired
    PasswordEncoder passwordEncoder;
    @Setter
    @Autowired
    PartnerService partnerService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            log.error("Authentication fail: no credentials provided.");
            throw new BadCredentialsException("security_exception_bad_credentials");
        }

        String credentialsPassword = authentication.getCredentials().toString();
        if (!passwordEncoder.matches(credentialsPassword, userDetails.getPassword())) {
            log.error("Authentication fail: password does not match stored value.");
            throw new BadCredentialsException("security_exception_bad_credentials");
        }
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        return partnerService.loadUserByUsername(username);
    }
}
