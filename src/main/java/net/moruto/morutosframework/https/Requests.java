package net.moruto.morutosframework.https;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Requests {
    public static RequestResponse sendHttpRequest(RequestOptions options) throws IOException {
        URL url = new URL(options.getUrl());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        switch (options.getRequestType()) {
            case GET -> connection.setRequestMethod("GET");
            case POST -> connection.setRequestMethod("POST");
            case DELETE -> connection.setRequestMethod("DELETE");
        }

        RequestResponse requestResponse = new RequestResponse(connection);

        return requestResponse;
    }
}
