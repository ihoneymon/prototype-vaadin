package io.honeymon.study.vaadin.ui.login;

import com.vaadin.server.ClassResource;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import io.honeymon.study.vaadin.ui.MainUI;
import io.honeymon.study.vaadin.ui.common.BaseComponent;
import io.honeymon.study.vaadin.ui.common.BaseComponentFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 로그인구성
 * Created by jake on 04/05/2017.
 */
@Slf4j
@org.springframework.stereotype.Component
public class LoginFormFactory implements BaseComponentFactory {

    @Autowired
    MessageSource messageSource;
    @Autowired
    AuthenticationProvider authenticationProvider;

    @Override
    public Component createComponent() {
        return new LoginForm().init().layout();
    }

    private class LoginForm implements BaseComponent<LoginForm> {
        private HorizontalLayout root;
        private VerticalLayout leftBodyLayout;
        private VerticalLayout rightBodyLayout;
        private HorizontalLayout header;
        private HorizontalLayout body;
        private FormLayout loginForm;
        private HorizontalLayout footer;

        private Image loginImg;
        private TextField username;
        private PasswordField password;
        private Button loginBtn;
        private Button signupBtn;
        private Button resetPasswordBtn;

        @Override
        public LoginForm init() {
            root = new HorizontalLayout();
            root.setMargin(true);
            root.setSizeFull();

            leftBodyLayout = new VerticalLayout();
            leftBodyLayout.setHeight("100%");

            loginImg = new Image(null, new ClassResource("/static/img/login.jpg"));
            loginImg.setHeight("100%");

            rightBodyLayout = new VerticalLayout();
            rightBodyLayout.setWidth("60%");
            rightBodyLayout.setHeight("100%");

            header = new HorizontalLayout();
            header.addComponent(new Label("Prototype Partner System"));

            body = new HorizontalLayout();
            body.setSizeFull();

            footer = new HorizontalLayout();
            footer.setHeight("10%");

            loginForm = new FormLayout();
            loginForm.addComponent(new Label("Login"));
            loginForm.setMargin(true);

            username = new TextField("Username");
            password = new PasswordField("Password");

            loginBtn = new Button("Login");
            loginBtn.setStyleName(ValoTheme.BUTTON_FRIENDLY);

            signupBtn = new Button("Sign Up");
            signupBtn.setStyleName(ValoTheme.BUTTON_PRIMARY);

            resetPasswordBtn = new Button("Reset Password");
            loginBtn.setStyleName(ValoTheme.BUTTON_PRIMARY);

            return this;
        }

        @Override
        public Component layout() {
            root.addComponent(leftBodyLayout);
            root.addComponent(rightBodyLayout);

            leftBodyLayout.addComponent(loginImg);
            leftBodyLayout.setComponentAlignment(loginImg, Alignment.BOTTOM_RIGHT);

            rightBodyLayout.addComponent(header);
            rightBodyLayout.addComponent(body);
            body.addComponent(loginForm);
            loginForm.addComponent(username);
            loginForm.addComponent(password);
            loginForm.addComponent(new HorizontalLayout(loginBtn, signupBtn));
            loginForm.addComponent(resetPasswordBtn);
            loginForm.setSizeUndefined();
            loginForm.setMargin(true);

            rightBodyLayout.addComponent(footer);

            loginBtn.addClickListener(event -> {
                log.debug("Username: {}, Password: {}", username.getValue(), password.getValue());
                try {
                    Authentication auth = new UsernamePasswordAuthenticationToken(username.getValue(), password.getValue());
                    SecurityContextHolder.getContext().setAuthentication(authenticationProvider.authenticate(auth));
                    //TODO 로그인 완료 후
                    UI.getCurrent().getPage().setLocation(MainUI.PATH);
                } catch (AuthenticationException ae) {
                    log.error("Occur Exception: {}", ae.toString());
                    Notification.show("Error!", ae.getMessage(), Notification.Type.ERROR_MESSAGE);
                }
            });

            signupBtn.addClickListener(e -> UI.getCurrent().getPage().setLocation(SignupUI.PATH));

            resetPasswordBtn.addClickListener(e -> UI.getCurrent().getPage().setLocation(ResetPasswordUI.PATH));

            return root;
        }

    }
}
