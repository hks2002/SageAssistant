/*********************************************************************************************************************
 * @Author                : Robert Huang<56649783@qq.com>                                                            *
 * @CreatedDate           : 2022-03-26 17:57:07                                                                      *
 * @LastEditors           : Robert Huang<56649783@qq.com>                                                            *
 * @LastEditDate          : 2023-04-05 12:38:05                                                                      *
 * @FilePath              : src/main/java/sageassistant/dataSrv/service/HttpService.java                             *
 * @CopyRight             : Dedienne Aerospace China ZhuHai                                                          *
 ********************************************************************************************************************/

package com.da.sage.assistant.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class HttpService {

    private static final Logger log = LogManager.getLogger();

    /**
     * google guava cache
     */
    private static Cache<String, String> cache = CacheBuilder
        .newBuilder()
        .expireAfterAccess(15, java.util.concurrent.TimeUnit.MINUTES)
        .maximumSize(10000)
        .build();

    private static HttpClient client = null;

    private static SSLContext getSSLContext() {
        try {
            TrustManager[] trustAllCertificates = new TrustManager[] {
                new X509TrustManager() {
                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    @Override
                    public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}

                    @Override
                    public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {}
                }
            };

            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCertificates, new SecureRandom());
            return sc;
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public static HttpResponse<String> request(String url, String method) {
        return request(url, method, null, null);
    }

    public static HttpResponse<String> request(String url, String method, String data) {
        return request(url, method, data, null);
    }

    public static HttpResponse<String> request(String url, String method, String data, String auth) {
        return request(url, method, data, auth, false);
    }

    public static HttpResponse<String> request(String url, String method, String data, String auth, boolean isAsync) {
        try {
            // Disable host name verification Globally
            Properties props = System.getProperties();
            props.setProperty("jdk.internal.httpclient.disableHostnameVerification", Boolean.TRUE.toString());

            if (client == null) {
                client = HttpClient.newBuilder().sslContext(getSSLContext()).build();
            }

            Builder reqBuilder = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .setHeader("Content-Type", "application/json")
                .setHeader("Accept", "application/json");

            if (auth != null && !auth.isEmpty() && !auth.isBlank()) {
                reqBuilder.header("authorization", auth);
                // Cookie
                if (cache.getIfPresent(auth) != null) {
                    reqBuilder.header("Cookie", cache.getIfPresent(auth));
                }
            }

            switch (method) {
                case "GET":
                    reqBuilder.GET();
                    break;
                case "POST":
                    if (data != null && !data.isEmpty() && !data.isBlank()) {
                        reqBuilder.POST(BodyPublishers.ofString(data));
                    }
                    break;
                case "PUT":
                    if (data != null && !data.isEmpty() && !data.isBlank()) {
                        reqBuilder.PUT(BodyPublishers.ofString(data));
                    }
                    break;
                case "DELETE":
                    reqBuilder.DELETE();
                    break;
                default:
                    reqBuilder.GET();
            }

            HttpRequest request = reqBuilder.build();
            HttpResponse<String> response = null;
            if (isAsync) {
                response = client.sendAsync(request, BodyHandlers.ofString()).get();
            } else {
                response = client.send(request, BodyHandlers.ofString());
            }

            // Cookie
            List<String> cookieResponse = response.headers().allValues("Set-Cookie");
            List<String> cookieCache = new ArrayList<String>();
            for (String cookie : cookieResponse) {
                cookieCache.add(cookie.split(";")[0]);
            }
            if (auth != null && !auth.isEmpty() && !auth.isBlank()) {
                cache.put(auth, String.join(";", cookieCache));
                log.debug(cache.getIfPresent(auth));
            }

            log.debug(response.statusCode());
            log.debug(response.body());

            return response;
        } catch (Exception e) {
            e.getStackTrace();
            log.error(e.getLocalizedMessage());
            return null;
        }
    }

    public static HttpResponse<String> proxy(String method, String url, Map<String, String> headerMap, String data) {
        try {
            // Disable host name verification Globally
            Properties props = System.getProperties();
            props.setProperty("jdk.internal.httpclient.disableHostnameVerification", Boolean.TRUE.toString());

            if (client == null) {
                client = HttpClient.newBuilder().sslContext(getSSLContext()).build();
            }

            Builder reqBuilder = HttpRequest.newBuilder().uri(URI.create(url));

            // add header
            headerMap.forEach((name, value) -> {
                switch (name) {
                    case "host":
                    case "connection":
                    case "content-length":
                        break;
                    default:
                        reqBuilder.setHeader(name, value);
                }
            });

            switch (method) {
                case "GET":
                    reqBuilder.GET();
                    break;
                case "POST":
                    if (data != null && !data.isEmpty() && !data.isBlank()) {
                        reqBuilder.POST(BodyPublishers.ofString(data));
                    }
                    break;
                case "PUT":
                    if (data != null && !data.isEmpty() && !data.isBlank()) {
                        reqBuilder.PUT(BodyPublishers.ofString(data));
                    }
                    break;
                case "DELETE":
                    reqBuilder.DELETE();
                    break;
                default:
                    reqBuilder.GET();
            }

            HttpRequest request = reqBuilder.build();
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            log.debug(response.body());

            return response;
        } catch (Exception e) {
            e.getStackTrace();
            log.error(e.getLocalizedMessage());
            return null;
        }
    }
}
