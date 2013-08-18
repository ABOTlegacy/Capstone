package com.model;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Revision {
    
    private int revisionId;
    private int surveyId;
    private boolean active;
    private Date dateCreated;
    private String revisionNumber;
    private Question question;
    private Reward reward;

    public Revision() {
        this.revisionId = 0;
        this.surveyId = 0;
        this.active = true;
        this.dateCreated = new Date();
        this.revisionNumber = "";
        this.question = null;
        this.reward = null;
    }

    public Revision(int revisionId, int surveyId, boolean active, Date dateCreated, String revisionNumber, Question question, Reward reward) {
        this();
        this.revisionId = revisionId;
        this.surveyId = surveyId;
        this.active = active;
        this.dateCreated = dateCreated;
        this.revisionNumber = revisionNumber;
        this.question = question;
        this.reward = reward;
    }

    public int getRevisionId() {
        return revisionId;
    }

    public void setRevisionId(int revisionId) {
        this.revisionId = revisionId;
    }

    public int getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(int surveyId) {
        this.surveyId = surveyId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getRevisionNumber() {
        return revisionNumber;
    }

    public void setRevisionNumber(String revisionNumber) {
        this.revisionNumber = revisionNumber;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Reward getReward() {
        return reward;
    }

    public void setReward(Reward reward) {
        this.reward = reward;
    }
    
    @Override
    public String toString() {
        // TODO: Implement
        return "";
    }
}