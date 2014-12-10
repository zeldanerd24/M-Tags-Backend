package com.krothenberger.m_tags.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.appengine.api.utils.SystemProperty;

import java.sql.*;
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

    @ApiMethod(name = "test", httpMethod = "get", path = "test")
    public Item test() {
        String url = null;
        try {
            if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
                Class.forName("com.mysql.jdbc.GoogleDriver");
                url = "jdbc:google:mysql://zippy-haiku-785:tagsql/test";
            }
            else {
                Class.forName("com.mysql.jdbc.Driver");
                url = "jdbc:mysql://127.0.0.1:3306/test";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        try {
            Connection conn = DriverManager.getConnection(url, "krothenberger", "zeldanerd24");
            conn.close();
            return new Item("Success!");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
