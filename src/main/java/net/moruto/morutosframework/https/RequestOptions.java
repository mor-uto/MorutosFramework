package net.moruto.morutosframework.https;

import java.net.URL;

public class RequestOptions {
    private requestType requestType;
    private String url;

    public void setRequestType(requestType type) {
        this.requestType = type;
    }

    public void setURL(String url) {
        this.url = url;
    }

    public RequestOptions.requestType getRequestType() {
        return requestType;
    }

    public String getUrl() {
        return url;
    }



    public enum requestType {
        GET,
        POST,
        DELETE
    }
}
