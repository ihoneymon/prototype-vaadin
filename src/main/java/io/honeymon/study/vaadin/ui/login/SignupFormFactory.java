package io.honeymon.study.vaadin.ui.login;

import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import io.honeymon.study.vaadin.domain.Partner;
import io.honeymon.study.vaadin.service.PartnerService;
import io.honeymon.study.vaadin.ui.CommonComponent;
import io.honeymon.study.vaadin.ui.MainUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import static org.springframework.util.StringUtils.hasText;


/**
 * Created by jake on 05/05/2017.
 */
@org.springframework.stereotype.Component
public class SignupFormFactory {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    PartnerService partnerService;
    @Autowired
    AuthenticationProvider authenticationProvider;

    private class SignupForm implements CommonComponent<SignupForm> {
        private VerticalLayout root;
        private FormLayout signupForm;
        private TextField email;
        private PasswordField password;
        private PasswordField confirmPassword;
        private Button cancelBtn;
        private Button submitBtn;
        private Button loginBtn;

        @Override
        public SignupForm init() {
            root = new VerticalLayout();

            signupForm = new FormLayout();
            signupForm.setSizeFull();
            signupForm.setMargin(true);

            email = new TextField("email");
            password = new PasswordField("Password");
            confirmPassword = new PasswordField("Confirm Password");

            cancelBtn = new Button("Cancel");
            cancelBtn.setStyleName(ValoTheme.BUTTON_FRIENDLY);

            submitBtn = new Button("Submit");
            submitBtn.setStyleName(ValoTheme.BUTTON_PRIMARY);

            loginBtn = new Button("Login");
            loginBtn.setStyleName(ValoTheme.BUTTON_FRIENDLY);

            return this;
        }

        @Override
        public Component layout() {
            Assert.notNull(root, "Required init()");

            root.addComponent(signupForm);

            signupForm.addComponent(email);
            signupForm.addComponent(password);
            signupForm.addComponent(confirmPassword);

            signupForm.addComponent(new HorizontalLayout(cancelBtn, submitBtn));
            signupForm.addComponent(loginBtn);

            cancelBtn.addClickListener(event -> {
                email.clear();
                password.clear();
                confirmPassword.clear();
            });

            submitBtn.addClickListener(event -> {
                try {
                    partnerService.loadUserByUsername(email.getValue());
                    // 이미 있는 계정이다!
                    Notification.show("Error!", "Contact Admin(admin@honeymon.io)", Notification.Type.WARNING_MESSAGE);
                } catch (AuthenticationException ae) {
                    if (!hasText(email.getValue())) {
                        Notification.show("Error!", "Required email", Notification.Type.ERROR_MESSAGE);
                        return;
                    }

                    if (!hasText(password.getValue())) {
                        Notification.show("Error!", "Required password", Notification.Type.ERROR_MESSAGE);
                        return;
                    }

                    if (!hasText(confirmPassword.getValue())) {
                        Notification.show("Error!", "Required password", Notification.Type.ERROR_MESSAGE);
                        return;
                    }

                    if(!password.getValue().equals(confirmPassword.getValue())) {
                        Notification.show("Error!", "Password not matched.", Notification.Type.ERROR_MESSAGE);
                        return;
                    }

                    Partner partner = partnerService.create(email.getValue(), password.getValue());
                    Authentication auth = new UsernamePasswordAuthenticationToken(email.getValue(), password.getValue(), partner.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationProvider.authenticate(auth));
                    UI.getCurrent().getPage().setLocation(MainUI.PATH);
                }
            });

            loginBtn.addClickListener(event -> {
                UI.getCurrent().getPage().setLocation(LoginUI.PATH);
            });

            return root;
        }
    }

    public Component createComponent() {
        return new SignupForm().init().layout();
    }
}
