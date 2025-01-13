package com.interview.fda.model.fda;

import lombok.Data;

import java.util.List;

@Data
public class OpenFda {
    private List<String> applicationNumber;
    private List<String> brandName;
    private List<String> genericName;
    private List<String> manufacturerName;
    private List<String> nui;
    private List<String> packageNdc;
    private List<String> pharmClassCs;
    private List<String> pharmClassEpc;
    private List<String> pharmClassPe;
    private List<String> pharmClassMoa;
    private List<String> productNdc;
    private List<String> route;
    private List<String> rxcui;
    private List<String> splId;
    private List<String> splSetId;
    private List<String> substanceName;
    private List<String> unii;
}