package com.interview.fda.model.fda;

import lombok.Data;

import java.util.List;

@Data
public class FdaDrugResponse {
    private Meta meta;
    private List<DrugResult> results;
}
