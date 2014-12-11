package com.krothenberger.m_tags.backend;

/**
 * Created by Kevin on 12/8/2014.
 */
public class Item {

    private int item_id;
    private String item_name;
    private String item_url;

    public Item() {}

    public Item(int item_id, String item_name, String item_url) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_url = item_url;
    }

    public String getItem_url() {
        return item_url;
    }

    public void setItem_url(String item_url) {
        this.item_url = item_url;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

}
