package com.example.acorpas.androidchatmvp.lib;

/**
 * Created by a.corpas on 26/07/2016.
 */
public interface EventBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}
