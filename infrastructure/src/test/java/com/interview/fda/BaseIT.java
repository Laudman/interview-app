package com.interview.fda;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = InterviewApplication.class)
public abstract class BaseIT {

    @Container
    @ServiceConnection
    public static final PostgreSQLContainer<?> postgresDb;

    protected static final RestTemplate restTemplate = new RestTemplate();

    static {
        postgresDb = new PostgreSQLContainer<>("postgres:16-alpine")
                .withDatabaseName("int-app-db-test")
                .withUsername("int-app-db-test")
                .withPassword("int-app-db-test");
        postgresDb.start();
    }

    @LocalServerPort
    private int port;

    public static String getRandomString() {
        return UUID.randomUUID().toString();
    }

    protected String readResourceAsString(String filePath) throws IOException {
        return new String(Objects.requireNonNull(getClass().getResourceAsStream(filePath)).readAllBytes(), StandardCharsets.UTF_8);
    }

    public String buildUrl(String address) {
        return "http://localhost:" + port + "/v1" + address;
    }

    public String buildUrl(String address, LinkedMultiValueMap<String, String> requestParams) {
        return UriComponentsBuilder.fromUriString(buildUrl(address)).queryParams(requestParams).build().toUriString();
    }

}
