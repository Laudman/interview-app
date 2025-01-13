package com.interview.fda.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.zalando.logbook.spring.LogbookClientHttpRequestInterceptor;

import java.util.Collections;
import java.util.List;

@Configuration
public class RestTemplateConfiguration {
    public static final String INTERVIEW_APP_REST_TEMPLATE = "interviewAppRestTemplate";
    public static final String FDA_REST_CLIENT_TEMPLATE = "fdaRestClientTemplate";


    private RestTemplate createRestTemplate(List<ClientHttpRequestInterceptor> restLoggingInterceptor) {
        var restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new HttpComponentsClientHttpRequestFactory()));
        restTemplate.setInterceptors(restLoggingInterceptor);
        return restTemplate;
    }

    @Bean(INTERVIEW_APP_REST_TEMPLATE)
    RestTemplate interviewAppRestTemplate(LogbookClientHttpRequestInterceptor restTemplateLoggingInterceptor) {
        return createRestTemplate(Collections.singletonList(restTemplateLoggingInterceptor));
    }

    @Bean(FDA_REST_CLIENT_TEMPLATE)
    RestTemplate fdaRestTemplate(LogbookClientHttpRequestInterceptor restTemplateLoggingInterceptor) {
        return createRestTemplate(List.of(restTemplateLoggingInterceptor));
    }
}
