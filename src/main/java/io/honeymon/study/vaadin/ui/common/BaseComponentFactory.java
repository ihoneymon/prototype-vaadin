package io.honeymon.study.vaadin.ui.common;

import com.vaadin.ui.Component;

/**
 * Created by jake on 09/06/2017.
 */
public interface BaseComponentFactory {
    /**
     * Create {@link Component} of UI implementations
     *
     * @return
     */
    Component createComponent();
}
