package com.proba.statperson.utils;

import com.squareup.otto.Bus;

/**
 * Created by Konstantin on 31.05.2016.
 */
public class EventBus extends Bus {
    private static final EventBus bus = new EventBus();

    public static Bus getInstance() {
        return bus;
    }

    private EventBus() {}
}
