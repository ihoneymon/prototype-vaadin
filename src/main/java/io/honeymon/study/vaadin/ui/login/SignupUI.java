package io.honeymon.study.vaadin.ui.login;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by jake on 05/05/2017.
 */
@Title("Login page of WB Partner System")
@Theme(ValoTheme.THEME_NAME)
@SpringUI(path = SignupUI.PATH)
public class SignupUI extends UI {
    public static final String PATH = "/sign-up";

    @Autowired
    SignupFormFactory signupFormFactory;

    @Override
    protected void init(VaadinRequest request) {
        setContent(signupFormFactory.createComponent());
    }
}
