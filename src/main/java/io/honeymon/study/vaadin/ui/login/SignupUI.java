package io.honeymon.study.vaadin.ui.login;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.themes.ValoTheme;
import io.honeymon.study.vaadin.ui.common.GenericUI;

/**
 * Created by jake on 05/05/2017.
 */
@Title("Login page of WB Partner System")
@Theme(ValoTheme.THEME_NAME)
@SpringUI(path = SignupUI.PATH)
public class SignupUI extends GenericUI<SignupFormFactory> {
    public static final String PATH = "/sign-up";
}
