package com.krothenberger.m_tags.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.krothenberger.m_tags.util.ConnectDB;

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

    @ApiMethod(name = "list.items", httpMethod = "get", path = "items")
    public ArrayList<Item> listItems() {
        Connection conn = null;
        ResultSet resultSet = null;
        ArrayList<Item> items = new ArrayList<>();
        try {
            conn = ConnectDB.getDBConnection();

            PreparedStatement statement = conn.prepareStatement("select * from Items", ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            resultSet = statement.executeQuery();

            while(resultSet.next()) {
                items.add(new Item(resultSet.getInt("item_id"), resultSet.getString("item_name"), resultSet.getString("item_url")));
            }

            return items;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @ApiMethod(name = "get.item", httpMethod = "get", path = "items/{itemId}")
    public Item getItem(@Named("itemId") int itemId) {
        Connection conn = null;
        ResultSet resultSet = null;
        try {
            conn = ConnectDB.getDBConnection();

            PreparedStatement statement = conn.prepareStatement("select * from Items where item_id=?", ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            statement.setInt(1, itemId);

            resultSet = statement.executeQuery();

            if(resultSet != null && resultSet.next()) {
                return new Item(resultSet.getInt("item_id"), resultSet.getString("item_name"), resultSet.getString("item_url"));
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
