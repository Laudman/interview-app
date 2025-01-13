package com.interview.fda.inbound.controller;

import com.interview.fda.BaseIT;
import com.interview.fda.configuration.RestTemplateConfiguration;
import com.interview.fda.openfda.api.model.FdaDrugResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static com.interview.fda.outbound.adapter.fda.FdaDrugOutboundAdapter.FIND_BY_MANUFACTURER_NAME;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

class FdaControllerIT extends BaseIT {

    private static String address;
    private MockRestServiceServer mockRestServiceServer;

    @Autowired
    @Qualifier(RestTemplateConfiguration.FDA_REST_CLIENT_TEMPLATE)
    private RestTemplate fdaRestTemplate;


    @BeforeEach
    void setUp() {
        mockRestServiceServer = MockRestServiceServer.createServer(fdaRestTemplate);
        address = buildUrl("/fda/search");
    }

    @Test
    void should_find_openfda_drug_records() throws IOException {
        String fdaAddress = "https://api.fda.gov/drug/drugsfda.json?api_key=8jUGQF95wGG6fgtPDPLbG2pvSiJAuIwKnhnrVyTh&search=openfda.manufacturer_name:Lupan&limit=10";
        String response = readResourceAsString("/data/open-fda-correct-response.json");
        mockRestServiceServer.expect(ExpectedCount.times(1), requestTo(fdaAddress))
                .andExpect(request -> {
                    String query = request.getURI().getQuery();
                    assert query != null;
                    assert query.contains(FIND_BY_MANUFACTURER_NAME + "Lupan");
                    assert query.contains("limit=10");
                })
                .andRespond(withSuccess(response, MediaType.APPLICATION_JSON));

        restTemplate.getForEntity(address + "?manufacturerName=Lupan&size=10", FdaDrugResponseDTO.class);

        mockRestServiceServer.verify();
    }

    @Test
    void should_throw_exception_when_no_manufacturer_name_provided_and_brand_name() {
        assertThrows(HttpClientErrorException.BadRequest.class, () -> restTemplate.getForEntity(address, FdaDrugResponseDTO.class));
    }

    @Test
    void should_throw_exception_when_both_manufacturer_name_and_brand_name_are_provided() {
        assertThrows(HttpClientErrorException.BadRequest.class, () -> restTemplate.getForEntity(address + "?manufacturerName=Lupan&brandName=ABCD&size=10", FdaDrugResponseDTO.class));
    }
}