package com.spsvn.api.domain.type;

/**
 * Created by npkhanh on 6/8/2018.
 */
public enum Client {
    CLIENT_A("ClientA"),
    CLIENT_B("ClientB");

    String value;

    Client(String value) {
        this.value = value;
    }
}
