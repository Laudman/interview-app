package com.interview.fda.outbound.client.openfda;

import com.interview.fda.openfda.ApiClient;
import com.interview.fda.openfda.api.DefaultApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static com.interview.fda.configuration.RestTemplateConfiguration.FDA_REST_CLIENT_TEMPLATE;

@EnableConfigurationProperties(OpenFdaProperties.class)
@Slf4j
@Component
public class OpenFdaRestClient implements OpenFdaRestConnector {
    private final RestTemplate restTemplate;
    private final OpenFdaProperties properties;

    public OpenFdaRestClient(@Qualifier(FDA_REST_CLIENT_TEMPLATE) RestTemplate restTemplate, OpenFdaProperties properties) {
        this.restTemplate = restTemplate;
        this.properties = properties;
    }

    @Override
    public DefaultApi getAuthApi() {
        return new DefaultApi(createApiClient());
    }

    private ApiClient createApiClient() {
        var apiClient = new ApiClient(restTemplate);
        apiClient.setBasePath(properties.url());
        return apiClient;
    }

}
