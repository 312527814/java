package com.my.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.io.InputStream;

/**
 * RestTemplate请求，返回response
 * */
public class RestTemplateClientHttpResponse implements ClientHttpResponse {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    private HttpStatus statusCode;
    private int rawStatusCode;
    private String statusText;
    private InputStream body;
    private HttpHeaders headers;


    @Override
    public HttpStatus getStatusCode() throws IOException {
        return statusCode;
    }

    @Override
    public int getRawStatusCode() throws IOException {
        return rawStatusCode;
    }

    @Override
    public String getStatusText() throws IOException {
        return statusText;
    }

    @Override
    public void close() {
        try {
            body.close();
        } catch (IOException e) {
            logger.error(e.toString());
        }
    }

    @Override
    public InputStream getBody() throws IOException {
        return body;
    }

    @Override
    public HttpHeaders getHeaders() {
        return headers;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public void setRawStatusCode(int rawStatusCode) {
        this.rawStatusCode = rawStatusCode;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public void setBody(InputStream body) {
        this.body = body;
    }

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }
}
