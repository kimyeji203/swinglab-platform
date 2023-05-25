package com.dailystudy.swinglab.service.framework.http.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map.Entry;

@Slf4j
public class RestTemplateLoggingInterceptor implements ClientHttpRequestInterceptor
{
    private final int RESPONSE_LOGGING_LENGTH = 1024 * 128;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
        throws IOException
    {
        traceRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        response = new BufferingClientHttpResponseWrapper(response);
        traceResponse(response);

        return response;
    }

    private void traceRequest(HttpRequest request, byte[] body)
    {
        log.info("[===========================REQUEST=============================================]");
        log.info("[Method : {}, URI : {}]", request.getMethod(), request.getURI());
        log.info("[Request Headers]");
        for (Entry<String, List<String>> entry : request.getHeaders().entrySet())
        {
            log.info("  -> {}: {}", entry.getKey(), entry.getValue());
        }
        log.info("[Request Body : {}]", new String(body, StandardCharsets.UTF_8));
    }

    private void traceResponse(ClientHttpResponse response) throws IOException
    {
        String contentType = "";
        if (response.getHeaders() != null && response.getHeaders().getContentType() != null)
        {
            contentType = response.getHeaders().getContentType().toString();
        }

        StringBuilder inputStringBuilder = new StringBuilder();
        if (response.getBody() != null && !contentType.contains("image"))
        {
            BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(response.getBody(), StandardCharsets.UTF_8));
            char[] buffer = new char[1024 * 10];

            int len;
            while ((len = bufferedReader.read(buffer, 0, buffer.length)) != -1)
            {
                inputStringBuilder.append(buffer, 0, len);
            }
        }

        log.info("[============================RESPONSE==========================================");
        log.info("[Status : {} {}]", response.getStatusCode(), response.getStatusText());
        log.info("[Headers : {}   ]", response.getHeaders());
        if (log.isDebugEnabled())
        {
            log.info("[Response Body : {}]",
                inputStringBuilder.substring(0, Math.min(inputStringBuilder.length(), RESPONSE_LOGGING_LENGTH)));
        }
        log.info("[=========================REST TEMPLATE END====================================]");

        inputStringBuilder.setLength(0);
        inputStringBuilder.trimToSize();
    }

    /**
     * Response wrapper 클래스 response 객체에서 body 내용을 복사하여 재사용 가능하게 처리함.
     *
     * @author silverb
     */
    public static class BufferingClientHttpResponseWrapper implements ClientHttpResponse
    {
        private final ClientHttpResponse response;

        private byte[] body;

        BufferingClientHttpResponseWrapper(ClientHttpResponse response)
        {
            this.response = response;
        }

        @Override
        public HttpStatus getStatusCode() throws IOException
        {
            return HttpStatus.valueOf(response.getStatusCode().value());
        }

//        @Override
        public int getRawStatusCode() throws IOException
        {
            return this.response.getStatusCode().value();
        }

        @Override
        public String getStatusText() throws IOException
        {
            return this.response.getStatusText();
        }

        @Override
        public HttpHeaders getHeaders()
        {
            return this.response.getHeaders();
        }

        @Override
        public InputStream getBody() throws IOException
        {
            if (this.body == null)
            {
                if (this.response.getBody() != null)
                {
                    this.body = FileCopyUtils.copyToByteArray(this.response.getBody());
                }
            }
            return (this.body == null) ? null : new ByteArrayInputStream(this.body);
        }

        @Override
        public void close()
        {
            this.response.close();
        }
    }
}
