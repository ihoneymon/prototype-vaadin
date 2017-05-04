package io.honeymon.study.vaadin.ui;

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
@Title("Main page of WB Partner System")
@SpringUI(path = MainUI.PATH)
@Theme(ValoTheme.THEME_NAME)
public class MainUI extends UI{
    public static final String PATH = "/";

    @Autowired
    MainComponentFactory mainComponentFactory;
    @Override
    protected void init(VaadinRequest request) {
        setContent(mainComponentFactory.createComponent());
    }
}
