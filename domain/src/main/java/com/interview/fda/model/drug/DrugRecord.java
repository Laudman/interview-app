package com.interview.fda.model.drug;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DrugRecord {

    private String applicationNumber;
    private String manufacturerName;
    private String substanceName;
    private List<String> productNumbers;
}
