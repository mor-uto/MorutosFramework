package net.moruto.morutosframework.https;

import java.io.IOException;
import java.net.HttpURLConnection;

public class RequestResponse {
    private final HttpURLConnection connection;
    private final int responseCode;

    public RequestResponse(HttpURLConnection connection) throws IOException {
        this.connection = connection;
        this.responseCode = connection.getResponseCode();
    }

    public boolean isSucess() {
        return responseCode == 200;
    }

    public HttpURLConnection getConnection() {
        return connection;
    }

    public int getResponseCode() {
        return responseCode;
    }
}
