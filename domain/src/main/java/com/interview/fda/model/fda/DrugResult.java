package com.interview.fda.model.fda;

import lombok.Data;

import java.util.List;

@Data
public class DrugResult {
    private String applicationNumber;
    private String sponsorName;
    private OpenFda openfda;
    private List<Product> products;
    private List<Submission> submissions;
}
