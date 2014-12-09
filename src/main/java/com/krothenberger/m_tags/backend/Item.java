package com.krothenberger.m_tags.backend;

/**
 * Created by Kevin on 12/8/2014.
 */
public class Item {

    private String text;

    public Item() {}

    public Item(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
