package com.krothenberger.m_tags.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;

import java.util.ArrayList;

/**
  * Add your first API methods in this class, or you may create another class. In that case, please
  * update your web.xml accordingly.
 **/
@Api(
        name = "mtags",
        version = "v1",
        scopes = {Constants.EMAIL_SCOPE},
        clientIds = {Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID, Constants.IOS_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID},
        audiences = {Constants.ANDROID_AUDIENCE}
)
public class MTagsAPI {

    public static ArrayList<Item> items = new ArrayList<>();

    static {
        items.add(new Item("Test1"));
        items.add(new Item("Test2"));
    }

    @ApiMethod(name = "list.items", httpMethod = "get", path = "items")
    public ArrayList<Item> listItems() {
        return items;
    }
}
