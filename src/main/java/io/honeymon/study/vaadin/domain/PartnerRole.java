package io.honeymon.study.vaadin.domain;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

/**
 * Created by jake on 26/04/2017.
 */
@Getter
public enum PartnerRole implements GrantedAuthority {
    /**
     * 관리자
     */
    ADMIN,
    /**
     * 파트너
     */
    PARTNER;

    @Override
    public String getAuthority() {
        return toString();
    }
}
