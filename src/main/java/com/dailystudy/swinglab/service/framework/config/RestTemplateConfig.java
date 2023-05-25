package com.dailystudy.swinglab.service.framework.config;


import com.dailystudy.swinglab.service.framework.http.log.RestTemplateLoggingInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.socket.ConnectionSocketFactory;
import org.apache.hc.client5.http.socket.PlainConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.http.config.Registry;
import org.apache.hc.core5.http.config.RegistryBuilder;
import org.apache.hc.core5.ssl.SSLContexts;
import org.apache.hc.core5.ssl.TrustStrategy;

/**
 * RestTemplate의 호출시 발생하는 payload 및 기타 정보를 로깅하는데 사용하는 클래스 입니다.
 * {@link RestTemplateConfig}으로 간단하게 설정하여 사용 가능합니다.
 *
 * @author Gwanggeun, Yoo
 */
public class RestTemplateConfig {
    @Value("${rest.read.timeout.secs:10}")
    private int restReadTimeoutSeconds;
    @Value("${rest.connection.timeout.secs:3}")
    private int restConnectionTimeoutSeconds;

    @Bean
    public RestTemplateLoggingInterceptor restTemplateLoggingInterceptor()
    {
        return new RestTemplateLoggingInterceptor();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder, MappingJackson2HttpMessageConverter converter, RestTemplateLoggingInterceptor loggingInterceptor)
            throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException
    {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
        SSLConnectionSocketFactory sslcsf = new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());

        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create().register("https", sslcsf)
            .register("http", new PlainConnectionSocketFactory())
            .build();
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(new PoolingHttpClientConnectionManager(socketFactoryRegistry)).build();
        
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setConnectTimeout(restConnectionTimeoutSeconds * 1000);
        requestFactory.setHttpClient(httpClient);
        
        RestTemplate template = builder.requestFactory(() -> requestFactory).additionalMessageConverters(converter).build();
        template.getInterceptors().add(loggingInterceptor);
        return template;
    }
}
