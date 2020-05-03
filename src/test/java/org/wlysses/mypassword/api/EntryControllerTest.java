package org.wlysses.mypassword.api;

import com.sun.deploy.net.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EntryControllerTest {

    @Test
    public void addEntryTest() throws IOException {
        String jsonMimeType = "application/json";
        HttpUriRequest request = new HttpGet( "https://api.github.com/users/eugenp" );

        CloseableHttpResponse response = HttpClientBuilder.create().build().execute( request );

        String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
        assertEquals( jsonMimeType, mimeType );
    }

}