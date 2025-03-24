package me.moruto.framework.http;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class Requests {
    public static HttpResponse get(String url) {
        return request(url, "GET", null, null);
    }

    public static HttpResponse post(String url, Map<String, String> postDataMap, String payload) {
        return request(url, "POST", postDataMap, payload);
    }

    public static HttpResponse put(String url, Map<String, String> postDataMap, String payload) {
        return request(url, "PUT", postDataMap, payload);
    }

    public static HttpResponse delete(String url) {
        return request(url, "DELETE", null, null);
    }

    public static HttpResponse patch(String url, Map<String, String> postDataMap, String payload) {
        return request(url, "PATCH", postDataMap, payload);
    }

    private static HttpResponse request(String url, String method, Map<String, String> postDataMap, String payload) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod(method);
            connection.setDoInput(true);

            if ((postDataMap != null && !postDataMap.isEmpty()) || payload != null) {
                connection.setDoOutput(true);
                try (OutputStream os = connection.getOutputStream()) {
                    if (payload != null) {
                        os.write(payload.getBytes(StandardCharsets.UTF_8));
                    } else {
                        StringBuilder postDataStringBuilder = new StringBuilder();
                        for (Map.Entry<String, String> entry : postDataMap.entrySet()) {
                            postDataStringBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
                        }
                        String postData = postDataStringBuilder.substring(0, postDataStringBuilder.length() - 1);
                        os.write(postData.getBytes(StandardCharsets.UTF_8));
                    }
                }
            }

            StringBuilder response = new StringBuilder();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
            }

            return new HttpResponse(response.toString(), connection.getResponseCode());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
