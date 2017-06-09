package io.honeymon.study.vaadin.ui.common;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by jake on 09/06/2017.
 */
public class GenericUI<F extends BaseComponentFactory> extends UI {
    @Autowired
    F factory;

    @Override
    protected void init(VaadinRequest request) {
        setContent(factory.createComponent());
    }
}
