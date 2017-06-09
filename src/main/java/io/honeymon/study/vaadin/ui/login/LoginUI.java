package io.honeymon.study.vaadin.ui.login;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import io.honeymon.study.vaadin.ui.common.GenericUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

/**
 * Created by jake on 04/05/2017.
 */
@Title("Login page of WB Partner System")
@Theme(ValoTheme.THEME_NAME)
@SpringUI(path = LoginUI.PATH)
public class LoginUI extends GenericUI<LoginFormFactory> {
    public static final String PATH = "/login";
}
