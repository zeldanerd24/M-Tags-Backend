package com.krothenberger.m_tags.backend;

import com.krothenberger.m_tags.util.ConnectDB;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.ext.h2.H2Connection;
import org.h2.jdbcx.JdbcConnectionPool;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.junit.runners.JUnit4;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by Kevin on 12/17/2014.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(ConnectDB.class)
public class MTagsAPITest {

    private IDatabaseTester tester;

    @Before
    public void setUp() throws Exception {
        mockStatic(ConnectDB.class);
        initMocks(this);

        DataSource ds = JdbcConnectionPool.create("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "user", "password");
        Connection connection = ds.getConnection();


        if(connection.createStatement().executeUpdate("create table test(item_id int,item_name varchar(200),item_url varchar(200));") != 0){
            throw new Exception("Create table failed");
        }

        tester = new JdbcDatabaseTester("org.h2.Driver", "jdbc:h2:mem:testdb", "user", "password");
        IDataSet dataSet = new FlatXmlDataSet(getClass().getClassLoader().getResourceAsStream("testdataset.xml"));

        tester.setDataSet(dataSet);
        tester.onSetup();

        when(ConnectDB.getDBConnection()).thenReturn(connection);
    }

    @After
    public void tearDown() throws Exception {
        tester.onTearDown();
    }

    @Test
    public void listItemsTest() throws Exception {

    }

}
