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

    public String getItemUrl() {
        return item_url;
    }

    public void setItemUrl(String item_url) {
        this.item_url = item_url;
    }

    public int getItemId() {
        return item_id;
    }

    public void setItemId(int item_id) {
        this.item_id = item_id;
    }

    public String getItemName() {
        return item_name;
    }

    public void setItemName(String item_name) {
        this.item_name = item_name;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Item)){
            return false;
        }

        Item test = (Item) obj;

        return (test.getItemId()==item_id && test.getItemName().equals(item_name) && test.getItemUrl().equals(item_url));
    }

}
