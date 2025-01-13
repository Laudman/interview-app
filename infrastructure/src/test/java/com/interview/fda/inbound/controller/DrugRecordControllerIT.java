package com.interview.fda.inbound.controller;

import com.interview.fda.BaseIT;
import com.interview.fda.drugs.api.model.DrugRecordDTO;
import com.interview.fda.outbound.adapter.drug.DrugRecordITFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DrugRecordControllerIT extends BaseIT {

    private static String address;

    @Autowired
    private DrugRecordITFactory drugRecordITFactory;

    @BeforeEach
    public void setup() {
        address = buildUrl("/drug-record");
    }

    @Test
    void should_save_drugRecord() {
        var request = drugRecordITFactory.newDrugRecordDTO();
        var response = restTemplate.postForEntity(address, request, DrugRecordDTO.class);

        assertNotNull(response);
        var result = response.getBody();
        assertDrugRecord(request, result);
    }

    @Test
    void should_get_drugRecord_by_applicationNumber() {
        var request = drugRecordITFactory.newDrugRecordDTO();
        var saved = restTemplate.postForEntity(address, request, DrugRecordDTO.class).getBody();

        var response = restTemplate.getForEntity(address + "/" + saved.getApplicationNumber(), DrugRecordDTO.class);
        assertNotNull(response);
        var result = response.getBody();
        assertDrugRecord(saved, result);
    }

    @Test
    void should_get_all_drugRecords() {
        var saved_1 = drugRecordITFactory.saveDrugRecordEntity();
        var saved_2 = drugRecordITFactory.saveDrugRecordEntity();
        var saved_3 = drugRecordITFactory.saveDrugRecordEntity();

        var response = restTemplate.getForEntity(address, DrugRecordDTO[].class);
        assertNotNull(response);
        var result = response.getBody();
        assertNotNull(result);
        assertTrue(result.length >= 3);
        assertTrue(Arrays.stream(result).map(DrugRecordDTO::getApplicationNumber)
                .toList().containsAll(List.of(saved_1.getApplicationNumber(), saved_2.getApplicationNumber(), saved_3.getApplicationNumber())));
    }

    @Test
    void should_delete_drugRecord() {
        var saved = drugRecordITFactory.saveDrugRecordEntity();
        restTemplate.delete(address + "/" + saved.getApplicationNumber());

        assertThrows(HttpClientErrorException.NotFound.class, () -> restTemplate.getForEntity(address + "/" + saved.getApplicationNumber(), DrugRecordDTO.class));
    }

    @Test
    void should_not_save_drugRecord_with_the_same_applicationNumber() {
        var saved = drugRecordITFactory.saveDrugRecordEntity();
        var request = drugRecordITFactory.newDrugRecordDTO();
        request.setApplicationNumber(saved.getApplicationNumber());

        assertThrows(HttpClientErrorException.Conflict.class, () -> restTemplate.postForEntity(address, request, DrugRecordDTO.class));
    }

    private void assertDrugRecord(DrugRecordDTO request, DrugRecordDTO response) {
        assertEquals(request.getApplicationNumber(), response.getApplicationNumber());
        assertEquals(request.getManufacturerName(), response.getManufacturerName());
        assertEquals(request.getSubstanceName(), response.getSubstanceName());
        assertTrue(request.getProductNumbers().containsAll(response.getProductNumbers()));
    }
}