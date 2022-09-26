/*
 * @Author         : Robert Huang<56649783@qq.com>
 * @Date           : 2022-03-26 17:57:07
 * @LastEditors    : Robert Huang<56649783@qq.com>
 * @LastEditTime   : 2022-09-22 15:44:48
 * @FilePath       : \server\src\main\java\sageassistant\service\HttpService.java
 * @CopyRight      : Dedienne Aerospace China ZhuHai
 */
package sageassistant.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class HttpService {

    private static final Logger log = LogManager.getLogger();

    public static String getRequestResult(String path, String method, String postData) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(path);
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");

            switch (method.toUpperCase()) {
                case "GET":
                    {
                        connection.setRequestMethod("GET");
                        break;
                    }
                case "POST":
                    {
                        connection.setRequestMethod("POST");
                        connection.setDoOutput(true);
                        connection.setDoInput(true);

                        connection.setRequestProperty("accept", "application/json");
                        connection.setRequestProperty("content-type", "application/json;charset=utf-8");

                        OutputStream os = null;
                        os = connection.getOutputStream();
                        if (postData != null && postData.length() > 0) {
                            os.write(postData.getBytes());
                            os.flush();
                            os.close();
                        }
                        break;
                    }
                default:
                    {
                        connection.setRequestMethod("GET");
                    }
            }

            if (connection.getResponseCode() == 200) {
                InputStream in = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                return response.toString();
            } else {
                return "";
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }

        return "";
    }

    public void doRequest(HttpServletRequest request, HttpServletResponse responseToClinet) {
        String method = request.getMethod();
        String path = request.getPathInfo().toString();
        log.debug("path before:" + path);
        path = path.replaceFirst("^/Proxy/", "");
        log.debug("path after:" + path);

        Enumeration<String> headers = request.getHeaderNames();

        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL("http://www.baidu.com");
            connection = (HttpURLConnection) url.openConnection();

            connection.setConnectTimeout(8000);
            connection.setReadTimeout(8000);
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");

            switch (method.toUpperCase()) {
                case "GET":
                    {
                        connection.setRequestMethod("GET");
                        break;
                    }
                case "POST":
                    {
                        connection.setRequestMethod("POST");
                        connection.setDoOutput(true);
                        connection.setDoInput(true);

                        connection.setRequestProperty("accept", "application/json");
                        connection.setRequestProperty("content-type", "application/json;charset=utf-8");

                        while (headers.hasMoreElements()) {
                            String name = headers.nextElement();
                            String value = request.getHeader(name);
                            connection.setRequestProperty(name, value);
                        }

                        OutputStream os = null;
                        os = connection.getOutputStream();
                        String data = null;
                        data = request.getInputStream().toString();
                        os.write(data.getBytes());
                        os.flush();
                        os.close();
                        break;
                    }
                default:
                    {
                        connection.setRequestMethod("GET");
                    }
            }

            responseToClinet.setStatus(connection.getResponseCode());
            Map<String, List<String>> map = connection.getHeaderFields();

            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                responseToClinet.setHeader(entry.getKey(), entry.getValue().toString());
            }

            InputStream in = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            PrintWriter wr = responseToClinet.getWriter();
            wr.write(response.toString());
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
