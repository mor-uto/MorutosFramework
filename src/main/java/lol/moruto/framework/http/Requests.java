package lol.moruto.framework.http;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class Requests {
    public static HttpResponse get(String url, RequestOptions options) {
        return request(url, "GET", options);
    }

    public static HttpResponse post(String url, RequestOptions options) {
        return request(url, "POST", options);
    }

    public static HttpResponse put(String url, RequestOptions options) {
        return request(url, "PUT", options);
    }

    public static HttpResponse delete(String url, RequestOptions options) {
        return request(url, "DELETE", options);
    }

    public static HttpResponse patch(String url, RequestOptions options) {
        return request(url, "PATCH", options);
    }

    private static HttpResponse request(String url, String method, RequestOptions options) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod(method);
            connection.setDoInput(true);
            connection.setConnectTimeout(options.getConnectTimeout());
            connection.setReadTimeout(options.getReadTimeout());

            for (Map.Entry<String, String> header : options.getHeaders().entrySet()) {
                connection.setRequestProperty(header.getKey(), header.getValue());
            }

            if (options.getPayload() != null) {
                connection.setDoOutput(true);
                try (OutputStream os = connection.getOutputStream()) {
                    os.write(options.getPayload().getBytes(StandardCharsets.UTF_8));
                }
            }

            int responseCode = connection.getResponseCode();
            StringBuilder response = new StringBuilder();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(
                    responseCode < HttpURLConnection.HTTP_BAD_REQUEST ?
                            connection.getInputStream() :
                            connection.getErrorStream()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
            }

            return new HttpResponse(response.toString(), responseCode);
        } catch (IOException e) {
            e.printStackTrace();
            return new HttpResponse("", -1);
        }
    }
}