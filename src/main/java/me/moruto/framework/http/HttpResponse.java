package me.moruto.framework.http;

public class HttpResponse {
    private final String response;
    private final int responseCode;

    public HttpResponse(String response, int responseCode) {
        this.response = response;
        this.responseCode = responseCode;
    }

    public String getResponse() {
        return response;
    }

    public int getResponseCode() {
        return responseCode;
    }
}
