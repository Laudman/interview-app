package com.interview.fda.model.fda;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Data
public class FdaDrugQuery {

    private String manufacturerName;
    private String brandName;
    private Integer limit;

    public FdaDrugQuery(String manufacturerName, String brandName, Integer limit) {
        if (manufacturerName != null && brandName != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only one of 'manufacturerName' or 'brandName' can be set.");
        }
        this.manufacturerName = manufacturerName;
        this.brandName = brandName;
        this.limit = limit;
    }
}
