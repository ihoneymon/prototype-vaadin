package io.honeymon.study.vaadin.ui.login;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by jake on 04/05/2017.
 */
@Slf4j
@Title("Partner reset of account")
@Theme(ValoTheme.THEME_NAME)
@SpringUI(path = ResetPasswordUI.PATH)
public class ResetPasswordUI extends UI {
    public static final String PATH = "/reset-password";

    @Autowired
    ResetPasswordFactory resetpasswordFactory;

    @Override
    protected void init(VaadinRequest request) {
        log.info("Reset password[IP: {}]", request.getRemoteAddr());
        setContent(resetpasswordFactory.createComponent());
    }
}
