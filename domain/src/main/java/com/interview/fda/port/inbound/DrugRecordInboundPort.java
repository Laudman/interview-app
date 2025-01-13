package com.interview.fda.port.inbound;

import com.interview.fda.model.drug.DrugRecord;

import java.util.List;

public interface DrugRecordInboundPort {

    DrugRecord save(DrugRecord entity);

    DrugRecord getOne(String applicationNumber);

    List<DrugRecord> getAll();

    void delete(String drugRecordId);
}
