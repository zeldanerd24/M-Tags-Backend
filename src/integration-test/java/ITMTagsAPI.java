import com.google.appengine.repackaged.org.apache.http.HttpResponse;
import com.google.appengine.repackaged.org.apache.http.HttpStatus;
import com.google.appengine.repackaged.org.apache.http.client.HttpClient;
import com.google.appengine.repackaged.org.apache.http.client.methods.HttpGet;
import com.google.appengine.repackaged.org.apache.http.impl.client.DefaultHttpClient;
import com.google.appengine.repackaged.org.apache.http.util.EntityUtils;
import com.google.appengine.repackaged.org.codehaus.jackson.map.DeserializationConfig;
import com.google.appengine.repackaged.org.codehaus.jackson.map.ObjectMapper;
import com.krothenberger.m_tags.backend.Item;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by kevinrothenberger on 12/19/14.
 */
public class ITMTagsAPI {

    @Test
    public void listItemsE2ETest() throws Exception {

        HttpClient client = new DefaultHttpClient();

        HttpResponse response = client.execute(new HttpGet("http://localhost:8080/_ah/api/mtags/v1/items"));

        ArrayList<Item> actualItems = getResource(response);

        ArrayList<Item> expectedItems = new ArrayList<>();
        expectedItems.add(new Item(1, "Test", "www.test.com"));
        expectedItems.add(new Item(2, "Test2", "www.test2.com"));

        assertEquals(expectedItems.size(), actualItems.size());

        for(int i=0; i<expectedItems.size(); i++) {
            assertEquals(expectedItems.get(i), actualItems.get(i));
        }
    }

    @Test
    public void getItemE2ETest() throws Exception {

        HttpClient client = new DefaultHttpClient();

        HttpResponse response = client.execute(new HttpGet("http://localhost:8080/_ah/api/mtags/v1/items/1"));

        Item actualItem = getResource(response);

        Item expectedItem = new Item(1, "Test", "www.test.com");

        assertEquals(HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
        assertEquals(expectedItem, actualItem);

    }

    private static <T> T getResource(HttpResponse response) throws Exception {
        String json = EntityUtils.toString(response.getEntity());

        ObjectMapper mapper = new ObjectMapper().configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        if(json.contains("[")) {
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(json);

            JSONArray array = (JSONArray) object.get("items");

            ArrayList<Item> items = new ArrayList<>();

            for(Object item: array) {
                items.add(mapper.readValue(item.toString(), Item.class));
            }

            return (T) items;
        } else {
            return (T) mapper.readValue(json, Item.class);
        }
    }

}
