package io.honeymon.study.vaadin.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by jake on 24/04/2017.
 */
@Data
@Entity
public class Partner implements UserDetails {
    @Id
    @GeneratedValue
    private Long id;

    private String code;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = PartnerRole.class, fetch = FetchType.EAGER)
    private List<PartnerRole> roleAuthorities;

    private boolean expired;

    private boolean locked;

    @CreationTimestamp
    private Date created;

    @UpdateTimestamp
    private Date updated;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoleAuthorities();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
