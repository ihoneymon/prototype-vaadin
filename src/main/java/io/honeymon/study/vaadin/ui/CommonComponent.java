package io.honeymon.study.vaadin.ui;


import com.vaadin.ui.Component;

/**
 * Created by jake on 05/05/2017.
 */
public interface CommonComponent<T> {
    /**
     * 화면구성에 필요한 컴포넌트 초기화
     *
     * @return
     */
    T init();

    /**
     * {@link CommonComponent#init()} 을 통해 초기화된 컴포넌트를 이용하여 화면구성
     *
     * @return
     */
    Component layout();
}
