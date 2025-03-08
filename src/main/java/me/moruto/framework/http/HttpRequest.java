package me.moruto.framework.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class HttpRequest {
    public static HttpResponse create(String url, HttpRequestType type, Map<String, String> postDataMap) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod(type.toString());
            connection.setDoInput(true);
            connection.setDoOutput(true);

            if (type == HttpRequestType.POST || type == HttpRequestType.PUT || type == HttpRequestType.PATCH) {
                if (postDataMap != null && !postDataMap.isEmpty()) {
                    StringBuilder postDataStringBuilder = new StringBuilder();
                    for (Map.Entry<String, String> entry : postDataMap.entrySet()) {
                        postDataStringBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
                    }

                    String postData = postDataStringBuilder.toString();
                    postData = postData.substring(0, postData.length() - 1);
                    byte[] postDataBytes = postData.getBytes(StandardCharsets.UTF_8);
                    try (OutputStream os = connection.getOutputStream()) {
                        os.write(postDataBytes, 0, postDataBytes.length);
                    }
                }
            }

            StringBuilder response = new StringBuilder();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null)
                    response.append(line);
            }

            return new HttpResponse(response.toString(), connection.getResponseCode());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
