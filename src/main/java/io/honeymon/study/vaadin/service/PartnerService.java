package io.honeymon.study.vaadin.service;

import io.honeymon.study.vaadin.domain.Partner;
import io.honeymon.study.vaadin.domain.PartnerRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by jake on 26/04/2017.
 */
public interface PartnerService extends GenericService<Partner, Long, PartnerRepository>, UserDetailsService {
    /**
     * Reset Password of Email
     * <ol>
     * <li>Reset password</li>
     * <li>Send Email or Alert</li>
     * </ol>
     *
     * @param email
     * @throws UsernameNotFoundException
     */
    void resetPassword(String email) throws UsernameNotFoundException;

    /**
     * Create {@link Partner}
     * @param username
     * @param password
     */
    Partner create(String username, String password);
}
