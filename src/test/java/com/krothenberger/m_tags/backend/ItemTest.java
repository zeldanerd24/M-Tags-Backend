package com.krothenberger.m_tags.backend;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by kevinrothenberger on 12/12/14.
 */
public class ItemTest {

    private Item emptyTestItem;
    private Item testItem;

    @Before
    public void setUp(){
        emptyTestItem = new Item();
        testItem = new Item(2, "Testing", "http://test.com/testing");
    }

    @Test
    public void getItemIdTest() throws Exception {
        assertEquals(2, testItem.getItemId());
    }

    @Test
    public void getItemNameTest() throws Exception {
        assertEquals("Testing", testItem.getItemName());
    }

    @Test
    public void getItemUrlTest() throws Exception {
        assertEquals("http://test.com/testing", testItem.getItemUrl());
    }

    @Test
    public void setItemIdTest() throws Exception {
        assertEquals(0, emptyTestItem.getItemId());
        emptyTestItem.setItemId(3);
        assertEquals(3, emptyTestItem.getItemId());
    }

    @Test
    public void setItemNameTest() throws Exception {
        assertEquals(null, emptyTestItem.getItemName());
        emptyTestItem.setItemName("name");
        assertEquals("name", emptyTestItem.getItemName());
    }

    @Test
    public void setItemUrlTest() throws Exception {
        assertEquals(null, emptyTestItem.getItemUrl());
        emptyTestItem.setItemUrl("http://url.org/url");
        assertEquals("http://url.org/url", emptyTestItem.getItemUrl());
    }

}
