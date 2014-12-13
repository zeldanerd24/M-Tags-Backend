package com.krothenberger.m_tags.util;

import com.google.appengine.api.utils.SystemProperty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Kevin on 12/13/2014.
 */
public class ConnectDB {

    public static Connection getDBConnection() throws SQLException, ClassNotFoundException {
        if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
            Class.forName("com.mysql.jdbc.GoogleDriver");
            return DriverManager.getConnection("jdbc:google:mysql://zippy-haiku-785:tagsql/mtags_db?user=root");
        }
        else {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mtags_db", "krothenberger", "zeldanerd24");
        }
    }

}
