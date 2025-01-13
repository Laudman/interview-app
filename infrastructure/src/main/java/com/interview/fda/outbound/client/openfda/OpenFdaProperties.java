package com.interview.fda.outbound.client.openfda;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rest.client.fda")
public record OpenFdaProperties(String url, String apiKey) {
}
