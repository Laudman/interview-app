package com.interview.fda.model.fda;

import lombok.Data;

import java.util.List;

@Data
public class Product {

    private List<ProductsActiveIngredient> activeIngredients;
    private String brandName;
    private String dosageForm;
    private String marketingStatus;
    private String productNumber;
    private String referenceDrug;
    private String referenceStandard;
    private String route;
    private String teCode;
}
