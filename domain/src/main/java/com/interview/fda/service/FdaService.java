package com.interview.fda.service;

import com.interview.fda.model.fda.FdaDrugQuery;
import com.interview.fda.model.fda.FdaDrugResponse;
import com.interview.fda.port.inbound.FdaDrugInboundPort;
import com.interview.fda.port.outbound.FdaDrugOutboundPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FdaService implements FdaDrugInboundPort {

    private final FdaDrugOutboundPort port;

    @Override
    public FdaDrugResponse search(FdaDrugQuery filter) {
        return port.search(filter);
    }
}
