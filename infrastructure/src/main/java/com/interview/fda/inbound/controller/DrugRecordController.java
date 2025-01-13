package com.interview.fda.inbound.controller;

import com.interview.fda.drugs.api.model.DrugRecordDTO;
import com.interview.fda.inbound.mapper.DrugRecordInboundMapper;
import com.interview.fda.port.inbound.DrugRecordInboundPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/drug-record")
@RequiredArgsConstructor
@Tag(name = "Drug Records", description = "Operations related to managing drug records")
public class DrugRecordController {

    private final DrugRecordInboundPort port;
    private final DrugRecordInboundMapper mapper;

    @Operation(summary = "Get all Drug Records", description = "Fetch a list of all available drug records from the database.")
    @GetMapping
    public List<DrugRecordDTO> getDrugRecordsFromFDA() {
        return mapper.map(port.getAll());
    }

    @Operation(summary = "Get Drug Record by applicationNumber (ID)", description = "Fetch specific drug record details based on the given application number.")
    @GetMapping("/{applicationNumber}")
    public DrugRecordDTO getDrugRecordById(@PathVariable String applicationNumber) {
        return mapper.map(port.getOne(applicationNumber));
    }

    @Operation(summary = "Add a Drug Record", description = "Create a new drug record in the database using provided data.")
    @PostMapping
    public DrugRecordDTO saveDrugRecord(@RequestBody @Valid DrugRecordDTO drugRecordDTO) {
        return mapper.map(port.save(mapper.map(drugRecordDTO)));
    }

    @Operation(summary = "Delete Drug Record by ID", description = "Delete an existing drug record from the database using the application number.")
    @DeleteMapping("/{applicationNumber}")
    public void deleteDrugRecord(@PathVariable String applicationNumber) {
        port.delete(applicationNumber);
    }
}
