package com.interview.fda.service;

import com.interview.fda.model.drug.DrugRecord;
import com.interview.fda.port.inbound.DrugRecordInboundPort;
import com.interview.fda.port.outbound.DrugRecordOutboundPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DrugRecordService implements DrugRecordInboundPort {

    private final DrugRecordOutboundPort port;

    @Override
    public DrugRecord save(DrugRecord entity) {
        return port.save(entity);
    }

    @Override
    public DrugRecord getOne(String applicationNumber) {
        return port.getOne(applicationNumber);
    }

    @Override
    public List<DrugRecord> getAll() {
        return port.getAll();
    }

    @Override
    public void delete(String drugRecordId) {
        port.delete(drugRecordId);
    }


}
