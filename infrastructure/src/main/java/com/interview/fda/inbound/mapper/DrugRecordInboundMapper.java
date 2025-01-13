package com.interview.fda.inbound.mapper;

import com.interview.fda.configuration.CommonMapperConfig;
import com.interview.fda.drugs.api.model.DrugRecordDTO;
import com.interview.fda.model.drug.DrugRecord;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = CommonMapperConfig.class)
public interface DrugRecordInboundMapper {

    DrugRecordDTO map(DrugRecord entity);

    DrugRecord map(DrugRecordDTO entity);

    List<DrugRecordDTO> map(List<DrugRecord> entities);
}
