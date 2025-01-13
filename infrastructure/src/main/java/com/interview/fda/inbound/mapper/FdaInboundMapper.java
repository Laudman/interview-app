package com.interview.fda.inbound.mapper;

import com.interview.fda.configuration.CommonMapperConfig;
import com.interview.fda.drugs.api.model.FdaDrugFilterDTO;
import com.interview.fda.model.fda.*;
import com.interview.fda.openfda.api.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = CommonMapperConfig.class)
public interface FdaInboundMapper {

    @Mapping(target = "limit", source = "externalLimit")
    FdaDrugQuery map(FdaDrugFilterDTO filterDto, int externalLimit);

    FdaDrugResponseDTO map(FdaDrugResponse response);


    DrugResultDTOOpenfda map(OpenFda openFda);

    ProductDTO map(Product products);


    DrugResultDTO mapToDrugResult(DrugResult drugResult);

    SubmissionDTO mapToSubmission(Submission submissionData);

    CodeDTO mapToCode(Code code);
}
