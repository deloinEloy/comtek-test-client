package com.compek;

import java.io.Serializable;

/**
 * Тестовый объект для передачи
 * */
public class SampleObject implements Serializable {
    private final String text;

    public SampleObject(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
