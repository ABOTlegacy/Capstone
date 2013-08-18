package com.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SubmissionAnswer {

    private int submissionAnswerId;
    private int submissionId;
    private int questionId;
    private int formElementId;
    private String value;

    public SubmissionAnswer() {
        this.submissionAnswerId = 0;
        this.submissionId = 0;
        this.questionId = 0;
        this.formElementId = 0;
        this.value = "";
    }

    public SubmissionAnswer(int submissionAnswerId, int submissionId, int questionId, int formElementId, String value) {
        this();
        this.submissionAnswerId = submissionAnswerId;
        this.submissionId = submissionId;
        this.questionId = questionId;
        this.formElementId = formElementId;
        this.value = value;
    }

    public int getSubmissionAnswerId() {
        return submissionAnswerId;
    }

    public void setSubmissionAnswerId(int submissionAnswerId) {
        this.submissionAnswerId = submissionAnswerId;
    }

    public int getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(int submissionId) {
        this.submissionId = submissionId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getFormElementId() {
        return formElementId;
    }

    public void setFormElementId(int formElementId) {
        this.formElementId = formElementId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        // TODO: Implement
        return "";
    }
}