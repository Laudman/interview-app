package com.interview.fda.outbound.mapper.drug;

import com.interview.fda.configuration.CommonMapperConfig;
import com.interview.fda.model.drug.DrugRecord;
import com.interview.fda.outbound.persistence.entity.DrugRecordEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = CommonMapperConfig.class)
public interface DrugRecordOutboundMapper {

    DrugRecord map(DrugRecordEntity entity);

    DrugRecordEntity map(DrugRecord entity);

    List<DrugRecord> map(List<DrugRecordEntity> entities);
}
