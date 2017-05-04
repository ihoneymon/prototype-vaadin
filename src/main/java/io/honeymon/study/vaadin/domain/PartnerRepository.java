package io.honeymon.study.vaadin.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jake on 24/04/2017.
 */
public interface PartnerRepository extends JpaRepository<Partner, Long> {
    Partner findByUsername(String username);
}
