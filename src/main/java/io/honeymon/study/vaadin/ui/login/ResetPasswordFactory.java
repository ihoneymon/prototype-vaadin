package io.honeymon.study.vaadin.ui.login;

import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import io.honeymon.study.vaadin.service.PartnerService;
import io.honeymon.study.vaadin.ui.CommonComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.util.StringUtils.hasText;

/**
 * Created by jake on 04/05/2017.
 */
@Slf4j
@org.springframework.stereotype.Component
public class ResetPasswordFactory {
    @Autowired
    PartnerService partnerService;

    private class ResetPasswordForm implements CommonComponent<ResetPasswordForm> {
        private HorizontalLayout root;
        private FormLayout resetPasswordForm;
        private Label formLabel;
        private TextField email;
        private Button resetBtn;
        private Button loginBtn;

        @Override
        public ResetPasswordForm init() {
            root = new HorizontalLayout();

            resetPasswordForm = new FormLayout();
            resetPasswordForm.setMargin(true);
            resetPasswordForm.setSizeFull();

            formLabel = new Label("Reset password by Email");

            email = new TextField("Email");

            resetBtn = new Button("Reset");
            resetBtn.setStyleName(ValoTheme.BUTTON_DANGER);

            loginBtn = new Button("Back login");

            return this;
        }

        @Override
        public Component layout() {
            root.addComponent(resetPasswordForm);

            resetPasswordForm.addComponent(formLabel);
            resetPasswordForm.addComponent(email);
            resetPasswordForm.addComponent(new HorizontalLayout(resetBtn, loginBtn));

            resetBtn.addClickListener(event -> {
                log.debug("Reset password of email: {}", email.getValue());
                if (!hasText(email.getValue())) {
                    Notification.show("Error", "Required Email", Notification.Type.WARNING_MESSAGE);
                    return;
                }

                partnerService.resetPassword(email.getValue());
                Notification.show("Success!", "Send email for reset password.", Notification.Type.HUMANIZED_MESSAGE);
            });

            loginBtn.addClickListener(event -> {
                UI.getCurrent().getPage().setLocation(LoginUI.PATH);
            });

            return root;
        }
    }

    public Component createComponent() {
        return new ResetPasswordForm().init().layout();
    }
}
