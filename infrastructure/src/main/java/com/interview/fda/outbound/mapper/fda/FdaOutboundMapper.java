package com.interview.fda.outbound.mapper.fda;

import com.interview.fda.configuration.CommonMapperConfig;
import com.interview.fda.drugs.api.model.FdaDrugFilterDTO;
import com.interview.fda.model.fda.FdaDrugQuery;
import com.interview.fda.model.fda.FdaDrugResponse;
import com.interview.fda.openfda.api.model.FdaDrugResponseDTO;
import org.mapstruct.Mapper;

@Mapper(config = CommonMapperConfig.class)
public interface FdaOutboundMapper {


    FdaDrugQuery map(FdaDrugFilterDTO filterDto);

    FdaDrugResponse map(FdaDrugResponseDTO responseDto);
}
