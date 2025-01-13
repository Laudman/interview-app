package com.interview.fda.model.fda;

import lombok.Data;

@Data
public class Meta {
    private String disclaimer;
    private String terms;
    private String license;
    private String last_updated;
    private Results results;
}
