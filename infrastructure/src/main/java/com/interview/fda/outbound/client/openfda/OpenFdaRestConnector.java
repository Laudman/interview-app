package com.interview.fda.outbound.client.openfda;


import com.interview.fda.openfda.api.DefaultApi;

public interface OpenFdaRestConnector {

    DefaultApi getAuthApi();
}
