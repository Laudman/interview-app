package com.interview.fda.port.outbound;

import com.interview.fda.model.fda.FdaDrugQuery;
import com.interview.fda.model.fda.FdaDrugResponse;

public interface FdaDrugOutboundPort {

    FdaDrugResponse search(FdaDrugQuery filter);
}
