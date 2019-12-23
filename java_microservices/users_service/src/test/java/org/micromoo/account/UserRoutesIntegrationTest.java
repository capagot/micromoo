// Test source code based on
//   https://github.com/mscharhag/blog-examples/blob/master/sparkdemo/src/test/java/com/mscharhag/sparkdemo/UserControllerIntegrationTest.java

package org.micromoo.account;

import spark.Spark;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import java.util.Map;
import java.net.URL;
import java.net.HttpURLConnection;
import spark.utils.IOUtils;
import java.io.IOException;
import java.util.HashMap;
import com.google.gson.Gson;


public class UserRoutesIntegrationTest {
    @BeforeClass
    public static void beforeClass() {
        UserService.main(null);
    }

    @Test
    public void newUserCreattionTest() {
        System.out.println("Testing new user creation.");

        TestResponse response = request("POST", "/users?user_type=0&user_password=011011&user_name=HanSolo");
        Map<String, String> json = response.json();
        assertEquals(200, response.status);
        assertEquals("HanSolo", json.get("user_name"));
        assertEquals("011011", json.get("user_password"));
        assertEquals("0", json.get("user_type"));
        assertNotNull(json.get("user_id"));
    }

    @AfterClass
    public static void afterClass() {
        Spark.stop();
    }

    private TestResponse request(String method, String path) {
        try {
            URL url = new URL("http://localhost:4567" + path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setDoOutput(true);
            connection.connect();
            String body = IOUtils.toString(connection.getInputStream());
            return new TestResponse(connection.getResponseCode(), body);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Sending request failed: " + e.getMessage());
            return null;
        }
    }

    private static class TestResponse {

        public final String body;
        public final int status;

        public TestResponse(int status, String body) {
            this.status = status;
            this.body = body;
        }

        public Map<String,String> json() {
            return new Gson().fromJson(body, HashMap.class);
        }
    }
}