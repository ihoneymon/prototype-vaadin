package io.honeymon.study.vaadin.service;

import io.honeymon.study.vaadin.domain.Partner;
import io.honeymon.study.vaadin.domain.PartnerRepository;
import io.honeymon.study.vaadin.domain.PartnerRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

/**
 * Created by jake on 26/04/2017.
 */
@Slf4j
@Service
public class PartnerServiceImpl extends GenericServiceImpl<Partner, Long, PartnerRepository> implements PartnerService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Load user by Username: {}", username);
        Partner partner = repository.findByUsername(username);
        if (partner == null) {
            throw new UsernameNotFoundException("Username(" + username + ") don't found.");
        }
        return partner;
    }

    @Override
    public void resetPassword(String email) throws UsernameNotFoundException {
        log.debug("Reset password of Email: {}", email);
        try {
            Partner partner = (Partner) loadUserByUsername(email);
            String resetPassword = passwordEncoder.encode(UUID.randomUUID().toString());
            partner.setPassword(resetPassword);
            //TODO SEND EMAIL
        } catch (AuthenticationException ae) {
            log.error("Occur exception: {}", ae.toString());
            //TODO 잘못된 요청에 대한 추가요청
        }
    }

    @Override
    public Partner create(String username, String password) {
        Partner partner = new Partner();
        partner.setUsername(username);
        partner.setPassword(passwordEncoder.encode(password));
        partner.setRoleAuthorities(Arrays.asList(PartnerRole.PARTNER));
        partner.setCreated(new Date());
        return repository.save(partner);
    }
}
