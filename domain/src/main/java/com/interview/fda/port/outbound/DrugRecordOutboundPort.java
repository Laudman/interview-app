package com.interview.fda.port.outbound;

import com.interview.fda.model.drug.DrugRecord;

import java.util.List;

public interface DrugRecordOutboundPort {

    DrugRecord save(DrugRecord entity);

    DrugRecord getOne(String id);

    List<DrugRecord> getAll();

    void delete(String drugRecordId);
}
