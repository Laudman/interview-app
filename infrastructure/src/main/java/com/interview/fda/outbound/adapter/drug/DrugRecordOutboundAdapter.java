package com.interview.fda.outbound.adapter.drug;

import com.interview.fda.exception.ConflictException;
import com.interview.fda.exception.ResourceNotFoundException;
import com.interview.fda.model.drug.DrugRecord;
import com.interview.fda.outbound.mapper.drug.DrugRecordOutboundMapper;
import com.interview.fda.outbound.persistence.repository.DrugRecordRepository;
import com.interview.fda.port.outbound.DrugRecordOutboundPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DrugRecordOutboundAdapter implements DrugRecordOutboundPort {

    private final DrugRecordRepository repository;
    private final DrugRecordOutboundMapper mapper;

    @Transactional
    @Override
    public DrugRecord save(DrugRecord entity) {
        if (repository.existsById(entity.getApplicationNumber())) {
            throw new ConflictException("DrugRecord with applicationId: " + entity.getApplicationNumber() + " already exists");
        }
        return mapper.map(repository.save(mapper.map(entity)));
    }

    @Override
    public DrugRecord getOne(String applicationNumber) {
        return mapper.map(repository.findById(applicationNumber)
                .orElseThrow(() -> new ResourceNotFoundException("DrugRecord with applicationId:" + applicationNumber + " not found"))
        );
    }

    @Override
    public List<DrugRecord> getAll() {
        return mapper.map(repository.findAll());
    }

    @Transactional
    @Override
    public void delete(String drugRecordId) {
        repository.deleteById(drugRecordId);
    }
}
