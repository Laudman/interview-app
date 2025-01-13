package com.interview.fda.port.inbound;

import com.interview.fda.model.fda.FdaDrugQuery;
import com.interview.fda.model.fda.FdaDrugResponse;

public interface FdaDrugInboundPort {

    FdaDrugResponse search(FdaDrugQuery filter);
}
