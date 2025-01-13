package com.interview.fda.model.fda;

import lombok.Data;

import java.util.List;

@Data
public class Submission {
    private List<Code> submissionPropertyType;
    private List<SubmissionsApplicationDocsInner> applicationDocs;
    private String reviewPriority;
    private String submissionClassCode;
    private String submissionClassCodeDescription;
    private String submissionNumber;
    private String submissionPublicNotes;
    private String submissionStatus;
    private String submissionStatusDate;
    private String submissionType;
}
