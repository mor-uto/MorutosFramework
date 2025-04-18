package lol.moruto.framework.http;

import java.util.HashMap;
import java.util.Map;

public class RequestOptions {
    private final Map<String, String> headers = new HashMap<>();
    private int connectTimeout = 5000;
    private int readTimeout = 5000;
    private String payload = null;

    public RequestOptions setHeader(String key, String value) {
        headers.put(key, value);
        return this;
    }

    public RequestOptions setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
        return this;
    }

    public RequestOptions setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
        return this;
    }

    public RequestOptions setPayload(String payload) {
        this.payload = payload;
        return this;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public String getPayload() {
        return payload;
    }
}
