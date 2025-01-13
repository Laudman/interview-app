package com.interview.fda.inbound.controller;

import com.interview.fda.drugs.api.model.FdaDrugFilterDTO;
import com.interview.fda.inbound.mapper.FdaInboundMapper;
import com.interview.fda.openfda.api.model.DrugResultDTO;
import com.interview.fda.openfda.api.model.FdaDrugResponseDTO;
import com.interview.fda.port.inbound.FdaDrugInboundPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/fda")
@RequiredArgsConstructor
@Tag(name = "FDA Search", description = "Operations related to fetching FDA drug data")
public class FdaController {

    private final FdaDrugInboundPort port;
    private final FdaInboundMapper mapper;

    @Operation(summary = "Search FDA Drug Records", description = "Search FDA drug records using specific filters. Results are paginated.")
    @GetMapping("/search")
    public Page<DrugResultDTO> getDrugRecordsFromFDA(@ModelAttribute @Valid FdaDrugFilterDTO filterDTO,
                                                     @RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "5") int size) {

        int externalLimit = (page + 1) * size;
        FdaDrugResponseDTO responseDTO = mapper.map(port.search(mapper.map(filterDTO, externalLimit)));
        return paginateResults(responseDTO, page, size);
    }

    private Page<DrugResultDTO> paginateResults(FdaDrugResponseDTO results, int page, int size) {
        int start = page * size;
        int end = Math.min(start + size, results.getResults().size());
        List<DrugResultDTO> pageResults = results.getResults().subList(start, end);
        return new PageImpl<>(pageResults, PageRequest.of(page, size), results.getResults().size());
    }
}
