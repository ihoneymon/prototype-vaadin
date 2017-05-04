package io.honeymon.study.vaadin.ui;

import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import io.honeymon.study.vaadin.ui.login.LoginUI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by jake on 05/05/2017.
 */
@Slf4j
@org.springframework.stereotype.Component
public class MainComponentFactory {

    private class MainComponent {
        private VerticalLayout root;
        private Label label;
        private Button logoutBtn;


        public MainComponent init() {
            //TODO 화면구성
            root = new VerticalLayout();

            label = new Label("준비중");

            logoutBtn = new Button("Logout");
            logoutBtn.setStyleName(ValoTheme.BUTTON_DANGER);

            return this;
        }

        public Component layout() {
            root.addComponent(label);
            root.addComponent(logoutBtn);

            logoutBtn.addClickListener(event -> {
                SecurityContextHolder.clearContext();
                Notification.show("Success!", "Logout", Notification.Type.HUMANIZED_MESSAGE);
                UI.getCurrent().getPage().setLocation(LoginUI.PATH);
            });
            return root;
        }
    }

    public Component createComponent() {
        return new MainComponent().init().layout();
    }
}
