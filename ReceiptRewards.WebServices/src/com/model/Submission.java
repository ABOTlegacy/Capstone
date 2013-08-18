package com.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Submission {

    private int submissionId;
    private int revisionId;
    private Date dateStarted;
    private Date dateCompleted;
    private List<SubmissionAnswer> submissionAnswers;
    private SubmissionReward submissionReward;

    public Submission() {
        this.submissionId = 0;
        this.revisionId = 0;
        this.dateStarted = new Date();
        this.dateCompleted = new Date();
        this.submissionAnswers = new ArrayList<SubmissionAnswer>();
        this.submissionReward = null;
    }

    public Submission(int submissionId, int revisionId, Date dateStarted, Date dateCompleted, List<SubmissionAnswer> submissionAnswers, SubmissionReward submissionReward) {
        this();
        this.submissionId = submissionId;
        this.revisionId = revisionId;
        this.dateStarted = dateStarted;
        this.dateCompleted = dateCompleted;
        this.submissionAnswers = submissionAnswers;
        this.submissionReward = submissionReward;
    }

    public int getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(int submissionId) {
        this.submissionId = submissionId;
    }

    public int getRevisionId() {
        return revisionId;
    }

    public void setRevisionId(int revisionId) {
        this.revisionId = revisionId;
    }

    public Date getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(Date dateStarted) {
        this.dateStarted = dateStarted;
    }

    public Date getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(Date dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public List<SubmissionAnswer> getSubmissionAnswers() {
        return submissionAnswers;
    }

    public void setSubmissionAnswers(List<SubmissionAnswer> submissionAnswers) {
        this.submissionAnswers = submissionAnswers;
    }

    public SubmissionReward getSubmissionReward() {
        return submissionReward;
    }

    public void setSubmissionReward(SubmissionReward submissionReward) {
        this.submissionReward = submissionReward;
    }
    
    @Override
    public String toString() {
        // TODO: Implement
        return "";
    }
}