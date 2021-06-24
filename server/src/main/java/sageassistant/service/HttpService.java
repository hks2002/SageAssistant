package sageassistant.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class HttpService {
	private static final Logger log = LogManager.getLogger();

	private static CloseableHttpClient httpClient = HttpClientBuilder.create().build();

	public static String getRequestResult(String path, String method, String postData) {
		CloseableHttpResponse response = null;

		try {
			switch (method.toLowerCase()) {
			case "get": {
				HttpGet httpGet = new HttpGet(path);
				response = httpClient.execute(httpGet);
				break;
			}
			case "post": {
				HttpPost httpPost = new HttpPost(path);
				httpPost.setHeader("accept", "application/json");
				httpPost.setHeader("content-type", "application/json;charset=utf-8");
				HttpEntity entity = new StringEntity(postData);
				httpPost.setEntity(entity);
				response = httpClient.execute(httpPost);
				break;
			}
			default: {
				HttpGet httpGet = new HttpGet(path);
				response = httpClient.execute(httpGet);
			}
			}

			return EntityUtils.toString(response.getEntity());

		} catch (ClientProtocolException e) {
			log.error(e.getMessage());
		} catch (ParseException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		} finally {
			try {
				if (httpClient != null) {
					httpClient.close();
				}
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		}

		return "";
	}

	public void doRequest(HttpServletRequest request, HttpServletResponse responseToClinet) {
		CloseableHttpResponse response = null;

		String method = request.getMethod();
		String path = request.getRequestURL().toString();
		Enumeration<String> headers = request.getHeaderNames();

		try {
			String data = null;

			switch (method.toLowerCase()) {
			case "get": {
				HttpGet httpGet = new HttpGet(path);

				while (headers.hasMoreElements()) {
					String name = headers.nextElement();
					String value = request.getHeader(name);
					httpGet.setHeader(name, value);
				}
				response = httpClient.execute(httpGet);
				break;
			}
			case "post": {
				HttpPost httpPost = new HttpPost(path);

				while (headers.hasMoreElements()) {
					String name = headers.nextElement();
					String value = request.getHeader(name);
					httpPost.setHeader(name, value);
				}

				httpPost.setHeader("accept", "application/json");
				httpPost.setHeader("content-type", "application/json;charset=utf-8");
				data = request.getInputStream().toString();

				HttpEntity entity = new StringEntity(data);
				httpPost.setEntity(entity);
				response = httpClient.execute(httpPost);
				break;
			}
			case "put": {
				HttpPut httpPut = new HttpPut(path);

				while (headers.hasMoreElements()) {
					String name = headers.nextElement();
					String value = request.getHeader(name);
					httpPut.setHeader(name, value);
				}

				httpPut.setHeader("accept", "application/json");
				httpPut.setHeader("content-type", "application/json;charset=utf-8");

				data = request.getInputStream().toString();
				HttpEntity entity = new StringEntity(data);
				httpPut.setEntity(entity);
				response = httpClient.execute(httpPut);
				break;
			}
			default: {

			}
			}

			responseToClinet.setStatus(response.getStatusLine().getStatusCode());
			Header headersToClient[] = response.getAllHeaders();
			for (int i = 0; i < headersToClient.length; i++) {
				responseToClinet.setHeader(headersToClient[i].getName(), headersToClient[i].getValue());
			}
			PrintWriter wr = responseToClinet.getWriter();
			wr.write(response.getEntity().toString());

		} catch (ClientProtocolException e) {
			log.error(e.getMessage());
		} catch (ParseException e) {
			log.error(e.getMessage());
		} catch (IOException e) {
			log.error(e.getMessage());
		} finally {
			try {
				if (httpClient != null) {
					httpClient.close();
				}
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		}
	}

}
