package com.group13.tutormate.entity;

import java.time.LocalDateTime;

public class Submission {

    private String status;

    private LocalDateTime submissionDate = LocalDateTime.now(); // current time

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDateTime submissionDate) {
        this.submissionDate = submissionDate;
    }
}