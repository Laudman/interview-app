package com.interview.fda.outbound.adapter.fda;

import com.interview.fda.exception.BadRequestException;
import com.interview.fda.model.fda.FdaDrugQuery;
import com.interview.fda.model.fda.FdaDrugResponse;
import com.interview.fda.outbound.client.openfda.OpenFdaProperties;
import com.interview.fda.outbound.client.openfda.OpenFdaRestConnector;
import com.interview.fda.outbound.mapper.fda.FdaOutboundMapper;
import com.interview.fda.port.outbound.FdaDrugOutboundPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@RequiredArgsConstructor
@Service
public class FdaDrugOutboundAdapter implements FdaDrugOutboundPort {

    public static final String FIND_BY_MANUFACTURER_NAME = "openfda.manufacturer_name:";
    public static final String FIND_BY_BRAND_NAME = "openfda.brand_name:";

    private final OpenFdaRestConnector openFdaRestConnector;
    private final OpenFdaProperties openFdaProperties;
    private final FdaOutboundMapper mapper;

    @Override
    public FdaDrugResponse search(FdaDrugQuery filter) {
        try {
            return mapper.map(
                    openFdaRestConnector.getAuthApi()
                            .getDrugApplications(openFdaProperties.apiKey(),
                                    buildQuery(filter),
                                    filter.getLimit())
            );
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error fetching drug applications: " + e.getMessage(), e);
        }
    }

    private String buildQuery(FdaDrugQuery filter) {
        if (StringUtils.hasLength(filter.getManufacturerName())) {
            return FIND_BY_MANUFACTURER_NAME + filter.getManufacturerName();
        } else if (StringUtils.hasLength(filter.getBrandName())) {
            return FIND_BY_BRAND_NAME + filter.getBrandName();
        } else {
            throw new BadRequestException("Either manufacturer name or brand name must be provided");
        }
    }
}
